package sunbasedata.assingment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sunbasedata.assingment.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define custom methods here if needed
}
