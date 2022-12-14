package ahmed.services;

import ahmed.repositories.EmployeeDAO;
import ahmed.entities.Employee;
import ahmed.repositories.EmployeeDAOPostgres;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeServiceImp implements EmployeeService {
    private static EmployeeService eserv = null;

    @Inject
//    private EmployeeDAO edao;
	private static EmployeeDAO edao = EmployeeDAOPostgres.getEdao();

    private EmployeeServiceImp() {
        super();
    }

    public static EmployeeService getEserv() {
        if(eserv == null)
            eserv = new EmployeeServiceImp() ;

        return eserv;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return edao.createEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int eid) {
        return edao.getEmployeeById(eid);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {

        List<Employee> employees = edao.getAllEmployees();
        for(Employee e : employees)
        {
            if(e.getEmail().compareToIgnoreCase(email) == 0)
                return e;
        }

        return null;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        List<Employee> employees = edao.getAllEmployees();
        for(Employee e : employees)
        {
            if(e.getName().compareToIgnoreCase(name) == 0)
                return e;
        }

        return null;
    }

    @Override
    public List<Employee> getEmployeeByManager(int mgid)
    {
        List<Employee> employees = edao.getAllEmployees();
        List<Employee> managedByMgid = new ArrayList<>();

        for(Employee e : employees)
        {
            if(e.getMgid() == mgid)
                managedByMgid.add(e);
        }

        return managedByMgid;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return edao.getAllEmployees();
    }

    @Override
    public List<Employee> getAllEmployeesNameAtoZ() {
        List<Employee> employees = edao.getAllEmployees();
        employees.sort((e1, e2) -> String.CASE_INSENSITIVE_ORDER.compare(e1.getName(), e2.getName()));
        return employees;
    }

    @Override
    public List<Employee> getAllEmployeesNameZtoA() {
        List<Employee> employees = edao.getAllEmployees();

        employees.sort((e1, e2) -> String.CASE_INSENSITIVE_ORDER.compare(e1.getName(), e2.getName()));
        Collections.reverse(employees);
        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return edao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return edao.deleteEmployee(employee);
    }

}
