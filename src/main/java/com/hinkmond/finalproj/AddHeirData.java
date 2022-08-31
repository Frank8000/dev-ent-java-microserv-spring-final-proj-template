package com.hinkmond.finalproj;

public class AddHeirData {

    private String firstName;
    private String lastName;

    private int age;

    private int income;

    private int asset;

    private String hobby;

    private String gambling;

    private int parent_id;

    public AddHeirData() {
    }


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hob) {
        this.hobby = hob;
    }
    public String getGambling() {
        return gambling;
    }
    public void setGambling(String Gambl) {
        this.gambling = Gambl;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int iage) {
        this.age = iage;
    }
    public int getIncome() {
        return income;
    }
    public void SetIncome(int iincome) {
        this.income = iincome;
    }
    public int getAsset() {
        return asset;
    }
    public void SetAsset(int iAsset) {
        this.asset = iAsset;
    }
    public int getParent_id() {
        return parent_id;
    }
    public void SetParent_id(int parent) {
        this.parent_id = parent;
    }

}
