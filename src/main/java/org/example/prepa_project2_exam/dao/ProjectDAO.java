package org.example.prepa_project2_exam.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.prepa_project2_exam.model.EmployeeProject;
import org.example.prepa_project2_exam.model.Project;

import java.util.List;

public class ProjectDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mypresistence");

    private EntityManager em;

    public ProjectDAO() {
        em = emf.createEntityManager();
    }
    public List<Project> listerProjects() {
        return em.createQuery("select p from Project p", Project.class).getResultList();
    }
    public List<EmployeeProject> getProjectEmployees(Long projectId) {
        return em.createQuery(
                "SELECT ep FROM EmployeeProject ep WHERE ep.project.id = :projectId",
                EmployeeProject.class
        ).setParameter("projectId", projectId).getResultList();
    }

}
