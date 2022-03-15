package com.pbuczek.TaskForUJ.adapter;

import com.pbuczek.TaskForUJ.entity.LogbookEntity;
import com.pbuczek.TaskForUJ.repository.LogbookRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlLogbookRepository extends LogbookRepository, JpaRepository<LogbookEntity,String> {
}
