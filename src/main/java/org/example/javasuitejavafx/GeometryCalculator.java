package org.example.javasuitejavafx;
import java.util.Scanner;

public class GeometryCalculator {

    // input is the value and output is the metric
    private static double inchConversion(double input, String output) {
        return switch (output.toLowerCase()) {
            case "yard" -> (input / 36);
            case "centimeter" -> (input * 2.54);
            case "feet" -> (input / 12);
            case "meter" -> (input / 39.3700787);
            default -> 0.0;
        };
    }

    private static double yardConversion(double input, String output) {
        return switch (output) {
            case "inch" -> (input * 36);
            case "feet" -> (input * 3);
            case "centimeter" -> (input * 91.44);
            case "meter" -> (input * 1.0936133);
            default -> 0.0;
        };
    }

    private static double centimeterConversion(double input, String output) {
        return switch (output) {
            case "inch" -> (input / 2.54);
            case "feet" -> (input / 30.48);
            case "yard" -> (input / 91.44);
            case "meter" -> (input / 100);
            default -> 0.0;
        };
    }

    private static double feetConversion(double input, String output) {
        return switch (output) {
            case "inch" -> (input * 12);
            case "centimeter" -> (input * 30.48);
            case "yard" -> (input / 3);
            case "meter" -> (input / 3.2808399);
            default -> 0.0;
        };
    }

    private static double meterConversion(double input, String output) {
        return switch (output) {
            case "inch" -> (input * 39.37007870);
            case "centimeter" -> (input * 100);
            case "yard" -> (input * 1.0936133);
            case "feet" -> (input * 3.2808399);
            default -> 0.0;
        };
    }

    private static double twoDimensional(String shape, double length, double width, double height, double radius) {
        return switch (shape.toLowerCase()) {
            case "rectangle" -> (length * width);
            case "triangle" -> {
                double triTotal = length * height;
                yield (triTotal / 2);
            }
            case "square" -> (length * length);
            case "circle" -> {
                double pi = 3.14;
                yield (pi * (radius * radius));
            }
            case "trapezoid" -> {
                double baseTotal = (length + width); // let length represent base 1, width represent base 2
                yield (baseTotal * height) * .5;
            }
            default -> 0.0;
        };
    }

    private static double threeDimensional(String shape, double length, double width, double height, double radius, double length2) {
        return switch (shape.toLowerCase()) {
            case "pyramid" -> ((length * width * height) / 3);
            case "rectangular prism" -> (length * width * height);
            case "sphere" -> {
                double pi = 3.14;
                yield (pi * (Math.pow(radius, 3)) * ((double) 3 / 4));
            }
            case "trapezoidal prism" -> ((height * width * (length + length2)) / 2);
                // let length = base 1 and length2 = base 2
                // let width represent the length of the prism
            default -> 0.0;
        };
    }

    public static String convert(String inputType, double input, String output) {
        // convert to (selection) pass in (input value) return as (output)
        // outputs: Inch, Feet, Yard, Centimeter, Meter
        return switch (inputType.toLowerCase()) {
            case "inch" -> String.format("%.2f", inchConversion(input, output)) + "in";
            case "feet" -> String.format("%.2f", feetConversion(input, output)) + "ft";
            case "yard" -> String.format("%.2f", yardConversion(input, output)) + "yd";
            case "centimeter" -> String.format("%.2f", centimeterConversion(input, output)) + "cm";
            case "meter" -> String.format("%.2f", meterConversion(input, output)) + "m";
            default -> "Error";
        };
    }

    public static String area(String shape, double length, double width, double height, double radius) {
        return String.format("%.2f", twoDimensional(shape, length, width, height, radius));
    }

    public static String volume(String shape, double length, double width, double height, double radius, double length2) {
        return String.format("%.2f", threeDimensional(shape, length,  width, height, radius, length2));
    }

    public static void main(String[] args) {
        System.out.println(area("trapezoid", 47, 25, 30, 0));
    }

}
