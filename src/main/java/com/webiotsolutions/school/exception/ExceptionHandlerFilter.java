package com.webiotsolutions.school.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(0)
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ExpiredJwtException e){
            List<String> details = new ArrayList<>();
            details.add(e.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse("Session Expired",details);
            httpServletResponse.getWriter().write(convertToJson(error));
            throw e;
        }
        catch (RuntimeException e) {

            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    public String convertToJson(Object object) throws JsonProcessingException {
        if (this == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString((ErrorResponse)object);
    }
}
