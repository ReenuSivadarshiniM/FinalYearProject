package Project.testdriveapp.hospital;


import Project.testdriveapp.admin.Doctor;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hospitalId;
    private String hospitalName;

    private String phoneNumber;
     private String hospitalEmail;

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + hospitalId +
                ", staffName='" + hospitalName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", staffEmail='" + hospitalEmail + '\'' +
                ", modelName='" + DoctorName + '\'' +
                '}';
    }



    public void setDoctorName(String modelName) {
        this.DoctorName = modelName;
    }


    public String getDoctorName() {
        return DoctorName;

    }

    public Hospital(String modelName) {
        this.DoctorName = modelName;
    }

    @OneToOne
    private Doctor car;
    private String DoctorName;


    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer staffId) {
        this.hospitalId = staffId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String staffName) {
        this.hospitalName = staffName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHospitalEmail() {
        return hospitalEmail;
    }

    public void setHospitalEmail(String staffEmail) {
        this.hospitalEmail = staffEmail;
    }

    public Hospital() {
    }

    public Hospital(String staffName, String phoneNumber, String staffEmail) {
        this.hospitalName = staffName;

        this.phoneNumber = phoneNumber;
        this.hospitalEmail = staffEmail;
    }

    @RestControllerAdvice
    public static class StaffControllerAdvice {
        @ExceptionHandler(value = {HospitalException.class})
        public ResponseEntity<String> staffExceptionHandler(HospitalException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }
    }
}
