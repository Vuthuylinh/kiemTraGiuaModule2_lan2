package linhVu.service;
import linhVu.model.Department;
import linhVu.model.Employee;
import linhVu.model.EmployeeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    Employee findById(Long id);

    void save(EmployeeForm employeeForm);

    void remove(Long id);
    Iterable<Employee> findAllByDepartment(Department department);

    Page<Employee> findAllByNameContaining(String name, Pageable pageable);

    Employee getEmployee(EmployeeForm employeeForm);



}
