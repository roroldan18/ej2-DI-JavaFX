package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculadoraController {

    @FXML
    private Label label_result;

    private Double result = 0.0;
    private String currentInput = "";
    private String currentOperator = "";

    @FXML
    private void onNumberButton(ActionEvent event) {
        String digit = ((javafx.scene.control.Button) event.getSource()).getText();
        // Reemplaza la coma por un punto
        digit = digit.replace(",", ".");
        currentInput += digit;
        label_result.setText(currentInput);
    }

    @FXML
    private void onOperatorButton(ActionEvent event) {
        if (!currentInput.isEmpty()) {
            if (!currentOperator.isEmpty()) {
                performCalculation();
            }
            currentOperator = ((javafx.scene.control.Button) event.getSource()).getText();
            result = Double.parseDouble(currentInput);
            currentInput = "";
        }
    }

    @FXML
    private void onEqualButton() {
        if (!currentInput.isEmpty()) {
            performCalculation();
            currentOperator = "";
        }
    }

    @FXML
    private void onClearButton() {
        currentInput = "";
        currentOperator = "";
        result = 0.0;
        label_result.setText("");
    }

    private void performCalculation() {
        double input = Double.parseDouble(currentInput);
        switch (currentOperator) {
            case "+":
                result += input;
                break;
            case "-":
                result -= input;
                break;
            case "x":
                result *= input;
                break;
            case "/":
                if (input != 0) {
                    result /= input;
                } else {
                    label_result.setText("Error: División por cero");
                    return;
                }
                break;
        }
        label_result.setText(String.valueOf(result));
        currentInput = "";
    }

       public void onPercButton(ActionEvent actionEvent) {
            double input = Double.parseDouble(currentInput);
            input = input / 100;
            label_result.setText(String.valueOf(input));
       }

    public void onSignButton(ActionEvent actionEvent) {
        double input = Double.parseDouble(currentInput);
        input = input * -1;
        label_result.setText(String.valueOf(input));
    }
}
