package linhVu.service;

import linhVu.model.Department;

public interface DepartmentService {
    Iterable<Department> findAll();

    Department findById(Long id);

    void save(Department department);

    void remove(Long id);
}
