package org.example.prepa_project2_exam.controller;

import org.example.prepa_project2_exam.dao.EmployeeDAO;
import org.example.prepa_project2_exam.model.Employee;
import org.example.prepa_project2_exam.model.Post;

import java.io.Serializable;

public class EmployeeController implements Serializable {
    private Employee employee = new Employee();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    public void save() {
        employeeDAO.ajouterEmployee(employee);
        employee = new Employee();
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Post[] getListposts() {
        return Post.values();
    }

}
