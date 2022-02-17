package com.targetmonkey.securitycommon.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret; // На основании этого поля происходит шифрация и дешифрация токена
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds; // Время жизни этого токена

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        log.info("IN init - String encoded to Base64. Result: {}", secret);
    }


    public String createToken(UserDetails user){
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        // Этот класс позволяет нам сохранить доп. информацию о текующем пользователе.
        //В методы выше было установлено значение самого JWT.
        Collection<String> roles = user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put("roles", roles); //Сохранили список ролей для пользователя.
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds); //Дата окончания срока действия токена.
        return Jwts.builder() //Jwts - фабричный класс для создания экземпляров интерфейсов JWT.
                // {}.builder - возвращает новый JwtBuilder
                .setClaims(claims) //Устанавливает полезные данные JWT как экземпляр утверждений JSON.
                .setIssuedAt(now) //Устанавливает значение времени "Выданно в *"
                .setExpiration(validity) //Устанавливает время действия
                .signWith(SignatureAlgorithm.HS256, secret) //Подписывает указанный JWT используя
                //Указанный алгоритм шифрования и зашифрованный secret key
                .compact(); // На самом деле создает JWT и сериализует его в компактную, безопасную для URL строку в соответствии с правилами компактной сериализации JWT.
    }

    //Метод проверяет насколько правильно был подан токен.
    public String resolveToken(HttpServletRequest request){
        var bearerToken = request.getHeader("Authorization"); //Хедер запроса
        if(bearerToken != null && bearerToken.startsWith("Bearer_")){ // Проверяем, что бы запрос был верен
            return bearerToken.substring(7); //Возвращает сам токен
        }
        return null;
    }

    public String getUserName(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        // Парсим имя пользователя из Claims(Параметров токена). Установили в createToken()
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            // JWS - это механизм передачи полезной нагрузки JWT между 2мя сторонами.
            if(claimsJws.getBody().getExpiration().before(new Date())){ //Проверяем, что бы токен не был просрочен
                throw new JwtAuthenticationException("JWT token is expired or invalid");
            } else
                return true;
        }catch (JwtException | IllegalArgumentException | JwtAuthenticationException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
