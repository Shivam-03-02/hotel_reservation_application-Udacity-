package Service;
import Model.Customer;

import java.util.*;

public class CustomerService {

    private final Map<String, Customer> customerMap = new HashMap<>();

    private static final CustomerService customerService = new CustomerService();

    public void addCustomer(String email,String firstName, String lastName) {
        Model.Customer customer = new Customer(firstName,lastName,email);
        customerMap.put(email,customer);
    }

    public Customer getCustomer (String customerEmail) {
            return customerMap.get(customerEmail);

        }

    public Collection<Customer> getAllCustomer() {
        return customerMap.values();
    }

    public static CustomerService getCustomerService() {
        return customerService;
    }
}
