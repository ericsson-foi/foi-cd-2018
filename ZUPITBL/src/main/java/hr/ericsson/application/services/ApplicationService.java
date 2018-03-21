package hr.ericsson.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hr.ericsson.application.dao.ApplicationDAO;
import hr.ericsson.application.domain.Application;

@Component
public class ApplicationService {
	@Autowired
	private ApplicationDAO applicationDAO;

	public List<Application> getApplications() {
		return applicationDAO.getAll();
	}

	@Transactional
	public Application saveSampleType(final Application application) {
		if (application.getId() == null) {
			applicationDAO.insert(application);
		} else {
			applicationDAO.update(application);
		}
		return application;
	}


	

}
