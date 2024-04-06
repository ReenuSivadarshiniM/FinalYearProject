package Project.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    private CustomerRepository customerRepository;



    @Override
    public Customer addNewCustomer(CustomerDto newCustomer) throws CustomerException {

        if(newCustomer==null)
            throw new CustomerException("New customer cannot be null");

        Optional<Customer> customerOpt=this.customerRepository.findByCustomerEmail(newCustomer.getCustomerEmail());

        if(customerOpt.isPresent())
            throw new CustomerException("Customer already exists");

        Customer customer=new Customer();
        customer.setAddress(newCustomer.getAddress());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setPassword(newCustomer.getPassword());
        customer.setMobileNumber(newCustomer.getMobileNumber());


        return  this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() throws CustomerException {
        if(this.customerRepository.findAll().isEmpty()){
            throw new CustomerException("No customer exists");
        }
        return this.customerRepository.findAll();
    }

    @Override
    public Customer login(LoginDto loginDto) throws CustomerException {
        Optional<Customer> customerOpt=this.customerRepository.findByCustomerEmail(loginDto.getUserName());
        if(customerOpt.isEmpty()){
            throw  new CustomerException("Customer does not exists for "+loginDto.getUserName());
        }
        Customer foundCustomer=customerOpt.get();
        if(! foundCustomer.getPassword().equals(loginDto.getPassword()))
            throw new CustomerException("Password does not match");

        return foundCustomer;    }


    @Override
    public Customer updateCustomer(Customer customer) throws CustomerException {

        if(customer==null)
            throw new CustomerException("Customer cannot be null");
        Optional<Customer> customerOpt=this.customerRepository.findByCustomerEmail(customer.getCustomerEmail());


        if(customerOpt.isEmpty()) {
            throw new CustomerException("Customer not exists with id "+customer.getCustomerId());
        }
        customerOpt.get().setPassword(customer.getPassword());
        customerOpt.get().setCustomerEmail(customerOpt.get().getCustomerEmail());
        customerOpt.get().setCustomerName(customer.getCustomerName());
        customerOpt.get().setMobileNumber(customer.getMobileNumber());
        customerOpt.get().setAddress(customer.getAddress());
        customerOpt.get().setCustomerId(customerOpt.get().getCustomerId());
        Customer customer1=customerOpt.get();

        return this.customerRepository.save(customer1);    }

    @Override
    public void deleteCustomer(String email) throws CustomerException {
        if(email==null)
            throw new CustomerException("Id cannot be null");
        Optional<Customer> customerOpt=this.customerRepository.findByCustomerEmail(email);
        if(customerOpt.isEmpty()){
            throw new CustomerException("Customer does not exists with  id: "+email);


        }

        Customer customer=customerOpt.get();

        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomerById(Integer customerId) throws CustomerException {
        if(customerId==null){
            throw new CustomerException("Customer doesn't exists with given id"+customerId);
        }
        Optional<Customer> customer=this.customerRepository.findById(customerId);
        if(!customer.isPresent()) {
            throw new CustomerException("Customer doesn't exists");
        }
        else{

            Customer customer1=new Customer();
            customer1.setCustomerId(customer.get().getCustomerId());
            customer1.setCustomerName(customer.get().getCustomerName());
            customer1.setAddress(customer.get().getAddress());
            customer1.setCustomerEmail(customer.get().getCustomerEmail());
            customer1.setMobileNumber(customer.get().getMobileNumber());
            customer1.setPassword(customer.get().getPassword());
            return customer1;


        }    }

    @Override
    public Customer getCustomerByEmail(String email) throws CustomerException {
        if(email==null){
            throw new CustomerException("Customer doesn't exists with given id"+email);
        }
        Optional<Customer> customer=this.customerRepository.findByCustomerEmail(email);
        if(!customer.isPresent()) {
            throw new CustomerException("Customer doesn't exists");
        }

        return  customer.get();    }
}

