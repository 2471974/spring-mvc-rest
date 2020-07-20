package guru.spring.mvcrest.repositories;

import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 9/24/17.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerById(Long Id);
    //Customer customerDtoToCustomer(CustomerDTO customerDTO);

}
