package Project.testdriveapp.rating;

import Project.testdriveapp.admin.Doctor;
import Project.testdriveapp.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private Integer ratingId;
    @NotBlank(message = "Rating Stars can't be null")
    @Min(value = 0,message = "Minimum will be 0")
    @Max(value = 5,message = "Maximum will be 5")
    private Integer ratingStars;
    @NotBlank(message = "Comments can't be null")
    private String comments;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Doctor doctor;

    public Rating(Integer ratingId, Integer ratingStars, String comments, Customer customer, Doctor doctor) {
        this.ratingId = ratingId;
        this.ratingStars = ratingStars;
        this.comments = comments;
        this.customer = customer;
        this.doctor = doctor;
    }

    public Rating() {

    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(Integer ratingStars) {
        this.ratingStars = ratingStars;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Doctor getCar() {
        return doctor;
    }

    public void setCar(Doctor car) {
        this.doctor = car;
    }
}
