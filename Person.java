/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asad
 */
public class Person {
    
    private String personID;
    private String personName;
    private String role;
    private String nationality;
    
    public Person(String personID, String personName, String role, String nationality){
        
        this.personID = personID;
        this.personName = personName;
        this.role = role;
        this.nationality = nationality;
        
    }

    public String getPersonID(){
        return this.personID;
    }

    public String getPersonName(){
        return this.personName;
    }

    public String getRole(){
        return this.role;
    }

    public String getNationality(){
        return this.nationality;
    }
    
    @Override
    public String toString(){
        return this.personID + this.personName + this.role + this.nationality + "\n";
    }
}