package Project.testdriveapp.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("http://localhost:4200/")
public class HospitalController {
    @Autowired
    private HospitalService staffService;

    @PostMapping("staff")
    public Hospital addNewStaff(@RequestBody Hospital newStaff) throws HospitalException
    {
        return this.staffService.addNewHospital(newStaff);
    }

    @PutMapping("Staff/update")
    public Hospital updateAccount(@RequestBody Hospital staff) throws HospitalException {
        return this.staffService.updateHospitalDetails(staff);
    }

    @GetMapping("GetStaffbyId/{staffid}")
    public Optional<Hospital> getByStaffId(@PathVariable("staffid") Integer staffid) throws HospitalException {
        return this.staffService.getByHospitalId(staffid);
    }
    @DeleteMapping("staff/delete/{staffid}")
    public Optional<Hospital> deleteAccountById(@PathVariable Integer staffid) throws HospitalException {

        return this.staffService.deleteHospital(staffid);
    }
    @GetMapping("getAllStaffs")
    public List<Hospital> getAllAccounts() throws HospitalException {
        return this.staffService.getAllHosiptal();
    }
    @GetMapping("getStaffbyEmail/{staffEmail}")
    public Optional<Hospital> getByStaffEmail(@PathVariable("staffEmail") String staffEmail) throws HospitalException {
        return Optional.ofNullable(this.staffService.getByHospitalEmail(staffEmail));
    }

}
