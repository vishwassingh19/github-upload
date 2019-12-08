import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Report {

	
	public int getReporId() {
		return reporId;
	}
	public void setReporId(int reporId) {
		this.reporId = reporId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusinesspurpose() {
		return businesspurpose;
	}
	public void setBusinesspurpose(String businesspurpose) {
		this.businesspurpose = businesspurpose;
	}
	public int getStartdate() {
		return startdate;
	}
	public void setStartdate(int startdate) {
		this.startdate = startdate;
	}
	public int getEnddate() {
		return enddate;
	}
	public void setEnddate(int enddate) {
		this.enddate = enddate;
	}
	public List<Expense> getExpense() {
		return expense;
	}
	public void setExpense(List<Expense> expense) {
		this.expense = expense;
	}
	public Employee getApprovingEmployee() {
		return approvingemployee;
	}
	public void setApprovingEmployee(Employee approvingemployee) {
		this.approvingemployee = approvingemployee;
	}
	

	
	
	public Employee getCreatedbyemployee() {
		return createdbyemployee;
	}
	public void setCreatedbyemployee(Employee createdbyemployee) {
		this.createdbyemployee = createdbyemployee;
	}
	public boolean isSubmitted() {
		return submitted;
	}
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	public boolean isAppoved() {
		return appoved;
	}
	public void setAppoved(boolean appoved) {
		this.appoved = appoved;
	}
	public boolean isRejected() {
		return rejected;
	}
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
	public boolean isReimbursed() {
		return reimbursed;
	}
	public void setReimbursed(boolean reimbursed) {
		this.reimbursed = reimbursed;
	}
	public boolean isRejectedatreimburment() {
		return rejectedatreimburment;
	}
	public void setRejectedatreimburment(boolean rejectedatreimburment) {
		this.rejectedatreimburment = rejectedatreimburment;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int reporId;
	public String name;
	public String businesspurpose;
	public int startdate;
	public int enddate;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Expense> expense;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "approvingemployeeid")
	public Employee approvingemployee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="createdbyemployeeid")
	public Employee createdbyemployee;
	
	public boolean submitted;
	public boolean appoved;
	public boolean rejected;
	public boolean reimbursed;
	public boolean rejectedatreimburment;
}
