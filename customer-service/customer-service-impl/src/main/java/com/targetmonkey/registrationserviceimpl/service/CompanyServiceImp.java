package com.targetmonkey.registrationserviceimpl.service;

import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.dto.companies.CompanyUserDto;
import com.targetmonkey.registrationserviceapi.dto.customers.CustomerAdminDto;
import com.targetmonkey.registrationserviceimpl.domain.CompanyJpa;
import com.targetmonkey.registrationserviceimpl.domain.CustomerJpa;
import com.targetmonkey.registrationserviceimpl.exceptions.ObjectRepeatingException;
import com.targetmonkey.registrationserviceimpl.mappers.CompanyMapper;
import com.targetmonkey.registrationserviceimpl.repository.CompanyRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.targetmonkey.registrationserviceimpl.serviceapi.CompanyServiceAPI;
import org.webjars.NotFoundException;

import java.rmi.ServerError;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CompanyServiceImp implements CompanyServiceAPI {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<CompanyAdminDto> getAllCompany() {
        List<CompanyAdminDto> companyAdminDtos = companyRepository.findAll().stream()
                .map(CompanyMapper.INSTANCE::toCompanyAdminDto).toList();
        log.info("IN getAllCompany -> Всего найдено компаний: {}", companyAdminDtos.size());
        return companyAdminDtos;
    }

    @Override
    public List<CompanyAdminDto> getAllByOwnerId(long id) {
        List<CompanyAdminDto> findedCompanies = companyRepository.findByOwnerId(id)
                .stream().map(CompanyMapper.INSTANCE::toCompanyAdminDto).toList();
        log.info("IN getAllByOwnerId -> Всего найдено компаний: {}", findedCompanies.size());
        return findedCompanies;
    }

    @Override
    public CompanyAdminDto getCompanyById(long id) {
        var company = CompanyMapper.INSTANCE.toCompanyAdminDto(
                companyRepository.findById(id).orElseThrow(()
                        -> new NotFoundException("No company with this ID found")));
        return company;
    }

    @Override
    public CompanyAdminDto getByOwnerIdAndCompanyId(long ownerId, long companyId) {
        var company = companyRepository.findByOwnerIdAndId(ownerId, companyId);
        if (company == null)
            throw new NotFoundException("This user does not have a company with this ID");

        return CompanyMapper.INSTANCE.toCompanyAdminDto(company);
    }


    @Override
    @SneakyThrows
    public CompanyAdminDto createCompany(CompanyAdminDto companyAdminDtoDto) {
        CompanyAdminDto result;
        if(companyRepository.findByCompanyName(companyAdminDtoDto.getCompanyName()) != null)
            throw new ObjectRepeatingException("This company was registered");
        var resultJpa = companyRepository.save(CompanyMapper.INSTANCE.toCompanyJpa(companyAdminDtoDto));
        result = CompanyMapper.INSTANCE.toCompanyAdminDto(resultJpa);
        return result;
    }

    @Override
    public CompanyAdminDto editCompany(long id, CompanyAdminDto companyAdminDto) {
        var companyOld = getCompanyById(id);
        log.info("IN editCompany FOR CUSTOMERS: Найдена компания с id: {}, Компания: {}", id, companyOld);

        CompanyMapper.INSTANCE.updateCompanyAdminDTO(companyAdminDto, companyOld);

        companyOld.setUpdated(new Date());

        var updatedCompany = companyRepository.save(
                CompanyMapper.INSTANCE.toCompanyJpa(companyOld).setId(id));
        log.info("IN editCompany FOR CUSTOMERS: компания сохранена с id: {}, Пользователь: {}", id, companyOld);

        return CompanyMapper.INSTANCE.toCompanyAdminDto(updatedCompany);
    }

    @Override
    public CompanyAdminDto editCompany(long id, CompanyUserDto companyUserDto) {
        var companyOld = getCompanyById(id);
        log.info("IN editCompany FOR CUSTOMERS: Найдена компания с id: {}, Компания: {}", id, companyOld);

        CompanyMapper.INSTANCE.updateCompanyUserDTO(companyUserDto, companyOld);

        companyOld.setUpdated(new Date());

        var updatedCompany = companyRepository.save(
                CompanyMapper.INSTANCE.toCompanyJpa(companyOld).setId(id));
        log.info("IN editCompany FOR CUSTOMERS: компания сохранена с id: {}, Пользователь: {}", id, companyOld);

        return CompanyMapper.INSTANCE.toCompanyAdminDto(updatedCompany);
    }

    @Override
    public void deleteCompany(long ownerId, long companyId) {
        if (companyRepository.findByOwnerIdAndId(ownerId, companyId) == null)
            throw new NotFoundException("No company with this ID found");
        companyRepository.deleteById(companyId);
    }


    @Override
    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }
}
