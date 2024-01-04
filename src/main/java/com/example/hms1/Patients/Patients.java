package com.example.hms1.Patients;

public class Patients extends Names {

   private String medicine;
   private int price;


    public String getMedicine() {
        return medicine;
    }

    public void setMedicine( String medicine ) {
        this.medicine = medicine;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice( int price ) {
        this.price = price;
    }

    public Patients( String name, String medicine, int price, int id ) {
        super(id,name);
        this.medicine = medicine;
        this.price = price;
    }

}
