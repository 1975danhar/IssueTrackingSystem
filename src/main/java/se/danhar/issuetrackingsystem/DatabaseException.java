/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.danhar.issuetrackingsystem;

public class DatabaseException extends Exception {
	public DatabaseException(String msg) {
		super(msg);
	}
    public DatabaseException(String msg, Exception e) {
        super(msg, e);
    }
}

