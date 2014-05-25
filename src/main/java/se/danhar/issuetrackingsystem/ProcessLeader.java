/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.danhar.issuetrackingsystem;

/**
 *
 * @author daniel
 */
public class ProcessLeader {
    int id;
    String firstName;
    String lastName;
    
    public ProcessLeader(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    
}