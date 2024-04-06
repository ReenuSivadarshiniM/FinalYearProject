package Project.testdriveapp.hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalService {


    Hospital addNewHospital(Hospital newStaff) throws HospitalException;

    Hospital updateHospitalDetails(Hospital staff) throws HospitalException;



    Optional<Hospital> getByHospitalId(Integer HospitalId) throws HospitalException;

    Optional<Hospital> deleteHospital(Integer staffId) throws HospitalException;



    List<Hospital> getAllHosiptal() throws HospitalException;

    Hospital getByHospitalEmail(String staffEmail) throws HospitalException;

}
