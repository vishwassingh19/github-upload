import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="expense")
public class Expense {

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public boolean isPolicyViolationFlag() {
		return policyViolationFlag;
	}
	public void setPolicyViolationFlag(boolean policyViolationFlag) {
		this.policyViolationFlag = policyViolationFlag;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public byte[] getReciept() {
		return reciept;
	}
	public void setReciept(byte[] reciept) {
		this.reciept = reciept;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	public String category;
	public int date;
	public boolean policyViolationFlag;
	public int amount;
	public String merchant;
	public String discription;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emplopyeeid")
	public Employee employee;

	public byte[] reciept;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="reportsid")
	public List<Report> report;
		
		
}
