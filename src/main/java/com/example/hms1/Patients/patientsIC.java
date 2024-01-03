package com.example.hms1.Patients;

public class patientsIC extends patients {
    private int prospect;

    public int getProspect() {
        return prospect;
    }

    public void setProspect( int prospect ) {
        this.prospect = prospect;
    }

    public patientsIC( String name, String medicine, int price, int prospect,int id ) {
        super(name, medicine, price,id);
        this.prospect=prospect;
    }
}
