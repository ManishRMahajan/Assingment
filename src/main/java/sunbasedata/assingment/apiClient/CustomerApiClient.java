package sunbasedata.assingment.apiClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import sunbasedata.assingment.model.Customer;

@Component
public class CustomerApiClient {

    @Value("${api.base_url}")
    private String BASE_URL;

    private final RestTemplate restTemplate;

    public CustomerApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticate(String loginId, String password) {
        String url = BASE_URL + "/assignment_auth.jsp";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"login_id\": \"" + loginId + "\", \"password\": \"" + password + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to authenticate");
        }
    }

    public ResponseEntity<String> createCustomer(Customer customer, String authToken) {
        String url = BASE_URL + "/assignment.jsp?cmd=create";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);

        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response;
    }

    public ResponseEntity<String> getCustomerList(String authToken) {
        String url = BASE_URL + "/assignment.jsp?cmd=get_customer_list";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return response;
    }

    public ResponseEntity<String> deleteCustomer(String uuid, String authToken) {
        String url = BASE_URL + "/assignment.jsp?cmd=delete&uuid=" + uuid;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response;
    }

    public ResponseEntity<String> updateCustomer(String uuid, Customer customer, String authToken) {
        String url = BASE_URL + "/assignment.jsp?cmd=update&uuid=" + uuid;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);

        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response;
    }
}
