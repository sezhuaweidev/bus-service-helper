package app.infy.util.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.infy.util.entity.ShuttleRequest;
import app.infy.util.service.ShuttleService;

@RestController
@RequestMapping("search")
public class SearchServiceController {

	@Autowired
	private ShuttleService shuttleService;
	
	@GetMapping(value="{type}/{value}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ShuttleRequest> getShuttleBy(@PathVariable("type")String type, @PathVariable("value")String value) {
		System.out.println("called with type:"+type+" value:"+value);
		return shuttleService.searchShuttleRequest(type, value);
	}
}
