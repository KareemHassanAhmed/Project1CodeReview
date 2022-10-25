package ahmed.repositories;

import ahmed.entities.Manager;

import java.util.List;

public interface ManagerDAO {

    //	CRUD
    Manager createManager(Manager manager);

    //	READ
    Manager getManagerById(int mgid);
    List<Manager> getAllManagers();


    //	Update
    Manager updateManager(Manager manager);

    //	DELETE
    boolean deleteManager(Manager manager);
}
