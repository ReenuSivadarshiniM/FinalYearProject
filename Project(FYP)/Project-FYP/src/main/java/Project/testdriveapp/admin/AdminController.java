package Project.testdriveapp.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class AdminController {

    final AdminServices adminServices;

    @Autowired
    public AdminController(AdminServices adminServices){
        this.adminServices = adminServices;
    }

    @PostMapping("admin/doctor")
    public Doctor addNewDoctor(@RequestBody DoctorDto newCar) throws AdminException {
        return adminServices.addNewDoctor(newCar);
    }

    @GetMapping("admin/doctor/all")
    public List<Doctor> getDoctorDetails()
    {
        return this.adminServices.getDoctorsDetails();
    }

    @GetMapping("admin/doctor/search/{modelName}")
    public Doctor getCarDetailsByDoctorName(@PathVariable("modelName") String  modelName) throws AdminException
    {
        return adminServices.getDoctorDetailsByDoctorName(modelName);
    }

    @PutMapping("admin/doctor/update")
    public Doctor updateDoctorDetails(@RequestBody DoctorDto updateCar) throws AdminException
    {
        return adminServices.updateDoctorDetails(updateCar);
    }

    @GetMapping("admin/doctor/filter/price/{minprice}/{maxprice}")
    public List<Doctor> getDoctorDetailsWithinFeesRange(@PathVariable("minprice") Double minprice, @PathVariable("maxprice") Double maxprice)
    {
        return adminServices.getDoctorDetailsWithinFeesRange(minprice,maxprice);
    }

    @DeleteMapping("admin/doctor/delete/{id}")
    public Doctor deleteDoctorById(@PathVariable("id") Integer carId) throws AdminException {
        return adminServices.deleteDoctorById(carId);
    }
}