package sunbasedata.assingment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sunbasedata.assingment.apiClient.CustomerApiClient;
import sunbasedata.assingment.model.Customer;

@Controller
public class CustomerController {

    private final CustomerApiClient customerApiClient;

    public CustomerController(CustomerApiClient customerApiClient) {
        this.customerApiClient = customerApiClient;
    }

    @GetMapping("/customerList")
    public String showCustomerListPage() {
        // Implement logic to fetch customer list from API and pass it to the view
        // For example:
        String authToken = "your_authentication_token_here";
        ResponseEntity<String> response = customerApiClient.getCustomerList(authToken);
        String customerList = response.getBody();
        // You can parse the response and pass it to the view model

        return "customerList";
    }

    @GetMapping("/addCustomer")
    public String showAddCustomerPage() {
        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestParam("first_name") String firstName,
                              @RequestParam("last_name") String lastName,
                              @RequestParam("street") String street,
                              @RequestParam("address") String address,
                              @RequestParam("city") String city,
                              @RequestParam("state") String state,
                              @RequestParam("email") String email,
                              @RequestParam("phone") String phone) {
        // Create a Customer object with the form data
        Customer customer = new Customer();
        customer.setFirst_name(firstName);
        customer.setFirst_name(lastName);
        customer.setStreet(street);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setState(state);
        customer.setEmail(email);
        customer.setPhone(phone);

        // Send the customer data to the API for creation
        String authToken = "your_authentication_token_here";
        ResponseEntity<String> response = customerApiClient.createCustomer(customer, authToken);

        // You can handle the API response and redirect accordingly
        if (response.getStatusCode() == HttpStatus.CREATED) {
            // Customer created successfully, redirect to customer list page
            return "redirect:/customerList";
        } else {
            // Handle the error scenario, e.g., show an error message on the addCustomer page
            return "addCustomer";
        }
    }

    // Implement methods to handle update and delete customer form submissions
}
