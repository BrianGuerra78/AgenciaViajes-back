package mx.com.basantader.AgenciaViajeTA.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTA.dto.ApplicationEntry;

public interface ApplicationService {
	
	public void createApplicationItem(ApplicationEntry request);

    public List<ApplicationEntry> getApplicationItems();

}
