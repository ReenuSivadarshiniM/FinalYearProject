package Project.testdriveapp.booking;

import java.time.LocalDate;

public class BookingOutputDto {
    private Integer bookId;
    private String customerEmailId;
    private String doctorName;
    private Integer slotNo;
    private LocalDate date;
    private LocalDate bookingDate;
    private String doctorMobileNumber;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BookingOutputDto(Integer bookId, String customerEmailId, String doctorName, Integer slotNo, LocalDate date, LocalDate bookingDate, String doctorMobileNumber, Boolean status) {
        this.bookId = bookId;
        this.customerEmailId = customerEmailId;
        this.doctorName = doctorName;
        this.slotNo = slotNo;
        this.date = date;
        this.bookingDate = bookingDate;
        this.doctorMobileNumber = doctorMobileNumber;
        this.status = status;
    }

    private Boolean status;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }



    public String getDoctorMobileNumber() {
        return doctorMobileNumber;
    }

    public void setDoctorMobileNumber(String doctorMobileNumber) {
        this.doctorMobileNumber = doctorMobileNumber;
    }

    public BookingOutputDto(Integer bookId, String customerEmailId, String carModelName, Integer slotNo, LocalDate date, LocalDate bookingDate, String staffName, String staffMobileNumber,Boolean status) {
        this.bookId = bookId;
        this.customerEmailId = customerEmailId;
        this.doctorName = carModelName;
        this.slotNo = slotNo;
        this.date = date;
        this.bookingDate = bookingDate;
        this.doctorMobileNumber = staffMobileNumber;
        this.status = status;
    }
}
