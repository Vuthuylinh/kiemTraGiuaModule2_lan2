package linhVu.controller;

import linhVu.model.Department;
import linhVu.model.Employee;
import linhVu.service.DepartmentService;
import linhVu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    Environment env;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/departments")

    public ModelAndView listDepartment() {
        Iterable<Department> departments = departmentService.findAll();
        ModelAndView modelAndView = new ModelAndView("department/list");
        modelAndView.addObject("departments", departments);
        return modelAndView;
    }

    @GetMapping("/create-department")
    public String createDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/create";
    }

    @PostMapping("/save-department")
    public ModelAndView saveDepartment(@Validated @ModelAttribute("department") Department department, BindingResult result) {
        if(result.hasFieldErrors()){
            ModelAndView modelAndView= new ModelAndView("department/create");
            modelAndView.addObject("department", department);
            return modelAndView;
        }
        departmentService.save(department);
        ModelAndView modelAndView = new ModelAndView("redirect:/departments");
        modelAndView.addObject("department", department);
        modelAndView.addObject("message", "Create Department Successfully!");
        return modelAndView;
    }

    @GetMapping("/edit-department/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        if (department != null) {
            ModelAndView modelAndView = new ModelAndView("/department/edit");
            modelAndView.addObject("department", department);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }


    @PostMapping("/edit-department")
    public ModelAndView updateDepartment(@ModelAttribute("department") Department department){
        departmentService.save(department);
        ModelAndView modelAndView = new ModelAndView("redirect:/departments");
        modelAndView.addObject("department", department);
        modelAndView.addObject("message", "Edit Department Successfully!");
        return modelAndView;
    }

    @GetMapping("/delete-department/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        Department department = departmentService.findById(id);
        if (department != null) {
            ModelAndView modelAndView = new ModelAndView("/department/delete");
            modelAndView.addObject("department", department);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-department")
    public  String deleteDepartment(@ModelAttribute("department") Department department){
            departmentService.remove(department.getId());
        return "redirect:departments";
    }

    @GetMapping("view-department/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Department department = departmentService.findById(id);
        if(department == null){
            return new ModelAndView("/error-404");
        }

        Iterable<Employee> employees = employeeService.findAllByDepartment(department);
        ModelAndView modelAndView = new ModelAndView("department/view");
        modelAndView.addObject("department", department);
        modelAndView.addObject("employees", employees);
        return modelAndView;

    }
}
