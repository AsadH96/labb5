package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Person {
    private String personID,personName,role,nationality;
    public Person(String personID, String personName, String role, String nationality){
        this.personID = personID;
        this.personName= personName;
        this.role=role;
        this.nationality=nationality;
    }
    public String getPersonID(){
        return personID;
    }
    public String getPersonName(){
        return personName;
    }
    public String getRole(){
        return role;
    }
    public String getNationality(){
        return nationality;
    }
    @Override
    public String toString(){
        String info = "PersonID:"+personID+" Name:"+personName+" Role:"+role+" Nationality:"+nationality;
        return info;
    }
}
