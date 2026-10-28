package edu.lab.problem3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TipCalculatorApp extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("Tip Calculator");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label billLabel = new Label("Bill Amount (₹):");
        TextField billField = new TextField();
        billField.setPromptText("Enter bill amount");

        Label tipLabel = new Label("Tip Percentage (%):");
        TextField tipField = new TextField("10");

        Button calculateButton = new Button("Calculate");
        Button clearButton = new Button("Clear");

        Label tipResult = new Label("Tip: ₹0.00");
        Label totalResult = new Label("Total: ₹0.00");
        Label message = new Label();

        calculateButton.setOnAction(event -> {
            try {
                double bill = Double.parseDouble(billField.getText().trim());
                double percentage =
                        Double.parseDouble(tipField.getText().trim());

                if (!Double.isFinite(bill)
                        || !Double.isFinite(percentage)
                        || bill < 0 || percentage < 0) {
                    message.setText("Enter valid positive numbers.");
                    return;
                }

                double tip = bill * percentage / 100;
                double total = bill + tip;

                tipResult.setText(String.format("Tip: ₹%.2f", tip));
                totalResult.setText(String.format("Total: ₹%.2f", total));
                message.setText("");

            } catch (NumberFormatException e) {
                message.setText("Please enter valid numbers.");
            }
        });

        clearButton.setOnAction(event -> {
            billField.clear();
            tipField.setText("10");
            tipResult.setText("Tip: ₹0.00");
            totalResult.setText("Total: ₹0.00");
            message.setText("");
        });

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(14);
        grid.add(billLabel, 0, 0);
        grid.add(billField, 1, 0);
        grid.add(tipLabel, 0, 1);
        grid.add(tipField, 1, 1);

        HBox buttons = new HBox(12, calculateButton, clearButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(
                18, title, grid, buttons,
                tipResult, totalResult, message
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));

        Scene scene = new Scene(root, 430, 360);

        stage.setTitle("JavaFX Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
