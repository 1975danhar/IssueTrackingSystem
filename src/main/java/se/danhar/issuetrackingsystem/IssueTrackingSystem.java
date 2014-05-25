/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.danhar.issuetrackingsystem;

import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
public class IssueTrackingSystem {
    public static void main(String[] args){
        try {
            DatabaseConnector demo = new DatabaseConnector();
            demo.create();
            if(demo.getCount("Staff")==0){
                demo.initiate();
            }
            
        } catch (Exception e) {
            String info = "Failure: ";
            info += e.getMessage();
            System.err.println(info);
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
    
}
