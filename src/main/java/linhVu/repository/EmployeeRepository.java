package linhVu.repository;

import linhVu.model.Department;
import linhVu.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
Iterable<Employee> findAllByDepartment(Department department);

Page<Employee> findAllByNameContaining(String name, Pageable pageable);

@Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
  Page<Employee> sort(Pageable pageable);



//Pageable sortedByPriceDescNameAsc = PageRequest.of(0, 5, Sort.by("salary").descending().and(Sort.by("name")))
//List<Employee> findAllBySalary(Double salary, Sort sort);

}

