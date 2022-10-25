package ahmed.services;

import ahmed.entities.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryService {

    ExpenseCategory createExpenseCategory(ExpenseCategory expenseCategory);

    ExpenseCategory getExpenseCategoryById(int cid);
    ExpenseCategory getExpenseCategoryByTitle(String title);
    List<ExpenseCategory> getAllExpenseCategories();

    ExpenseCategory updateExpenseCategory(ExpenseCategory expenseCategory);


    boolean deleteExpenseCategory(ExpenseCategory expenseCategory);

}
