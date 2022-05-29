package ui.scene.component;

import entities.Criminal;
import entities.FIR;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.CriminalModel;
import ui.util.MSGBox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

class CriminalInsertForm extends BorderPane {
    private static CriminalInsertForm instance;

    public static CriminalInsertForm getInstance() {
        if(CriminalInsertForm.instance == null) {
            CriminalInsertForm.instance = new CriminalInsertForm();
        }
        return CriminalInsertForm.instance;
    }

    GridPane gp;
    Label titleLabel;

    Label firstNameLabel;
    Label middleNameLabel;
    Label lastNameLabel;
    Label dobLabel;
    Label addressLabel;

    TextField firstNameField;
    TextField middleNameField;
    TextField lastNameField;
    DatePicker dobPicker;
    TextField addressField;

    Button submitButton;
    Button clearButton;

    private CriminalInsertForm() {
        initialize();

        gp.getChildren().addAll(
                firstNameLabel, firstNameField,
                middleNameLabel, middleNameField,
                lastNameLabel, lastNameField,
                dobLabel, dobPicker,
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

        titleLabel = new Label("Insert Criminal");
        titleLabel.setFont(Font.font(null, FontWeight.BOLD, 26));

        firstNameLabel = new Label("First name:");
        middleNameLabel = new Label("Middle name:");
        lastNameLabel = new Label("Last name:");
        dobLabel = new Label("Date of birth:");
        addressLabel = new Label("Address:");

        firstNameField = getTextField("First name");
        middleNameField = getTextField("Middle name");
        lastNameField = getTextField("Last name");
        dobPicker = new DatePicker(); dobPicker.setPromptText("Date"); dobPicker.getEditor().setDisable(true);
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
        GridPane.setConstraints(dobLabel, 0, 3);
        GridPane.setConstraints(dobPicker, 1, 3);
        GridPane.setConstraints(addressLabel, 0, 4);
        GridPane.setConstraints(addressField, 1, 4);
        GridPane.setConstraints(clearButton, 0, 5);
        GridPane.setConstraints(submitButton, 1, 5);
    }

    private TextField getTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    private void submit() {
        Criminal criminal = new Criminal(
                (int) (Math.random() * 1000),
                firstNameField.getText(),
                middleNameField.getText(),
                lastNameField.getText(),
                getDate(dobPicker),
                addressField.getText()
        );
        boolean isSaved = CriminalModel.save(criminal);
        if(isSaved) {
            MSGBox.message("Criminal saved successfully");
            clearFields();
        } else {
            MSGBox.message("Some error occurred!");
        }
    }

    private void clearFields() {
        firstNameField.setText(null);
        middleNameField.setText(null);
        lastNameField.setText(null);
        dobPicker.setValue(null);
        addressField.setText(null);
    }

    private Date getDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

class CriminalViewForm extends BorderPane {
    class CriminalCard extends GridPane {
        CriminalViewForm parent;
        Label idLabel;
        Label firstNameLabel;
        Label middleNameLabel;
        Label lastNameLabel;
        Label dobLabel;
        Label addressLabel;
        Button deleteButton;

        CriminalCard(CriminalViewForm parent, Criminal criminal) {
            this.parent = parent;
            initialize(criminal);
        }

        private void initialize(Criminal criminal) {
            setHgap(80);
            setVgap(10);
            setPadding(new Insets(20, 20, 20, 20));
            setAlignment(Pos.TOP_CENTER);
            setStyle("-fx-border-color: #f0f0f0; -fx-border-width: 1px; ");


            idLabel = new Label("Criminal ID: " + criminal.Criminal_id);
            firstNameLabel = new Label("First name: " + criminal.firstname);
            middleNameLabel = new Label("Criminal ID: " + criminal.middlename);
            lastNameLabel = new Label("Crime: " + criminal.lastname);
            dobLabel = new Label("Date: " + criminal.DOB);
            addressLabel = new Label("Location: " + criminal.address);

            deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> delete(criminal));

            addRow(0, idLabel, firstNameLabel, middleNameLabel, lastNameLabel);
            addRow(1, dobLabel, addressLabel, deleteButton);
        }

        private void delete(Criminal criminal) {
            if(CriminalModel.delete(criminal)) {
                MSGBox.message("Deleted successfully");
                parent.loadCriminals();
            } else {
                MSGBox.message("Some error occurred");
            }
        }
    }

    private static CriminalViewForm instance;

    public static CriminalViewForm getInstance() {
        if(CriminalViewForm.instance == null) {
            CriminalViewForm.instance = new CriminalViewForm();
        }
        CriminalViewForm.instance.loadCriminals();
        return CriminalViewForm.instance;
    }

    GridPane gp;
    Label titleLabel;
    ArrayList<Criminal> list;

    private CriminalViewForm() {
        initialize();

        setPadding(new Insets(10, 10, 10, 10));
        setTop(titleLabel);
    }

    private void initialize() {
        titleLabel = new Label("View Criminal");
        titleLabel.setFont(Font.font(null, FontWeight.BOLD, 26));
        loadCriminals();
    }

    private void loadCriminals() {
        list = CriminalModel.findAll();

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(15);
        gp.setHgap(25);

        if(list != null)
            for(int i = 0; i < list.size(); i++) {
                gp.addRow(i, new CriminalCard(this, list.get(i)));
            }
        else
            MSGBox.message("Some error occurred");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: #191919; -fx-border-width: 0; ");
        scrollPane.setContent(gp);

        setCenter(scrollPane);
    }
}

public class CriminalPane extends BorderPane {

    TabPane tabPane;

    CriminalPane() {
        super();
        initialize();

        VBox vb = new VBox();
        vb.getChildren().addAll(tabPane);

        setTop(vb);
        setContent(CriminalInsertForm.getInstance());
    }

    private void initialize() {
        tabPane = new TabPane();

        Tab t1 = new Tab("Insert Criminal");
        Tab t2 = new Tab("View Criminals");

        tabPane.getTabs().addAll(
                t1, t2
        );

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().selectedItemProperty().addListener((o, oldTab, newTab) -> {
            if(newTab == t1) {
                setContent(CriminalInsertForm.getInstance());
            } else if(newTab == t2) {
                setContent(CriminalViewForm.getInstance());
            }
        });
    }

    private void setContent(Node n) {
        setCenter(n);
    }

}
