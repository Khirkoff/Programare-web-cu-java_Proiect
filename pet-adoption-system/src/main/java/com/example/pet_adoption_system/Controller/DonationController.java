package com.example.pet_adoption_system.Controller;

import com.example.pet_adoption_system.Constants.CurrentUser;
import com.example.pet_adoption_system.RequestBody.DonationBody;
import com.example.pet_adoption_system.Model.Donation;
import com.example.pet_adoption_system.Service.DonationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
@Tag(name = "Donation", description = "Create a donation and view the ones that you've made.")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/create")
    @Operation(summary = "Make a donation.")
    public ResponseEntity<Donation> createDonation(@RequestBody DonationBody donationBody) {
        try {
            Donation donation = donationService.createDonation(donationBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(donation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/view")
    @Operation(summary = "View the donations you've made.")
    public ResponseEntity<List<Donation>> getUserDonations() {
        try {
            List<Donation> donations = donationService.getUserDonations(CurrentUser.getId());
            return ResponseEntity.ok(donations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
