package Project.testdriveapp.hospital;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class HospitalLoginDto {
    @NotBlank(message = "Email can't be null")
    @Email(message = "Please provide valid email. e.g name@ford.com",regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String HospitalEmail;
    @NotBlank(message = "Phone number can't be null")
    @Pattern(regexp = "\\d{10}",message = "Tel no should contain only 10 digits")
    private String phoneNumber;

    public HospitalLoginDto(String staffEmail, String phoneNumber) {
        this.HospitalEmail = staffEmail;
        this.phoneNumber = phoneNumber;
    }

    public HospitalLoginDto() {
    }

    public String getHospitalEmail() {
        return HospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        this.HospitalEmail = hospitalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "StaffLoginDto{" +
                "staffEmail='" + HospitalEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
