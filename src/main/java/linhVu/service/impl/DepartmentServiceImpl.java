package linhVu.service.impl;

import linhVu.model.Department;
import linhVu.model.Employee;
import linhVu.repository.DepartmentRepository;
import linhVu.repository.EmployeeRepository;
import linhVu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Iterable<Department> findAll() {

        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public void save(Department department) {
    departmentRepository.save(department);
    }

    @Override
    public void remove(Long id)
    {
        Department department = findById(id);
        List<Employee> employees = (List<Employee>) employeeRepository.findAllByDepartment(department);
        for(int i=0; i<employees.size(); i++){
            employeeRepository.delete(employees.get(i));
        }
    departmentRepository.delete(id);
    }
}
