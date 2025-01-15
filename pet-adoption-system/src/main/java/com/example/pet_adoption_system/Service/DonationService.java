package com.example.pet_adoption_system.Service;

import com.example.pet_adoption_system.Constants.CurrentUser;
import com.example.pet_adoption_system.RequestBody.DonationBody;
import com.example.pet_adoption_system.Model.Donation;
import com.example.pet_adoption_system.Model.User;
import com.example.pet_adoption_system.Repository.DonationRepository;
import com.example.pet_adoption_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }

    public Donation createDonation(DonationBody donationBody) {
        User user = userRepository.findById(CurrentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        Donation donation = new Donation();
        donation.setUser(user);
        donation.setAmount(donationBody.getAmount());
        donation.setDate(Timestamp.valueOf(LocalDateTime.now()));

        return donationRepository.save(donation);
    }

    public List<Donation> getUserDonations(Long userId) {
        return donationRepository.findByUserId(CurrentUser.getId());
    }
}