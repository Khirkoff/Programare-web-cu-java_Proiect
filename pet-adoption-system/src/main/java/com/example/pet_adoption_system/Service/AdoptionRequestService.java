package com.example.pet_adoption_system.Service;

import com.example.pet_adoption_system.Constants.AdoptionStatus;
import com.example.pet_adoption_system.Constants.CurrentUser;
import com.example.pet_adoption_system.Constants.UserRole;
import com.example.pet_adoption_system.RequestBody.AdoptionRequestCreateBody;
import com.example.pet_adoption_system.RequestBody.AdoptionRequestUpdateBody;
import com.example.pet_adoption_system.Model.AdoptionRequest;
import com.example.pet_adoption_system.Model.Pet;
import com.example.pet_adoption_system.Model.User;
import com.example.pet_adoption_system.Repository.AdoptionRequestRepository;
import com.example.pet_adoption_system.Repository.PetRepository;
import com.example.pet_adoption_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRequestService {
    private final AdoptionRequestRepository adoptionRequestRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Autowired
    public AdoptionRequestService(
            AdoptionRequestRepository adoptionRequestRepository,
            UserRepository userRepository,
            PetRepository petRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    public AdoptionRequest createRequest(AdoptionRequestCreateBody constraint) {
        User user = userRepository.findById(CurrentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        Pet pet = petRepository.findByName(constraint.getPetName())
                .orElseThrow(() -> new IllegalArgumentException("Pet not found!"));


        Optional<AdoptionRequest> existingRequest = adoptionRequestRepository
                .findByUserIdAndPetIdAndStatus(user.getId(), pet.getId(), AdoptionStatus.PENDING);

        if (existingRequest.isPresent()) {
            throw new IllegalArgumentException("You already have a pending request for this pet!");
        }

        AdoptionRequest request = new AdoptionRequest();
        request.setUser(user);
        request.setPet(pet);
        request.setStatus(AdoptionStatus.PENDING);
        request.setRequest_date(LocalDateTime.now());

        return adoptionRequestRepository.save(request);
    }


    public AdoptionRequest updateRequestStatus(AdoptionRequestUpdateBody constraint){
        if (!UserRole.ADMIN.equals(CurrentUser.getRole())) {
            throw new IllegalArgumentException("Only admins can view request status!");
        }
        AdoptionRequest request=adoptionRequestRepository.findById(constraint.AdoptionID).
                orElseThrow(() -> new IllegalArgumentException("Request not found!"));

        request.setStatus(constraint.new_status);
        return adoptionRequestRepository.save(request);
    }

    public List<AdoptionRequest> getAllAdoptions() {
        if (!UserRole.ADMIN.equals(CurrentUser.getRole())) {
            throw new IllegalArgumentException("Only admins can view request status!");
        }
        return adoptionRequestRepository.findAll();
    }
}