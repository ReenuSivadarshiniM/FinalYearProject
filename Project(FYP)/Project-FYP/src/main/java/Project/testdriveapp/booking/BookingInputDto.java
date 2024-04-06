package Project.testdriveapp.booking;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BookingInputDto {
    private String customerEmailId;
    private String doctorName;

    private Integer slotNo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate bookingDate;

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingInputDto(String customerEmailId, String carModelName, Integer slotNo, LocalDate date, LocalDate bookingDate) {
        this.customerEmailId = customerEmailId;
        this.doctorName = carModelName;
        this.slotNo = slotNo;
        this.date = date;
        this.bookingDate = bookingDate;
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

    public BookingInputDto(String customerName, String carModelName, Integer slotNo, LocalDate date) {
        this.customerEmailId = customerName;
        this.doctorName = carModelName;
        this.slotNo = slotNo;
        this.date = date;
    }

    public BookingInputDto() {
    }
}
