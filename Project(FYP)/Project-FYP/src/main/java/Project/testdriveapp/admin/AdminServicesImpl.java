package Project.testdriveapp.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;




@Service
public class AdminServicesImpl implements AdminServices {

    final DoctorRepository carsRepository;

    @Autowired
    public AdminServicesImpl(DoctorRepository carsRepository){
        this.carsRepository = carsRepository;
    }


    @Override
    public Doctor addNewDoctor(DoctorDto newCars) throws AdminException{

        if(newCars.getDoctorName() == null||newCars.getDoctorFees()==null)
        {
            throw new AdminException("Car details cannot be null");
        }
        Doctor findDoctor = getDoctorDetailsBydoctorName(newCars.getDoctorName());
        Doctor car = new Doctor();
        if(findDoctor==null)
        {
            car.setDoctorFees(newCars.getDoctorFees());
            car.setDoctorEmail(newCars.getDoctorEmail());
            car.setAddress(newCars.getAddress());
            car.setImageUrl(newCars.getImageUrl());
            car.setDoctorName(newCars.getDoctorName());
            car.setDoctorPhoneNo(newCars.getDoctorPhoneNo());

        }
        else {
            throw new AdminException("Car already exist");
        }
        return this.carsRepository.save(car);

    }


    private Doctor getDoctorDetailsBydoctorName(String modelName) {
        return this.carsRepository.findByDoctorName(modelName);
    }

    @Override
    public Doctor getDoctorDetailsByDoctorName(String modelName) throws AdminException {
        if(modelName == null){
            throw new AdminException("Model name cannot be null");
        }
        Doctor foundModel = this.carsRepository.findByDoctorName(modelName);
        if(foundModel == null) {
            throw new AdminException("No such model exists");
        }
        return this.carsRepository.findByDoctorName(modelName);
    }

    @Override
    public Doctor updateDoctorDetails(DoctorDto updateCar) throws AdminException {
        if(updateCar == null)
        {
            throw new AdminException("Null car details cannot be updated");
        }
        if(updateCar.getDoctorId()==null)
        {
            throw new AdminException("Car ID is mandatory to update the car");
        }
        Optional<Doctor> foundCarId = this.carsRepository.findById(updateCar.getDoctorId());
        if(foundCarId.isEmpty())
        {
            throw new AdminException("Car ID not found");
        }
        Doctor car = new Doctor();
        car.setDoctorFees(updateCar.getDoctorFees());
        car.setDoctorEmail(updateCar.getDoctorEmail());
        car.setAddress(updateCar.getAddress());
        car.setImageUrl(updateCar.getImageUrl());
        car.setDoctorName(updateCar.getDoctorName());
        car.setDoctorPhoneNo(updateCar.getDoctorPhoneNo());
        return this.carsRepository.save(car);
    }

    @Override
    public Doctor deleteDoctorById(Integer carId) throws AdminException {
        Optional<Doctor> foundCar = this.carsRepository.findById(carId);
        if(carId==null)
            throw new AdminException("Car id cannot be null");
        if(foundCar.isPresent()) {
            this.carsRepository.deleteById(carId);
        }else{
            throw new AdminException("Car not found");
        }
        return null;
    }



    @Override
    public List<Doctor> getDoctorDetailsWithinFeesRange(Double minprice, Double maxprice) {

        return this.getDoctorsDetails().stream().filter(s->s.getDoctorFees()>=minprice&&s.getDoctorFees()<=maxprice).toList();
    }

    @Override
    public List<Doctor> getDoctorsDetails() {
        return this.carsRepository.findAll();
    }
}