package fr.isen.java2.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import fr.isen.java2.DataBase;
import fr.isen.java2.MainApp;
import fr.isen.java2.VCard;
import fr.isen.java2.model.Person;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNew() {

        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
        	DataBase dataBase = new DataBase();
        	dataBase.add(tempPerson);
        	dataBase.setPersonData(mainApp.getPersonData());
        }
    }

    /**
     * Opens a DirectoryChooser to let the user select a directory for importation
     * of all contacts inside.
     */
    @FXML
    private void handleImport() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        // Show save file dialog
        File file = directoryChooser.showDialog(mainApp.getPrimaryStage());

        if (file != null) {
	        VCard vCard = new VCard();
	        vCard.open(mainApp.getPersonData(), file);
        }
        
    }

    /**
     * Opens a FileChooser to let the user select a file to export contacts.
     */
    @FXML
    private void handleExport() {
        FileChooser fileChooser = new FileChooser();

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            VCard vCard = new VCard();
            vCard.export(mainApp.getPersonData(), file);
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}