package app.infy.util.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.entity.InfyCountry;
import app.infy.util.entity.InfyDc;
import app.infy.util.entity.InfyRegion;
import app.infy.util.entity.ShuttleRequest;
import app.infy.util.entity.ShuttleTiming;
import app.infy.util.exception.ControllerException;
import app.infy.util.helper.MessageConstants;
import app.infy.util.service.EmployeeService;
import app.infy.util.service.ShuttleService;

@Controller
@RequestMapping("view")
public class ViewServiceController {

	private EmployeeService employeeService;
	private ShuttleService shuttleService;
	@Autowired
	private HttpServletRequest request;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public ViewServiceController(EmployeeService employeeService, ShuttleService shuttleService) {
		this.employeeService = employeeService;
		this.shuttleService = shuttleService;
	}
	
	@GetMapping(value = "login")
	public String getLoginPage() {
		
		return "emp-login";
	}
	
	@GetMapping(value = "home/{id}")
	public String getHomePage(@PathVariable(name="id", required=true) String id,Model model) {
		Integer intId = null;
		try {
			intId = Integer.parseInt(id);
		} catch(NumberFormatException nfe) {
			throw new ControllerException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		EmployeeDetail ed = employeeService.getEmployeeDetailById(intId);
		String url = "";
		
		if(ed.getEmpType().equalsIgnoreCase("EMPLOYEE")){
			url = "redirect:"+ request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/"+ed.getEmpId();
		}else if(ed.getEmpType().equalsIgnoreCase("MANAGER")){
			url = "redirect:"+request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/view/manage/"+ed.getEmpId();
		}else if(ed.getEmpType().equalsIgnoreCase("TRANSPORT")){
			url = "redirect:"+ request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/view/transManage/"+ed.getEmpId();
		}
		return url;
	}
	
	@GetMapping(value = "apply/{id}")
	public String getIndexPage(@PathVariable(name="id", required=true) String id, Model model) {
		Integer intId = null;
		try {
			intId = Integer.parseInt(id); 
		} catch(NumberFormatException nfe) {
			throw new ControllerException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		EmployeeDetail ed = employeeService.getEmployeeDetailById(intId);
		String role = ed.getEmpType();
		String mngUrl = "";
		List<ShuttleTiming> lstShuttleTimings = shuttleService.getAllShuttles();
		List<InfyDc> lstInfyDcs = shuttleService.getAllInfyDcs();
		List<InfyRegion> lstRegion = shuttleService.getAllInfyRegion();
		List<InfyCountry> lstCountry = shuttleService.getInfyCountry("ALL");
		
		if(role.equalsIgnoreCase("EMPLOYEE")){
			mngUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/empManage/"+ed.getEmpId();
		}else if(role.equalsIgnoreCase("MANAGER")){
			mngUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/manage/"+ed.getEmpId();
		}else if(role.equalsIgnoreCase("TRANSPORT")){
			mngUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/trnsManage/"+ed.getEmpId();
		}
		
		model.addAttribute("employeeRole", role);
		model.addAttribute("employeename", ed.getEmpName());
		model.addAttribute("employeeid", ed.getEmpId());
		model.addAttribute("currentdate", sdf.format(new Date()));
		model.addAttribute("shuttletimings", lstShuttleTimings);
		model.addAttribute("shuttledcs", lstInfyDcs);
		model.addAttribute("region",lstRegion);
		model.addAttribute("country",lstCountry);
		model.addAttribute("pathApply",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/"+ed.getEmpId());
		model.addAttribute("pathManage",mngUrl);
		model.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
		
		return "emp-shuttle";
	}
	
	
	@GetMapping(value = "manage/{id}")
	public String getManagePage(@PathVariable(name="id", required=true) String id,Model model) {
		Integer intId = null;
		try {
			intId = Integer.parseInt(id);
		} catch(NumberFormatException nfe) {
			throw new ControllerException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		String curDate = sdf.format(new Date());
		EmployeeDetail ed = employeeService.getEmployeeDetailById(intId);
		model.addAttribute("employeename", ed.getEmpName());
		model.addAttribute("employeeRole", ed.getEmpType());
		model.addAttribute("employeeid", ed.getEmpId());
		model.addAttribute("pathApply",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/"+ed.getEmpId());
		model.addAttribute("pathManage",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/manage/"+ed.getEmpId());
		List<ShuttleRequest> lstShuttleTimings = shuttleService.findShuttleRequestByMngIdAndDate(intId,curDate);
		model.addAttribute("shuttleRequestList", lstShuttleTimings);
		model.addAttribute("optionUrl",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/shuttleservice/");
		return "mng-shuttle";
	}
	
	@GetMapping(value = "empManage/{id}")
	public String getEmpManagePage(@PathVariable(name="id", required=true) String id,Model model) {
		Integer intId = null;
		try {
			intId = Integer.parseInt(id);
		} catch(NumberFormatException nfe) {
			throw new ControllerException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		String curDate = sdf.format(new Date());
		EmployeeDetail ed = employeeService.getEmployeeDetailById(intId);
		model.addAttribute("employeeid", ed.getEmpId());
		model.addAttribute("employeename", ed.getEmpName());
		model.addAttribute("employeeRole", ed.getEmpType());
		model.addAttribute("pathApply",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/"+ed.getEmpId());
		model.addAttribute("pathManage",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/empManage/"+ed.getEmpId());
		List<ShuttleRequest> lstShuttleTimings = shuttleService.findShuttleRequestByEmpMngIdAndDate(intId,curDate);
		model.addAttribute("shuttleRequestList", lstShuttleTimings);
		model.addAttribute("optionUrl",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/shuttleservice/");
		return "mng-shuttle";
	}
	
	@GetMapping(value = "transManage/{id}")
	public String getTransManagePage(@PathVariable(name="id", required=true) String id,Model model) {
		Integer intId = null;
		try {
			intId = Integer.parseInt(id);
		} catch(NumberFormatException nfe) {
			throw new ControllerException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		String curDate = sdf.format(new Date());
		EmployeeDetail ed = employeeService.getEmployeeDetailById(intId);
		model.addAttribute("employeeid", ed.getEmpId());
		model.addAttribute("employeename", ed.getEmpName());
		model.addAttribute("employeeRole", ed.getEmpType());
		//model.addAttribute("pathApply",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/"+ed.getEmpId());
		model.addAttribute("pathManage",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/transManage/"+ed.getEmpId());
		List<ShuttleRequest> lstShuttleTimings = shuttleService.findShuttleRequestByTransMngIdAndDate(ed.getEmpDc(),curDate);
		model.addAttribute("shuttleRequestList", lstShuttleTimings);
		model.addAttribute("optionUrl",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/shuttleservice/");
		return "mng-shuttle";
	}
	
	
}
