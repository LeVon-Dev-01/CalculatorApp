package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class CalcMain extends AppCompatActivity {

    TextView calculatorDisplay;
    Normal_Calc normalCalc;

    double firstNumber = 0;

    String operator = "";

    boolean newNumber = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Start the splash screen
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.calclayout);


        // Create calculator object
        normalCalc = new Normal_Calc();


        // Connect display to Java
        calculatorDisplay =
                findViewById(R.id.calculatorDisplay);


        // ========================================================
        // NUMBER BUTTONS
        // ========================================================

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);


        // ========================================================
        // OPERATOR BUTTONS
        // ========================================================

        Button buttonAdd =
                findViewById(R.id.buttonAdd);

        Button buttonSubtract =
                findViewById(R.id.buttonSubtract);

        Button buttonMultiply =
                findViewById(R.id.buttonMultiply);

        Button buttonDivide =
                findViewById(R.id.buttonDivide);


        // ========================================================
        // OTHER BUTTONS
        // ========================================================

        Button buttonDecimal =
                findViewById(R.id.buttonDecimal);

        Button buttonEquals =
                findViewById(R.id.buttonEquals);

        Button buttonClear =
                findViewById(R.id.buttonClear);

        Button buttonBases =
                findViewById(R.id.buttonBases);


        // ========================================================
        // NUMBER BUTTON LISTENER
        // ========================================================

        View.OnClickListener numberListener =
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Button button =
                                (Button) v;

                        String number =
                                button.getText().toString();


                        // Start a new number
                        if (newNumber) {

                            if (operator.isEmpty()) {

                                calculatorDisplay.setText(number);

                            } else {

                                calculatorDisplay.append(number);
                            }

                            newNumber = false;

                        } else {

                            calculatorDisplay.append(number);
                        }
                    }
                };


        // Connect number buttons to listener

        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);


        // ========================================================
        // DECIMAL BUTTON
        // ========================================================

        buttonDecimal.setOnClickListener(
                v -> addDecimal()
        );


        // ========================================================
        // OPERATOR BUTTONS
        // ========================================================

        buttonAdd.setOnClickListener(
                v -> setOperator("+")
        );

        buttonSubtract.setOnClickListener(
                v -> setOperator("-")
        );

        buttonMultiply.setOnClickListener(
                v -> setOperator("*")
        );

        buttonDivide.setOnClickListener(
                v -> setOperator("/")
        );


        // ========================================================
        // EQUALS
        // ========================================================

        buttonEquals.setOnClickListener(
                v -> calculateResult()
        );


        // ========================================================
        // CLEAR
        // ========================================================

        buttonClear.setOnClickListener(
                v -> clearCalculator()
        );


        // ========================================================
        // BASE CONVERTER
        // ========================================================

        buttonBases.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            CalcMain.this,
                            BasesMain.class
                    );

            startActivity(intent);
        });
    }


    // ============================================================
    // ADD DECIMAL
    // ============================================================

    private void addDecimal() {

        String display =
                calculatorDisplay.getText().toString();


        // If starting a new number,
        // start it with 0.
        if (newNumber) {

            if (operator.isEmpty()) {

                calculatorDisplay.setText("0.");

            } else {

                calculatorDisplay.append("0.");
            }

            newNumber = false;

            return;
        }


        // Get the current number being entered.
        String currentNumber;


        if (operator.isEmpty()) {

            currentNumber = display;

        } else {

            String[] parts =
                    display.split(" ");

            currentNumber =
                    parts[parts.length - 1];
        }


        // Prevent multiple decimal points.
        if (currentNumber.contains(".")) {
            return;
        }


        calculatorDisplay.append(".");
    }


    // ============================================================
    // SET OPERATOR
    // ============================================================

    private void setOperator(String selectedOperator) {

        String display =
                calculatorDisplay.getText().toString();


        // Prevent an operator from being
        // entered before a number.
        if (display.equals("0") && operator.isEmpty()) {
            return;
        }


        // If an operation is already active,
        // don't add another operator.
        if (!operator.isEmpty()) {
            return;
        }


        try {

            firstNumber =
                    Double.parseDouble(display);

        } catch (NumberFormatException e) {

            calculatorDisplay.setText("Error");

            firstNumber = 0;
            operator = "";
            newNumber = true;

            return;
        }


        operator = selectedOperator;


        // Show the operation on screen.
        calculatorDisplay.append(
                " " + selectedOperator + " "
        );


        // The next number starts fresh.
        newNumber = true;
    }


    // ============================================================
    // CALCULATE RESULT
    // ============================================================

    private void calculateResult() {

        // There is no operation to calculate.
        if (operator.isEmpty()) {
            return;
        }


        String display =
                calculatorDisplay.getText().toString();


        String[] parts =
                display.split(" ");


        // We need:
        // parts[0] = first number
        // parts[1] = operator
        // parts[2] = second number

        if (parts.length < 3) {
            return;
        }


        double secondNumber;


        try {

            secondNumber =
                    Double.parseDouble(parts[2]);

        } catch (NumberFormatException e) {

            calculatorDisplay.setText("Error");

            firstNumber = 0;
            operator = "";
            newNumber = true;

            return;
        }


        try {

            double result =
                    normalCalc.calculate(
                            firstNumber,
                            secondNumber,
                            operator
                    );


            // Display the result.
            calculatorDisplay.setText(
                    formatResult(result)
            );


            // Store result for possible
            // continued calculations.
            firstNumber = result;


            // Reset operator.
            operator = "";


            // Next number starts fresh.
            newNumber = true;


        } catch (ArithmeticException e) {

            // Division by zero.
            calculatorDisplay.setText("Error");

            firstNumber = 0;
            operator = "";
            newNumber = true;
        }
    }


    // ============================================================
    // CLEAR CALCULATOR
    // ============================================================

    private void clearCalculator() {

        calculatorDisplay.setText("0");

        firstNumber = 0;

        operator = "";

        newNumber = true;
    }


    // ============================================================
    // FORMAT RESULT
    // ============================================================

    private String formatResult(double result) {

        // If the result is a whole number,
        // don't display unnecessary .0

        if (result == (long) result) {

            return String.valueOf(
                    (long) result
            );
        }


        return String.valueOf(result);
    }
}