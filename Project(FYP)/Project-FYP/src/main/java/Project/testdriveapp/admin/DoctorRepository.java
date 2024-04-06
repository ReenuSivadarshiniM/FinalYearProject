package Project.testdriveapp.admin;

import Project.testdriveapp.hospital.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findByHospital(Hospital staff);
    Doctor findByDoctorName(String name);
}