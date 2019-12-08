import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@Transactional
public class UserController {
	
	@Autowired
	EmployeeDAO EmployeeDAO;
	
	
	
	@RequestMapping(value="/user_login", method = RequestMethod.GET)
	String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
											HttpServletResponse response,HttpServletRequest request)
	{
		Boolean validuser=EmployeeDAO.loginUser(email,password);
		
		if(validuser) {
			updateNotificationPanel(email);
			setAndDeleteCookie(email, password, response, request);
			
		return "user_homepage";
	}else {
		return "login_page";
	}
	
	}

	private void setAndDeleteCookie(String email, String password, HttpServletResponse response, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			cookies[i].setMaxAge(0);
		}
		Cookie ck_email=new Cookie("email", email);
		Cookie ck_password=new Cookie("password", password);
		response.addCookie(ck_password);
		response.addCookie(ck_email);
	}
	
	private void updateNotificationPanel(String email) {
		// TODO Auto-generated method stub
		
	}

	@RequestMapping("/expenses_view_page")
	String showExpenses(HttpServletRequest request, Model m) {
		
		String email = getEmailFromCookie(request);
		
		if(email!="") {
		Expense[] exp = EmployeeDAO.getExpenses(email);
		
		m.addAttribute("Expenses",exp );
		}	
		return "expense_view_page";
	
	}
	
	@RequestMapping("/expense_creation_page")
	String createExpense(Model m,HttpServletRequest request) {
		String email = getEmailFromCookie(request);
		String[] eff=null;
		if(email!="") { eff = EmployeeDAO.getExpenseFormFeilds();}
		
		m.addAttribute("expense_form_feilds", eff);
		return "expense_creation_page";
	}
	
	@RequestMapping("/create_and_save__expense_form")
	String createAndSaveExpense(@Valid @ModelAttribute("expense") Expense e,BindingResult br,
					                                    HttpServletRequest request, Model m) 
	{  
	if(br.hasErrors())
	return "expense_creation_page";
	
	else {
		String email = getEmailFromCookie(request);
		
		if(email!="") { Boolean expenseCreated = EmployeeDAO.createExpense(email,e);}
			
		Policy p = EmployeeDAO.getPolicyForCategory(e.category);
		checkAndUpdatePolicyViolationFlag(p,e);
		
		return showExpenses(request, m); }
	 }

	private void checkAndUpdatePolicyViolationFlag(Policy p, Expense e) {
		if(p.date<e.date)
			e.policyViolationFlag=true;
		else if (p.amount < e.amount) {
			e.policyViolationFlag=true;
		}
		}

	String getEmailFromCookie(HttpServletRequest request) 
	{
	Cookie[] ck= request.getCookies();
	
	String email = null; String password = null;
	
	for (int i = 0; i < ck.length; i++) {	
		email=ck[i].getValue();
		password=ck[i+1].getValue();}
	
	if(EmployeeDAO.loginUser(email, password))
	{
		if(email!=null) { return email; }
		else { return ""; }
		}
	else { return ""; }
	}
	
	@RequestMapping("/create_report_form")
	String createReport(@Valid @ModelAttribute("report") Report r,BindingResult br,
														HttpServletRequest request, Model m) 
	{  
	if(br.hasErrors())
	return "report_creation_page";
	
	else {
		String email = getEmailFromCookie(request);
		
		if(email!="") { Boolean reportCreated = EmployeeDAO.createReport(email,r);}
	
		return showReport(request, m); }
	 }

	@RequestMapping("create_and_submit_report_form")
	String createAndSubmitReport(@Valid @ModelAttribute("report") Report r,BindingResult br,
														HttpServletRequest request, Model m) 
	{  
	if(br.hasErrors())
	return "report_creation_page";
	
	else {
		String email = getEmailFromCookie(request);
		
		if(email!="") { Boolean reportCreated = EmployeeDAO.createAndSubmitReport(email,r);}
	
		return showReport(request, m); }
	 }
	
	String showReport(HttpServletRequest request, Model m) {

	String email = getEmailFromCookie(request);
		
		if(email!="") {
		Report[] rep = EmployeeDAO.getReports(email);
		
		m.addAttribute("Reports", rep );
		}	
		return "report_view_page";
	
		
	}
	
	@RequestMapping("/show_Approvals_Requests")
	String showApprovalRequests(HttpServletRequest request,Model m) {

	String email = getEmailFromCookie(request);
	
	if(email!="") {
	Report[] rep = EmployeeDAO.getApprovalRequests(email);
	
	m.addAttribute("Reports", rep );
	}	
	return "approval_requests_view_page";

	
}
	
	@RequestMapping("/send_For_Reimburment")
	String sendApprovalsForReimbursment(@RequestParam("report_id") Integer[] report_id,
														  HttpServletRequest request) {
		String email = getEmailFromCookie(request);
		Boolean reimbursmentRequstCreated=null;
		if(email!="") {
			reimbursmentRequstCreated =
								EmployeeDAO.sendApprovalsForReimbursment(email,report_id); }
		if(reimbursmentRequstCreated==false) {
			Integer userId=null;
			String message=null;
			for (Integer rId : report_id) {
			//userId  = AppDAO.getUserIdByReportId(rId);
			message="Reports  with "+report_id+" sent for reimbursment";
			// Code for String to XML,text file conversion //
			//AppDAO.setReportApprovedMessage(message,userId);
			}
			}
			
			
		
		return "approval_requests_view_page";
	}

	@RequestMapping(value = "/reciept_upload_form" ,method = RequestMethod.POST)
	String uploadRecipt(@RequestParam("file") CommonsMultipartFile reciept , 
					@RequestParam("expenseid") String expenseId, HttpServletRequest request) {
		
		String email = getEmailFromCookie(request);
		if(email!="") { 
					byte[] recieptFile = reciept.getBytes();
			Boolean reciptUpdated=EmployeeDAO.updateReciptFile( expenseId, recieptFile, email);	
			return "reciept_upload_confirmation_page";
		}
		else {
			return "upload_reciept_form_page";
		}	}
	
	@RequestMapping("/dowload_reciept_form")
	String downloadRecipt(@RequestParam("expenseid") String expenseId, HttpServletRequest request,
										HttpServletResponse response ) throws IOException
	{ 				String email = getEmailFromCookie(request);
				if(email!="") {
					byte[][] reciepts = EmployeeDAO.getRecieptForExpense(expenseId);
					for (byte[] reciept : reciepts) {
						
						response.setContentType("APPLICATION/OCTET-STREAM");
						response.setHeader("Content-Disposition", "attachment");
						 OutputStream outputStream = response.getOutputStream();
						 outputStream.write(reciept);
					}
					
				}
		
		
		
		
		
		
	}
}
	
	
	
	
	
	
	
	
	
	