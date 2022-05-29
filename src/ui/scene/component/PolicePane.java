package ui.scene.component;

import entities.Police;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.PoliceModel;
import ui.util.MSGBox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

class PoliceInsertForm extends BorderPane {
    private static PoliceInsertForm instance;

    public static PoliceInsertForm getInstance() {
        if(PoliceInsertForm.instance == null) {
            PoliceInsertForm.instance = new PoliceInsertForm();
        }
        return PoliceInsertForm.instance;
    }

    GridPane gp;
    Label titleLabel;

    Label firstNameLabel;
    Label middleNameLabel;
    Label lastNameLabel;
    Label postLabel;
    Label dobLabel;
    Label contactNoLabel;
    Label addressLabel;

    TextField firstNameField;
    TextField middleNameField;
    TextField lastNameField;
    TextField postField;
    DatePicker dobPicker;
    TextField contactNoField;
    TextField addressField;

    Button submitButton;
    Button clearButton;

    private PoliceInsertForm() {
        initialize();

        gp.getChildren().addAll(
                firstNameLabel, firstNameField,
                middleNameLabel, middleNameField,
                lastNameLabel, lastNameField,
                postLabel, postField,
                dobLabel, dobPicker,
                contactNoLabel, contactNoField,
                addressLabel, addressField,
                clearButton, submitButton
        );

        setPadding(new Insets(10, 10, 10, 10));
        setTop(titleLabel);
        setCenter(gp);
    }

    private void initialize() {
        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(15);
        gp.setHgap(25);

        titleLabel = new Label("Insert Police");
        titleLabel.setFont(Font.font(null, FontWeight.BOLD, 26));

        firstNameLabel = new Label("First name:");
        middleNameLabel = new Label("Middle name:");
        lastNameLabel = new Label("Last name:");
        postLabel = new Label("Post");
        dobLabel = new Label("Date of birth:");
        contactNoLabel = new Label("Contact number");
        addressLabel = new Label("Address:");

        firstNameField = getTextField("First name");
        middleNameField = getTextField("Middle name");
        lastNameField = getTextField("Last name");
        postField = getTextField("Post");
        dobPicker = new DatePicker(); dobPicker.setPromptText("Date of birth"); dobPicker.getEditor().setDisable(true);
        contactNoField = getTextField("Contact number"); contactNoField.setOnKeyTyped(e -> validateNumber(contactNoField));
        addressField = getTextField("Address");

        submitButton = new Button("Submit");
        clearButton = new Button("Clear");
        submitButton.setStyle("-fx-font-size: 20pt; ");
        clearButton.setStyle("-fx-font-size: 20pt; ");
        submitButton.setOnAction(e -> submit());
        clearButton.setOnAction(e -> clearFields());

        GridPane.setConstraints(firstNameLabel, 0, 0);
        GridPane.setConstraints(firstNameField, 1, 0);
        GridPane.setConstraints(middleNameLabel, 0, 1);
        GridPane.setConstraints(middleNameField, 1, 1);
        GridPane.setConstraints(lastNameLabel, 0, 2);
        GridPane.setConstraints(lastNameField, 1, 2);
        GridPane.setConstraints(postLabel, 0, 3);
        GridPane.setConstraints(postField, 1, 3);
        GridPane.setConstraints(dobLabel, 0, 4);
        GridPane.setConstraints(dobPicker, 1, 4);
        GridPane.setConstraints(contactNoLabel, 0, 5);
        GridPane.setConstraints(contactNoField, 1, 5);
        GridPane.setConstraints(addressLabel, 0, 6);
        GridPane.setConstraints(addressField, 1, 6);
        GridPane.setConstraints(clearButton, 0, 7);
        GridPane.setConstraints(submitButton, 1, 7);
    }

    private TextField getTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    private void submit() {
        Police police = new Police(
                (int) (Math.random() * 1000),
                firstNameField.getText(),
                middleNameField.getText(),
                lastNameField.getText(),
                postField.getText(),
                getDate(dobPicker),
                contactNoField.getText(),
                addressField.getText()
        );
        boolean isSaved = PoliceModel.save(police);
        if(isSaved) {
            MSGBox.message("Success", "Police saved successfully");
            clearFields();
        } else {
            MSGBox.message("Error", "Some error occurred!");
        }
    }

    private void clearFields() {
        firstNameField.setText(null);
        middleNameField.setText(null);
        lastNameField.setText(null);
        postField.setText(null);
        dobPicker.setValue(null);
        contactNoField.setText(null);
        addressField.setText(null);
    }

    private Date getDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private void validateNumber(TextField textField) {
        String str = textField.getText();
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            textField.setText(null);
        }
    }
}

class PoliceViewForm extends BorderPane {
    class PoliceCard extends GridPane {
        PoliceViewForm parent;
        Label idLabel;
        Label firstNameLabel;
        Label middleNameLabel;
        Label lastNameLabel;
        Label postLabel;
        Label dobLabel;
        Label contactNoLabel;
        Label addressLabel;
        Button deleteButton;

        PoliceCard(PoliceViewForm parent, Police police) {
            this.parent = parent;
            initialize(police);
        }

        private void initialize(Police police) {
            setHgap(80);
            setVgap(10);
            setPadding(new Insets(20, 20, 20, 20));
            setAlignment(Pos.TOP_CENTER);
            setStyle("-fx-border-color: #f0f0f0; -fx-border-width: 1px; ");


            idLabel = new Label("Police ID: " + police.Police_id);
            firstNameLabel = new Label("First name:" + police.firstName);
            middleNameLabel = new Label("Middle name:" + police.middleName);
            lastNameLabel = new Label("Last name:" + police.lastName);
            postLabel = new Label("Post:" + police.post);
            dobLabel = new Label("Date: " + police.DOB);
            contactNoLabel = new Label("Contact number:" + police.contactNo);
            addressLabel = new Label("Address: " + police.address);

            deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> delete(police));

            addRow(0, idLabel, firstNameLabel, middleNameLabel, lastNameLabel, deleteButton);
            addRow(1, postLabel, dobLabel, contactNoLabel,  addressLabel);
        }

        private void delete(Police police) {
            if(PoliceModel.delete(police)) {
                MSGBox.message("Success", "Deleted successfully");
                parent.loadPolice();
            } else {
                MSGBox.message("Error", "Some error occurred");
            }
        }
    }

    private static PoliceViewForm instance;

    public static PoliceViewForm getInstance() {
        if(PoliceViewForm.instance == null) {
            PoliceViewForm.instance = new PoliceViewForm();
        }
        PoliceViewForm.instance.loadPolice();
        return PoliceViewForm.instance;
    }

    GridPane gp;
    Label titleLabel;
    ArrayList<Police> list;

    private PoliceViewForm() {
        initialize();

        setPadding(new Insets(10, 10, 10, 10));
        setTop(titleLabel);
    }

    private void initialize() {
        titleLabel = new Label("View Police");
        titleLabel.setFont(Font.font(null, FontWeight.BOLD, 26));
    }

    private void loadPolice() {
        list = PoliceModel.findAll();

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(15);
        gp.setHgap(25);

        if(list != null)
            for(int i = 0; i < list.size(); i++) {
                gp.addRow(i, new PoliceCard(this, list.get(i)));
            }
        else
            MSGBox.message("Error", "Some error occurred");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: #191919; -fx-border-width: 0; ");
        scrollPane.setContent(gp);

        setCenter(scrollPane);
    }
}

public class PolicePane extends BorderPane {

    TabPane tabPane;

    PolicePane() {
        super();
        initialize();

        VBox vb = new VBox();
        vb.getChildren().addAll(tabPane);

        setTop(vb);
        setContent(PoliceInsertForm.getInstance());
    }

    private void initialize() {
        tabPane = new TabPane();

        Tab t1 = new Tab("Insert");
        Tab t2 = new Tab("View all");

        tabPane.setTabMinWidth(450);

        tabPane.getTabs().addAll(
                t1, t2
        );

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().selectedItemProperty().addListener((o, oldTab, newTab) -> {
            if(newTab == t1) {
                setContent(PoliceInsertForm.getInstance());
            } else if(newTab == t2) {
                setContent(PoliceViewForm.getInstance());
            }
        });
    }

    private void setContent(Node n) {
        setCenter(n);
    }

}
