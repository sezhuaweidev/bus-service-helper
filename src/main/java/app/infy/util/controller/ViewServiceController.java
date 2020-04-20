package app.infy.util.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import app.infy.util.exception.UnwantedAccessException;
import app.infy.util.helper.MessageConstants;
import app.infy.util.service.EmployeeService;
import app.infy.util.service.ShuttleService;

@Controller
//@Scope("session")
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
	
	@GetMapping(value = "home")
	public String getHomePage(HttpServletRequest req,Model model) {
		//checking access
		//no need of id path variable
		String authString = checkGrantedAuthority(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities(), new String[]{"EMPLOYEE","MANAGER","TRANSPORT"});
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Integer intId = null;
		try {
			intId = Integer.parseInt(id);
		} catch(NumberFormatException nfe) {
			throw new ControllerException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		//EmployeeDetail ed = employeeService.getEmployeeDetailById(intId);
		String url = "";
		
		if(authString.equalsIgnoreCase("EMPLOYEE")){
			url = "redirect:"+ request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/";
		}else if(authString.equalsIgnoreCase("MANAGER")){
			url = "redirect:"+request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/view/manage/";
		}else if(authString.equalsIgnoreCase("TRANSPORT")){
			url = "redirect:"+ request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/view/transManage/";
		}
		return url;
	}
	
	private String checkGrantedAuthority(Collection<? extends GrantedAuthority> authorities, String[] strings) {
		List<String> authString = authorities.stream().map(t->t.getAuthority()).collect(Collectors.toList());
		boolean allowed = false;
		for(int ii=0;ii<strings.length;ii++) {
			if(authString.contains(strings[ii])) { 
				allowed = true;
			}
		}
		if(!allowed) {
			throw new UnwantedAccessException(MessageConstants.USER_NOT_AUTHORIZED_TO_SEE);
		} else {
			return authString.get(0);
		}
	}

	@GetMapping(value = "apply")
	public String getIndexPage(Model model) {
		//checking access
		//no need of id path variable
		checkGrantedAuthority(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities(), new String[]{"EMPLOYEE","MANAGER"});
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

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
			mngUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/empManage/";
		}else if(role.equalsIgnoreCase("MANAGER")){
			mngUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/manage/";
		}else if(role.equalsIgnoreCase("TRANSPORT")){
			mngUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/trnsManage/";
		}
		
		model.addAttribute("employeeRole", role);
		model.addAttribute("employeename", ed.getEmpName());
		model.addAttribute("employeeid", ed.getEmpId());
		model.addAttribute("currentdate", sdf.format(new Date()));
		model.addAttribute("shuttletimings", lstShuttleTimings);
		model.addAttribute("shuttledcs", lstInfyDcs);
		model.addAttribute("region",lstRegion);
		model.addAttribute("country",lstCountry);
		model.addAttribute("pathApply",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/");
		model.addAttribute("pathManage",mngUrl);
		model.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
		
		return "emp-shuttle";
	}
	
	
	@GetMapping(value = "manage")
	public String getManagePage(Model model) {
		//checking access
		//no need of id path variable
		checkGrantedAuthority(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities(), new String[]{"MANAGER"});
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

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
		model.addAttribute("pathApply",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/");
		model.addAttribute("pathManage",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/manage/");
		List<ShuttleRequest> lstShuttleTimings = shuttleService.findShuttleRequestByMngIdAndDate(intId,curDate);
		model.addAttribute("shuttleRequestList", lstShuttleTimings);
		model.addAttribute("optionUrl",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/shuttleservice/");
		return "mng-shuttle";
	}
	
	@GetMapping(value = "empManage")
	public String getEmpManagePage(Model model) {
		//checking access
		//no need of id path variable
		checkGrantedAuthority(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities(), new String[]{"EMPLOYEE"});
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
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
		model.addAttribute("pathApply",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/apply/");
		model.addAttribute("pathManage",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/empManage/");
		List<ShuttleRequest> lstShuttleTimings = shuttleService.findShuttleRequestByEmpMngIdAndDate(intId,curDate);
		model.addAttribute("shuttleRequestList", lstShuttleTimings);
		model.addAttribute("optionUrl",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/shuttleservice/");
		return "mng-shuttle";
	}
	
	@GetMapping(value = "transManage")
	public String getTransManagePage(Model model) {
		//checking access
		//no need of id path variable
		checkGrantedAuthority(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities(), new String[]{"TRANSPORT"});
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
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
		model.addAttribute("pathManage",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/view/transManage/");
		List<ShuttleRequest> lstShuttleTimings = shuttleService.findShuttleRequestByTransMngIdAndDate(ed.getEmpDc(),curDate);
		model.addAttribute("shuttleRequestList", lstShuttleTimings);
		model.addAttribute("optionUrl",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/shuttleservice/");
		return "mng-shuttle";
	}
	
	
}
