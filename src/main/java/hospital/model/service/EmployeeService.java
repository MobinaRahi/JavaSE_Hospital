package hospital.model.service;

import hospital.model.entity.Employee;
import hospital.model.repository.EmployeeRepository;
import lombok.Getter;


import java.util.List;


public class EmployeeService implements Service<Employee, Integer> {

    @Getter
    public static EmployeeService service =new EmployeeService();

    private EmployeeService() {
    }

    @Override
    public void save(Employee employee) throws Exception {
        try(EmployeeRepository employeeRepository = new EmployeeRepository()) {
            employeeRepository.save(employee);
        }
    }

    @Override
    public void edit(Employee employee) throws Exception {
        try(EmployeeRepository employeeRepository = new EmployeeRepository()) {
            employeeRepository.save(employee);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(EmployeeRepository employeeRepository = new EmployeeRepository()) {
            employeeRepository.delete(id);
        }
    }

    @Override
    public List<Employee> findAll() throws Exception {
        try(EmployeeRepository employeeRepository = new EmployeeRepository()) {
            return employeeRepository.findAll();
        }
    }

    @Override
    public Employee findById(Integer id) throws Exception {
        try(EmployeeRepository employeeRepository = new EmployeeRepository()) {
            return employeeRepository.findById(id);
        }
    }

    public List<Employee> findByUserId(Integer userId) throws Exception {
        try(EmployeeRepository employeeRepository = new EmployeeRepository()) {
            return employeeRepository.findByUserId(userId);
        }
    }
}
