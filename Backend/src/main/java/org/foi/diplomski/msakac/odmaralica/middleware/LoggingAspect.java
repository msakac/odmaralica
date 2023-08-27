package org.foi.diplomski.msakac.odmaralica.middleware;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ActivityTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ActivityTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.LogPostDTO;
import org.foi.diplomski.msakac.odmaralica.model.ActivityType;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ActivityTypeServiceImpl;
import org.foi.diplomski.msakac.odmaralica.service.implementation.LogServiceImpl;
import org.foi.diplomski.msakac.odmaralica.utils.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private ActivityTypeServiceImpl activityTypeService;

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
        " || within(@org.springframework.stereotype.Service *)" +
        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {}

   @Pointcut("within(org.foi.diplomski.msakac.odmaralica.controller..*)")
    public void applicationPackagePointcut() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
        " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
        " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
        " || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void requestMappingMethods() {}

    @AfterThrowing(pointcut = "applicationPackagePointcut() && (springBeanPointcut() || requestMappingMethods())", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    @Around("applicationPackagePointcut() && (springBeanPointcut() || requestMappingMethods())")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        try {
            ResponseEntity<CreateResponseDTO<Object>> result = (ResponseEntity<CreateResponseDTO<Object>>) joinPoint.proceed();
            logData(startTime, className, methodName, result);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                className, methodName);
            throw e;
        }
    }

    private void logData(long startTime, String className, String methodName, ResponseEntity<CreateResponseDTO<Object>> result) {

        long duration = (System.currentTimeMillis() - startTime);
        String httpMethod = request.getMethod();
        String endpoint = request.getRequestURI();
        if(endpoint.contains("image")) {
            return;
        }
        String activityType = getActivityType(httpMethod, endpoint);
        if (request != null) {
            String requestInfo = String.format("Req | Method: %s, URI: %s, IP: %s, Class: %s, ClassMethod: %s",
                    httpMethod, endpoint, request.getRemoteAddr(),
                    className, methodName);

            CreateResponseDTO<Object> body = (CreateResponseDTO<Object>) result.getBody();
            if (response != null && body != null) {
                String code = result.getStatusCode().toString();
                String resMessage = body.getMessage();
                if (resMessage == null) {
                    resMessage = "Request successful";
                }
                String responseInfo = String.format("Res | Status: %s, Time: %d ms, Message: %s\n",
                        code, duration, resMessage);

                log.info(requestInfo);
                log.info(responseInfo);

                LogPostDTO logPostDTO = LogPostDTO.builder()
                    .userId(SecurityConstants.getAuthenticatedUserId())
                    .activityTypeId(getActivityTypeId(activityType))
                    .logMessage(resMessage)
                    .httpMethod(httpMethod)
                    .endpoint(endpoint)
                    .statusCode(String.valueOf(code))
                    .ipAddress(request.getRemoteAddr())
                    .responseTime(String.valueOf(duration) + " ms")
                    .createdAt(Timestamp.from(new Date().toInstant()))
                    .build();
                logService.create(logPostDTO);
            }
        }
    }

    private String getActivityType(String httpMethod, String endpoint) {
        if (httpMethod.equals("POST")) {
            if (endpoint.equals("/auth/login") || endpoint.equals("/auth/refresh")) {
                return "Authentication";
            } else if (endpoint.equals("/auth/register")) {
                return "Registration";
            } else if (endpoint.equals("/auth/logout")) {
                return "Logout";
            } else{
                return "Create";
            }
        } else if (httpMethod.equals("GET")) {
            return "Read";
        } else if (httpMethod.equals("PUT")) {
            return "Update";
        } else if (httpMethod.equals("DELETE")) {
            return "Delete";
        }
        return "Unknown";
    }

    private Long getActivityTypeId(String activityTypeName) {
        ActivityType activityType = activityTypeService.findByName(activityTypeName);
        if (activityType == null) {
             ActivityTypePostDTO activityTypePost = ActivityTypePostDTO.builder().name(activityTypeName).build();
            ActivityTypeGetDTO newActivityType = activityTypeService.create(activityTypePost);
            return newActivityType.getId();
        }
        return activityType.getId();
    }

}
