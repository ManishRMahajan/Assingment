package sunbasedata.assingment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import sunbasedata.assingment.Repository.CustomerRepository;
import sunbasedata.assingment.model.Customer;

import java.util.List;

@Service
public class CustomerService {
	
	
	@Configuration
	public class AppConfig {
	    @Bean
	    public CustomerService customerService() {
	        return new CustomerService();
	    }
	}
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCustomerByUuid(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
