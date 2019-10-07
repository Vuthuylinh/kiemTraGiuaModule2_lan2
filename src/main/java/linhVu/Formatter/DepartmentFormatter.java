package linhVu.Formatter;

import linhVu.model.Department;
import linhVu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class DepartmentFormatter implements Formatter<Department> {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentFormatter(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    @Override
    public Department parse(String text, Locale locale) throws ParseException {
        return departmentService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Department object, Locale locale) {
        return "["+ object.getId()+","+ object.getName()+"]";
    }


}
