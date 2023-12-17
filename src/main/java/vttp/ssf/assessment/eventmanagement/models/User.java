package vttp.ssf.assessment.eventmanagement.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "Name must be provided")
    @Size(min = 5, max = 25, message = "Name must be between 5 and 25 characters")
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dob;

    @NotNull(message = "Email must be provided")
    @Email(message = "Must be a valid email")
    @Size(max = 50, message = "Email must be up to a maximum of 50 characters")
    private String email;

    @Pattern(regexp = "[8|9][0-9]{8}", message = "Phone Number must start with 8 or 9 and contain 8 digits")
    private String phoneno;

    private String gender;

    @Min(value = 1, message = "Minimum request is 1")
    @Max(value = 10, message = "Maximum request is 10")
    private int ticketno;

    public User() {
    }

    public User(
            @NotBlank(message = "Name must be provided") @Size(min = 5, max = 25, message = "Name must be between 5 and 25 characters") String fullName,
            @Past(message = "Date of Birth must be in the past") LocalDate dob,
            @NotNull(message = "Email must be provided") @Email(message = "Must be a valid email") @Size(max = 50, message = "Email must be up to a maximum of 50 characters") String email,
            @Pattern(regexp = "[8|9][0-9]{8}", message = "Phone Number must start with 8 or 9 and contain 8 digits") String phoneno,
            String gender,
            @Min(value = 1, message = "Minimum request is 1") @Max(value = 10, message = "Maximum request is 10") int ticketno) {
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phoneno = phoneno;
        this.gender = gender;
        this.ticketno = ticketno;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTicketno() {
        return ticketno;
    }

    public void setTicketno(int ticketno) {
        this.ticketno = ticketno;
    }
}