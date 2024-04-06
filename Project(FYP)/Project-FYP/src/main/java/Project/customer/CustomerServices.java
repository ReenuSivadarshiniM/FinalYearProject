package Project.customer;


import Project.doctor.Doctor;
import Project.doctor.DoctorException;

import java.util.List;

public interface CustomerServices {


    List<Customer> getAllCustomers() throws CustomerException;
    Customer login(LoginDto loginDto) throws CustomerException;
    Customer addNewCustomer(CustomerDto newCustomer) throws CustomerException;
    Customer updateCustomer(Customer customer) throws CustomerException;
    void deleteCustomer(String email) throws CustomerException;
    Customer getCustomerById(Integer customerId)throws CustomerException;
    Customer getCustomerByEmail(String email) throws CustomerException;



}
