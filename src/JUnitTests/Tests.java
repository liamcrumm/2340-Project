package JUnitTests;

import model.*;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Sakhi on 11/7/16.
 */
public class Tests {
    //Liam's method
    @Test
    public void getUserList() throws Exception {
        AccountsManager accounts = new AccountsManager();
        ArrayList<User> j = accounts.getUserList();
        ArrayList<User> storeTest = new ArrayList<>();
        //store data from before test
        while (!j.isEmpty()) {
            storeTest.add(j.get(0));
            accounts.deleteUser(j.get(0));
            j.remove(0);
        }
        j = accounts.getUserList();
        //make sure data was properly cleared, and set has size 0
        assertEquals(j.size(), 0);
        accounts.addUser(new User("1303", "52430", null, "23412"));
        j = accounts.getUserList();
        //test with null profile
        assertEquals(j.get(0).getUsername(), "1303");
        assertEquals(j.get(0).getPassword(), "52430");
        assertEquals(j.get(0).getAccountType(), "23412");
        assertEquals(j.get(0).getProfile(), null);
        accounts.addUser(new User("39402", "cbj9qwk", new Profile("19j01", "jd9g0", "js90df", "1j0fk", "xcb9u0w", "2j09ekg"), "dj90qk"));
        j = accounts.getUserList();
        //test with 2 profiles
        assertEquals(j.get(1).getUsername(), "39402");
        assertEquals(j.get(1).getPassword(), "cbj9qwk");
        assertEquals(j.get(1).getAccountType(), "dj90qk");
        assertEquals(j.get(1).getProfile().getName(), "19j01");
        assertEquals(j.get(1).getProfile().getTitle(), "jd9g0");
        assertEquals(j.get(1).getProfile().getEmail(), "js90df");
        assertEquals(j.get(1).getProfile().getPhone(), "1j0fk");
        assertEquals(j.get(1).getProfile().getAddress(), "xcb9u0w");
        assertEquals(j.get(1).getProfile().getBio(), "2j09ekg");
        //make sure the original 0 hasn't been written over
        assertEquals(j.get(0).getUsername(), "1303");
        assertEquals(j.get(0).getPassword(), "52430");
        assertEquals(j.get(0).getAccountType(), "23412");
        assertEquals(j.get(0).getProfile(), null);
        accounts.deleteUser(new User("1303", "52430", null, "23412"));
        j = accounts.getUserList();
        assertEquals(j.get(0).getUsername(), "39402");
        assertEquals(j.get(0).getPassword(), "cbj9qwk");
        assertEquals(j.get(0).getAccountType(), "dj90qk");
        assertEquals(j.get(0).getProfile().getName(), "19j01");
        assertEquals(j.get(0).getProfile().getTitle(), "jd9g0");
        assertEquals(j.get(0).getProfile().getEmail(), "js90df");
        assertEquals(j.get(0).getProfile().getPhone(), "1j0fk");
        assertEquals(j.get(0).getProfile().getAddress(), "xcb9u0w");
        assertEquals(j.get(0).getProfile().getBio(), "2j09ekg");
        //restore the data stored before test
        while (!storeTest.isEmpty()) {
            accounts.addUser(storeTest.get(0));
            storeTest.remove(0);
        }
    }

    //Andrew
    @Test
    public void getQualityReportsList() throws Exception {
        AccountsManager accounts = new AccountsManager();
        ArrayList<QualityReport> j = accounts.getQualityReportsList();
        ArrayList<QualityReport> storeTest = new ArrayList<>();
        //store data from before test
        while (!j.isEmpty()) {
            storeTest.add(j.get(0));
            accounts.removeQualityReport(j.get(0));
            j.remove(0);
        }
        j = accounts.getQualityReportsList();
        //make sure data was properly cleared, and set has size 0
        assertEquals(j.size(), 0);
        accounts.addQualityReport(new QualityReport(1303, "sml71dfn", new Date(12316L), "11:11", 10.5, 15.5, "muddy", 150, 150));
        j = accounts.getQualityReportsList();
        //test with random values
        assertEquals(j.get(0).getReportNumber(), 1303);
        assertEquals(j.get(0).getReportUsername(), "sml71dfn");
        assertEquals(j.get(0).getDate(), new Date(12316L));
        assertEquals(j.get(0).getTime(), "11:11");
        assertEquals(j.get(0).getLat(), 10.5, 0.0);
        assertEquals(j.get(0).getLong(), 15.5, 0.0);
        assertEquals(j.get(0).getCondition(), "muddy");
        assertEquals(j.get(0).getVirus(), 150);
        assertEquals(j.get(0).getContaminant(), 150);
        assertEquals(j.size(), 1);
        accounts.addQualityReport(new QualityReport(3842, "j3pi0", new Date(57022L), "10:55", 30.5, 18.5, "clean", 50, 15));
        j = accounts.getQualityReportsList();
        //test with 2 reports
        assertEquals(j.get(1).getReportNumber(), 3842);
        assertEquals(j.get(1).getReportUsername(), "j3pi0");
        assertEquals(j.get(1).getDate(), new Date(57022L));
        assertEquals(j.get(1).getTime(), "10:55");
        assertEquals(j.get(1).getLat(), 30.5, 0.0);
        assertEquals(j.get(1).getLong(), 18.5, 0.0);
        assertEquals(j.get(1).getCondition(), "clean");
        assertEquals(j.get(1).getVirus(), 50);
        assertEquals(j.get(1).getContaminant(), 15);
        assertEquals(j.size(), 2);
        //make sure the original 0 hasn't been written over
        assertEquals(j.get(0).getReportNumber(), 1303);
        assertEquals(j.get(0).getReportUsername(), "sml71dfn");
        assertEquals(j.get(0).getDate(), new Date(12316L));
        assertEquals(j.get(0).getTime(), "11:11");
        assertEquals(j.get(0).getLat(), 10.5, 0.0);
        assertEquals(j.get(0).getLong(), 15.5, 0.0);
        assertEquals(j.get(0).getCondition(), "muddy");
        assertEquals(j.get(0).getVirus(), 150);
        assertEquals(j.get(0).getContaminant(), 150);
        //delete the first report
        accounts.removeQualityReport(new QualityReport(1303, "sml71dfn", new Date(12316L), "11:11", 10.5, 15.5, "muddy", 150, 150));
        j = accounts.getQualityReportsList();
        assertEquals(j.get(0).getReportNumber(), 3842);
        assertEquals(j.get(0).getReportUsername(), "j3pi0");
        assertEquals(j.get(0).getDate(), new Date(57022L));
        assertEquals(j.get(0).getTime(), "10:55");
        assertEquals(j.get(0).getLat(), 30.5, 0.0);
        assertEquals(j.get(0).getLong(), 18.5, 0.0);
        assertEquals(j.get(0).getCondition(), "clean");
        assertEquals(j.get(0).getVirus(), 50);
        assertEquals(j.get(0).getContaminant(), 15);
        assertEquals(j.size(), 1);
        //restore the data stored before test
        while (!storeTest.isEmpty()) {
            accounts.addQualityReport(storeTest.get(0));
            storeTest.remove(0);
        }
    }

    //Sahithi
    @Test
    public void getWaterReportsList() {
        AccountsManager accounts = new AccountsManager();
        ArrayList<WaterReport> j = accounts.getWaterReportsList();
        ArrayList<WaterReport> storeTest = new ArrayList<>();
        //store data from before test
        while (!j.isEmpty()) {
            storeTest.add(j.get(0));
            accounts.removeWaterReport(j.get(0));
            j.remove(0);
        }
        j = accounts.getWaterReportsList();
        //make sure data was properly cleared, and set has size 0
        assertEquals(j.size(), 0);
        accounts.addWaterReport(new WaterReport(100, "m123", new Date(12316L), "8:30", 25.5, 30.0,"Lake","Clean"));
        j = accounts.getWaterReportsList();
        //test with random values
        assertEquals(j.get(0).getReportNumber(), 100);
        assertEquals(j.get(0).getReportUsername(), "m123");
        assertEquals(j.get(0).getDate(), new Date(12316L));
        assertEquals(j.get(0).getTime(), "8:30");
        assertEquals(j.get(0).getLat(), 25.5, 0.0);
        assertEquals(j.get(0).getLong(), 30.0, 0.0);
        assertEquals(j.get(0).getType(), "Lake");
        assertEquals(j.get(0).getCondition(), "Clean");
        assertEquals(j.size(), 1);
        accounts.addWaterReport(new WaterReport(98, "cd451", new Date(57022L), "10:55", 20.5, 10.5, "Stream","Muddy"));
        j = accounts.getWaterReportsList();
        //test with 2 reports
        assertEquals(j.get(1).getReportNumber(), 98);
        assertEquals(j.get(1).getReportUsername(), "cd451");
        assertEquals(j.get(1).getDate(), new Date(57022L));
        assertEquals(j.get(1).getTime(), "10:55");
        assertEquals(j.get(1).getLat(), 20.5, 0.0);
        assertEquals(j.get(1).getLong(), 10.5, 0.0);
        assertEquals(j.get(1).getType(), "Stream");
        assertEquals(j.get(1).getCondition(), "Muddy");
        assertEquals(j.size(), 2);
        //make sure the original 0 hasn't been written over
        assertEquals(j.get(0).getReportNumber(), 100);
        assertEquals(j.get(0).getReportUsername(), "m123");
        assertEquals(j.get(0).getDate(), new Date(12316L));
        assertEquals(j.get(0).getTime(), "8:30");
        assertEquals(j.get(0).getLat(), 25.5, 0.0);
        assertEquals(j.get(0).getLong(), 30.0, 0.0);
        assertEquals(j.get(0).getType(), "Lake");
        assertEquals(j.get(0).getCondition(), "Clean");
        //delete the first report
        accounts.removeWaterReport(new WaterReport(100, "m123", new Date(12316L), "8:30", 25.5, 30.0, "Lake", "Clean"));
        j = accounts.getWaterReportsList();
        assertEquals(j.get(0).getReportNumber(), 98);
        assertEquals(j.get(0).getReportUsername(), "cd451");
        assertEquals(j.get(0).getDate(), new Date(57022L));
        assertEquals(j.get(0).getTime(), "10:55");
        assertEquals(j.get(0).getLat(), 20.5, 0.0);
        assertEquals(j.get(0).getLong(), 10.5, 0.0);
        assertEquals(j.get(0).getType(), "Stream");
        assertEquals(j.get(0).getCondition(), "Muddy");
        assertEquals(j.size(), 1);
        //restore the data stored before test
        while (!storeTest.isEmpty()) {
            accounts.addWaterReport(storeTest.get(0));
            storeTest.remove(0);
        }
    }
    //Cat's Test
    @Test
    public void getUser() throws Exception {
        AccountsManager accounts = new AccountsManager();
        ArrayList<User> j = accounts.getUserList();
        ArrayList<User> storeTest = new ArrayList<>();
        //store data from before test
        while (!j.isEmpty()) {
            storeTest.add(j.get(0));
            accounts.deleteUser(j.get(0));
            j.remove(0);
        }
        j = accounts.getUserList();
        //make sure data was properly cleared, and set has size 0
        assertEquals(j.size(), 0);
       //adds user to userlist and sets as current user logged in, asserts equality
        accounts.addUser(new User("11oij3eo", "342oi3j4", null, "2o3848hoisdf"));
        accounts.setCurrentUser("11oij3eo");
        String userString = accounts.getCurrentUsername();
        assertEquals(userString, "11oij3eo");

        //adds new user with null username to assert equality with null entry
        accounts.addUser(new User(null, "asud98fu", null, "89uujiosdf"));
        accounts.setCurrentUser(null);
        userString = accounts.getCurrentUsername();
        assertEquals("tests to see if username is null", userString, null);

        accounts.addUser(new User("9812u3iujn", "892uadsd", new Profile("908hua", "ncjsi8", "oij3wenaksd", "aosijdffposijm", "oiuhjoejdsn", "oiajxckasd"), "oasidjak"));
        accounts.setCurrentUser("9812u3iujn");
        userString = accounts.getCurrentUsername();
        assertEquals(userString, "9812u3iujn");

        //restore the data stored before test
        while (!storeTest.isEmpty()) {
            accounts.addUser(storeTest.get(0));
            storeTest.remove(0);
        }
    }

    // Kim's test
    @Test
    public void testToDocUser() {
        User testUser = new User("testName", "testPass", null, "testType");
        // test that a Document was created
        Document d1 = testUser.toDocUser();
        assertNotNull(d1);
        // test that document for User with no profile created
        assertEquals("testName", d1.get("username"));
        assertEquals("testPass", d1.get("password"));
        assertNull(d1.get("profile"));
        assertEquals("testType", d1.get("accountType"));
        // test that document for User with profile created
        User testUser2 = new User("testName2", "testPass2",
                new Profile("testName2", "testTitle2", "testEmail2",
                "testPhone2", "testAddress2", "testBio2"), "testType2");
        Document d2 = testUser2.toDocUser();
        assertEquals("testName2", d2.get("username"));
        assertEquals("testPass2", d2.get("password"));
        Document profDoc = (Document) d2.get("profile");
        assertEquals("testName2", profDoc.get("name"));
        assertEquals("testTitle2", profDoc.get("title"));
        assertEquals("testEmail2", profDoc.get("email"));
        assertEquals("testPhone2", profDoc.get("phone"));
        assertEquals("testAddress2", profDoc.get("address"));
        assertEquals("testBio2", profDoc.get("bio"));
        assertEquals("testType2", d2.get("accountType"));
    }

}