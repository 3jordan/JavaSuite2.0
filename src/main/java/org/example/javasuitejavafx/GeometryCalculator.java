package org.example.javasuitejavafx;

public class GeometryCalculator {

    public static String convert(String inputType, double input, String outputType) {
        double result = 0.0;
        String outputUnit = "";
        // Handle conversion based on input and output types
        switch (inputType.toLowerCase()) {
            case "inch":
                result = convertFromInch(input, outputType);
                outputUnit = "in";
                break;
            case "feet":
                result = convertFromFeet(input, outputType);
                outputUnit = "ft";
                break;
            case "yard":
                result = convertFromYard(input, outputType);
                outputUnit = "yd";
                break;
            case "centimeter":
                result = convertFromCentimeter(input, outputType);
                outputUnit = "cm";
                break;
            case "meter":
                result = convertFromMeter(input, outputType);
                outputUnit = "m";
                break;
            default:
                return "Error: Invalid input type";
        }
        return String.format("%.2f", result) + outputUnit;
    }


    private static double convertFromInch(double input, String outputType) {
        switch (outputType.toLowerCase()) {
            case "inch":
                return input;
            case "feet":
                return input / 12;
            case "yard":
                return input / 36;
            case "centimeter":
                return input * 2.54;
            case "meter":
                return input * 0.0254;
            default:
                return 0.0;
        }
    }

    private static double convertFromFeet(double input, String outputType) {
        switch (outputType.toLowerCase()) {
            case "inch":
                return input * 12;
            case "feet":
                return input;
            case "yard":
                return input / 3;
            case "centimeter":
                return input * 30.48;
            case "meter":
                return input * 0.3048;
            default:
                return 0.0;
        }
    }

    private static double convertFromYard(double input, String outputType) {
        switch (outputType.toLowerCase()) {
            case "inch":
                return input * 36;
            case "feet":
                return input * 3;
            case "yard":
                return input;
            case "centimeter":
                return input * 91.44;
            case "meter":
                return input * 0.9144;
            default:
                return 0.0;
        }
    }

    private static double convertFromCentimeter(double input, String outputType) {
        switch (outputType.toLowerCase()) {
            case "inch":
                return input / 2.54;
            case "feet":
                return input / 30.48;
            case "yard":
                return input / 91.44;
            case "centimeter":
                return input;
            case "meter":
                return input / 100;
            default:
                return 0.0;
        }
    }

    private static double convertFromMeter(double input, String outputType) {
        switch (outputType.toLowerCase()) {
            case "inch":
                return input * 39.3700787;
            case "feet":
                return input * 3.2808399;
            case "yard":
                return input * 1.0936133;
            case "centimeter":
                return input * 100;
            case "meter":
                return input;
            default:
                return 0.0;
        }
    }
}
