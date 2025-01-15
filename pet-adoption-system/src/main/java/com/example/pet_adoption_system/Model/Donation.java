package com.example.pet_adoption_system.Model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Donation")
public class Donation {
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


    @Column(name = "amount" ,  nullable = false)
    private int amount ;

    public void setAmount(int amount ) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Column(name = "date" ,  nullable = false)
    private Timestamp date;

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
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
}
