package ahmed.controllers;

import ahmed.driver.Driver;
import com.google.gson.Gson;
import ahmed.entities.Manager;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class ManagerController
{
//    private static ManagerService App.managerService = ManagerServiceImp.;
//    private static Gson gson = new Gson();

    public static Handler createManager = (ctx) ->
    {
        String body = ctx.body();
        Gson gson = new Gson();


        try

        {
            Manager manager = gson.fromJson(body, Manager.class);
            Manager newman = Driver.managerService.createManager(manager);
            System.out.println(manager);
            if(manager != null) {
//               Manager returned = App.managerService.createManager(manager);

                ctx.result(gson.toJson(newman));
                ctx.status(200);
            }
            else
                ctx.status(404);


        }catch (Exception e)
        {
            ctx.status(404);
            e.printStackTrace();
        }

    };

    public static Handler getManagerById = (ctx) -> {
        String mid = ctx.pathParam("mid");
        Gson gson = new Gson();

        try
        {
            Manager manager = Driver.managerService.getManagerById(Integer.parseInt(mid));
            String json = gson.toJson(manager);
            ctx.result(json);
            ctx.status(200);
        } catch (NumberFormatException e)
        {
            ctx.status(404);
            e.printStackTrace();
        }
    };

    public static Handler getAllManagers = (ctx) -> {
//		Can have query to search
        String name = ctx.queryParam("name");
        String email = ctx.queryParam("email");


        List<Manager> managers = new ArrayList<Manager>();

        if(name != null)
        {
            managers.add(Driver.managerService.getManagerByName(name));
        }
        else if (email != null)
        {
            managers.add(Driver.managerService.getManagerByEmail(email));
        }
        else
        {
            managers = Driver.managerService.getAllManagers();
        }
        Gson gson = new Gson();

        String json = gson.toJson(managers);
        ctx.result(json);
        ctx.status(200);
    };


    public static Handler updateManager = (ctx) -> {
        String body = ctx.body();
        Gson gson = new Gson();

        Manager manager = gson.fromJson(body, Manager.class);
        Manager result = Driver.managerService.updateManager(manager);

        ctx.result(gson.toJson(result));
        ctx.status(202);

    };

    public static Handler deleteManager = (ctx) -> {
        String body = ctx.body();
        Gson gson = new Gson();

        Manager manager = gson.fromJson(body, Manager.class);
        try
        {
            boolean result = Driver.managerService.deleteManager(manager);

            if(result)
                ctx.status(200);
            else
                ctx.status(404);
        }catch(NumberFormatException e)
        {
            ctx.status(404);
            e.printStackTrace();
        }
    };
}
