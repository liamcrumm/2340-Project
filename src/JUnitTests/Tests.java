package JUnitTests;

import model.AccountsManager;
import model.Profile;
import model.QualityReport;
import model.User;
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
}