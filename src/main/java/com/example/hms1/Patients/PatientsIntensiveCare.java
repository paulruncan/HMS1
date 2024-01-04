package com.example.hms1.Patients;

public class PatientsIntensiveCare extends Patients {
    private int prospect;

    public int getProspect() {
        return prospect;
    }

    public void setProspect( int prospect ) {
        this.prospect = prospect;
    }

    public PatientsIntensiveCare( String name, String medicine, int price, int prospect, int id ) {
        super(name, medicine, price,id);
        this.prospect=prospect;
    }
}
