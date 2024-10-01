import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StatsCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter file name (with extension, e.g., numbers.txt): ");
        String fileName = input.nextLine();

        // Add this line to handle the src directory
        String filePath =  fileName;

        try {
            // Open the file from src/numbers folder
            File file = new File(filePath);
            Scanner fileReader = new Scanner(file);

            // Debug: Check if the file is found and opened
            System.out.println("File opened successfully!");

            // Read the number of data points
            if (fileReader.hasNextInt()) {
                int numDataPoints = fileReader.nextInt();
                System.out.println("Number of data points: " + numDataPoints);

                double[] numbers = new double[numDataPoints];

                // Read all the numbers into the array
                for (int i = 0; i < numDataPoints; i++) {
                    if (fileReader.hasNextDouble()) {
                        numbers[i] = fileReader.nextDouble();
                    } else {
                        System.out.println("Expected a number, but didn't find one.");
                        return;
                    }
                }

                // Perform the calculations
                double mean = calculateMean(numbers);
                double standardDeviation = calculateStandardDeviation(numbers, mean);

                // Output the results
                System.out.println("The numbers are: ");
                for (double number : numbers) {
                    System.out.print(number + " ");
                }
                System.out.println("\nThe mean is: " + mean);
                System.out.println("The standard deviation is: " + standardDeviation);

            } else {
                System.out.println("The first element in the file is not a valid integer.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    // Method to calculate the mean
    public static double calculateMean(double[] numbers) {
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    // Method to calculate the standard deviation
    public static double calculateStandardDeviation(double[] numbers, double mean) {
        double sumSquaredDifferences = 0;
        for (double number : numbers) {
            sumSquaredDifferences += Math.pow(number - mean, 2);
        }
        return Math.sqrt(sumSquaredDifferences / numbers.length);
    }
}
