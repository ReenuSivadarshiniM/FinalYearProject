package Project.customer;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {


    @Id
    @GeneratedValue
    private Integer customerId;
    private String customerName;
    private  String address;
    private String mobileNumber;
    private String customerEmail;
    private String password;
    private String illness;

    public Customer() {
    }

    public Customer(Integer customerId, String customerName, String address, String mobileNumber, String customerEmail, String password, String illness) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.customerEmail = customerEmail;
        this.password = password;
        this.illness = illness;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", password='" + password + '\'' +
                ", illness='" + illness + '\'' +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }
}






