package com.pbuczek.TaskForUJ.controller;

import com.pbuczek.TaskForUJ.entity.ServiceEntity;
import com.pbuczek.TaskForUJ.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServiceController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);
    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/serwis-dodaj")
    ResponseEntity<?> addNewService(@RequestBody ServiceEntity source) {
        LOGGER.info("Create new service in db");
        return service
                .createNewService(source);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/serwis-usun/{id}")
    ResponseEntity<?> deleteServiceById(@PathVariable String id) {
        LOGGER.warn("Delete service by id: " + id);
        return service
                .deleteServiceById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/serwis-lista")
    ResponseEntity<?> getAllService() {
        LOGGER.warn("Exposing all service");
        return service
                .findAllServices();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/serwis-modyfikuj")
    ResponseEntity<?> updateService(@RequestBody ServiceEntity source) {
        LOGGER.info("Edit service" + source.getId());
        return service
                .updateService(source);
    }


}
