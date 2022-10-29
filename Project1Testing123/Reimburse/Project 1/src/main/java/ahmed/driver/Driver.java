package ahmed.driver;

import ahmed.controllers.*;
import ahmed.repositories.ManagerDAOPostgres;
import ahmed.services.ManagerService;
import ahmed.services.ManagerServiceImp;
import io.javalin.Javalin;


public class Driver {

//public static ManagerService managerService = new ManagerServiceImp(new ManagerDAOPostgres());


    public static void main(String[] args) {
        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();
                    config.addStaticFiles("/public");}
        ).start(7070);

//		########################
//		EMPLOYEE
// 		########################
        app.put("/employee", EmployeeController.createEmployee);

        app.get("/employee", EmployeeController.getAllEmployees);
        app.get("/employee:eid", EmployeeController.getEmployeeById);

        app.post("/employee", EmployeeController.updateEmployee);
        app.delete("/employee", EmployeeController.deleteEmployee);
//		########################
//		EXPENSE_CATEGORY
// 		########################
        app.put("/expense-category", ExpenseCategoryController.createExpenseCategory);

        app.get("/expense-category", ExpenseCategoryController.getAllExpenseCategories);
        app.get("/expense-category:cid", ExpenseCategoryController.getExpenseCategoryById);

        app.post("/expense-category", ExpenseCategoryController.updateExpenseCategory);
        app.delete("/expense-category", ExpenseCategoryController.deleteExpenseCategory);
//		########################
//		MANAGERS
// 		########################
        app.put("/manager", ManagerController.createManager);
        app.get("/managers", ManagerController.getAllManagers);
        app.get("/manager:mid", ManagerController.getManagerById);

        app.post("/manager", ManagerController.updateManager);
        app.delete("/manager", ManagerController.deleteManager);
//		########################
//		REIMBURSEMENT
// 		########################
        app.put("/reimbursement", ReimbursementController.createReimbursement);

        app.get("/reimbursement", ReimbursementController.getAllReimbursements);
        app.get("/reimbursement:rid", ReimbursementController.getReimbursementById);

        app.post("/reimbursement", ReimbursementController.updateReimbursement);
        app.delete("/reimbursement", ReimbursementController.deleteReimbursement);
//        app.start();



    }



}
