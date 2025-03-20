package org.example.prepa_project2_exam.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.prepa_project2_exam.model.Employee;
import org.example.prepa_project2_exam.model.EmployeeProject;
import org.example.prepa_project2_exam.model.Project;

import java.util.List;

public class EmployeeDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mypresistence");

    private EntityManager em;

    public EmployeeDAO() {
        em = emf.createEntityManager();
    }
    public void ajouterEmployee(Employee et) {
        em.getTransaction().begin();
        em.persist(et);
        em.getTransaction().commit();
    }
    public void supprimerEmployee(Long id) {
        em.getTransaction().begin();
        Employee et = em.find(Employee.class, id);
        if (et != null) {
            em.remove(et);
        }else{
            System.out.println("Le Employee n'existe pas");
        }
        em.getTransaction().commit();
    }
    public List<Employee> listerEmployees() {
        return em.createQuery(
                        "SELECT DISTINCT e FROM Employee e " +
                                "LEFT JOIN FETCH e.employeeProjects ep " +
                                "LEFT JOIN FETCH ep.project p "+
                        "LEFT JOIN FETCH e.skills s" , Employee.class)
                .getResultList();
    }
    public void assignProjectToEmployee(Long employeeId, Long projectId, double percentage) {
        em.getTransaction().begin();
        try {
            // Check if the employee already has this project assigned
            TypedQuery<EmployeeProject> query = em.createQuery(
                    "SELECT ep FROM EmployeeProject ep WHERE ep.employee.id = :employeeId AND ep.project.id = :projectId",
                    EmployeeProject.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("projectId", projectId);

            List<EmployeeProject> results = query.getResultList();

            if (!results.isEmpty()) {
                // If the relation exists, update percentage
                EmployeeProject existingRelation = results.getFirst();
                existingRelation.setPercentage(percentage);
                EmployeeProject employeeUpdated = em.merge(existingRelation);  // Update instead of inserting a duplicate
                System.out.println(employeeUpdated);

            } else {
                // Otherwise, create a new relation
                EmployeeProject newRelation = new EmployeeProject();
                Employee employee = em.find(Employee.class, employeeId);
                Project project = em.find(Project.class, projectId);
                newRelation.setEmployee(employee);
                newRelation.setProject(project);
                newRelation.setPercentage(percentage);
                em.persist(newRelation);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
