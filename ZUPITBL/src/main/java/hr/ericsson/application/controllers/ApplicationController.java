package hr.ericsson.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.ericsson.application.domain.Application;
import hr.ericsson.application.services.ApplicationService;

@RestController
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;
	
	
	
	@RequestMapping("/application/getAll")
	public List<Application> getSampleTypes() {
		return applicationService.getApplications();
	}
	
	@RequestMapping(value = "/application/delete", method = RequestMethod.POST)
	public String delete(@RequestBody Application application) {
		applicationService.deleteApplication(application);
		return "{\"message\" : \"OK\"}";
	}

	@RequestMapping(value = "/application/save", method = RequestMethod.POST)
	public Application save(@RequestBody Application application) {
		return applicationService.saveSampleType(application);
	}


}
