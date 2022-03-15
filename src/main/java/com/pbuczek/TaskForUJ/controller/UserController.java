package com.pbuczek.TaskForUJ.controller;

import com.pbuczek.TaskForUJ.dto.UserDTO;
import com.pbuczek.TaskForUJ.entity.ServiceEntity;
import com.pbuczek.TaskForUJ.entity.UserEntity;
import com.pbuczek.TaskForUJ.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/uzytkownik-dodaj")
    ResponseEntity<?> addNewUser(@RequestBody UserEntity source) {
        LOGGER.info("Create new user");
        return userService
                .createNewUser(source);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/dodaj-servis-do-uzytkownika/{idUser}")
    ResponseEntity<?> addAccessToServiceToUser(@PathVariable String idUser,
                                               @RequestBody ServiceEntity source) {
        LOGGER.info("Add access to the website to the user");
        return userService
                .addAccessToServiceToUser(source, idUser);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/autoryzuj/{urlService}")
    ResponseEntity<?> authorizationServiceToUser(@PathVariable String urlService,
                                                 @RequestBody UserDTO source) {
        LOGGER.info("Authorization Service To User");
        return userService
                .authorizationServiceToUser(source, urlService);
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/uzytkownik-zmiana-hasla")
    ResponseEntity<?> changePassword(@RequestBody UserDTO source) {
        LOGGER.info("Update user change password");
        return userService
                .changePassword(source);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/uzytkownik-odbierz-dostepu-do-serwisu/{idUser}")
    ResponseEntity<?> deleteAccessToServiceToUser(@PathVariable String idUser,
                                                  @RequestBody ServiceEntity source) {
        LOGGER.info("Delete access to the website to the user");
        return userService
                .deleteAccessToServiceToUser(source.getId(), idUser);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/uzytkownik-info/{id}")
    ResponseEntity<?> getUserInformationById(@PathVariable String id) {
        LOGGER.info("Find user by id: " + id);
        return userService
                .findUserById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/uzytkownik-autentykuj/{id}")
    ResponseEntity<?> getListLogbookOfUser(@PathVariable String id) {
        LOGGER.info("List service user: " + id);
        return userService
                .findLogbookUserById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/uzytkownik-uprawnienia/{id}")
    ResponseEntity<?> getListServiceOfUser(@PathVariable String id) {
        LOGGER.info("List service user: " + id);
        return userService
                .findServicesUserById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/uzytkownik-lista")
    ResponseEntity<?> getUsers() {
        LOGGER.info("Get all users");
        return userService
                .findAllUsers();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/autentykuj")
    ResponseEntity<?> identifyUser(@RequestBody UserDTO source) {
        LOGGER.info("identify user");
        return userService.identifyUserByLoginAndPsw(source);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/uzytkownik-zablokuj/{id}")
    ResponseEntity<?> lockedUser(@PathVariable String id) {
        LOGGER.info("User id: " + id + " locked");
        return userService
                .lockedUserById(id);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/uzytkownik-modyfikuj")
    ResponseEntity<?> updateUserByBody(@RequestBody UserEntity source) {
        LOGGER.info("Update user: " + source.getLogin());
        return userService
                .updateBodyUser(source);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/uzytkownik-odblokuj/{id}")
    ResponseEntity<?> unlockedUser(@PathVariable String id) {
        LOGGER.info("User id: " + id + " unlocked");
        return userService
                .unlockedUserById(id);
    }

}
