package com.example.pet_adoption_system.RequestBody;

public class AdoptionRequestUpdateBody {

    public Long AdoptionID;

    public Long getAdoptionID(){
        return AdoptionID;
    }
    public void setAdoptionID(Long AdoptionID){
        this.AdoptionID=AdoptionID;
    }

    public String new_status;

    public String getNew_status() {
        return new_status;
    }

    public void setNew_status(String new_status) {
        this.new_status = new_status;
    }
}
