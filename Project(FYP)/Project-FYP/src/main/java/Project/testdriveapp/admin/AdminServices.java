package Project.testdriveapp.admin;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {
    Doctor addNewDoctor(DoctorDto newCars) throws AdminException;

    Doctor getDoctorDetailsByDoctorName(String modelName) throws AdminException;

    Doctor updateDoctorDetails(DoctorDto updateCar) throws AdminException;

    Doctor deleteDoctorById(Integer carId) throws AdminException;


    List<Doctor> getDoctorDetailsWithinFeesRange(Double minprice, Double maxprice);

    List<Doctor> getDoctorsDetails();
}