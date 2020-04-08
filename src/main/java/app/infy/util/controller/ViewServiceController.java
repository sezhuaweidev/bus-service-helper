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
	
	public ViewServiceController(EmployeeService employeeService, ShuttleService shuttleService) {
		this.employeeService = employeeService;
		this.shuttleService = shuttleService;
	}
	
	@GetMapping(value = "{id}")
	public String getIndexPage(@PathVariable(name="id", required=true) String id, Model model) {
		Integer intId = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			intId = Integer.parseInt(id); 
		} catch(NumberFormatException nfe) {
			throw new ControllerException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		EmployeeDetail ed = employeeService.getEmployeeDetailById(intId);
		List<ShuttleTiming> lstShuttleTimings = shuttleService.getAllShuttles();
		List<InfyDc> lstInfyDcs = shuttleService.getAllInfyDcs();
		List<InfyRegion> lstRegion = shuttleService.getAllInfyRegion();
		List<InfyCountry> lstCountry = shuttleService.getInfyCountry("ALL");
		
		
		model.addAttribute("employeename", ed.getEmpName());
		model.addAttribute("employeeid", ed.getEmpId());
		model.addAttribute("currentdate", sdf.format(new Date()));
		model.addAttribute("shuttletimings", lstShuttleTimings);
		model.addAttribute("shuttledcs", lstInfyDcs);
		model.addAttribute("region",lstRegion);
		model.addAttribute("country",lstCountry);
		model.addAttribute("path",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
		
		return "emp-shuttle";
	}
	
	@GetMapping(value = "manage")
	public String getManagePage() {
		return "mng-shuttle";
	}
	
	
}
