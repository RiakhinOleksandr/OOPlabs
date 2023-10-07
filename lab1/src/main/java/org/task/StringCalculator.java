package org.task;

import java.util.Scanner;
import java.util.ArrayList;

public class StringCalculator{
    public static void main(String[] args){
        String exit = "n";
        StringCalculator calc = new StringCalculator();
        Scanner sc = new Scanner(System.in);
        String numbers;
        System.out.println("This is a String Calculator program");
        while(!(exit.equals("y"))) {
            System.out.println("Enter numbers, separated by comma or \\n symbol: ");
            numbers = sc.nextLine();
            numbers = numbers.replace("\\n", "\n");
            int sum = calc.add(numbers);
            if (sum != -1) {
                System.out.println("Sum of your numbers is " + sum);
            }
            System.out.println("Do you want to stop(y - stop)");
            exit = sc.nextLine();
        }
        sc.close();
    }

    public int add(String numbers){
        String number = "";
        char x;
        int sum = 0;
        boolean neg_number = false;
        ArrayList<Integer> neg_numbers = new ArrayList<>();
        ArrayList<Character> denominators = new ArrayList<>();
        denominators.add(',');
        denominators.add('\n');
        if(numbers.isEmpty()) {
            return sum;
        } else {
            try {
                int z = 0;
                if(numbers.charAt(0) == '/' & numbers.length() > 3){
                    if(numbers.charAt(1) == '/' & numbers.charAt(3) == '\n'){
                        denominators.add(numbers.charAt(2));
                        z = 4;
                    }
                }
                for (int i = z; i < numbers.length(); i++) {
                    x = numbers.charAt(i);
                    if (denominators.contains(x)) {
                        if (!number.isEmpty()) {
                            if(neg_number){
                                neg_numbers.add(-Integer.parseInt(number));
                                neg_number = false;
                            } else {
                                if(Integer.parseInt(number) <= 1000){
                                    sum += Integer.parseInt(number);
                                }
                            }
                            number = "";
                        } else {
                            throw new TwoDenominatorsInARow("There can't be two denominators in a row");
                        }
                    } else if (Character.isDigit(x)) {
                        number = number + x;
                    } else if(x == '-'){
                        if(number.isEmpty()){
                            neg_number = true;
                        } else {
                            throw new WrongDenominator("You've used wrong denominator");
                        }
                    } else {
                        throw new WrongDenominator("You've used wrong denominator");
                    }
                }
                if (!number.isEmpty()) {
                    if(neg_number){
                        neg_numbers.add(-Integer.parseInt(number));
                    } else {
                        if(Integer.parseInt(number) <= 1000){
                            sum += Integer.parseInt(number);
                        }
                    }
                }
                if(!neg_numbers.isEmpty()){
                    System.out.println("You've used negative numbers");
                    for(int i : neg_numbers){
                        System.out.printf("%d ", i);
                    }
                    System.out.println("");
                    throw new NegativeNumbers("You can't use negative numbers");
                }
            } catch(WrongDenominator | TwoDenominatorsInARow | NegativeNumbers e){
                System.out.println("There were exception. " + e.getMessage());
                return -1;
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

class NegativeNumbers extends Exception{
    public NegativeNumbers(String str) {
        super(str);
    }
}