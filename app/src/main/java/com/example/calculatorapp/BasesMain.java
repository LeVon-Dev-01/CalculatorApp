package com.example.calculatorapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity used for converting numbers between
 * binary, decimal, hexadecimal, and octal.
 */
public class BasesMain extends AppCompatActivity {

    private EditText baseInput;
    private Spinner conversionSpinner;
    private TextView baseResult;

    private Calculation_Bases calculationBases;

    /**
     * Creates the base converter screen and sets up
     * the conversion controls.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.baseslayout);

        baseInput = findViewById(R.id.baseInput);

        conversionSpinner = findViewById(R.id.conversionSpinner);

        baseResult = findViewById(R.id.baseResult);

        Button convertButton = findViewById(R.id.convertButton);

        Button backButton = findViewById(R.id.backButton);

        calculationBases = new Calculation_Bases();

        String[] conversions = {
                "Decimal to Binary",
                "Decimal to Hexadecimal",
                "Decimal to Octal",
                "Binary to Decimal",
                "Binary to Hexadecimal",
                "Binary to Octal",
                "Hexadecimal to Decimal",
                "Hexadecimal to Binary",
                "Hexadecimal to Octal",
                "Octal to Decimal",
                "Octal to Binary",
                "Octal to Hexadecimal"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                conversions
        ) {

            /**
             * Sets the text size and color of the selected
             * spinner item.
             *
             * @param position selected position
             * @param convertView spinner view
             * @param parent spinner parent
             * @return configured spinner view
             */
            @Override
            public android.view.View getView(
                    int position,
                    android.view.View convertView,
                    android.view.ViewGroup parent) {

                TextView textView = (TextView) super.getView(
                        position,
                        convertView,
                        parent
                );

                textView.setTextSize(26);

                textView.setTextColor(
                        android.graphics.Color.WHITE
                );

                textView.setPadding(16, 20, 16, 20);

                return textView;
            }

            /**
             * Sets the text size and color of the dropdown
             * spinner items.
             *
             * @param position item position
             * @param convertView dropdown view
             * @param parent dropdown parent
             * @return configured dropdown view
             */
            @Override
            public android.view.View getDropDownView(
                    int position,
                    android.view.View convertView,
                    android.view.ViewGroup parent) {

                TextView textView = (TextView) super.getDropDownView(
                        position,
                        convertView,
                        parent
                );

                textView.setTextSize(26);

                textView.setTextColor(
                        android.graphics.Color.WHITE
                );

                textView.setBackgroundColor(
                        android.graphics.Color.rgb(30, 30, 30)
                );

                textView.setPadding(16, 24, 16, 24);

                return textView;
            }
        };

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        conversionSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(view -> convertNumber());

        backButton.setOnClickListener(view -> finish());
    }

    /**
     * Converts the input number using the selected conversion.
     */
    private void convertNumber() {

        String input = baseInput.getText().toString().trim();

        if (input.isEmpty()) {

            baseResult.setText("Enter a number");

            return;
        }

        try {

            String conversion =
                    conversionSpinner.getSelectedItem().toString();

            String result;

            switch (conversion) {

                case "Decimal to Binary":
                    result = calculationBases.decimalToBinary(
                            Integer.parseInt(input)
                    );
                    break;

                case "Decimal to Hexadecimal":
                    result = calculationBases.decimalToHex(
                            Integer.parseInt(input)
                    );
                    break;

                case "Decimal to Octal":
                    result = calculationBases.decimalToOctal(
                            Integer.parseInt(input)
                    );
                    break;

                case "Binary to Decimal":
                    result = String.valueOf(
                            calculationBases.binaryToDecimal(input)
                    );
                    break;

                case "Binary to Hexadecimal":
                    result = calculationBases.binaryToHex(input);
                    break;

                case "Binary to Octal":
                    result = calculationBases.binaryToOctal(input);
                    break;

                case "Hexadecimal to Decimal":
                    result = String.valueOf(
                            calculationBases.hexToDecimal(input)
                    );
                    break;

                case "Hexadecimal to Binary":
                    result = calculationBases.hexToBinary(input);
                    break;

                case "Hexadecimal to Octal":
                    result = calculationBases.hexToOctal(input);
                    break;

                case "Octal to Decimal":
                    result = String.valueOf(
                            calculationBases.octalToDecimal(input)
                    );
                    break;

                case "Octal to Binary":
                    result = calculationBases.octalToBinary(input);
                    break;

                case "Octal to Hexadecimal":
                    result = calculationBases.octalToHex(input);
                    break;

                default:
                    result = "Invalid conversion";
                    break;
            }

            baseResult.setText(result);

        } catch (Exception e) {

            baseResult.setText("Invalid input");
        }
    }
}