import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emploeeFormFeild")
public class ExpenseFormFeilds {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int serialnumber;
	
	
}
