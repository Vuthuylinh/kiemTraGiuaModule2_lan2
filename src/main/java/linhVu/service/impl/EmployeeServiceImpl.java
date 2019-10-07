package linhVu.service.impl;

import linhVu.model.Department;
import linhVu.model.Employee;
import linhVu.model.EmployeeForm;
import linhVu.repository.EmployeeRepository;
import linhVu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@PropertySource("classpath:global_config_app.properties")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    Environment env;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.sort(pageable);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Iterable<Employee> findAllByDepartment(Department department) {
        return employeeRepository.findAllByDepartment(department);
    }

    @Override
    public Page<Employee> findAllByNameContaining(String name, Pageable pageable) {
        return employeeRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public void remove(Long id) {
        employeeRepository.delete(id);
    }

    @Override
    public void save(EmployeeForm employeeForm) {
        Employee employeeObject = getEmployee(employeeForm);
        employeeRepository.save(employeeObject);

    }

    @Override
    public Employee getEmployee(EmployeeForm employeeForm) {
        //lay ten file
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        // luu file len server

        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (employeeForm.getId() != null) {
            Employee employeeObject = findById(employeeForm.getId());
            if (fileName.equals(""))
                fileName = employeeObject.getAvatar();
        }
        if (employeeForm.getId() == null) {
            return new Employee(employeeForm.getName(), employeeForm.getBirthDate(),
                    employeeForm.getAddress(), fileName, employeeForm.getSalary(), employeeForm.getDepartment());
        } else {
            return new Employee(employeeForm.getId(), employeeForm.getName(), employeeForm.getBirthDate(),
                    employeeForm.getAddress(), fileName, employeeForm.getSalary(), employeeForm.getDepartment());
        }
    }


//    Iterable<Employee> findAll(Sort sort) {
//        sort = new Sort(new Sort.Order(Sort.Direction.ASC, "salary"));
//        Pageable pageable = new PageRequest(0, 10, sort);
//        return sort;
//    }

//    Pageable sortedBySalaryDescNameAsc = PageRequest.of(0, 5, Sort.by("salary").descending());


}
