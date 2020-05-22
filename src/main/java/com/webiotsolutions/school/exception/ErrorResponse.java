package com.webiotsolutions.school.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@AllArgsConstructor
@Data
public class ErrorResponse
{
    private String message;
    private List<String> details;
}