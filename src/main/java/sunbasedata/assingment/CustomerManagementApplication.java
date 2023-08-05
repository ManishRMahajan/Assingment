package sunbasedata.assingment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import sunbasedata.assingment.apiClient.CustomerApiClient;

@SpringBootApplication
@ComponentScan(basePackages = {"sunbasedata.assingment.controller"})
public class CustomerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManagementApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public CustomerApiClient customerApiClient(RestTemplate restTemplate) {
        return new CustomerApiClient(restTemplate);
    }
}
