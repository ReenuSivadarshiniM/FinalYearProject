package Project.testdriveapp.booking;

import java.time.LocalDate;
import java.util.List;


public interface BookingService {
    BookingOutputDto createNewBooking(BookingInputDto newBooking) throws BookingException;
    List<BookingOutputDto> getAllBookings() throws BookingException;
    BookingOutputDto getBookingById(Integer id) throws BookingException;
    void deleteBooking(Integer bookId)throws BookingException;

    List<BookingOutputDto> getAllUserBookingByEmail(String mailId) throws BookingException;
//
    List<BookingOutputDto> getAllUserBookingBySlotNo(Integer slotNo) throws BookingException;
//
//    List<BookingOutputDto> getAllUserBookingByDate(LocalDate date) throws BookingException;
//
    List<BookingOutputDto> getAllUserBookingByDoctorName(String carModelName)throws BookingException;




}
