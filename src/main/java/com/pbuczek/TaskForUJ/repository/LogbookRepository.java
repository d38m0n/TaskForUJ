package com.pbuczek.TaskForUJ.repository;

import com.pbuczek.TaskForUJ.entity.LogbookEntity;

import java.util.List;

public interface LogbookRepository {
    LogbookEntity save(LogbookEntity le);
    List<LogbookEntity> findAll();
}
