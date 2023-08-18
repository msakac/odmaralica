package org.foi.diplomski.msakac.odmaralica.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "log")
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_type_id", nullable = false)
    private ActivityType activityType;

    @Column(name = "log_message", nullable = false)
    private String logMessage;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "http_method", nullable = false)
    private String httpMethod;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "status_code", nullable = false)
    private String statusCode;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "response_time", nullable = false)
    private String responseTime;
}
