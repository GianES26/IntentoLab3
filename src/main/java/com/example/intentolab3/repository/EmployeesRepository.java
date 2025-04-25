package com.example.intentolab3.repository;

import com.example.intentolab3.dto.EmpleadosSalarioMayorDto;
import com.example.intentolab3.dto.GerenteConExperienciaDto;
import com.example.intentolab3.dto.MayorTresEmpleadosDto;
import com.example.intentolab3.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    List<Employees> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrJob_JobTitleContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCaseOrDepartment_Location_CityContainingIgnoreCase(String search1, String search2, String search3, String search4, String search5);


    @Query(value = """
        SELECT 
            e.first_name AS firstName,
            e.last_name AS lastName,
            j.job_title AS jobTitle,
            jh.start_date AS startDate,
            jh.end_date AS endDate
        FROM 
            employees e
            JOIN job_history jh ON e.employee_id = jh.employee_id
            JOIN jobs j ON jh.job_id = j.job_id
        WHERE 
            e.salary > 15000
        """, nativeQuery = true)
    List<EmpleadosSalarioMayorDto> obtenerHistorialDeEmpleadosConSalarioMayorA15000();


    @Query(value = """
        SELECT 
            c.country_name AS pais,
            l.city AS ciudad,
            COUNT(*) AS cantidad
        FROM departments d
        JOIN locations l ON d.location_id = l.location_id
        JOIN countries c ON l.country_id = c.country_id
        WHERE d.department_id IN (
            SELECT department_id
            FROM employees
            GROUP BY department_id
            HAVING COUNT(employee_id) > 3
        )
        GROUP BY c.country_name, l.city
    """, nativeQuery = true)
    List<MayorTresEmpleadosDto> obtenerDepartamentosConMasDeTresEmpleados();

    @Query(value = """
    SELECT 
        d.department_name AS nombreDepartamento,
        e.first_name AS nombreGerente,
        e.last_name AS apellidoGerente,
        e.salary AS salarioGerente
    FROM 
        departments d
    JOIN 
        employees e ON d.manager_id = e.employee_id
    WHERE 
        TIMESTAMPDIFF(YEAR, e.hire_date, CURDATE()) > 5
    """, nativeQuery = true)
    List<GerenteConExperienciaDto> obtenerGerentesConMasDeCincoAniosExperiencia();


    //Lab3
    List<Employees> findByDepartment_DepartmentNameAndFirstNameContainingIgnoreCase(String search1, String search2);

    List<Employees> findByDepartment_DepartmentNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(String search1, String search2);


}
