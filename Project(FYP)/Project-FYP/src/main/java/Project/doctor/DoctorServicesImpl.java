package Project.doctor;

import Project.doctor.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServicesImpl implements DoctorServices {
    @Autowired
    private DoctorRepository doctorRepository;



    @Override
    public Doctor addNewDoctor(DoctorDto newDoctor) throws DoctorException {

        if(newDoctor==null)
            throw new DoctorException("New customer cannot be null");

        Optional<Doctor> doctorOpt=this.doctorRepository.findByDoctorEmail(newDoctor.getDoctorEmail());

        if(doctorOpt.isPresent())
            throw new DoctorException("Customer already exists");

        Doctor doctor=new Doctor();
        doctor.setAddress(newDoctor.getAddress());
        doctor.setDoctorEmail(newDoctor.getDoctorEmail());
        doctor.setDoctorName(newDoctor.getDoctorName());
        doctor.setPassword(newDoctor.getPassword());
        doctor.setMobileNumber(newDoctor.getMobileNumber());


        return  this.doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctor() throws DoctorException {
        if(this.doctorRepository.findAll().isEmpty()){
            throw new DoctorException("No customer exists");
        }
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor loginDoctor(Project.customer.LoginDto loginDto) throws DoctorException {
        Optional<Doctor> doctorOpt=this.doctorRepository.findByDoctorEmail(loginDto.getUserName());
        if(doctorOpt.isEmpty()){
            throw  new DoctorException("Customer does not exists for "+loginDto.getUserName());
        }
        Doctor foundDoctor=doctorOpt.get();
        if(! foundDoctor.getPassword().equals(loginDto.getPassword()))
            throw new DoctorException("Password does not match");

        return foundDoctor;
    }




    @Override
    public Doctor updateDoctor(Doctor doctor) throws DoctorException {

        if(doctor==null)
            throw new DoctorException("Customer cannot be null");
        Optional<Doctor> doctorOpt=this.doctorRepository.findByDoctorEmail(doctor.getDoctorEmail());


        if(doctorOpt.isEmpty()) {
            throw new DoctorException("Customer not exists with id "+doctor.getId());
        }
        doctorOpt.get().setPassword(doctor.getPassword());
        doctorOpt.get().setDoctorEmail(doctorOpt.get().getDoctorEmail());
        doctorOpt.get().setDoctorName(doctor.getDoctorName());
        doctorOpt.get().setMobileNumber(doctor.getMobileNumber());
        doctorOpt.get().setAddress(doctor.getAddress());
        doctorOpt.get().setId(doctorOpt.get().getId());
        Doctor doc1=doctorOpt.get();

        return this.doctorRepository.save(doc1);    }

    @Override
    public void deleteDoctor(String email) throws DoctorException {
        if(email==null)
            throw new DoctorException("Id cannot be null");
        Optional<Doctor> customerOpt=this.doctorRepository.findByDoctorEmail(email);
        if(customerOpt.isEmpty()){
            throw new DoctorException("Customer does not exists with  id: "+email);


        }

        Doctor customer=customerOpt.get();

        doctorRepository.delete(customer);
    }

    @Override
    public Doctor getDoctorById(Integer customerId) throws DoctorException {
        if(customerId==null){
            throw new DoctorException("Customer doesn't exists with given id"+customerId);
        }
        Optional<Doctor> customer=this.doctorRepository.findById(customerId);
        if(!customer.isPresent()) {
            throw new DoctorException("Customer doesn't exists");
        }
        else{

            Doctor customer1=new Doctor();
            customer1.setId(customer.get().getId());
            customer1.setDoctorName(customer.get().getDoctorName());
            customer1.setAddress(customer.get().getAddress());
            customer1.setDoctorEmail(customer.get().getDoctorEmail());
            customer1.setMobileNumber(customer.get().getMobileNumber());
            customer1.setPassword(customer.get().getPassword());
            return customer1;


        }    }

    @Override
    public Doctor getDoctorByTheEmail(String email) throws DoctorException {
        if(email==null){
            throw new DoctorException("Customer doesn't exists with given id"+email);
        }
        Optional<Doctor> doctor=this.doctorRepository.findByDoctorEmail(email);
        if(!doctor.isPresent()) {
            throw new DoctorException("Customer doesn't exists");
        }

        return  doctor.get();    }
}

