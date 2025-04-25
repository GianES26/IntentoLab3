package com.example.intentolab3.controller;

import com.example.intentolab3.entity.Employees;
import com.example.intentolab3.repository.DepartmentsRepository;
import com.example.intentolab3.repository.EmployeesRepository;
import com.example.intentolab3.repository.JobsRepository;
import com.example.intentolab3.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    private LocationsRepository locationsRepository;

    @GetMapping("")
    public String listarEmpleados(Model model) {
        List<Employees> empleados = employeesRepository.findAll();

        model.addAttribute("listaEmpleados", empleados);
        return "Employee/list";
    }

    @PostMapping("/buscar")
    public String buscarEmpleado( @RequestParam(name = "searchField", required = false) String searchField, Model model ) {

        //List<Employees> empleadosFiltrados = employeesRepository.searchEmployees(searchField);
        //List<Employees> empleadosFiltrados = employeesRepository.findByDepartment_DepartmentNameAndFirstNameContainingIgnoreCase(searchField, searchField);
        List<Employees> empleadosFiltrados = employeesRepository.findByDepartment_DepartmentNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(searchField, searchField);

        model.addAttribute("listaEmpleados", empleadosFiltrados);

        return "Employee/list";
    }

    /*
    @GetMapping("/editar")
    public String editarEmpleado(@RequestParam("id") Integer id, Model model) {
        Optional<Employees> empleadoOpt = employeesRepository.findById(id);
        if (empleadoOpt.isPresent()) {
            Employees empleado = empleadoOpt.get();
            model.addAttribute("empleado", empleado);

            // Estandarizado
            model.addAttribute("jobs", jobsRepository.findAll());
            model.addAttribute("departments", departmentsRepository.findAll());
            model.addAttribute("managers", departmentsRepository.findDistinctManagers());

            return "Employee/editForm";
        } else {
            return "redirect:/employee";
        }
    }
    */


    @GetMapping("/editar")
    public String mostrarFormularioUbicacion(@RequestParam("id") Integer id, Model model) {
        Employees empleado = employeesRepository.findById(id).orElseThrow();
        model.addAttribute("empleado", empleado);
        return "Employee/editForm";
    }

    @PostMapping("/actualizarUbicacion")
    public String actualizarUbicacion(
            @RequestParam("postalCode") String postalCode,
            @RequestParam("city") String city,
            @RequestParam("locationId") int locationId) {
        locationsRepository.actualizarLocationEmpleado(postalCode, city, locationId);
        return "redirect:/employee";
    }



    /*
    @GetMapping("")
    public String listarEmpleados(Model model) {
        List<Employees> empleados = employeesRepository.findAll();

        model.addAttribute("listaEmpleados", empleados);
        return "Employee/list";
    }

    @PostMapping("/buscar")
    public String buscarEmpleado( @RequestParam(name = "searchField", required = false) String searchField, Model model ) {

        //List<Employees> empleadosFiltrados = employeesRepository.searchEmployees(searchField);
        List<Employees> empleadosFiltrados = employeesRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrJob_JobTitleContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCaseOrDepartment_Location_CityContainingIgnoreCase(searchField, searchField, searchField, searchField, searchField);

        model.addAttribute("listaEmpleados", empleadosFiltrados);

        return "Employee/list";
    }

    @GetMapping("/nuevo")
    public String nuevoEmpleado(Model model) {
        model.addAttribute("empleado", new Employees());

        // Estandarizado
        model.addAttribute("jobs", jobsRepository.findAll());
        model.addAttribute("departments", departmentsRepository.findAll());
        model.addAttribute("managers", departmentsRepository.findDistinctManagers());

        return "Employee/form";
    }

    @GetMapping("/editar")
    public String editarEmpleado(@RequestParam("id") Integer id, Model model) {
        Optional<Employees> empleadoOpt = employeesRepository.findById(id);
        if (empleadoOpt.isPresent()) {
            Employees empleado = empleadoOpt.get();
            model.addAttribute("empleado", empleado);

            // Estandarizado
            model.addAttribute("jobs", jobsRepository.findAll());
            model.addAttribute("departments", departmentsRepository.findAll());
            model.addAttribute("managers", departmentsRepository.findDistinctManagers());

            return "Employee/editForm";
        } else {
            return "redirect:/employee";
        }
    }


    @PostMapping("/guardar")
    public String guardarEmpleado(Employees empleado) {
        // Creamos el hire_date, es un parametro not null de la tabla employee
        if (empleado.getHireDate() == null) {
            empleado.setHireDate(LocalDateTime.now());
        }
        employeesRepository.save(empleado);
        return "redirect:/employee";

    }

    @GetMapping("/borrar")
    public String borrarEmpleado(@RequestParam("id") Integer id) {
        employeesRepository.deleteById(id);
        return "redirect:/employee";
    }
    */
}
