package org.example.prepa_project2_exam.model;


import jakarta.persistence.*;

@Entity
@Table(name= "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double budget;





    public Project(Double budget, String nom) {
        this.budget = budget;
        this.nom = nom;
    }
    public Project() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }



}
