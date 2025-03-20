package org.example.prepa_project2_exam.controller;

import org.example.prepa_project2_exam.dao.EmployeeDAO;
import org.example.prepa_project2_exam.dao.ProjectDAO;
import org.example.prepa_project2_exam.model.Employee;
import org.example.prepa_project2_exam.model.Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AffectationController implements Serializable {
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private Project project = new Project();
    private List<Long> selectedProjets = new ArrayList<>();
    private ProjectDAO projectDAO = new ProjectDAO();
    private int pourcentage;
    private Long employeeId;


    public List<Employee> getEmployees() {
        return employeeDAO.listerEmployees();
    }

    public List<Project> getAllProjects() {
        List<Project> projectList = projectDAO.listerProjects();
        return projectList;
    }

    public void removeEmployee(Long id) {
        employeeDAO.supprimerEmployee(id);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public List<Long> getSelectedProjets() {
        return selectedProjets;
    }

    public void setSelectedProjets(List<Long> selectedProjets) {
        this.selectedProjets = selectedProjets;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void affecterProject() {
        for (Long project : selectedProjets) {
            employeeDAO.assignProjectToEmployee(employeeId, project, pourcentage);
        }
    }

}