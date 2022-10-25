package ahmed.repositories;

import ahmed.entities.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {

    //	CRUD
    Reimbursement createReimbursement(Reimbursement reimbursement);

    //	Read
    Reimbursement getReimbursementById(int rid);
    List<Reimbursement> getAllReimbursement();

    //	Update
    Reimbursement updateReimbursement(Reimbursement reimbursement);

    //	Delete
    boolean deleteReimbursement(Reimbursement reimbursement);

}
