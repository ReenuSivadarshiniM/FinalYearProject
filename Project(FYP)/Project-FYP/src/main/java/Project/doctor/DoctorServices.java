package Project.doctor;


import Project.customer.Customer;
import Project.customer.CustomerDto;
import Project.customer.CustomerException;
import Project.customer.LoginDto;

import java.util.List;

public interface DoctorServices {


//    List<Doctor> getAllDoctor() throws DoctorException;
    Doctor addNewDoctor(DoctorDto newDoctor) throws DoctorException;
    Doctor updateDoctor(Doctor doctor) throws DoctorException;

    List<Doctor> getAllDoctor() throws DoctorException;

    Doctor loginDoctor(LoginDto loginDto) throws DoctorException;


    void deleteDoctor(String email) throws DoctorException;

    Doctor getDoctorById(Integer customerId) throws DoctorException;


    Doctor getDoctorByTheEmail(String email) throws DoctorException;
}
