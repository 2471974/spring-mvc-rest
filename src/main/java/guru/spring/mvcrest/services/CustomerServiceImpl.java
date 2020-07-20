package guru.spring.mvcrest.services;

import guru.spring.mvcrest.api.v1.mapper.CustomerMapper;
import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.domain.Customer;
import guru.spring.mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomer_det("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                    })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long Id) {
        return customerRepository.findById(Id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
        //return customerMapper.customerToCustomerDTO(customerRepository.findCustomerById(Id));
    }

    /*
    @Override
    public  CustomerDTO createNewCustomer(CustomerDTO customerDTO){

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnCustomerDto = customerMapper.customerToCustomerDTO(savedCustomer);
        returnCustomerDto.setCustomer_det("/api/v1/customers/" + savedCustomer.getId());
        return returnCustomerDto;

    }    */


    @Override
    public  CustomerDTO createNewCustomer(CustomerDTO customerDTO){
        return saveCustomer(customerMapper.customerDtoToCustomer(customerDTO));
    }

    @Override
    public  CustomerDTO updateCustomer(Long Id, CustomerDTO customerDTO){
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(Id);
        return saveCustomer(customer);
    }

    public  CustomerDTO saveCustomer(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnCustomerDto = customerMapper.customerToCustomerDTO(savedCustomer);
        returnCustomerDto.setCustomer_det("/api/v1/customers/" + savedCustomer.getId());
        return returnCustomerDto;

    }

}
