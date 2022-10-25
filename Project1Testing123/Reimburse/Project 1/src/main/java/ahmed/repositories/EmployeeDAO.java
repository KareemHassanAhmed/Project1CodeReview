package ahmed.repositories;

import ahmed.entities.Employee;

import java.util.List;

public interface EmployeeDAO
{
    Employee createEmployee(Employee employee);

    Employee getEmployeeById(int eid);
    List<Employee> getAllEmployees();

    Employee updateEmployee(Employee employee);

    boolean deleteEmployee(Employee employee);

}
