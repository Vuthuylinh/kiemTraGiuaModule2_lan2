package linhVu.service.impl;

import linhVu.model.Department;
import linhVu.repository.DepartmentRepository;
import linhVu.repository.EmployeeRepository;
import linhVu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
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
    public void remove(Long id) {
    departmentRepository.delete(id);
    }
}
