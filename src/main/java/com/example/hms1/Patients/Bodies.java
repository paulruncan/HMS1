package com.example.hms1.Patients;

public class Bodies {
    private int id;
    private String name;
    private String organ;

    public Bodies( int id, String name, String tsd ) {
        this.id = id;
        this.name = name;
        this.organ = tsd;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan( String organ ) {
        this.organ = organ;
    }
}
