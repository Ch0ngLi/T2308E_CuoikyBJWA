package com.t2308e.salarymanagementsystem.controller;

import com.t2308e.salarymanagementsystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.t2308e.salarymanagementsystem.service.EmployeeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Employee> list = (keyword != null) ? service.search(keyword) : service.getAll();
        model.addAttribute("listEmployees", list);
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee, RedirectAttributes redirect) {
        try {
            service.save(employee);
            redirect.addFlashAttribute("success", "User created/updated successfully");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Employee emp = service.getById(id);
        model.addAttribute("employee", emp);
        model.addAttribute("listEmployees", service.getAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}