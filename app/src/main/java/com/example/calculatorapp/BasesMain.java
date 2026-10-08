package com.example.calculatorapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BasesMain extends AppCompatActivity {

    EditText baseInput;
    Spinner conversionSpinner;
    Button convertButton;
    Button backButton;
    TextView baseResult;

    Calculation_Bases baseCalculator;

    String[] conversions = {
            "Decimal → Binary",
            "Decimal → Octal",
            "Decimal → Hexadecimal",

            "Binary → Decimal",
            "Binary → Octal",
            "Binary → Hexadecimal",

            "Octal → Decimal",
            "Octal → Binary",
            "Octal → Hexadecimal",

            "Hexadecimal → Decimal",
            "Hexadecimal → Binary",
            "Hexadecimal → Octal"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.baseslayout);

        baseCalculator = new Calculation_Bases();

        baseInput = findViewById(R.id.baseInput);
        conversionSpinner = findViewById(R.id.conversionSpinner);
        convertButton = findViewById(R.id.convertButton);
        baseResult = findViewById(R.id.baseResult);
        backButton = findViewById(R.id.backButton);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        conversions
                ) {

                    @Override
                    public View getView(
                            int position,
                            View convertView,
                            ViewGroup parent) {

                        TextView textView =
                                (TextView) super.getView(
                                        position,
                                        convertView,
                                        parent
                                );

                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(18);
                        textView.setPadding(
                                16,
                                0,
                                16,
                                0
                        );

                        return textView;
                    }

                    @Override
                    public View getDropDownView(
                            int position,
                            View convertView,
                            ViewGroup parent) {

                        TextView textView =
                                (TextView) super.getDropDownView(
                                        position,
                                        convertView,
                                        parent
                                );

                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(18);

                        textView.setBackgroundColor(
                                Color.rgb(30, 30, 30)
                        );

                        textView.setPadding(
                                16,
                                20,
                                16,
                                20
                        );

                        return textView;
                    }
                };

        conversionSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(
                v -> convertNumber()
        );

        backButton.setOnClickListener(
                v -> finish()
        );
    }

    private void convertNumber() {

        String input =
                baseInput.getText().toString().trim();

        if (input.isEmpty()) {

            baseResult.setText("Enter a number");

            return;
        }

        String conversion =
                conversionSpinner
                        .getSelectedItem()
                        .toString();

        try {

            String result;

            switch (conversion) {

                case "Decimal → Binary":

                    result =
                            baseCalculator.decimalToBinary(
                                    Integer.parseInt(input)
                            );

                    break;

                case "Decimal → Octal":

                    result =
                            baseCalculator.decimalToOctal(
                                    Integer.parseInt(input)
                            );

                    break;

                case "Decimal → Hexadecimal":

                    result =
                            baseCalculator.decimalToHex(
                                    Integer.parseInt(input)
                            );

                    break;

                case "Binary → Decimal":

                    result =
                            String.valueOf(
                                    baseCalculator.binaryToDecimal(
                                            input
                                    )
                            );

                    break;

                case "Binary → Octal":

                    result =
                            baseCalculator.binaryToOctal(
                                    input
                            );

                    break;

                case "Binary → Hexadecimal":

                    result =
                            baseCalculator.binaryToHex(
                                    input
                            );

                    break;

                case "Octal → Decimal":

                    result =
                            String.valueOf(
                                    baseCalculator.octalToDecimal(
                                            input
                                    )
                            );

                    break;

                case "Octal → Binary":

                    result =
                            baseCalculator.octalToBinary(
                                    input
                            );

                    break;

                case "Octal → Hexadecimal":

                    result =
                            baseCalculator.octalToHex(
                                    input
                            );

                    break;

                case "Hexadecimal → Decimal":

                    result =
                            String.valueOf(
                                    baseCalculator.hexToDecimal(
                                            input
                                    )
                            );

                    break;

                case "Hexadecimal → Binary":

                    result =
                            baseCalculator.hexToBinary(
                                    input
                            );

                    break;

                case "Hexadecimal → Octal":

                    result =
                            baseCalculator.hexToOctal(
                                    input
                            );

                    break;

                default:

                    result = "Invalid conversion";
            }

            baseResult.setText(result);

        } catch (NumberFormatException e) {

            baseResult.setText("Invalid number");
        }
    }
}