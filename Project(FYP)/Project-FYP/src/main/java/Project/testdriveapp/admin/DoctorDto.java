package Project.testdriveapp.admin;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class DoctorDto {
    @Id
    @GeneratedValue
    private Integer doctorId;
    private String doctorName;
    private String doctorEmail;
    private Double doctorFees;
    private String doctorPhoneNo;
    private String address;
    private String imageUrl;

    public DoctorDto(Integer doctorId, String doctorName, String doctorEmail, Double doctorFees, String doctorPhoneNo, String address, String imageUrl) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
        this.doctorFees = doctorFees;
        this.doctorPhoneNo = doctorPhoneNo;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public DoctorDto() {
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public Double getDoctorFees() {
        return doctorFees;
    }

    public void setDoctorFees(Double doctorFees) {
        this.doctorFees = doctorFees;
    }

    public String getDoctorPhoneNo() {
        return doctorPhoneNo;
    }

    public void setDoctorPhoneNo(String doctorPhoneNo) {
        this.doctorPhoneNo = doctorPhoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}