package com.example.calculatorapp;

/**
 * Handles conversions between decimal, binary,
 * hexadecimal, and octal number systems.
 */
public class Calculation_Bases {

    /**
     * Converts a decimal number to binary.
     *
     * @param number decimal number
     * @return binary representation
     */
    public String decimalToBinary(int number) {

        return Integer.toBinaryString(number);
    }

    /**
     * Converts a decimal number to hexadecimal.
     *
     * @param number decimal number
     * @return hexadecimal representation
     */
    public String decimalToHex(int number) {

        return Integer.toHexString(number).toUpperCase();
    }

    /**
     * Converts a decimal number to octal.
     *
     * @param number decimal number
     * @return octal representation
     */
    public String decimalToOctal(int number) {

        return Integer.toOctalString(number);
    }

    /**
     * Converts a binary number to decimal.
     *
     * @param binary binary number
     * @return decimal value
     */
    public int binaryToDecimal(String binary) {

        return Integer.parseInt(binary, 2);
    }

    /**
     * Converts a binary number to hexadecimal.
     *
     * @param binary binary number
     * @return hexadecimal representation
     */
    public String binaryToHex(String binary) {

        int decimal = binaryToDecimal(binary);

        return decimalToHex(decimal);
    }

    /**
     * Converts a binary number to octal.
     *
     * @param binary binary number
     * @return octal representation
     */
    public String binaryToOctal(String binary) {

        int decimal = binaryToDecimal(binary);

        return decimalToOctal(decimal);
    }

    /**
     * Converts a hexadecimal number to decimal.
     *
     * @param hex hexadecimal number
     * @return decimal value
     */
    public int hexToDecimal(String hex) {

        return Integer.parseInt(hex, 16);
    }

    /**
     * Converts a hexadecimal number to binary.
     *
     * @param hex hexadecimal number
     * @return binary representation
     */
    public String hexToBinary(String hex) {

        int decimal = hexToDecimal(hex);

        return decimalToBinary(decimal);
    }

    /**
     * Converts a hexadecimal number to octal.
     *
     * @param hex hexadecimal number
     * @return octal representation
     */
    public String hexToOctal(String hex) {

        int decimal = hexToDecimal(hex);

        return decimalToOctal(decimal);
    }

    /**
     * Converts an octal number to decimal.
     *
     * @param octal octal number
     * @return decimal value
     */
    public int octalToDecimal(String octal) {

        return Integer.parseInt(octal, 8);
    }

    /**
     * Converts an octal number to binary.
     *
     * @param octal octal number
     * @return binary representation
     */
    public String octalToBinary(String octal) {

        int decimal = octalToDecimal(octal);

        return decimalToBinary(decimal);
    }

    /**
     * Converts an octal number to hexadecimal.
     *
     * @param octal octal number
     * @return hexadecimal representation
     */
    public String octalToHex(String octal) {

        int decimal = octalToDecimal(octal);

        return decimalToHex(decimal);
    }
}