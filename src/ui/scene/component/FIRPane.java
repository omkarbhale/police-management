package ui.scene.component;

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
import models.FIRModel;
import ui.util.MSGBox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

class FIRInsertForm extends BorderPane {
    private static FIRInsertForm instance;

    public static FIRInsertForm getInstance() {
        if(FIRInsertForm.instance == null) {
            FIRInsertForm.instance = new FIRInsertForm();
        }
        return FIRInsertForm.instance;
    }

    GridPane gp;
    Label titleLabel;

    Label policeInChargeLabel;
    Label criminalLabel;
    Label dateLabel;
    Label locationLabel;
    Label severityLabel;
    Label crimeLabel;

    TextField policeInChargeField;
    TextField criminalField;
    DatePicker datePicker;
    TextField locationField;
    TextField severityField;
    TextField crimeField;

    Button submitButton;
    Button clearButton;

    private FIRInsertForm() {
        initialize();

        gp.getChildren().addAll(
                policeInChargeLabel, policeInChargeField,
                criminalLabel, criminalField,
                dateLabel, datePicker,
                locationLabel, locationField,
                severityLabel, severityField,
                crimeLabel, crimeField,
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

        titleLabel = new Label("Insert FIR");
        titleLabel.setFont(Font.font(null, FontWeight.BOLD, 26));

        policeInChargeLabel = new Label("Police ID:");
        criminalLabel = new Label("Criminal ID:");
        dateLabel = new Label("Date:");
        locationLabel = new Label("Location:");
        severityLabel = new Label("Severity:");
        crimeLabel = new Label("Crime:");

        policeInChargeField = getTextField("Police in charge"); policeInChargeField.setOnKeyTyped(e -> validateNumber(policeInChargeField));
        criminalField = getTextField("Criminal ID"); criminalField.setOnKeyTyped(e -> validateNumber(criminalField));
        datePicker = new DatePicker(); datePicker.setPromptText("Date"); datePicker.getEditor().setDisable(true);
        locationField = getTextField("Location");
        severityField = getTextField("Number from 1 to 10"); severityField.setOnKeyTyped(e -> validateNumber(severityField));
        crimeField = getTextField("Crime");

        submitButton = new Button("Submit");
        clearButton = new Button("Clear");
        submitButton.setStyle("-fx-font-size: 20pt; ");
        clearButton.setStyle("-fx-font-size: 20pt; ");
        submitButton.setOnAction(e -> submit());
        clearButton.setOnAction(e -> clearFields());


        GridPane.setConstraints(policeInChargeLabel, 0, 0);
        GridPane.setConstraints(policeInChargeField, 1, 0);
        GridPane.setConstraints(criminalLabel, 0, 1);
        GridPane.setConstraints(criminalField, 1, 1);
        GridPane.setConstraints(dateLabel, 0, 2);
        GridPane.setConstraints(datePicker, 1, 2);
        GridPane.setConstraints(locationLabel, 0, 3);
        GridPane.setConstraints(locationField, 1, 3);
        GridPane.setConstraints(severityLabel, 0, 4);
        GridPane.setConstraints(severityField, 1, 4);
        GridPane.setConstraints(crimeLabel, 0, 5);
        GridPane.setConstraints(crimeField, 1, 5);
        GridPane.setConstraints(submitButton, 1, 6);
        GridPane.setConstraints(clearButton, 0, 6);
    }

    private TextField getTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    private void validateNumber(TextField textField) {
        String str = textField.getText();
        try {
            int n = Integer.parseInt(str);
        } catch (Exception e) {
            textField.setText(null);
        }
    }

    private void submit() {
        try {
            FIR fir = new FIR(
                    (int) (Math.random() * 1000),
                    Integer.parseInt(policeInChargeField.getText()),
                    Integer.parseInt(criminalField.getText()),
                    getDate(datePicker),
                    locationField.getText(),
                    Integer.parseInt(severityField.getText()),
                    crimeField.getText()
            );
            boolean isSaved = FIRModel.save(fir);
            if(isSaved) {
                MSGBox.message("Success", "FIR saved successfully");
                clearFields();
            } else {
                MSGBox.message("Error", "Some error occurred!");
            }
        } catch (Exception e) {
            MSGBox.message("Error", "IDs should be numbers");
        }
    }

    private void clearFields() {
        policeInChargeField.setText(null);
        criminalField.setText(null);
        datePicker.setValue(null);
        locationField.setText(null);
        severityField.setText(null);
        crimeField.setText(null);
    }

    private Date getDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

class FIRViewForm extends BorderPane {
    class FIRCard extends GridPane {
        FIRViewForm parent;
        Label idLabel;
        Label policeIdLabel;
        Label criminalIdLabel;
        Label crimeLabel;
        Label dateLabel;
        Label locationLabel;
        Label severityLabel;
        Button deleteButton;

        FIRCard(FIRViewForm parent, FIR fir) {
            this.parent = parent;
            initialize(fir);
        }

        private void initialize(FIR fir) {
            setHgap(80);
            setVgap(10);
            setPadding(new Insets(20, 20, 20, 20));
            setAlignment(Pos.TOP_CENTER);
            setStyle("-fx-border-color: #f0f0f0; -fx-border-width: 1px; ");


            idLabel = new Label("FIR ID: " + fir.Police_id);
            policeIdLabel = new Label("Police ID: " + fir.Police_id);
            criminalIdLabel = new Label("Criminal ID: " + fir.Criminal_id);
            crimeLabel = new Label("Crime: " + fir.crime);
            dateLabel = new Label("Date: " + fir.date);
            locationLabel = new Label("Location: " + fir.Location);
            severityLabel = new Label("Severity: " + fir.Severity);

            deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> delete(fir));

            addRow(0, idLabel, policeIdLabel, criminalIdLabel, crimeLabel);
            addRow(1, dateLabel, locationLabel, severityLabel, deleteButton);
        }

        private void delete(FIR fir) {
            if(FIRModel.delete(fir)) {
                MSGBox.message("Success", "Deleted successfully");
                parent.loadFIRs();
            } else {
                MSGBox.message("Error", "Some error occurred");
            }
        }
    }

    private static FIRViewForm instance;

    public static FIRViewForm getInstance() {
        if(FIRViewForm.instance == null) {
            FIRViewForm.instance = new FIRViewForm();
        }
        FIRViewForm.instance.loadFIRs();
        return FIRViewForm.instance;
    }

    GridPane gp;
    Label titleLabel;
    ArrayList<FIR> list;

    private FIRViewForm() {
        initialize();

        setPadding(new Insets(10, 10, 10, 10));
        setTop(titleLabel);
    }

    private void initialize() {
        titleLabel = new Label("View FIR");
        titleLabel.setFont(Font.font(null, FontWeight.BOLD, 26));
        loadFIRs();
    }

    private void loadFIRs() {
        list = FIRModel.findAll();

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(15);
        gp.setHgap(25);

        if(list != null)
            for(int i = 0; i < list.size(); i++) {
                gp.addRow(i, new FIRCard(this, list.get(i)));
            }
        else
            MSGBox.message("Error", "Some error occurred");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: #191919; -fx-border-width: 0; ");
        scrollPane.setContent(gp);

        setCenter(scrollPane);
    }
}

public class FIRPane extends BorderPane {

    TabPane tabPane;

    FIRPane() {
        super();
        initialize();

        VBox vb = new VBox();
        vb.getChildren().addAll(tabPane);

        setTop(vb);
        setContent(FIRInsertForm.getInstance());
    }

    private void initialize() {
        tabPane = new TabPane();

        Tab t1 = new Tab("Insert FIR");
        Tab t2 = new Tab("View FIRs");

        tabPane.getTabs().addAll(
                t1, t2
        );

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().selectedItemProperty().addListener((o, oldTab, newTab) -> {
            if(newTab == t1) {
                setContent(FIRInsertForm.getInstance());
            } else if(newTab == t2) {
                setContent(FIRViewForm.getInstance());
            }
        });
    }

    private void setContent(Node n) {
        setCenter(n);
    }

}
