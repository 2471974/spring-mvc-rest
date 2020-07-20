package guru.spring.mvcrest.services;

import guru.spring.mvcrest.api.v1.model.CategoryDTO;
import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long Id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long Id, CustomerDTO customerDTO);

    //CustomerDTO saveCustomer(Customer customer);



}
