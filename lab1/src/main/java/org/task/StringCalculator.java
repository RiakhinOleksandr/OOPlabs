package org.task;

import java.util.Scanner;

public class StringCalculator {

    public static boolean error = false;

    public static void main(String[] args) {
        String exit = "n";
        StringCalculator calc = new StringCalculator();
        Scanner sc = new Scanner(System.in);
        String numbers;
        System.out.println("This is a String Calculator program");
        while (!(exit.equals("y"))) {
            System.out.println("Enter numbers, separated by comma or \\n symbol: ");
            numbers = sc.nextLine();
            numbers = numbers.replace("\\n", "\n");
            int sum = calc.add(numbers);
            if (!StringCalculator.error) {
                System.out.println("Sum of your numbers is " + sum);
            }
            System.out.println("Do you want to stop(y - stop)");
            exit = sc.nextLine();
        }
        sc.close();
    }

    public int add(String numbers) {
        StringCalculator.error = false;
        String number = "";
        boolean neg_number = false;
        char x;
        int sum = 0;
        if (numbers.isEmpty()) {
            return sum;
        } else {
            try {
                for (int i = 0; i < numbers.length(); i++) {
                    x = numbers.charAt(i);
                    if (Character.isDigit(x)) {
                        number = number + x;
                    } else if (x == ',' | x == '\n') {
                        if (!(number.isEmpty())) {
                            if (!neg_number) {
                                sum += Integer.parseInt(number);
                            } else {
                                sum -= Integer.parseInt(number);
                            }
                            neg_number = false;
                            number = "";
                        } else {
                            StringCalculator.error = true;
                            throw new TwoDenominatorsInARow("There can't be two denominators in a row");
                        }
                    } else if (x == '-') {
                        if (number.isEmpty()) {
                            neg_number = true;
                        } else {
                            StringCalculator.error = true;
                            throw new WrongDenominator("You've used wrong denominator");
                        }
                    } else {
                        StringCalculator.error = true;
                        throw new WrongDenominator("You've used wrong denominator");
                    }
                }
                if (!(number.isEmpty())) {
                    if (!neg_number) {
                        sum += Integer.parseInt(number);
                    } else {
                        sum -= Integer.parseInt(number);
                    }
                }
            } catch (WrongDenominator | TwoDenominatorsInARow e) {
                System.out.println("There were exception. " + e.getMessage());
                return 0;
            }
        }
        return sum;
    }
}

class WrongDenominator extends Exception {
    public WrongDenominator(String str) {
        super(str);
    }
}

class TwoDenominatorsInARow extends Exception {
    public TwoDenominatorsInARow(String str) {
        super(str);
    }
}