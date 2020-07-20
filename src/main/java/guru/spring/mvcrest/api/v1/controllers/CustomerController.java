package guru.spring.mvcrest.api.v1.controllers;

import guru.spring.mvcrest.api.v1.model.CustomerDTO;
import guru.spring.mvcrest.api.v1.model.CustomerListDTO;
import guru.spring.mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api/v1/customers")
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

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
    public ResponseEntity<CustomerDTO> postUpdateCustomer(
            @PathVariable Long custid,
            @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.postCustomer(custid, customerDTO),
                HttpStatus.OK
        );
    }

    @PatchMapping("{custid}")
    public ResponseEntity<CustomerDTO> patchUpdateCustomer(
            @PathVariable Long custid,
            @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.patchCustomer(custid, customerDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{custid}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long custid){
        try{
            customerService.deleteCustomer(custid);
            return new ResponseEntity<String>(
                    "Customer with ID ['" + custid + "'] deleted successfully",
                    HttpStatus.OK
            );

        }catch(Exception e){
            return new ResponseEntity<String>(
                    e.getLocalizedMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
