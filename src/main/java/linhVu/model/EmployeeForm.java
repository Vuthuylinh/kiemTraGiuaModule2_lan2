package linhVu.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class EmployeeForm {
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate ;
    private String address;
    private MultipartFile avatar;
    @Min(5)
    private Double salary;
    private Department department;

    public EmployeeForm() {
    }

    public EmployeeForm(Long id, String name, LocalDate birthDate, String address, MultipartFile avatar, Double salary, Department department) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.avatar = avatar;

        this.salary = salary;
        this.department = department;
    }

    public EmployeeForm(String name, LocalDate birthDate, String address, MultipartFile avatar, Double salary, Department department) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.avatar = avatar;
        this.salary = salary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
