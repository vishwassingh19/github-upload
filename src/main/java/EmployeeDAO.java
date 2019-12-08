import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
	

	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	
	
	public static Expense[] getExpenses(String email) {
		return null;	
	}

	public static Boolean loginUser(String email, String password) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public static Boolean createExpense(String email, Expense e) {
		Employee employee = getEmployeeByEmail(email);
		e.setEmployee(employee);
		List<Expense> expense = employee.getExpense();
		expense.add(e);
		Session ss=sessionFactory.openSession();
		ss.persist(e);
		
		return null;
	}

	public static Boolean createReport(String email, Report r,int[] expenseId) {
		Employee employee = getEmployeeByEmail(email);
	
		r.setCreatedbyemployee(employee);
		
		Session ss=sessionFactory.openSession();
		ss.persist(r);
		return null;
	}

	public static Expense getExpenseByExpenseId(int[] expenseId) {
		
		return null;
	}

	public static Report[] getReports(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean createAndSubmitReport(String email, Report r) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Report[] getApprovalRequests(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean sendApprovalsForReimbursment(String email, Integer[] report_id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public static Report[] getReimbursmentRequests(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean sendReimbursmentToClear(String email, Integer[] report_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Policy getPolicyForCategory(String category) {
		return null;
	}

	public static Boolean createPolicy(Policy p) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Policy[] getPolicies() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean editPolicy(Policy p) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean updateExpenseFormFeilds(String[] eff) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String[] getExpenseFormFeilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean createEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Employee getEmployeeByEmail(String email) {
		return null;
	}
	
	public static Employee[] getAllEmployees() {
		return null;
		
	}
	
	public Boolean updateReciptFile(String expenseId, byte[] recieptFile, String email) {
		
		return null;
	}
	public byte[][] getRecieptForExpense(String expenseId) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
