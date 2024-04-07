package Project.testdriveapp.booking;

import Project.testdriveapp.admin.Doctor;
import Project.testdriveapp.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomer(Customer customer);

    List<Booking> findByDoctorName(Doctor doctor);

    Booking findByDoctorNameAndCustomer(Doctor carDetails, Customer foundCustomer);

    Booking findByDoctorNameAndDateAndSlotNo(Doctor carDetails, LocalDate date, Integer slotNo);

    List<Booking> findBySlotNoOrderBySlotNo(Integer slotNo);

//    List<Booking> findBy
}
