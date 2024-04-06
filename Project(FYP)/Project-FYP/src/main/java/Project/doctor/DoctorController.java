package Project.doctor;

import Project.customer.Customer;
import Project.customer.CustomerDto;
import Project.customer.CustomerException;
import Project.customer.CustomerServices;
import Project.customer.LoginDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class DoctorController {
    @Autowired
    public DoctorController(DoctorServices doctorServices){
        this.doctorServices=doctorServices;
    }
    private DoctorServices doctorServices;

    @PostMapping("doctor")
    public Doctor addNewDoctor(@Valid @RequestBody DoctorDto doctor) throws DoctorException {
        return this.doctorServices.addNewDoctor(doctor);

    }

    @GetMapping("doctor/AllDoctors")
    public List<Doctor> getAllDoctor() throws  DoctorException {
        return this.doctorServices.getAllDoctor();

    }

    @PutMapping("doctor/updateDoctor")
    public Doctor updateDoctor(@Valid @RequestBody Doctor doctor) throws DoctorException{
        return this.doctorServices.updateDoctor(doctor);

    }

    @DeleteMapping("doctor/delete/{email}")
    public void deleteDoctorById(@Valid @PathVariable String email) throws DoctorException
    {
        this.doctorServices.deleteDoctor(email);

    }

    @PostMapping("doctor/login")
    public Doctor loginDoctor(@Valid @RequestBody LoginDto loginDto) throws DoctorException {
        return this.doctorServices.loginDoctor(loginDto);
    }

    @GetMapping("doctor/withaddress/{id}")
    public Doctor getDoctorById(@Valid @PathVariable Integer id) throws DoctorException{
        return this.doctorServices.getDoctorById(id);
    }

    @GetMapping("doctor/{email}")
    public  Doctor getDoctorByEmail(@Valid @PathVariable String email)throws DoctorException{
        return this.doctorServices.getDoctorByTheEmail(email);
    }

}
