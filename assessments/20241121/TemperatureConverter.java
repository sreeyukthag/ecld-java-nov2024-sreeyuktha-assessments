package tempConverter;
import java.util.Scanner;

public class TemperatureConverter {

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;}

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selection;
        System.out.println("______________Temperature Converter_____________");
        System.out.println("1. To convert Celsius to Fahrenheit");
        System.out.println("2. To convert Fahrenheit to Celsius");
        selection = scanner.nextInt();
        if (selection == 1) {
            System.out.print("Enter temperature in Celsius: ");
            double celsius = scanner.nextDouble();
            double fahrenheit = celsiusToFahrenheit(celsius);
            System.out.printf("Temperature in Fahrenheit: %.1f°F", fahrenheit);
            }
        else if (selection == 2) {
            System.out.print("Enter temperature in Fahrenheit: ");
            double fahrenheit = scanner.nextDouble();
            double celsius = fahrenheitToCelsius(fahrenheit);
            System.out.printf("Temperature in Celsius: %.1f°C", celsius);
            }
        else {
            System.out.println("Invalid choice. Please select a valid option.");
            }
            System.out.println();
        }
    }
