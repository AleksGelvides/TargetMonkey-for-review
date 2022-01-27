package interfaces;

import entitiesDto.AdCustomerRegistrationDTO;

import java.util.List;

public interface DataServiceApiRegistrationAdCustomer {

    void saveAdCustomer(AdCustomerRegistrationDTO adCustomerRegistrationDTO);

    List<AdCustomerRegistrationDTO> getAllCustomers();

}
