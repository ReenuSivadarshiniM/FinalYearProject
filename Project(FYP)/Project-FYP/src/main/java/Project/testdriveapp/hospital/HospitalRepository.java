package Project.testdriveapp.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface
HospitalRepository extends JpaRepository<Hospital,Integer> {

    Optional<Hospital> findByHospitalId(Integer staffId);

    Optional<Hospital> findByHospitalEmail(String staffEmail);

    void deleteByHospitalId(Integer staffId);

}
