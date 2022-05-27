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

    TextField policeInChargeField;
    TextField criminalField;
    DatePicker datePicker;
    TextField locationField;
    TextField severityField;

    Button submitButton;

    private FIRInsertForm() {
        initialize();

        gp.getChildren().addAll(
                policeInChargeLabel, policeInChargeField,
                criminalLabel, criminalField,
                dateLabel, datePicker,
                locationLabel, locationField,
                severityLabel, severityField,
                submitButton
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
        locationLabel = new Label("Location");
        severityLabel = new Label("Severity");

        policeInChargeField = getTextField("Police in charge");
        criminalField = getTextField("Criminal ID");
        datePicker = new DatePicker(); datePicker.setPromptText("Date");
        locationField = getTextField("Location");
        severityField = getTextField("Number from 1 to 10");

        submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 20pt; ");

        GridPane.setConstraints(policeInChargeLabel, 0, 0);
        GridPane.setConstraints(policeInChargeField, 2, 0);
        GridPane.setConstraints(criminalLabel, 0, 1);
        GridPane.setConstraints(criminalField, 2, 1);
        GridPane.setConstraints(dateLabel, 0, 2);
        GridPane.setConstraints(datePicker, 2, 2);
        GridPane.setConstraints(locationLabel, 0, 3);
        GridPane.setConstraints(locationField, 2, 3);
        GridPane.setConstraints(severityLabel, 0, 4);
        GridPane.setConstraints(severityField, 2, 4);
        GridPane.setConstraints(submitButton, 2, 6);
    }

    private TextField getTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    private void submit() {

    }
}

class FIRViewForm extends GridPane {

    private static FIRViewForm instance;

    public static FIRViewForm getInstance() {
        if(FIRViewForm.instance == null) {
            FIRViewForm.instance = new FIRViewForm();
        }
        return FIRViewForm.instance;
    }

    private FIRViewForm() {
        getChildren().add(new Label("VIEW FORM HERE"));
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
