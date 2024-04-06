package Project.testdriveapp.booking;

import Project.testdriveapp.admin.Doctor;
import Project.testdriveapp.admin.DoctorRepository;
import Project.testdriveapp.customer.Customer;
import Project.testdriveapp.customer.CustomerRepository;
import Project.testdriveapp.hospital.Hospital;
import Project.testdriveapp.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/************************************************************************************
 *          @author          Indra
 *          Description      It is a service class that provides the services for Booking of the application.
 It has CreateBooking, UpdateBooking, DeleteBooking, ViewAllBookings and Bookings based on mailId, date and slot.
 *         Version             1.0
 *         Created Date    22-FEB-2024
 ************************************************************************************/


@Service
public class BookingServiceImpl implements BookingService{
    String message = "Invalid Slot Number";

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DoctorRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HospitalRepository staffRepository;

    /************************************************************************************
     * Method: 			         -createNewBooking
     *Description: 			     -To create new Booking
     * @param newBooking         -DTO to get the booking input parameters

     * @return BookingOutputDto  - Dto to show the booking details, if created otherwise throws BookingException
     * @throws BookingException  - It is raised due to if Booking already exists or null or slot already booked
    server side validation
     *Created By                                - Indra
     *Created Date                            - 22-FEB-2024

     ************************************************************************************/

    @Override
    public BookingOutputDto createNewBooking(BookingInputDto newBooking) throws BookingException {
        if (newBooking == null) {
            throw new BookingException("Booking Input can't be null");
        }
        Doctor carDetails = carRepository.findByDoctorName(newBooking.getDoctorName());
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
        return new BookingOutputDto(newBookingProcess.getBookId(), newBookingProcess.getCustomer().getCustomerEmail(), newBookingProcess.getDoctorName().getDoctorName(), newBookingProcess.getSlotNo(),newBookingProcess.getDate(),newBookingProcess.getBookingDate(),newBookingProcess.getDoctorName().getHospital().getHospitalName(),newBookingProcess.getDoctorName().getHospital().getPhoneNumber(),newBookingProcess.getStatus());
    }

    /************************************************************************************
     * Method: 			        -updateBooking
     *Description: 			    -To update existing Booking
     * @param updateBooking     -DTO to get the booking input parameters

     * @return BookingOutputDto - Dto to show the booking details, if updated otherwise throws BookingException
     * @throws BookingException - It is raised due to if Booking already exists or null or slot already booked
    server side validation
     *Created By                                - Indra
     *Created Date                            - 07-MAR-2024

     ************************************************************************************/

    @Override
    public BookingOutputDto updateBooking(BookingInputDto updateBooking) throws BookingException {
        if (updateBooking == null) {
            throw new BookingException("Booking Input can't be null");
        }
        Doctor carDetails = carRepository.findByDoctorName(updateBooking.getDoctorName());
        Optional<Customer> customerDetails = customerRepository.findByCustomerEmail(updateBooking.getCustomerEmailId());

        if (customerDetails.isEmpty()) {
            throw new BookingException("No such Customer Exists");
        }
        Customer foundCustomer  = customerDetails.get();
        if (carDetails==null) {
            throw new BookingException("No such car exists");
        }
        if(updateBooking.getSlotNo()<1 || updateBooking.getSlotNo()>8)
            throw new BookingException(message);
        if(updateBooking.getBookingDate().isAfter(updateBooking.getDate()))
            throw new BookingException("The Booking date has to be less than Test drive date");
        Booking foundBooking = this.bookingRepository.findByDoctorNameAndDateAndSlotNo(carDetails,updateBooking.getDate(), updateBooking.getSlotNo());
        if(foundBooking!=null && foundBooking.getStatus().equals(false))
            throw new BookingException("Slot already booked");
        Booking booking = this.bookingRepository.findByDoctorNameAndCustomer(carDetails,foundCustomer);
        if(booking!=null && booking.getStatus().equals(true))
            throw new BookingException("You already test drove this model car");
        assert booking != null;
        booking.setStatus(true);
        Booking newBookingProcess = new Booking();
        newBookingProcess.setBookId(booking.getBookId());
        newBookingProcess.setDoctorName(carDetails);
        newBookingProcess.setCustomer(foundCustomer);
        newBookingProcess.setDate(updateBooking.getDate());
        newBookingProcess.setSlotNo(updateBooking.getSlotNo());
        newBookingProcess.setBookingDate(updateBooking.getBookingDate());
        newBookingProcess.setStatus(false);

        this.bookingRepository.save(newBookingProcess);
        return new BookingOutputDto(newBookingProcess.getBookId(), newBookingProcess.getCustomer().getCustomerEmail(), newBookingProcess.getDoctorName().getDoctorName(), newBookingProcess.getSlotNo(),newBookingProcess.getDate(),newBookingProcess.getBookingDate(),newBookingProcess.getDoctorName().getHospital().getHospitalName(),newBookingProcess.getDoctorName().getHospital().getPhoneNumber(),newBookingProcess.getStatus());
    }

    /************************************************************************************
     * Method: 			        -deleteBooking
     *Description: 			    -To delete existing Booking
     * @param bookId            -Integer -> the generated book id


     * @throws BookingException - It is raised due to if book id is null or no such id exists
    server side validation
     *Created By                                - Indra
     *Created Date                            - 22-FEB-2024

     ************************************************************************************/


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

    /************************************************************************************
     * Method: 			               -getAllUserBookingByEmail
     *Description: 			           -To return all the Bookings of a particular email
     * @param mailId                   -String -> mail id of the user

     * @return List<BookingOutputDto>  - A list of bookings, otherwise throws BookingException
     * @throws BookingException        - It is raised due to if mail id is null or no such mail id exists
    server side validation
     *Created By                                - Indra
     *Created Date                            - 23-FEB-2024

     ************************************************************************************/



    @Override
    public List<BookingOutputDto> getAllUserBookingByEmail(String mailId) throws BookingException{
        if(mailId == null)
            throw new BookingException("Mail Id can't be null");
        Optional<Customer> foundCustomer = this.customerRepository.findByCustomerEmail(mailId);
        if(foundCustomer.isEmpty())
            throw new BookingException("No such Customer exists");
        List<Booking> bookings = this.bookingRepository.findByCustomer(foundCustomer.get());
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<bookings.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(bookings.get(i).getBookId(),bookings.get(i).getCustomer().getCustomerEmail(),bookings.get(i).getDoctorName().getDoctorName(),bookings.get(i).getSlotNo(),bookings.get(i).getDate(),bookings.get(i).getBookingDate(),bookings.get(i).getDoctorName().getHospital().getHospitalName(),bookings.get(i).getDoctorName().getHospital().getPhoneNumber(),bookings.get(i).getStatus()));
        }
        return bookingDtos;
    }

/************************************************************************************
 * Method: 			               -getAllUserBookingBySlotNo
 *Description: 			           -To return all the Bookings based on slot no
 * @param slotNo                   -Integer -> Slot no

 * @return List<BookingOutputDto>  - A list of bookings, otherwise throws BookingException
 * @throws BookingException        - It is raised due to if mail id is null or no such mail id exists
server side validation
 *Created By                                - Indra
 *Created Date                            - 27-FEB-2024

 ************************************************************************************/

    @Override
    public List<BookingOutputDto> getAllUserBookingBySlotNo(Integer slotNo) throws BookingException{
        if(slotNo==null)
            throw new BookingException("Slot no can't be null");
        if(slotNo<1 || slotNo>8)
            throw new BookingException(message);
        List<Booking> bookings = this.bookingRepository.findBySlotNoOrderBySlotNo(slotNo);
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<bookings.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(bookings.get(i).getBookId(),bookings.get(i).getCustomer().getCustomerEmail(),bookings.get(i).getDoctorName().getDoctorName(),bookings.get(i).getSlotNo(),bookings.get(i).getDate(),bookings.get(i).getBookingDate(),bookings.get(i).getDoctorName().getHospital().getHospitalName(),bookings.get(i).getDoctorName().getHospital().getPhoneNumber(),bookings.get(i).getStatus()));
        }
        return bookingDtos;
    }

    /************************************************************************************
     * Method: 			               -getAllUserBookingByDate
     *Description: 			           -To return all the Bookings based on date
     * @param date                     -Date -> Booking date

     * @return List<BookingOutputDto>  - A list of bookings, otherwise throws BookingException
     * @throws BookingException        - It is raised due to if date is null
    server side validation
     *Created By                                - Indra
     *Created Date                            - 26-FEB-2024

     ************************************************************************************/

    @Override
    public List<BookingOutputDto> getAllUserBookingByDate(LocalDate date) throws BookingException {
        if(date == null)
            throw new BookingException("Date can't be null");
        List<Booking> bookings = this.bookingRepository.findByDate(date);
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<bookings.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(bookings.get(i).getBookId(),bookings.get(i).getCustomer().getCustomerEmail(),bookings.get(i).getDoctorName().getDoctorName(),bookings.get(i).getSlotNo(),bookings.get(i).getDate(),bookings.get(i).getBookingDate(),bookings.get(i).getDoctorName().getHospital().getHospitalName(),bookings.get(i).getDoctorName().getHospital().getPhoneNumber(),bookings.get(i).getStatus()));
        }
        return bookingDtos;
    }

    /************************************************************************************
     * Method: 			               -getAllUserBookingByCarModelName
     *Description: 			           -To return all the Bookings based on Car Model name
     * @param carModelName             -String -> Car Model Name

     * @return List<BookingOutputDto>  - A list of bookings, otherwise throws BookingException
     * @throws BookingException        - It is raised due to if model name is null or no such model exists
    server side validation
     *Created By                                - Indra
     *Created Date                            - 27-FEB-2024

     ************************************************************************************/

    @Override
    public List<BookingOutputDto> getAllUserBookingByDoctorName(String carModelName) throws BookingException {
        if (carModelName == null)
            throw new BookingException("Car model name can't be null");
        Doctor foundCar = this.carRepository.findByDoctorName(carModelName);

        if(foundCar==null)
            throw new BookingException("No such Car exists");
        List<Booking> bookings = this.bookingRepository.findByDoctorName(foundCar);
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<bookings.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(bookings.get(i).getBookId(),bookings.get(i).getCustomer().getCustomerEmail(),bookings.get(i).getDoctorName().getDoctorName(),bookings.get(i).getSlotNo(),bookings.get(i).getDate(),bookings.get(i).getBookingDate(),bookings.get(i).getDoctorName().getHospital().getHospitalName(),bookings.get(i).getDoctorName().getHospital().getPhoneNumber(),bookings.get(i).getStatus()));
        }
        return bookingDtos;
    }

    /************************************************************************************
     * Method: 			               -getAllBookings
     *Description: 			           -To return all the Bookings based on Car Model name

     * @return List<BookingOutputDto>  - A list of bookings
     * @throws BookingException        - It is raised due to if no bookings exists

    server side validation
     *Created By                                - Indra
     *Created Date                            - 27-FEB-2024

     ************************************************************************************/


    @Override
    public List<BookingOutputDto> getAllBookings() throws BookingException {
        List<Booking> bookings = this.bookingRepository.findAll();
        if(bookings.isEmpty())
            throw new BookingException("No Bookings Found");
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<bookings.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(bookings.get(i).getBookId(),bookings.get(i).getCustomer().getCustomerEmail(),bookings.get(i).getDoctorName().getDoctorName(),bookings.get(i).getSlotNo(),bookings.get(i).getDate(),bookings.get(i).getBookingDate(),bookings.get(i).getDoctorName().getHospital().getHospitalName(),bookings.get(i).getDoctorName().getHospital().getPhoneNumber(),bookings.get(i).getStatus()));
        }
        return bookingDtos;
    }


    /************************************************************************************
     * Method: 			               -getAllUserBookingsByStaffEmail
     *Description: 			           -To return all the Bookings based on StaffEmail
     * @param staffEmail               -String -> Staff email

     * @return List<BookingOutputDto>  - A list of bookings, otherwise throws BookingException
     * @throws BookingException        - It is raised due to if staff email is null or no such staff email exists
    server side validation
     *Created By                                - Indra
     *Created Date                            - 08-MAR-2024

     ************************************************************************************/



    @Override
    public List<BookingOutputDto> getAllUserBookingsByStaffEmail(String staffEmail) throws BookingException{
        if(staffEmail == null)
            throw new BookingException("Staff mail can't be null");
        Optional<Hospital> foundStaff = this.staffRepository.findByHospitalEmail(staffEmail);
        if(foundStaff.isEmpty())
            throw new BookingException("No such Staff exists");
        Hospital staff = foundStaff.get();
        Doctor foundCar = this.carRepository.findByHospital(staff);
        List<Booking> foundBooking = this.bookingRepository.findByDoctorName(foundCar);
        List<BookingOutputDto> bookingDtos = new ArrayList<>();
        for(int i=0;i<foundBooking.size();i++)
        {
            bookingDtos.add(new BookingOutputDto(foundBooking.get(i).getBookId(),foundBooking.get(i).getCustomer().getCustomerEmail(),foundBooking.get(i).getDoctorName().getDoctorName(),foundBooking.get(i).getSlotNo(),foundBooking.get(i).getDate(),foundBooking.get(i).getBookingDate(),foundBooking.get(i).getDoctorName().getHospital().getHospitalName(),foundBooking.get(i).getDoctorName().getHospital().getPhoneNumber(),foundBooking.get(i).getStatus()));
        }
        return bookingDtos;
    }

    /************************************************************************************
     * Method: 			               -getBookingById
     *Description: 			           -To return all the Bookings based on Book id
     * @param id                       -Integer -> Book id

     * @return List<BookingOutputDto>  - A list of bookings, otherwise throws BookingException
     * @throws BookingException        - It is raised due to if book id is null or no such book id exists
    server side validation
     *Created By                                - Indra
     *Created Date                            - 13-MAR-2024

     ************************************************************************************/



    @Override
    public BookingOutputDto getBookingById(Integer id) throws BookingException{
        if(id==null)
            throw new BookingException("Book Id can't be null");
        Booking foundBooking = this.bookingRepository.getReferenceById(id);
        if(foundBooking==null)
            throw new BookingException("No such Bookings found");
        return new BookingOutputDto(foundBooking.getBookId(),foundBooking.getCustomer().getCustomerEmail(),foundBooking.getDoctorName().getDoctorName(),foundBooking.getSlotNo(),foundBooking.getDate(),foundBooking.getBookingDate(),foundBooking.getDoctorName().getHospital().getHospitalName(),foundBooking.getDoctorName().getHospital().getPhoneNumber(),foundBooking.getStatus());
    }


}

