package com.example.intentolab3.dto;

import java.time.LocalDateTime;

public interface EmpleadosSalarioMayorDto {

    String getFirstName();
    String getLastName();
    String getJobTitle();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();

}
