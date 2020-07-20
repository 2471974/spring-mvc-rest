package guru.spring.mvcrest.controllers.v1;

import guru.spring.mvcrest.api.v1.model.CategoryDTO;
import guru.spring.mvcrest.api.v1.model.CategoryListDTO;
import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.api.v1.model.CustomerListDTO;
import guru.spring.mvcrest.services.CategoryService;
import guru.spring.mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){

        return new ResponseEntity<CustomerListDTO>(
            new CustomerListDTO(
                    customerService.getAllCustomers()),
                    HttpStatus.OK
                );
    }

    @GetMapping("{custid}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String custid){
        return new ResponseEntity<CustomerDTO>(
                    customerService.getCustomerById(Long.valueOf(custid)),
                    HttpStatus.OK
                );
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("{custid}")
    public ResponseEntity<CustomerDTO> updateNewCustomer(
            @PathVariable Long custid,
            @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.updateCustomer(custid, customerDTO),
                HttpStatus.OK
        );
    }

}