package com.example.pet_adoption_system.Model;

import com.example.pet_adoption_system.Constants.AdoptionStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="AdoptionRequest")

public class AdoptionRequest {

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


    @Column(name = "status" ,  nullable = false)
    private String status;

    public void setStatus(String status) {
        if (!AdoptionStatus.isValid(status)) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Column(name = "request_date" ,  nullable = false)
    private LocalDateTime request_date;

    public void setRequest_date(LocalDateTime request_date) {
        this.request_date = request_date;
    }

    public LocalDateTime getRequest_date() {
        return request_date;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setRequestDate(LocalDateTime now) {
    }
}
