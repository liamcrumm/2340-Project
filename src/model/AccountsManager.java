package model;



import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class AccountsManager {

    private ArrayList<User> users;
    private String currentUsername;
    private User currentUser;
    private ArrayList<WaterReport> waterReports;
    private ArrayList<QualityReport> qualityReports;
    MongoDatabase accountsDB;

    /**
     * Instantiates the AccountsManager class
     */
    public AccountsManager() {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://Team15:Team15@ds049925.mlab.com:49925/2340project"));
        accountsDB = client.getDatabase("2340project");
        MongoCollection userDB = accountsDB.getCollection("user");
        MongoCollection waterRepDB = accountsDB.getCollection("water_reports");
        MongoCollection qualityRepDB = accountsDB.getCollection("quality_reports");

        users = new ArrayList<>();
        waterReports = new ArrayList<>();
        qualityReports = new ArrayList<>();
    }

    /**
     * Sets the current username that is using the app
     * @param username    The username of the user that is in a session right now
     */
    public void setCurrentUser(String username) {
        currentUsername = username;
    }

    /**
     * Returns the user that is using the app right now
     * @return A user variable containing the details of the user that is using the application.
     */
    public User getUser() {
        MongoCollection userDB = accountsDB.getCollection("user");
        Document user = (Document)userDB.findOneAndDelete(new Document("username",currentUsername));
        if(user == null) {
            return null;
        }
        Document toprof = (Document)user.get("profile");
        Profile prof = new Profile((String)toprof.get("name"),(String)toprof.get("title"),
                (String)toprof.get("email"),(String)toprof.get("phone"),(String)toprof.get("address"),
                (String)toprof.get("bio"));
        User p = new User((String)user.get("username"),(String)user.get("password"),
                prof,(String)user.get("accountType"));
        userDB.insertOne(user);
        return p;
    }

    /**
     * Returns a list of users that are registered in the application.
     * @return a array list of all the users registered for this application
     */
    public ArrayList<User> getUserList() {
        users = new ArrayList<>();
        MongoCollection userDB = accountsDB.getCollection("user");
        FindIterable<Document> finder = userDB.find();
        long entries = userDB.count();
        for(long i = 0; i < entries; i++) {
            Document user = finder.skip((int)i).first();
            if(user == null) {
                continue;
            }
            Document toprof = (Document)user.get("profile");
            User p;
            if(toprof != null) {
                Profile prof = new Profile((String)toprof.get("name"),(String)toprof.get("title"),
                        (String)toprof.get("email"),(String)toprof.get("phone"),(String)toprof.get("address"),
                        (String)toprof.get("bio"));
                p = new User((String)user.get("username"),(String)user.get("password"),
                        prof,(String)user.get("accountType"));
            } else {
                p = new User((String)user.get("username"),(String)user.get("password"),
                        null,(String)user.get("accountType"));
            }
            users.add(p);
        }
        return users;
    }

    /**
     * Returns a list of users that are registered in the application.
     * @param u  a user to add to user list
     */
    public void addUser(User u) {
        MongoCollection userDB = accountsDB.getCollection("user");
        Document d = u.toDoc();
        userDB.insertOne(d);
    }

    /**
     * updates User information
     * @param u  a user to add to user list
     */
    public void updateUser(User u) {
        MongoCollection userDB = accountsDB.getCollection("user");
        Document d = u.toDoc();
        userDB.findOneAndUpdate(new Document("username",currentUsername),new BasicDBObject("$set",
                new BasicDBObject("profile", u.getProfile().toDocument())));
    }

    /**
     * deletes user account
     * @param u  a user to add to user list
     */
    public void deleteUser(User u) {
        MongoCollection userDB = accountsDB.getCollection("user");
        Document d = u.toDoc();
        userDB.findOneAndDelete(d);
    }

    /**
     * returns the username of the current user that is logged in to the application
     * @return The username of the user that is logged into the application.
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * returns the list of water reports that are in the system.
     * @return The list of water reports in the system
     */
    public ArrayList<WaterReport> getWaterReportsList() {
        waterReports = new ArrayList<WaterReport>();
        MongoCollection waterDB = accountsDB.getCollection("water_reports");
        FindIterable<Document> finder = waterDB.find();
        long entries = waterDB.count();
        for(long i = 0; i < entries; i++) {
            Document report = finder.skip((int)i).first();
            WaterReport w = new WaterReport((int)report.get("repNum"),(String)report.get("username"),
                    (Date)report.get("date"),(String)report.get("time"),(double)report.get("lat"),
                    (double)report.get("long"),(String)report.get("type"),(String)report.get("condition"));
            waterReports.add(w);
        }
        return waterReports;
    }

    /**
     * Adds a water report to the system.
     * @param r The water report to be added to the system.
     */
    public void addWaterReport(WaterReport r) {
        MongoCollection waterDB = accountsDB.getCollection("water_reports");
        Document w = r.toDoc();
        waterDB.insertOne(w);
    }

    /**
     * Removes a water report from the system.
     * @param r The water report that needs to be removed from the system.
     */
    public void removeWaterReport(WaterReport r) {
        MongoCollection waterDB = accountsDB.getCollection("water_reports");
        waterDB.findOneAndDelete(r.toDoc());
    }

    /**
     * returns the list of quality reports that are in the system.
     * @return The list of quality reports in the system
     */
    public ArrayList<QualityReport> getQualityReportsList() {
        qualityReports = new ArrayList<QualityReport>();
        MongoCollection qualityDB = accountsDB.getCollection("quality_reports");
        FindIterable<Document> finder = qualityDB.find();
        long entries = qualityDB.count();
        for(long i = 0; i < entries; i++) {
            Document report = finder.skip((int)i).first();
            QualityReport q = new QualityReport((int)report.get("repNum"),(String)report.get("username"),
                    (Date)report.get("date"),(String)report.get("time"),(double)report.get("lat"),
                    (double)report.get("long"),(String)report.get("condition"),(int)report.get("virus"),
                    (int)report.get("contaminant"));
            qualityReports.add(q);
        }
        return qualityReports;
    }

    /**
     * Adds a quality report to the system.
     * @param r The quality report to be added to the system.
     */
    public void addQualityReport(QualityReport r) {
        MongoCollection qualityDB = accountsDB.getCollection("quality_reports");
        Document q = r.toDoc();
        qualityDB.insertOne(q);
    }

    /**
     * Removes a quality report from the system.
     * @param r The quality report that needs to be removed from the system.
     */
    public void removeQualityReport(QualityReport r) {
        MongoCollection qualityDB = accountsDB.getCollection("quality_reports");
        qualityDB.findOneAndDelete(r.toDoc());
    }


}
