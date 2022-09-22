package service;

import api.AdminResource;
import model.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomerService {

    private static CustomerService INSTANCE = new CustomerService();

    public static CustomerService getInstance() {return INSTANCE;}

    private static final Map<String, Customer> customers = new HashMap<String, Customer>();

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {return customers.get(customerEmail);}

    public Collection<Customer> getAllCustomers(){return customers.values();}
}
