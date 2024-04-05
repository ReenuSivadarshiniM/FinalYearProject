package Project.doctor;



public class DoctorDto {


    private String doctorName;

    private String mobileNumber;

    private String doctorEmail;

    private String password;

    private String address;

    public DoctorDto(String doctorName, String mobileNumber, String doctorEmail, String password, String address) {
        this.doctorName = doctorName;
        this.mobileNumber = mobileNumber;
        this.doctorEmail = doctorEmail;
        this.password = password;
        this.address = address;
    }

    public DoctorDto() {
    }

    @Override
    public String toString() {
        return "DoctorDto{" +
                "doctorName='" + doctorName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
