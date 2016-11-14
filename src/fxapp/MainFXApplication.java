package fxapp;

import controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Team 0xF on 9/18/16.
 *
 * Some code reused from M3 assignment.
 *
 * MainFXApplication application class. This class handles all the scene
 * switching to reuse the main stage.
 */

// controls scenes and stages
public class MainFXApplication extends Application {
    /**  the java logger for this class */
    private static final Logger LOGGER =Logger.getLogger("MainFXApplication");

    /** the main container for the application window */
    private Stage mainScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;


    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        primaryStage.setTitle("Clean Water Crowd-sourcing System");
        showWelcome(mainScreen);
    }

    /**
     * return a reference to the main window stage
     * @return reference to main stage
     * */
    public Stage getMainScreen() { return mainScreen;}


    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(); // always call FXMLLoader to create controller
            loader.setLocation(MainFXApplication.class.getResource("../view/MainScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("Welcome!");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!");
            e.printStackTrace();
        }
    }


    /**
     * Setup our default application view that is shown on application startup
     * This is displayed in the startup window
     *
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * post-condition - the view is initialized and displayed
     *
     * @param mainScreen  the main stage to show this view in
     */
    public void showWelcome(Stage mainScreen) {
        try {
            // Load course overview.
            FXMLLoader loader = new FXMLLoader(); // each controller needs it own loader
            loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeScreen.fxml"));
            AnchorPane welcomeScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(welcomeScreen);

            // Give the controller access to the main app.
            WelcomeController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for WelcomeScreen!");
            e.printStackTrace();
        }

    }

    /**
     * Opens a dialog for user to enter credentials to login. If the user
     * clicks OK, will check if credentials are valid and login user in.
     *
     * @return true if the user clicked OK, false otherwise.
     * */

    public boolean showLoginScreen() {
        // feel free to add User model to this
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/LoginScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            LoginController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for ShowLoginScreen!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog for user to enter credentials to register a new account.
     * If the user clicks OK, will create a new User and store into the
     * Accounts Manager.
     *
     * @return true if the user clicked submit or cancel, false otherwise.
     * */

    public boolean showRegistrationScreen() {
        // feel free to add User model to this
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/RegistrationScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("User Registration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RegistrationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for ShowRegistrationScreen!");
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Setup our main account screen that is shown when user is logged in.
     * This is displayed in the startup window
     *
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * post-condition - the account view is initialized and displayed
     */
    public void showAccountScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/AccountScreen.fxml"));
            BorderPane AccountScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(AccountScreen);

            // Give the controller access to the main app.
            AccountController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for showAccountScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Setup the profile screen that is shown when user selects "View Profile"
     * This is displayed in the startup window
     *
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * post-condition - the account view is initialized and displayed
     */
    public void showViewProfileScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ViewProfileScreen.fxml"));
            AnchorPane ViewProfileScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(ViewProfileScreen);

            // Give the controller access to the main app.
            ViewProfileController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for ViewProfileScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Setup the edit profile screen that is shown when user selects "Edit Profile".
     * This is displayed in the startup window
     *
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * post-condition - the edit profile view is initialized and displayed
     */
    public void showEditProfileScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/EditProfileScreen.fxml"));
            BorderPane EditProfileScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(EditProfileScreen);

            // Give the controller access to the main app.
            EditProfileController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for EditProfileScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Setup the submit water report screen that is shown when user selects "Submit Report".
     * This is displayed in the startup window
     *
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * post-condition - the submit report view is initialized and displayed
     */
    public void showSubmitWaterReportScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/SubmitWaterReportScreen.fxml"));
            TitledPane SubmitWaterReportScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(SubmitWaterReportScreen);

            // Give the controller access to the main app.
            SubmitWaterReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for SubmitReportScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Setup the list view of reports screen that is shown when user selects "Your Submitted Reports"
     * This can be accessed from the account menu
     *
     */
    public void showViewWaterReportScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ViewWaterReportScreen.fxml"));
            AnchorPane ViewWaterReportScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(ViewWaterReportScreen);

            // Give the controller access to the main app.
            ViewWaterReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for ViewWaterReportScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Setup the map screen that shows users the location where water is available and the condition of that water.
     *
     */
    public void showMapScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/WaterAvailabilityMapScreen.fxml"));
            TabPane WaterAvailabilityMapScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(WaterAvailabilityMapScreen);

            // Give the controller access to the main app.
            MapController controller = loader.getController();
            controller.setCallbacks(mainScreen, this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for WaterAvailabilityMapScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Setup the submit quality report screen that is shown when user selects "Submit Quality Report".
     * This is displayed in the startup window
     *
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * post-condition - the submit quality report view is initialized and displayed
     */
    public void showSubmitQualityScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/SubmitQualityReportScreen.fxml"));
            TitledPane SubmitQualityScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(SubmitQualityScreen);

            // Give the controller access to the main app.
            SubmitQualityReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for SubmitQualityReportScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Setup the list view of quality reports screen that is shown when user selects "View Quality Reports"
     * This can be accessed from the account menu
     *
     */
    public void showViewQualityScreen() {
        try {
            // Load main screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ViewQualityReportScreen.fxml"));
            AnchorPane ViewQualityReportScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(ViewQualityReportScreen);

            // Give the controller access to the main app.
            ViewQualityReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for ViewQualityReportScreen!");
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog for manager to create history graphs. If the user
     * clicks "Return to Home Screen", will close window.
     * */

    public void showHistoryGraphScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/HistoryGraphScreen.fxml"));
            TitledPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Historical Graphs");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            HistoryGraphController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for HistoryGraphScreen!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {launch(args);}
}