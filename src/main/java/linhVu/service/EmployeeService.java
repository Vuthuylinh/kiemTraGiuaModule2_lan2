package linhVu.service;

import linhVu.model.Department;
import linhVu.model.Employee;
import linhVu.model.EmployeeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    Employee findById(Long id);

    void save(EmployeeForm employeeForm, BindingResult result);

    void remove(Long id);
    void update(Long id,EmployeeForm employeeForm, BindingResult result);
    Iterable<Employee> findAllByDepartment(Department department);

    Page<Employee> findAllByNameContaining(String name, Pageable pageable);




}
