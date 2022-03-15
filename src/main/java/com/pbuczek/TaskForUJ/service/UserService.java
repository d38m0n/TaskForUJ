package com.pbuczek.TaskForUJ.service;

import com.pbuczek.TaskForUJ.dto.UserDTO;
import com.pbuczek.TaskForUJ.entity.LogbookEntity;
import com.pbuczek.TaskForUJ.entity.ServiceEntity;
import com.pbuczek.TaskForUJ.entity.UserEntity;
import com.pbuczek.TaskForUJ.repository.LogbookRepository;
import com.pbuczek.TaskForUJ.repository.ServiceRepository;
import com.pbuczek.TaskForUJ.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private ServiceRepository serviceRepository;
    private LogbookRepository logbookRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       ServiceRepository serviceRepository,
                       PasswordEncoder passwordEncoder,
                       LogbookRepository logbookRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.passwordEncoder = passwordEncoder;
        this.logbookRepository = logbookRepository;
    }

    public ResponseEntity<?> createNewUser(UserEntity source) {
        if (userRepository.existsByLogin(source.getLogin())) {
            return ResponseEntity
                    .status(409)
                    .build();
        } else {
            try {
                source.setPassword(passwordEncoder.encode(source.getPassword()));
                userRepository.save(source);
                return ResponseEntity
                        .status(200)
                        .build();
            } catch (Exception e) {
                return ResponseEntity
                        .status(401)
                        .build();
            }
        }
    }

    public ResponseEntity<?> findUserById(String id) {
        try {
            return ResponseEntity
                    .ok(userRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    public ResponseEntity<?> identifyUserByLoginAndPsw(UserDTO source) {
        if (!userRepository.existsByLogin(source.getLogin())) return ResponseEntity.badRequest().build();
        try {
            UserEntity userEntity = userRepository.findByLogin(source.getLogin())
                    .orElseThrow();

            if (userEntity.authorize(source, passwordEncoder)) {
                userEntity.addAuthorizationDate(logbookRepository.save(new LogbookEntity()));
                userRepository.save(userEntity);
                return ResponseEntity
                        .ok()
                        .build();
            } else {
                return ResponseEntity
                        .badRequest()
                        .build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

    public ResponseEntity<?> updateBodyUser(UserEntity source) {
        if (userRepository.existsById(source.getId())) {
            userRepository
                    .findById(source.getId())
                    .ifPresent(user -> {
                        user.updateFrom(source);
                        userRepository.save(user);
                    });
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity
                .ok(userRepository.findAll());

    }

    public ResponseEntity<?> unlockedUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository
                    .findById(id)
                    .ifPresent(user -> {
                        user.unlocked();
                        userRepository.save(user);
                    });
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    public ResponseEntity<?> lockedUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository
                    .findById(id)
                    .ifPresent(user -> {
                        user.locked();
                        userRepository.save(user);
                    });
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    public ResponseEntity<?> addAccessToServiceToUser(ServiceEntity source, String idUser) {
        if (userRepository.existsById(idUser)) {
            userRepository
                    .findById(idUser)
                    .ifPresent(user -> {
                        if (source.getId() == null) {
                            user.addServiceAccess(serviceRepository.save(source));
                        } else {
                            user.addServiceAccess(source);
                        }
                        userRepository.save(user);
                    });
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    public ResponseEntity<?> deleteAccessToServiceToUser(String idService, String idUser) {
        if (userRepository.existsById(idUser) && serviceRepository.existsById(idService)) {
            ServiceEntity serviceEntity = serviceRepository.findById(idService).orElseThrow();
            UserEntity ue = userRepository
                    .findById(idUser)
                    .orElseThrow()
                    .deleteServiceAccess(serviceEntity);
            userRepository.save(ue);

            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    public ResponseEntity<?> changePassword(UserDTO source) {
        if (checkNoCorrectSource(source)) return ResponseEntity.badRequest().build();

        AtomicBoolean result = new AtomicBoolean(false);
        userRepository.findById(source.getId())
                .ifPresent(ue -> {
                    result.set(ue.changePassword(source, passwordEncoder));
                    userRepository.save(ue);
                });
        if (result.get()) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean checkNoCorrectSource(UserDTO source) {
        if (!userRepository.existsByLogin(source.getLogin())) return true;
        if (source.getId() == null) return true;
        return false;
    }

    public ResponseEntity<?> findServicesUserById(String id) {
        if (userRepository.existsById(id)) {
            return ResponseEntity
                    .ok(userRepository.findById(id)
                            .orElseThrow()
                            .getService()
                            .stream()
                            .sorted(Comparator.comparing(ServiceEntity::getUrl))
                            .collect(Collectors.toList()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> findLogbookUserById(String id) {
        if (userRepository.existsById(id)) {
            return ResponseEntity
                    .ok(userRepository.findById(id)
                            .orElseThrow()
                            .getLogbook()
                            .stream()
                            .sorted(Comparator.comparing(LogbookEntity::getLoginTime))
                            .collect(Collectors.toList()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> authorizationServiceToUser(UserDTO source, String urlService) {
        if (!userRepository.existsByLogin(source.getLogin())) return ResponseEntity.badRequest().build();
        try {
            UserEntity userEntity = userRepository.findByLogin(source.getLogin())
                    .orElseThrow();

            if (userEntity.authorize(source, passwordEncoder) &&
                    userEntity.findService(urlService) &&
                    userEntity.isActive()) {
                userEntity.addAuthorizationDate(logbookRepository.save(new LogbookEntity()));
                userRepository.save(userEntity);
                return ResponseEntity
                        .ok()
                        .build();
            } else {
                return ResponseEntity
                        .badRequest()
                        .build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }
}




