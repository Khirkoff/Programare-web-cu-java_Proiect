package com.example.pet_adoption_system.Repository;

import com.example.pet_adoption_system.Model.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    Optional<AdoptionRequest> findByUserIdAndPetIdAndStatus(Long userId, Long petId, String status);
}