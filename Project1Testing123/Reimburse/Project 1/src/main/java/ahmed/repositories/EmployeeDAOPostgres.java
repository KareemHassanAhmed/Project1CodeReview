package ahmed.repositories;

import ahmed.entities.Employee;
import ahmed.utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class EmployeeDAOPostgres implements EmployeeDAO
{
    private static EmployeeDAO edao;

    private EmployeeDAOPostgres() {super();}

    public static EmployeeDAO getEdao() {

        if (edao == null)
            edao = new EmployeeDAOPostgres();

        return edao;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO EMPLOYEE VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPassword());
            ps.setString(4, employee.getName());
            ps.setString(5, employee.getImage_url());
            ps.setInt(6, employee.getMgid());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("eid");
            employee.setEid(key);

            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int eid) {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "SELECT * FROM EMPLOYEE WHERE eid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eid);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setEmail(rs.getString("email"));
            employee.setPassword(rs.getString("password"));
            employee.setName(rs.getString("name"));
            employee.setImage_url(rs.getString("image_url"));
            employee.setMgid(rs.getInt("mgid"));
            employee.setEid(rs.getInt("eid"));

            return employee;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "SELECT * FROM EMPLOYEE";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Employee> employees = new ArrayList<Employee>();
            Employee employee;
            while (rs.next())
            {
                employee = new Employee();
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("password"));
                employee.setName(rs.getString("name"));
                employee.setImage_url(rs.getString("image_url"));
                employee.setMgid(rs.getInt("mgid"));
                employee.setEid(rs.getInt("eid"));

                employees.add(employee);
            }

            return employees;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "UPDATE EMPLOYEE SET email=?,password=?,name=?,image_url=?,mgid=? WHERE eid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getPassword());
            ps.setString(3, employee.getName());
            ps.setString(4, employee.getImage_url());
            ps.setInt(5, employee.getMgid());
            ps.setInt(6, employee.getEid());

            if(ps.executeUpdate() > 0)
            {
                return employee;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "DELETE FROM EMPLOYEE WHERE eid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getEid());

            if(ps.executeUpdate() > 0)
            {
                return true;
            }

            return false;

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }





}
