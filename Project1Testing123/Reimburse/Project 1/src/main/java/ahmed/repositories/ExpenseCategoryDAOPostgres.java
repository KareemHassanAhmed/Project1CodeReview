package ahmed.repositories;

import ahmed.entities.ExpenseCategory;
import ahmed.utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoryDAOPostgres implements ExpenseCategoryDAO {


    private static ExpenseCategoryDAO edao;

    private ExpenseCategoryDAOPostgres() {
    }

    public static ExpenseCategoryDAO getEdao() {
        if(edao == null)
            edao = new ExpenseCategoryDAOPostgres();
        return edao;
    }

    @Override
    public ExpenseCategory createExpenseCategory(ExpenseCategory expenseCategory) {
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO EXPENSE_CATEGORY VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ps.setString(2, expenseCategory.getTitle());
            ps.setString(3, expenseCategory.getImage_url());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("cid");
            expenseCategory.setCid(key);

            return expenseCategory;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExpenseCategory getExpenseCategoryById(int cid) {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "SELECT * FROM EXPENSE_CATEGORY WHERE cid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);

            ResultSet rs = ps.executeQuery();
            rs.next();

            ExpenseCategory expenseCategory = new ExpenseCategory();
            expenseCategory.setCid(rs.getInt("cid"));
            expenseCategory.setTitle(rs.getString("title"));
            expenseCategory.setImage_url(rs.getString("image_url"));

            return expenseCategory;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<ExpenseCategory> getAllExpenseCategories() {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "SELECT * FROM EXPENSE_CATEGORY";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<ExpenseCategory> expenseCategories = new ArrayList<ExpenseCategory>();
            ExpenseCategory expenseCategory;
            while (rs.next()){
                expenseCategory = new ExpenseCategory();
                expenseCategory.setCid(rs.getInt("cid"));
                expenseCategory.setTitle(rs.getString("title"));
                expenseCategory.setImage_url(rs.getString("image_url"));

                expenseCategories.add(expenseCategory);
            }

            return expenseCategories;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ExpenseCategory updateExpenseCategory(ExpenseCategory expenseCategory) {
        try(Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "UPDATE EXPENSE_CATEGORY SET title=?,image_url=? WHERE cid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, expenseCategory.getTitle());
            ps.setString(2, expenseCategory.getImage_url());
            ps.setInt(3, expenseCategory.getCid());

            if(ps.executeUpdate() > 0)
            {
                return expenseCategory;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteExpenseCategory(ExpenseCategory expenseCategory)
    {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            String sql = "DELETE FROM EXPENSE_CATEGORY WHERE cid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expenseCategory.getCid());

            return ps.executeUpdate() > 0;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
