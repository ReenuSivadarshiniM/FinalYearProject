package Project.doctor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Doctor {

    @Id
    @GeneratedValue
    private Integer id;
    private String doctorName;
    private  String address;
    private String mobileNumber;
    private String doctorEmail;
    private String password;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Doctor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Doctor(Integer id, String doctorName, String address, String mobileNumber, String doctorEmail, String password) {
        this.id = id;
        this.doctorName = doctorName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.doctorEmail = doctorEmail;
        this.password = password;
    }
}
