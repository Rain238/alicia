package com.pic.alicia.api.aop;

import com.pic.alicia.api.annotation.CurrentLimiting;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 限制IP每分钟请求次数
 */
@Aspect
@Component
public class CurrentLimitingAspect {

    private static ConcurrentHashMap<String, ExpiringMap<String, Integer>> map = new ConcurrentHashMap<>();

    /**
     * 层切点
     */
    @Pointcut("@annotation(currentLimiting)")
    public void controllerAspect(CurrentLimiting currentLimiting) {
    }

    @Around(value = "controllerAspect(currentLimiting)", argNames = "pjp,currentLimiting")
    public ModelAndView doAround(ProceedingJoinPoint pjp, CurrentLimiting currentLimiting) throws Throwable {
        // 获得request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        // 获取Map value对象， 如果没有则返回默认值
        // //getOrDefault获取参数，获取不到则给默认值
        ExpiringMap<String, Integer> em = map.getOrDefault(request.getRequestURI(), ExpiringMap.builder().variableExpiration().build());
        Integer Count = em.getOrDefault(request.getRemoteAddr(), 0);
        if (Count >= currentLimiting.value()) { // 超过次数，不执行目标方法
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/limiting.html");
            return modelAndView;
        } else if (Count == 0) { // 第一次请求时，设置有效时间
            em.put(request.getRemoteAddr(), Count + 1, ExpirationPolicy.CREATED, currentLimiting.time(), TimeUnit.MILLISECONDS);
        } else { // 未超过次数， 记录加一
            em.put(request.getRemoteAddr(), Count + 1);
        }
        map.put(request.getRequestURI(), em);
        // result的值就是被拦截方法的返回值
        return (ModelAndView) pjp.proceed();
    }
}