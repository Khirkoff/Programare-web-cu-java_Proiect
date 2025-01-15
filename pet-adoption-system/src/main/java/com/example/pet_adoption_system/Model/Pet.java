package com.example.pet_adoption_system.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "name" ,  nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Column(name = "breed" ,  nullable = false)
    private String breed;

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Column(name = "age" ,  nullable = false)
    private int age ;

    public void setAge(int age ) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Column(name = "status" ,  nullable = false)
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelter;

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "pet" , cascade = CascadeType.REMOVE , orphanRemoval = true)
    private List<AdoptionRequest> adoption = new ArrayList<>();

    public List<AdoptionRequest> getAdoption() {
        return adoption;
    }

    public void setAdoption(List<AdoptionRequest> adoption) {
        this.adoption = adoption;
    }

}
