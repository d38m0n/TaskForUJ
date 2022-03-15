package com.pbuczek.TaskForUJ.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logbook")
public class LogbookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String log;

    public String getLoginTime() {
        return log;
    }

    public LogbookEntity() {
        this.log = LocalDateTime.now().toString();
    }
}
