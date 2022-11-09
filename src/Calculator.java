/*
 *  Created by Juan Pablo Gutiérez
 *  Copyright (c) 2022. All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        print("Welcome to the calculator");

        boolean isRunning = true;

        while (isRunning) {

            print("Number of data sets: ");
            int num = in.nextInt();
            List<Double> data = new ArrayList<Double>();
            for (int i = 0; i < num; i++) {
                print("Enter data set #" + (i + 1));
                data.add(in.nextDouble());
            }

            showResults(data);

            print("Do you want to continue? (y/n)");
            char answer = in.next().charAt(0);
            switch (answer) {
                case 'y':
                    isRunning = true;
                    break;
                case 'n':
                    isRunning = false;
                    break;
                default:
                    isRunning = true;
                    break;
            }
        }
        in.close();
    }

    public static void showResults(List<Double> data) {
        print("The results are:");
        System.out.println("Average: " + getAverage(data));
        getVariance(data, true);
        System.out.println("Standard Deviation (Error): " + getStandardDeviation(data));
        System.out.println("Rounded Standard Deviation: " + roundDeviation(getStandardDeviation(data)));
    }

    public static double getAverage(List<Double> data) {
        double sum = 0;
        for (double d : data) {
            sum += d;
        }
        double average = sum / data.size();
        return average;
    }

    public static List<Double> getDeviation(List<Double> data) {
        List<Double> deviation = new ArrayList<Double>();
        double average = getAverage(data);
        for (double d : data) {
            deviation.add(d - average);
        }
        return deviation;
    }

    public static double getVariance(List<Double> data, boolean showResults) {
        List<Double> deviation = getDeviation(data);
        double sum = 0;
        double currentNum = 0;
        for (double d : deviation) {
            currentNum = Math.pow(d, 2);
            if (showResults) {
                print("Current deviation: " + d + " Corresponding variance: " + currentNum);
            }
            sum += currentNum;
        }
        double variance = sum / (deviation.size() - 1);
        return variance;
    }

    public static double getStandardDeviation(List<Double> data) {
        double variance = getVariance(data, false);
        double standardDeviation = Math.sqrt(variance);
        return standardDeviation;
    }

    public static double roundDeviation(double deviation) {
        return Math.round(deviation * 100.0) / 100.0;
    }

    public static void print(String text) {
        System.out.println(text);
    }
}
