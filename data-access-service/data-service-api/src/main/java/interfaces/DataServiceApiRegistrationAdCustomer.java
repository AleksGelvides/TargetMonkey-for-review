package interfaces;

import entities.AdCustomerRegistrationDTO;

import java.util.List;

public interface DataServiceApiRegistrationAdCustomer {

    void saveAdCustomer(AdCustomerRegistrationDTO adCustomerRegistrationDTO);

    List<AdCustomerRegistrationDTO> getAllCustomers();

}
