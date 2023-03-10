package mx.com.basantader.AgenciaViajeTA.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.basantader.AgenciaViajeTA.repository.ApplicationRepository;
import mx.com.basantader.AgenciaViajeTA.dto.ApplicationEntry;
import mx.com.basantader.AgenciaViajeTA.model.ApplicationItem;
import mx.com.basantader.AgenciaViajeTA.service.ApplicationService;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;


/**
 * Created by 
 */
@Service
public class ApplicationServiceImpl implements  ApplicationService{
    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private ApplicationRepository applicationRepository;

    @Transactional
    public void createApplicationItem(ApplicationEntry request) {
    	ApplicationItem applicationItem = modelMapper.map(request, ApplicationItem.class);
        applicationRepository.save(applicationItem);
    }

    public List<ApplicationEntry> getApplicationItems() {
    	List<ApplicationEntry> lstReturn = applicationRepository.findAll().stream()
        .map(applicationItem -> modelMapper.map(applicationItem, ApplicationEntry.class)).collect(Collectors.toList());
      return lstReturn;
    }

}
