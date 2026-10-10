package com.example.calculatorapp;

/*
 Logan LeVon
 ID : 42413849
 Honor Code: I pledge that I have neither given nor received help from anyone
 other than the instructor or the TAs for all program components included here.
 */




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

/**
 * Main activity for the calculator.
 * Handles the calculator buttons and displays the user's expression and result.
 */
public class CalcMain extends AppCompatActivity {

    private TextView calculatorDisplay;
    private Normal_Calc normalCalc;

    /**
     * Stores the current calculator expression.
     */
    private String expression = "";

    /**
     * Tracks whether the calculator just displayed a result.
     */
    private boolean justCalculated = false;

    /**
     * Creates the calculator screen and sets up all button listeners.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.calclayout);

        calculatorDisplay = findViewById(R.id.calculatorDisplay);

        normalCalc = new Normal_Calc();

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

        Button buttonDecimal = findViewById(R.id.buttonDecimal);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        Button buttonOpenParen = findViewById(R.id.buttonOpenParen);
        Button buttonCloseParen = findViewById(R.id.buttonCloseParen);

        Button buttonAC = findViewById(R.id.buttonAC);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonBases = findViewById(R.id.buttonBases);

        View.OnClickListener numberListener = view -> {

            Button button = (Button) view;

            String number = button.getText().toString();

            if (calculatorDisplay.getText().toString().equals("LeVon 42413849")
                    || calculatorDisplay.getText().toString().equals("Error")
                    || justCalculated) {

                expression = number;

                justCalculated = false;

            } else {

                expression += number;
            }

            calculatorDisplay.setText(expression);
        };

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

        buttonDecimal.setOnClickListener(view -> addDecimal());

        buttonAdd.setOnClickListener(view -> addOperator("+"));
        buttonSubtract.setOnClickListener(view -> addOperator("-"));
        buttonMultiply.setOnClickListener(view -> addOperator("*"));
        buttonDivide.setOnClickListener(view -> addOperator("/"));

        buttonOpenParen.setOnClickListener(view -> addOpenParenthesis());
        buttonCloseParen.setOnClickListener(view -> addCloseParenthesis());

        buttonEquals.setOnClickListener(view -> calculateResult());

        buttonClear.setOnClickListener(view -> clearLastEntry());

        buttonAC.setOnClickListener(view -> allClear());

        buttonBases.setOnClickListener(view -> {

            Intent intent = new Intent(CalcMain.this, BasesMain.class);

            startActivity(intent);
        });
    }

    /**
     * Adds a decimal point to the current number.
     */
    private void addDecimal() {

        if (calculatorDisplay.getText().toString().equals("Logan LeVon 42413849")
                || justCalculated) {

            expression = "0.";

            justCalculated = false;

            calculatorDisplay.setText(expression);

            return;
        }

        int index = expression.length() - 1;

        while (index >= 0) {

            char character = expression.charAt(index);

            if (character == '+' || character == '-'
                    || character == '*' || character == '/'
                    || character == '(' || character == ')') {

                break;
            }

            if (character == '.') {

                return;
            }

            index--;
        }

        expression += ".";

        calculatorDisplay.setText(expression);
    }

    /**
     * Adds an arithmetic operator to the expression.
     *
     * @param operator operator to add
     */
    private void addOperator(String operator) {

        if (calculatorDisplay.getText().toString().equals("Logan LeVon 42413849")) {
            return;
        }

        if (justCalculated) {

            expression = calculatorDisplay.getText().toString();

            justCalculated = false;
        }

        if (expression.length() == 0) {
            return;
        }

        char lastCharacter = expression.charAt(expression.length() - 1);

        if (lastCharacter == '+'
                || lastCharacter == '-'
                || lastCharacter == '*'
                || lastCharacter == '/') {

            expression = expression.substring(0, expression.length() - 1);
        }

        expression += operator;

        calculatorDisplay.setText(expression);
    }

    /**
     * Adds an opening parenthesis to the expression.
     */
    private void addOpenParenthesis() {

        if (calculatorDisplay.getText().toString().equals("Logan LeVon 42413849")
                || justCalculated) {

            expression = "(";

            justCalculated = false;

        } else {

            expression += "(";
        }

        calculatorDisplay.setText(expression);
    }

    /**
     * Adds a closing parenthesis to the expression.
     */
    private void addCloseParenthesis() {

        if (expression.length() == 0) {
            return;
        }

        int openParentheses = 0;
        int closeParentheses = 0;

        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == '(') {
                openParentheses++;
            }

            if (expression.charAt(i) == ')') {
                closeParentheses++;
            }
        }

        if (openParentheses <= closeParentheses) {
            return;
        }

        char lastCharacter = expression.charAt(expression.length() - 1);

        if (lastCharacter == '+'
                || lastCharacter == '-'
                || lastCharacter == '*'
                || lastCharacter == '/'
                || lastCharacter == '(') {

            return;
        }

        expression += ")";

        calculatorDisplay.setText(expression);
    }

    /**
     * Calculates the complete mathematical expression.
     */
    private void calculateResult() {

        if (expression.length() == 0) {
            return;
        }

        try {

            double result = normalCalc.calculateExpression(expression);

            expression = normalCalc.formatResult(result);

            calculatorDisplay.setText(expression);

            justCalculated = true;

        } catch (Exception e) {

            calculatorDisplay.setText("Error");

            expression = "";

            justCalculated = false;
        }
    }

    /**
     * Clears the most recent entry from the calculator display.
     */
    private void clearLastEntry() {

        if (expression.length() == 0) {
            return;
        }

        expression = expression.substring(0, expression.length() - 1);

        calculatorDisplay.setText(expression);
    }

    /**
     * Clears the entire calculator and returns the display
     * to the developer identification text.
     */
    private void allClear() {

        expression = "";

        justCalculated = false;

        calculatorDisplay.setText("Logan LeVon 42413849");
    }
}