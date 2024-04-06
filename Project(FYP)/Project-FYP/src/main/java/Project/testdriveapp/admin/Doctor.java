package Project.testdriveapp.admin;

import Project.testdriveapp.rating.Rating;
import Project.testdriveapp.hospital.Hospital;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue
    private Integer doctorId;
    private String doctorName;
    private String doctorEmail;
    private Double doctorFees;
    private String doctorPhoneNo;
    private String address;
    private String imageUrl;


    @OneToOne
    private Hospital hospital;

    @OneToMany
    private List<Rating> ratings = new ArrayList<>();

    public Integer getDoctorId() {
        return doctorId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Doctor() {
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Doctor(Integer doctorId, String doctorName, String doctorEmail, Double doctorFees, String doctorPhoneNo, String address, Hospital hospital, List<Rating> ratings,String imageUrl) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
        this.doctorFees = doctorFees;
        this.doctorPhoneNo = doctorPhoneNo;
        this.address = address;
        this.hospital = hospital;
        this.ratings = ratings;
        this.imageUrl=imageUrl;
    }
}