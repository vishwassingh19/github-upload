import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name ="employees")
public class Employee {
		
		
		public int getEmployeeid() {
			return employeeid;
		}
		public void setEmployeeid(int employeeid) {
			this.employeeid = employeeid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public List<Expense> getExpense() {
			return expense;
		}
		public void setExpense(List<Expense> expense) {
			this.expense = expense;
		}
		public List<Report> getReportforapproval() {
			return reportforapproval;
		}
		public void setReportforapproval(List<Report> reportforapproval) {
			this.reportforapproval = reportforapproval;
		}
		
		public List<Report> getReportcreatedbyuser() {
			return reportcreatedbyuser;
		}
		public void setReportcreatedbyuser(List<Report> reportcreatedbyuser) {
			this.reportcreatedbyuser = reportcreatedbyuser;
		}

		public String getApproverforuser() {
			return approverforuser;
		}
		public void setApproverforuser(String approverforuser) {
			this.approverforuser = approverforuser;
		}

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@NotNull
		@Size(min = 4,message = "size is less",max = 8)
		@
		public int employeeid;
		public String name;
		@Password
		public String email;
		public String password;
		
		@OneToMany(cascade = CascadeType.ALL)
		public List<Expense> expense;
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name = "approvingemployeeid")
		public List<Report> reportforapproval;
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name = "createdbyemployeeid")
		public List<Report> reportcreatedbyuser;
		public String approverforuser;




}
