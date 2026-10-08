package com.example.calculatorapp;

public class Calculation_Bases {

    // Decimal to Binary
    public String decimalToBinary(int number) {

        return Integer.toBinaryString(number);
    }

    // Decimal to Hexadecimal
    public String decimalToHex(int number) {

        return Integer.toHexString(number).toUpperCase();
    }

    // Decimal to Octal
    public String decimalToOctal(int number) {

        return Integer.toOctalString(number);
    }

    // Binary to Decimal
    public int binaryToDecimal(String binary) {

        return Integer.parseInt(binary, 2);
    }

    // Binary to Hexadecimal
    public String binaryToHex(String binary) {

        int decimal = binaryToDecimal(binary);

        return decimalToHex(decimal);
    }

    // Binary to Octal
    public String binaryToOctal(String binary) {

        int decimal = binaryToDecimal(binary);

        return decimalToOctal(decimal);
    }

    // Hexadecimal to Decimal
    public int hexToDecimal(String hex) {

        return Integer.parseInt(hex, 16);
    }

    // Hexadecimal to Binary
    public String hexToBinary(String hex) {

        int decimal = hexToDecimal(hex);

        return decimalToBinary(decimal);
    }

    // Hexadecimal to Octal
    public String hexToOctal(String hex) {

        int decimal = hexToDecimal(hex);

        return decimalToOctal(decimal);
    }

    // Octal to Decimal
    public int octalToDecimal(String octal) {

        return Integer.parseInt(octal, 8);
    }

    // Octal to Binary
    public String octalToBinary(String octal) {

        int decimal = octalToDecimal(octal);

        return decimalToBinary(decimal);
    }

    // Octal to Hexadecimal
    public String octalToHex(String octal) {

        int decimal = octalToDecimal(octal);

        return decimalToHex(decimal);
    }
}