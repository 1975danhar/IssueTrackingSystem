/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.danhar.issuetrackingsystem;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;
/**
 *
 * @author daniel
 */
public class DatabaseConnector {
    public static final String APPLICATION_NAME = "IssueTrackingSystem";

    // For simplicity reasons, I choose the same database name as the user name
    public static final String DB_NAME = "myits";
    public static final String DB_USER = "itsuser";
    public static final String DB_PASSWORD = "itsuser";
    
    enum DatabaseBackend {
        AN_EMBEDDED_APACHE_DERBY,
        A_REMOTE_POSTGRESQL
    }

    DatabaseBackend currentBackend = DatabaseBackend.A_REMOTE_POSTGRESQL;
    
    private final String GET_PROCESSLEADERS =
            "SELECT leaderId, firstName, lastName " +
            "FROM processLeader";
    private final String GET_LEADER_ID =
            "SELECT * "
            + "FROM processLeader "
            + "WHERE firstName = ? ";
    
    private final String GET_CASES =
            "SELECT caseId, caseAlias, caseStatus " +
            "FROM Cases " +
            "WHERE leaderId = ? ";

    private final String NUMBER_OF_ROWS =
            "SELECT COUNT(*) FROM ProcessLeader";
    
    private final DataSource dataSource;
    public DatabaseConnector() throws DatabaseException{
        switch (currentBackend) {
            case A_REMOTE_POSTGRESQL:
                dataSource = Database.getDataSource("org.postgresql.ds.PGPoolingDataSource");
                {
                    // Initiation of parameters for this data source must be done using
                    // the specific class, so we "cast" the general 'DataSource' into a specific
                    // 'PGPoolingDataSource'.
                    //
                    org.postgresql.ds.PGPoolingDataSource ds = (org.postgresql.ds.PGPoolingDataSource) dataSource;
                    ds.setApplicationName(APPLICATION_NAME);
                    ds.setDatabaseName(DB_NAME);
                    ds.setUser(DB_USER);
                    ds.setPassword(DB_PASSWORD);
                    ds.setServerName("10.0.1.20");
                    ds.setPortNumber(5432); // default well-known port number
                    ds.setMaxConnections(10);
                }
                break;
                case AN_EMBEDDED_APACHE_DERBY:
            default:
                dataSource = Database.getDataSource("org.apache.derby.jdbc.EmbeddedDataSource");
                {
                    // Initiation of parameters for this data source must be done using
                    // the specific class, so we "cast" the general 'DataSource' into a specific
                    // 'EmbeddedDataSource'.
                    //
                    org.apache.derby.jdbc.EmbeddedDataSource ds = (org.apache.derby.jdbc.EmbeddedDataSource) dataSource;
                    ds.setDescription(APPLICATION_NAME);
                    ds.setDatabaseName(DB_NAME);
                    ds.setUser(DB_USER);  // Not really needed since we are running Derby embedded
                    ds.setPassword(DB_PASSWORD);  // Not really needed since we are running Derby embedded
                    ds.setCreateDatabase("create");  // Create database if it does not exist
                }
                break;
        }
    }
    public void create() throws SQLException, IOException {
        try (InputStream is = getClass().getResourceAsStream("create.sql")) {
            Database.loadAndExecute(dataSource, is);
        }
    }
    /**
     * Initiates the database with basic content.
     * <p/>
     * @throws SQLException
     * @throws IOException
     */
    public void initiate() throws SQLException, IOException {
        try (InputStream is = getClass().getResourceAsStream("initiate.sql")) {
            Database.loadAndExecute(dataSource, is);
        }
    }
    
    public int getCount(final String table){
        int numberOfRows=0;
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pStmt = conn.prepareStatement(NUMBER_OF_ROWS)) {
                try (ResultSet rs = pStmt.executeQuery()) {
                    while (rs.next()) {
                        numberOfRows = rs.getInt(1);
                    }
                }
            }
        }
        catch (SQLException sqle) {
            String info = "Failed to query database: ";
            info += Database.squeeze(sqle);
            System.err.println(info);
        }
        return numberOfRows;
    }
    /**
     * Gets all students admitted to a specific course
     * <p/>
     * @return 
     */
    public Collection<ProcessLeader> getAllLeaders() {
        Collection<ProcessLeader> leaders = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pStmt = conn.prepareStatement(GET_PROCESSLEADERS)) {

                try (ResultSet rs = pStmt.executeQuery()) {
                    while (rs.next()) {
                        leaders.add(new ProcessLeader(
                        rs.getInt("leaderId"),
                        rs.getString("firstName"),
                        rs.getString("lastName")));
                    }
                }
            }
        }
        catch (SQLException sqle) {
            String info = "Failed to query database: ";
            info += Database.squeeze(sqle);
            System.err.println(info);
        }
        return leaders;
    }
    
    public int getLeaderId(String fName) throws SQLException{
        int leaderId = 1;        
        try (Connection conn = dataSource.getConnection()) {

            try (PreparedStatement pStmt = conn.prepareStatement(GET_LEADER_ID)) {
                pStmt.setString(1, fName);
                
                try (ResultSet rs = pStmt.executeQuery()) {
                    while (rs.next()) {                       
                        leaderId=rs.getInt("leaderId");
                        
                    }
                }
            }
            
        }
        catch (SQLException sqle) {
            String info = "Failed to query database: ";
            info += Database.squeeze(sqle);
            System.err.println(info);
        }
        return leaderId;
    }

    /**
     *
     * @param id
     * @return
     */
    public Collection<Case> getCases(int id){
        Collection<Case> cases = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pStmt = conn.prepareStatement(GET_CASES)) {
                pStmt.setInt(1, id);
                try (ResultSet rs = pStmt.executeQuery()) {
                    while (rs.next()) {
                        cases.add(new Case(
                        rs.getInt("caseId"),
                        rs.getString("caseAlias"),
                        rs.getString("caseStatus")));
                    }
                }
            }
        }
        catch (SQLException sqle) {
            String info = "Failed to query database: ";
            info += Database.squeeze(sqle);
            System.err.println(info);
        }

        return cases;
    }
        
}
