package app.infy.util.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.infy.util.entity.InfyDc;
import app.infy.util.entity.ShuttleTiming;
import app.infy.util.service.ShuttleService;

@RestController
@RequestMapping("core")
public class CoreDataController {

	private ShuttleService shuttleService;
	
	public CoreDataController(ShuttleService shuttleService) {
		this.shuttleService = shuttleService;
	}
	
	@GetMapping(value = "shuttletiming", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<ShuttleTiming> getAllShuttleTimings() {
		List<ShuttleTiming> shuttleTimings = shuttleService.getAllShuttles();
		return shuttleTimings;
	}
	
	@GetMapping(value = "infydc", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code=HttpStatus.OK)
	public List<InfyDc> getAllDcs() {
		List<InfyDc> infyDcs = shuttleService.getAllInfyDcs();
		return infyDcs;
	} 
	
	@GetMapping(value = "getCountry")
	public String getCountry() {
		return "Write country service";
	}
	
	@GetMapping(value = "getDc")
	public String getDc() {
		return "Write Dc service";
	}
	
}
