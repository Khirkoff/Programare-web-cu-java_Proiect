package com.example.pet_adoption_system.Controller;

import com.example.pet_adoption_system.RequestBody.AdoptionRequestCreateBody;
import com.example.pet_adoption_system.RequestBody.AdoptionRequestUpdateBody;
import com.example.pet_adoption_system.Model.AdoptionRequest;
import com.example.pet_adoption_system.Service.AdoptionRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/adoption")
@Tag(name = "Adoption", description = "Create adoption requests, view and update them if you are an admin.")
public class AdoptionRequestController {
    private final AdoptionRequestService adoptionRequestService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @PostMapping("/create_requests")
    @Operation(summary = "Create an adoption request.")
    public ResponseEntity<AdoptionRequest> createRequest(@RequestBody AdoptionRequestCreateBody constraint) {
        try {
            AdoptionRequest request = adoptionRequestService.createRequest(constraint);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update_requests")
    @Operation(summary = "Update an adoption request.")
    public ResponseEntity<AdoptionRequest> updateUserRequests(@RequestBody AdoptionRequestUpdateBody constraint){
        try{
            AdoptionRequest request=adoptionRequestService.updateRequestStatus(constraint);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/view_requests")
    @Operation(summary = "View all the adoption requests.")
    public ResponseEntity<List<AdoptionRequest>> getUserRequests() {
        try {
            List<AdoptionRequest> requests = adoptionRequestService.getAllAdoptions();
            return ResponseEntity.ok(requests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}