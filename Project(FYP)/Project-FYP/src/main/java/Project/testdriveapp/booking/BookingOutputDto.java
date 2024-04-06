package Project.testdriveapp.booking;

import java.time.LocalDate;

public class BookingOutputDto {
    private Integer bookId;
    private String customerEmailId;
    private String doctorName;
    private Integer slotNo;
    private LocalDate date;
    private LocalDate bookingDate;
    private String staffName;
    private String staffMobileNumber;
    private Boolean status;

    public Integer getBookId() {
        return bookId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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


    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffMobileNumber() {
        return staffMobileNumber;
    }

    public void setStaffMobileNumber(String staffMobileNumber) {
        this.staffMobileNumber = staffMobileNumber;
    }

    public BookingOutputDto(Integer bookId, String customerEmailId, String doctorName, Integer slotNo, LocalDate date, LocalDate bookingDate, String staffName, String staffMobileNumber, Boolean status) {
        this.bookId = bookId;
        this.customerEmailId = customerEmailId;
        this.doctorName = doctorName;
        this.slotNo = slotNo;
        this.date = date;
        this.bookingDate = bookingDate;
        this.staffName = staffName;
        this.staffMobileNumber = staffMobileNumber;
        this.status = status;
    }
}
