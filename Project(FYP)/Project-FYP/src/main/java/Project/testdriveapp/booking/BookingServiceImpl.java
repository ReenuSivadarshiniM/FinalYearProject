package Project.testdriveapp.booking;

import Project.testdriveapp.admin.Doctor;
import Project.testdriveapp.admin.DoctorRepository;
import Project.testdriveapp.customer.Customer;
import Project.testdriveapp.customer.CustomerRepository;
import Project.testdriveapp.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingServiceImpl implements BookingService{
    String message = "Invalid Slot Number";

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HospitalRepository hospitalRepository;


    @Override
    public BookingOutputDto createNewBooking(BookingInputDto newBooking) throws BookingException {
        if (newBooking == null) {
            throw new BookingException("Booking Input can't be null");
        }
        Doctor carDetails = doctorRepository.findByDoctorName(newBooking.getDoctorName());
        Optional<Customer> customerDetails = customerRepository.findByCustomerEmail(newBooking.getCustomerEmailId());
        Customer foundCustomer = null;
        if (customerDetails.isEmpty()) {
            throw new BookingException("No such Customer Exists");
        }
        foundCustomer = customerDetails.get();

        if (carDetails==null) {
            throw new BookingException("No such car exists");
        }
        if(newBooking.getSlotNo()<1 || newBooking.getSlotNo()>8)
            throw new BookingException(message);
        if(newBooking.getBookingDate().isAfter(newBooking.getDate()))
            throw new BookingException("The Booking date has to be less than Test drive date");
        Booking foundBooking = this.bookingRepository.findByDoctorNameAndDateAndSlotNo(carDetails,newBooking.getDate(), newBooking.getSlotNo());
        if(foundBooking!=null && foundBooking.getStatus().equals(false))
            throw new BookingException("Slot already booked");
        Booking booking = this.bookingRepository.findByDoctorNameAndCustomer(carDetails,foundCustomer);
        if(booking!=null)
        {
            throw new BookingException("You already Test drove this model car");
        }
        Booking newBookingProcess = new Booking();
        newBookingProcess.setDoctorName(carDetails);
        newBookingProcess.setCustomer(foundCustomer);
        newBookingProcess.setDate(newBooking.getDate());
        newBookingProcess.setSlotNo(newBooking.getSlotNo());
        newBookingProcess.setBookingDate(newBooking.getBookingDate());
        newBookingProcess.setStatus(false);



        this.bookingRepository.save(newBookingProcess);
        return new BookingOutputDto(newBookingProcess.getBookId(), newBookingProcess.getCustomer().getCustomerEmail(), newBookingProcess.getDoctorName().getDoctorName(), newBookingProcess.getSlotNo(),newBookingProcess.getDate(),newBookingProcess.getBookingDate(),newBookingProcess.getDoctorName().getDoctorPhoneNo(),newBookingProcess.getStatus());
    }



    @Override
    public void deleteBooking(Integer bookId) throws BookingException {
        if(bookId==null)
            throw new BookingException("Id can't be null");
        Booking foundBooking = this.bookingRepository.getReferenceById(bookId);
        if(foundBooking==null) {
            throw new BookingException("No such Book Id exists");
        }
        this.bookingRepository.deleteById(bookId);
    }




    @Override
    public List<BookingOutputDto> getAllUserBookingByEmail(String mailId) throws BookingException{
        if(mailId == null)
            throw new BookingException("Mail Id can't be null");
        Optional<Customer> foundCustomer = this.customerRepository.findByCustomerEmail(mailId);
        if(foundCustomer.isEmpty())
            throw new BookingException("No such Customer exists");
        List<Booking> newBookingProcess = this.bookingRepository.findByCustomer(foundCustomer.get());
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<newBookingProcess.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(newBookingProcess.get(i).getBookId(), newBookingProcess.get(i).getCustomer().getCustomerEmail(), newBookingProcess.get(i).getDoctorName().getDoctorName(), newBookingProcess.get(i).getSlotNo(),newBookingProcess.get(i).getDate(),newBookingProcess.get(i).getBookingDate(),newBookingProcess.get(i).getDoctorName().getDoctorPhoneNo(),newBookingProcess.get(i).getStatus() ));
        }
        return bookingDtos;
    }



    @Override
    public List<BookingOutputDto> getAllUserBookingBySlotNo(Integer slotNo) throws BookingException{
        if(slotNo==null)
            throw new BookingException("Slot no can't be null");
        if(slotNo<1 || slotNo>8)
            throw new BookingException(message);
        List<Booking> newBookingProcess = this.bookingRepository.findBySlotNoOrderBySlotNo(slotNo);
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<newBookingProcess.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(newBookingProcess.get(i).getBookId(), newBookingProcess.get(i).getCustomer().getCustomerEmail(), newBookingProcess.get(i).getDoctorName().getDoctorName(), newBookingProcess.get(i).getSlotNo(),newBookingProcess.get(i).getDate(),newBookingProcess.get(i).getBookingDate(),newBookingProcess.get(i).getDoctorName().getDoctorPhoneNo(),newBookingProcess.get(i).getStatus() ));
        }
        return bookingDtos;
    }



//    @Override
//    public List<BookingOutputDto> getAllUserBookingByDate(LocalDate date) throws BookingException {
//        if(date == null)
//            throw new BookingException("Date can't be null");
//        List<Booking> bookings = this.bookingRepository.findByDate(date);
//        List<BookingOutputDto> bookingDtos = new ArrayList<>();
//        for(int i=0;i<bookings.size();i++)
//        {
//            bookingDtos.add(new BookingOutputDto(bookings.get(i).getBookId(),bookings.get(i).getCustomer().getCustomerEmail(),bookings.get(i).getDoctorName().getModelName(),bookings.get(i).getSlotNo(),bookings.get(i).getDate(),bookings.get(i).getBookingDate(),bookings.get(i).getDoctorName().getStaff().getStaffName(),bookings.get(i).getDoctorName().getStaff().getPhoneNumber(),bookings.get(i).getStatus()));
//        }
//        return bookingDtos;
//    }
//
//

    @Override
    public List<BookingOutputDto> getAllUserBookingByDoctorName(String carModelName) throws BookingException {
        if (carModelName == null)
            throw new BookingException("Car model name can't be null");
        Doctor foundCar = this.doctorRepository.findByDoctorName(carModelName);

        if(foundCar==null)
            throw new BookingException("No such Car exists");
        List<Booking> newBookingProcess = this.bookingRepository.findByDoctorName(foundCar);
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<newBookingProcess.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(newBookingProcess.get(i).getBookId(), newBookingProcess.get(i).getCustomer().getCustomerEmail(), newBookingProcess.get(i).getDoctorName().getDoctorName(), newBookingProcess.get(i).getSlotNo(),newBookingProcess.get(i).getDate(),newBookingProcess.get(i).getBookingDate(),newBookingProcess.get(i).getDoctorName().getDoctorPhoneNo(),newBookingProcess.get(i).getStatus() ));
        }
        return bookingDtos;
    }



    @Override
    public List<BookingOutputDto> getAllBookings() throws BookingException {
        List<Booking> newBookingProcess = this.bookingRepository.findAll();
        if(newBookingProcess.isEmpty())
            throw new BookingException("No Bookings Found");
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<newBookingProcess.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(newBookingProcess.get(i).getBookId(), newBookingProcess.get(i).getCustomer().getCustomerEmail(), newBookingProcess.get(i).getDoctorName().getDoctorName(), newBookingProcess.get(i).getSlotNo(),newBookingProcess.get(i).getDate(),newBookingProcess.get(i).getBookingDate(),newBookingProcess.get(i).getDoctorName().getDoctorPhoneNo(),newBookingProcess.get(i).getStatus() ));
        }
        return bookingDtos;
    }





//    @Override
//    public List<BookingOutputDto> getAllUserBookingsByStaffEmail(String staffEmail) throws BookingException{
//        if(staffEmail == null)
//            throw new BookingException("Staff mail can't be null");
//        Optional<Staff> foundStaff = this.hospitalRepository.findBystaffEmail(staffEmail);
//        if(foundStaff.isEmpty())
//            throw new BookingException("No such Staff exists");
//        Staff staff = foundStaff.get();
//        Car foundCar = this.doctorRepository.findByStaff(staff);
//        List<Booking> foundBooking = this.bookingRepository.findByTestDriveCar(foundCar);
//        List<BookingOutputDto> bookingDtos = new ArrayList<>();
//        for(int i=0;i<foundBooking.size();i++)
//        {
//            bookingDtos.add(new BookingOutputDto(foundBooking.get(i).getBookId(),foundBooking.get(i).getCustomer().getCustomerEmail(),foundBooking.get(i).getDoctorName().getModelName(),foundBooking.get(i).getSlotNo(),foundBooking.get(i).getDate(),foundBooking.get(i).getBookingDate(),foundBooking.get(i).getDoctorName().getStaff().getStaffName(),foundBooking.get(i).getDoctorName().getStaff().getPhoneNumber(),foundBooking.get(i).getStatus()));
//        }
//        return bookingDtos;
//    }




    @Override
    public BookingOutputDto getBookingById(Integer id) throws BookingException{
        if(id==null)
            throw new BookingException("Book Id can't be null");
        Booking newBookingProcess = this.bookingRepository.getReferenceById(id);
        if(newBookingProcess==null)
            throw new BookingException("No such Bookings found");
        return new BookingOutputDto(newBookingProcess.getBookId(), newBookingProcess.getCustomer().getCustomerEmail(), newBookingProcess.getDoctorName().getDoctorName(), newBookingProcess.getSlotNo(),newBookingProcess.getDate(),newBookingProcess.getBookingDate(),newBookingProcess.getDoctorName().getDoctorPhoneNo(),newBookingProcess.getStatus());
    }


}

