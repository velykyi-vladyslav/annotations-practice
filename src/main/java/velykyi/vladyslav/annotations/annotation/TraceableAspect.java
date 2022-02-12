package velykyi.vladyslav.annotations.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class TraceableAspect {

    protected static final Logger logger = LoggerFactory.getLogger(TraceableAspect.class);

    /**
     * Method traces the requests and responses to a REST method in Spring Boot.
     * <p>{@link org.aspectj.lang.annotation.Around} annotation is used to defining logic that needs to be executed.</p>
     * <p>Inside the {@link org.aspectj.lang.annotation.Around} annotation is required to pass the annotation name
     * so that Spring knows that it needs to apply the logic around this annotation.</p>
     * <p>The method should accept a single parameter ProceedingJoinPoint and should return Object data type.</p>
     *
     * @param joinPoint gives access to the method.
     */
    @Around("@annotation(Traceable)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        //Getting input from the first parameter.
        Object input = joinPoint.getArgs()[0];

        logger.info("Input : {}", input);

        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];

        logger.info("Host : {}", servletRequest.getRemoteAddr());

        //Getting the response. This is where the original method is executed.
        Object result = joinPoint.proceed();
        String resultString = result + ": message from the annotation aspect";

        logger.info(resultString);

        return resultString;
    }
}
