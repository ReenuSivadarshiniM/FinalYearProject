package Project.testdriveapp.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Hospital addNewHospital(Hospital newStaff) throws HospitalException {
        if(newStaff==null){
            throw new HospitalException("Staff can't be null");
        }
//        Optional<Staff> foundAccount = this.staffRepository.findBystaffId(newStaff.getStaffId());
//        if(foundAccount.isPresent())
//            throw new StaffException("Staff Already exists:"+newStaff.getStaffName());

        Optional<Hospital> accountOpt=this.hospitalRepository.findByHospitalEmail(newStaff.getHospitalEmail());
        if(accountOpt.isPresent())
            throw new HospitalException("Email already registered,please retry. "+newStaff.getHospitalEmail());
        return this.hospitalRepository.save(newStaff);
    }

    @Override
    public Hospital updateHospitalDetails(Hospital staff) throws HospitalException {
            if(staff==null)
                throw new HospitalException("Staff cannot be null");
            Optional<Hospital> staffOpt=this.hospitalRepository.findByHospitalEmail(staff.getHospitalEmail());
            String staff2=staffOpt.get().getPhoneNumber();

            if(staff2==null) {
                throw new HospitalException("Staff not exists with id "+staff.getHospitalId());
            }
            staffOpt.get().setHospitalEmail(staffOpt.get().getHospitalEmail());
            staffOpt.get().setPhoneNumber(staff.getPhoneNumber());
            staffOpt.get().setHospitalId(staffOpt.get().getHospitalId());
            Hospital staff1=staffOpt.get();

            return this.hospitalRepository.save(staff1);
    }

    @Override
    public Optional<Hospital> getByHospitalId(Integer HospitalId) throws HospitalException {
        Optional<Hospital> foundStaff = this.hospitalRepository.findByHospitalId(HospitalId);
        if(!foundStaff.isPresent())
            throw new HospitalException("No such Id Exists: "+ HospitalId);
        return foundStaff;
    }

    @Override
    public Optional<Hospital> deleteHospital(Integer staffId) throws HospitalException {
        Optional<Hospital> foundStaff = this.hospitalRepository.findByHospitalId(staffId);
        if(foundStaff.isPresent()) {
            this.hospitalRepository.deleteById(staffId);
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHosiptal() throws HospitalException {
        List<Hospital> staffList = new ArrayList<>();
        staffList = this.hospitalRepository.findAll();
        if(staffList == null)
            throw new HospitalException("No Product Exists");
        return staffList;
    }

    @Override
    public Hospital getByHospitalEmail(String staffEmail) throws HospitalException {
        if(staffEmail==null){
            throw new HospitalException("Staff doesn't exists with given id"+staffEmail);
        }
        Optional<Hospital> staff=this.hospitalRepository.findByHospitalEmail(staffEmail);
        if(!staff.isPresent()) {
            throw new HospitalException("Staff doesn't exists");
        }
        Hospital staff1=staff.get();
        return  staff1;
    }



}

