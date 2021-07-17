package com.example.springbootprojectdemo.dto;

import com.example.springbootprojectdemo.annotation.YearMonth;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Max(value = 90)
    private Integer age;

    @Pattern(regexp = "^\\d{2,3}-\\{3,4}-\\d{4}$", message = "양식이 맞지 않습니다")
    private String phoneNumber;

    private String address;

    @Email
    private String email;

    @YearMonth(pattern = "yyyyMM")
    private String reqYearMonth;

    //@Valid //클래스 밖에서 @Valid를 반드시 붙여줘야 된다
    //private List<Car> cars;

    @AssertTrue(message = "yyyyMM 형식에 맞지 않습니다")
    public boolean isReqYearMonthValidation(){ //is~ 를 붙여야함
        try {
            LocalDate localDate = LocalDate.parse(getReqYearMonth() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public User() {
    }

    public User(String name, int age, String phoneNumber, String address) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
