package linhVu.controller;

import linhVu.model.Department;
import linhVu.model.Employee;
import linhVu.model.EmployeeForm;
import linhVu.service.DepartmentService;
import linhVu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@PropertySource("classpath:global_config_app.properties")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute("departments")
    public Iterable<Department> departments(){
       return departmentService.findAll();
    }

    @GetMapping("/employees")
    public ModelAndView listEmployees(@RequestParam("searchName") Optional<String> name, @PageableDefault(value =10) Pageable pageable){
        Page<Employee> employees;
        if(name.isPresent()){
            employees= employeeService.findAllByNameContaining(name.get(),pageable);
        }else{
            employees = employeeService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("employee/list");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/create-employee")
    public String createEmployeeForm(Model model){
        model.addAttribute("employeeForm", new EmployeeForm());
        return "employee/create";
    }
    @PostMapping("/save-employee")
    public ModelAndView saveEmployee(@Validated @ModelAttribute EmployeeForm employeeForm, BindingResult result) {

        // thong bao neu xay ra loi
        if (result.hasFieldErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
            ModelAndView modelAndView= new ModelAndView("employee/create");
            modelAndView.addObject("employeeForm", employeeForm);
            return modelAndView;
        }

        employeeService.save(employeeForm,result);

        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        modelAndView.addObject("success", "New employee created successfully!");
        return modelAndView;
    }

    @GetMapping("/edit-employee/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeService.findById(id));
        return "employee/edit";
    }

    @PostMapping("/edit-employee")
    public ModelAndView updateEmployee(@Validated @ModelAttribute EmployeeForm employeeForm, BindingResult result) {

        // thong bao neu xay ra loi
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
            ModelAndView modelAndView= new ModelAndView("employee/edit");
            return modelAndView;
        }
        employeeService.save(employeeForm,result);
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        modelAndView.addObject("employee", employeeForm);
        modelAndView.addObject("success", "Updated employee successfully!");
        return modelAndView;
    }

    @GetMapping("delete-employee/{id}")
    public ModelAndView deleteEmployeeForm(@PathVariable Long id, Model model){
        Employee employee = employeeService.findById(id);
        if(employee!=null){
            ModelAndView modelAndView = new ModelAndView("employee/delete");
            modelAndView.addObject("employee", employee);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-employee")
    public String deleteEmployee(@ModelAttribute("employee") Employee employee){
       employeeService.remove(employee.getId());
       return "redirect:/employees";
    }
}
