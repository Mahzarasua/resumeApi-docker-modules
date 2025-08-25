package dev.mhzars.projects.commons.resumeapidockercompose.advice;

import static dev.mhzars.projects.commons.resumeapidockercompose.mapper.CommonCustomMapper.COMMON_MAPPER;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    // Define a pointcut that matches the broader scope of the second class,
    // but adapted for the common module's base package.
    // This pointcut targets methods in any project's 'resumeapidockercompose' modules,
    // excluding config packages and a specific exception handler.
    @Pointcut(
            value =
                    "execution(* dev.mhzars.projects.*.resumeapidockercompose..*.*(..) ) "
                            + // Targets all methods in any resumeapidockercompose module and its
                            // sub-packages
                            "&& !execution(* dev.mhzars.projects.*.resumeapidockercompose.config.*.*(..) ) "
                            + // Excludes config packages
                            "&& !execution(* dev.mhzars.projects.commons.resumeapidockercompose.config.*.*(..) ) "
                            + // Explicitly exclude common config
                            // Adjust this line if CustomExceptionHandler is specific to postgres or
                            // mongo,
                            // or if it's a common class. For now, assuming it could be in either.
                            "&& !execution(* dev.mhzars.projects.*.resumeapidockercompose.controller.CustomExceptionHandler.*(..) )")
    public void applicationLoggingPointcut() {}

    @Around("applicationLoggingPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        // Use getTarget().getClass().getSimpleName() for cleaner class names in logs,
        // or getTarget().getClass().toString().replace("class", "") if you prefer the full class
        // name.
        String className = pjp.getTarget().getClass().getSimpleName();
        Object[] args = pjp.getArgs();

        try {
            log.info(
                    "Entering {}.{}(): Args: {}",
                    className,
                    methodName,
                    COMMON_MAPPER.writeValueAsString(args) // Serialize arguments
                    );

            Object result = pjp.proceed(); // Execute the original method

            log.info(
                    "Exiting {}.{}(): Response: {}",
                    className,
                    methodName,
                    COMMON_MAPPER.writeValueAsString(result) // Serialize result
                    );
            return result;
        } catch (Throwable ex) {
            log.error(
                    "Exception in {}.{}(): Message: {}",
                    className,
                    methodName,
                    ex.getMessage(), // Log the exception message
                    ex // Include the full stack trace
                    );
            throw ex; // Re-throw the exception so it propagates correctly
        }
    }
}
