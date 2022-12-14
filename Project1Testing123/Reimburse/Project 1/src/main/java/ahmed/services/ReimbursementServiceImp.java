package ahmed.services;

import ahmed.repositories.ReimbursementDAO;
import ahmed.repositories.ReimbursementDAOPostgres;
import ahmed.entities.Employee;
import ahmed.entities.Reimbursement;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReimbursementServiceImp implements ReimbursementService {

    @Inject
//    private  ReimbursementDAO rdao ;

    private static ReimbursementDAO rdao = ReimbursementDAOPostgres.getRdao();

    private static EmployeeService employeeService = EmployeeServiceImp.getEserv();


    private static ReimbursementService rserv;

    private ReimbursementServiceImp() {
    }

    public static ReimbursementService getRserv() {
        if (rserv == null)
            rserv = new ReimbursementServiceImp();
        return rserv;
    }

    @Override
    public Reimbursement createReimbursement(Reimbursement reimbursement) {
        return rdao.createReimbursement(reimbursement);
    }

    @Override
    public Reimbursement getReimbursementById(int rid) {
        return rdao.getReimbursementById(rid);
    }

    @Override
    public List<Reimbursement> getAllReimbursement() {
        return rdao.getAllReimbursement();
    }

    @Override
    public List<Reimbursement> getReimbursementByEmployee(int eid)
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        List<Reimbursement> result = new ArrayList<>();
        for (Reimbursement r:filterMe)
        {
            if(r.getEid() == eid)
                result.add(r);
        }

        return result;
    }

    @Override
    public List<Reimbursement> getReimbursementByCategory(int cid)
    {
        List<Reimbursement> filterMe;
        filterMe = rserv.getAllReimbursement();
        List<Reimbursement> result = new ArrayList<>();
        for (Reimbursement r:filterMe)
        {
            if(r.getCid() == cid)
                result.add(r);
        }

        return result;
    }

    @Override
    public List<Reimbursement> getReimbursementByManager(int mgid)
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        List<Reimbursement> result = new ArrayList<>();
        List<Employee> employees = employeeService.getEmployeeByManager(mgid);

        for (Employee e:employees)
        {
            for (Reimbursement r:filterMe)
            {
                if (e.getEid() == r.getEid())
                    result.add(r);
            }
        }

        return result;
    }

    @Override
    public List<Reimbursement> getReimbursementsApproved()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        List<Reimbursement> result = new ArrayList<>();
        for (Reimbursement r:filterMe)
        {
            if(r.getStatus() >0)
                result.add(r);
        }

        return result;
    }

    @Override
    public List<Reimbursement> getReimbursementsDenied()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        List<Reimbursement> result = new ArrayList<>();
        for (Reimbursement r:filterMe)
        {
            if(r.getStatus() <1)
                result.add(r);
        }

        return result;
    }

    @Override
    public List<Reimbursement> getReimbursementsAmountAscending()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        filterMe.sort(Comparator.comparingDouble(Reimbursement::getAmount));
        return filterMe;
    }

    @Override
    public List<Reimbursement> getReimbursementsAmountDescending()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        filterMe.sort(Comparator.comparingDouble(Reimbursement::getAmount).reversed());
        return filterMe;
    }

    @Override
    public List<Reimbursement> getReimbursementsStatusDateAscending()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        filterMe.sort((r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getStatus_date().toString(), r2.getStatus_date().toString()));
        Collections.reverse(filterMe);
        return filterMe;
    }

    @Override
    public List<Reimbursement> getReimbursementsStatusDateDescending()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        filterMe.sort((r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getStatus_date().toString(), r2.getStatus_date().toString()));
        return filterMe;
    }

    @Override
    public List<Reimbursement> getReimbursementsSubmitDateAscending()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        filterMe.sort((r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getSubmit_date().toString(), r2.getSubmit_date().toString()));
        Collections.reverse(filterMe);
        return filterMe;
    }

    @Override
    public List<Reimbursement> getReimbursementsSubmitDateDescending()
    {
        List<Reimbursement> filterMe = rserv.getAllReimbursement();
        filterMe.sort((r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getSubmit_date().toString(), r2.getSubmit_date().toString()));
        return filterMe;
    }

    @Override
    public Reimbursement updateReimbursement(Reimbursement reimbursement) {
        return rdao.updateReimbursement(reimbursement);
    }

    @Override
    public boolean deleteReimbursement(Reimbursement reimbursement) {
        return rdao.deleteReimbursement(reimbursement);
    }
}
