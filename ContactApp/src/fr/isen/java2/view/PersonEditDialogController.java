package fr.isen.java2.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.isen.java2.model.Person;

/**
 * Dialog to edit details of a person.
 */
public class PersonEditDialogController {

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField nickNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private TextField birthDateField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        lastNameField.setText(person.getLastName());
        firstNameField.setText(person.getFirstName());
        nickNameField.setText(person.getNickName());
        phoneNumberField.setText(person.getPhoneNumber());
        addressField.setText(person.getAddress());
        emailAddressField.setText(person.getEmailAddress());
        birthDateField.setText(person.getBirthDate());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setLastName(lastNameField.getText());
            person.setFirstName(firstNameField.getText());
            person.setNickName(nickNameField.getText());
            person.setPhoneNumber(phoneNumberField.getText());
            person.setAddress(addressField.getText());
            person.setEmailAddress(emailAddressField.getText());
            person.setBirthDate(birthDateField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nickNameField.getText() == null || nickNameField.getText().length() == 0) {
            errorMessage += "No valid nick name!\n";
        }
        if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) {
            errorMessage += "No valid phone number!\n";
        }
        if (addressField.getText() == null || addressField.getText().length() == 0) {
            errorMessage += "No valid address!\n";
        }
        if (emailAddressField.getText() == null || emailAddressField.getText().length() == 0) {
            errorMessage += "No valid email address!\n";
        }
        if (birthDateField.getText() == null || birthDateField.getText().length() == 0) {
            errorMessage += "No valid birth date!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}