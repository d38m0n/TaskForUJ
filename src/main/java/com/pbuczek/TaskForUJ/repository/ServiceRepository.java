package com.pbuczek.TaskForUJ.repository;

import com.pbuczek.TaskForUJ.entity.ServiceEntity;
import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    Optional<ServiceEntity> findById(String id);

    ServiceEntity save(ServiceEntity se);

    void deleteById(String id);

    boolean existsById(String id);

    List<ServiceEntity> findAll();
}
