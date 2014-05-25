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
class Case {
     private int caseId;
     private String caseAlias;
     private String caseStatus;
     
    @Override
    public String toString() {
        return caseId + ":\t" + caseAlias + ": Status: " + caseStatus;
    }
    
    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public Case(int caseId, String caseAlias, String caseStatus) {
        this.caseId = caseId;
        this.caseAlias = caseAlias;
        this.caseStatus = caseStatus;
    }
   
}
