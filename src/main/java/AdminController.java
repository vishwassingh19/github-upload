import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class AdminController {

	@Autowired
	EmployeeDAO EmployeeDAO;
	
	
	String getEmailFromCookie(HttpServletRequest request) 
	{
	Cookie[] ck= request.getCookies();
	
	String email = null; String password = null;
	
	for (int i = 0; i < ck.length; i++) {	
		email=ck[i].getValue();
		password=ck[i+2].getValue();}
	
	if(EmployeeDAO.loginUser(email, password))
	{
		if(email!=null) { return email; }
		else { return ""; }
		}
	else { return ""; }
	}

	@RequestMapping("/show_Reimbursment_Requests")
	String showReimbursmentRequests(HttpServletRequest request,Model m) {

	String email = getEmailFromCookie(request);
	
	if(email!="") {
	Report[] rep = EmployeeDAO.getReimbursmentRequests(email);
	
	m.addAttribute("Reports", rep );
	}	
	return "reimbursment_requests_view_page"; }

	@RequestMapping("/send_reimbursment_to_clear")
	String sendReimbursmentToClear(@RequestParam("report_id") Integer[] report_id,
														  HttpServletRequest request) {
		String email = getEmailFromCookie(request);
		if(email!="") {
			Boolean reimbursmentCleared =
								EmployeeDAO.sendReimbursmentToClear(email,report_id); }
		
		return "reimbursment_requests_view_page";
	}
	
	@RequestMapping("/new_policy_form")
	String createPolicy(@Valid @ModelAttribute("policy") Policy p,BindingResult br,
					                                    HttpServletRequest request, Model m) 
	{  
	if(br.hasErrors())
	return "policy_creation_page";
	
	else {
		String email = getEmailFromCookie(request);
		
		if(email!="") { Boolean expenseCreated = EmployeeDAO.createPolicy(p);}
		
		
		return showPolicies(request, m); }
	 }
	
	@RequestMapping("/show_policies")

	String showPolicies(HttpServletRequest request, Model m) {
		 String email = getEmailFromCookie(request);
			
			if(email!="") {
			Policy[] pol = EmployeeDAO.getPolicies();
			
			m.addAttribute("Policies",pol );
			}	
			return "policy_view_page";
	
	}

	@RequestMapping("/edit_policy_form")
	String editPolicy(@Valid @ModelAttribute("policy") Policy p,BindingResult br,
					                                    HttpServletRequest request, Model m) 
	{  
	if(br.hasErrors())
	return "policy_creation_page";
	
	else {
		String email = getEmailFromCookie(request);
		
		if(email!="") { Boolean expenseCreated = EmployeeDAO.editPolicy(p);}
		
		
		return showPolicies(request, m); }
	 }
	
	@RequestMapping(value="/edit_expense_form_feild", method = RequestMethod.POST)
	String editExpenseFormFeild(@RequestParam("expense_form_feild") String[] eff,
																	HttpServletRequest request)
	{
	String email = getEmailFromCookie(request);
	if(email!="") { Boolean expenseCreated = EmployeeDAO.updateExpenseFormFeilds(eff);}	
	return "expense_settings_page";
	}

	@RequestMapping("/set_expenses_reminder_time")
	String setExpensesReminderTime (HttpServletRequest request, HttpServletResponse response, 
													   @RequestParam("time") Integer n)
	{return null;}
	
	@RequestMapping("/expense_expiration_Time")
	String setExpenseExpirationTime(HttpServletRequest request,@RequestParam("time") Integer n) {
		return null;}	
			
	@RequestMapping("/create_employee_page")
	String createEmployeePage(HttpServletRequest request) {
		
			String email = getEmailFromCookie(request);
			
			if(email!="") {
				return "employee_creation_page";
			}
			return "admin_homepage"; }
	
	@RequestMapping(value = "/create_employee", method = RequestMethod.POST)
	String createEmployee(HttpServletRequest request, @Valid @ModelAttribute("employee") Employee emp)
	{
		String email = getEmailFromCookie(request);
		
		if(email!="") {Boolean createemployee=EmployeeDAO.createEmployee(emp);}
		return "employees_list_page";
		}	
	
	String employeeListPage(HttpServletRequest request, Model m ) {
		return null;
		
	}
	
		
	
	
	
}