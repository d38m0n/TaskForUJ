package com.pbuczek.TaskForUJ.service;

import com.pbuczek.TaskForUJ.entity.ServiceEntity;
import com.pbuczek.TaskForUJ.repository.ServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {
    private ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ResponseEntity<?> createNewService(ServiceEntity source) {
        try {
            serviceRepository.save(new ServiceEntity().setUrl(source.getUrl()));
            return ResponseEntity
                    .ok()
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

    public ResponseEntity<?> deleteServiceById(String idService) {
        try {
            serviceRepository.deleteById(idService);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

    public ResponseEntity<?> findAllServices() {
        try {
            return ResponseEntity
                    .ok(serviceRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

    public ResponseEntity<?> updateService(ServiceEntity source) {
        if (!serviceRepository.existsById(source.getId())) return ResponseEntity.badRequest().build();

        try {
            serviceRepository.findById(source.getId())
                    .ifPresent(se -> {
                        se.updateFrom(source);
                        serviceRepository.save(se);
                    });

            return ResponseEntity
                    .ok()
                    .build();

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

}
