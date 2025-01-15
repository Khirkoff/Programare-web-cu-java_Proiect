package com.example.pet_adoption_system.Model;
import com.example.pet_adoption_system.Constants.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
public class User {

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
    //
    @Column(name = "username" ,  nullable = false, unique = true)
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Column(name = "email" ,  nullable = false, unique = true, length = 255)
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Column(name = "password" ,  nullable = false, length = 255)
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Column(name = "role" ,  nullable = false, length = 255)
    private String role;

    public void setRole(String role) {
        if (!UserRole.isValid(role)) {
            throw new IllegalArgumentException("Invalid role");
        }
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE , orphanRemoval = true)
    private List<AdoptionRequest> adoption = new ArrayList<>();

    public List<AdoptionRequest> getAdoption() {
        return adoption;
    }

    public void setAdoption(List<AdoptionRequest> adoption) {
        this.adoption = adoption;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE , orphanRemoval = true)
    private List<Donation> donation = new ArrayList<>();

    public List<Donation> getDonation() {
        return donation;
    }

    public void setDonation(List<Donation> donation) {
        this.donation = donation;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE , orphanRemoval = true)
    private List<com.example.pet_adoption_system.Model.Message> message = new ArrayList<>();

    public List<com.example.pet_adoption_system.Model.Message> message() {
        return message;
    }

}
