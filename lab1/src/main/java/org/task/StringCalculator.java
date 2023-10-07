package org.task;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Comparator;
import java.util.Collections;

public class StringCalculator{

    public static Pattern p = Pattern.compile("//(\\[.*\\])*\\n");
    public static Pattern p2 = Pattern.compile("[^\\[\\]]+");

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
                System.out.println("Sum of your numbers which are less than 1001 is: " + sum);
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
        boolean denominators_added = false;
        boolean isdenominator = false;
        ArrayList<Integer> neg_numbers = new ArrayList<>();
        ArrayList<String> denominators = new ArrayList<>();
        Matcher m = p.matcher(numbers);
        if(numbers.isEmpty()) {
            return sum;
        } else {
            try {
                int z;
                if(m.find()){
                    z = m.group().length();
                    denominators = StringCalculator.add_user_denominator(denominators, m.group(1));
                    denominators_added = true;
                } else {
                    z = 0;
                }
                for (int i = z; i < numbers.length(); i++) {
                    isdenominator = false;
                    x = numbers.charAt(i);
                    if (x == '\n' | x == ',') {
                        isdenominator = true;
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
                    } else if(denominators_added){
                        for(String s : denominators){
                            if(numbers.length() >= i + s.length()){
                                if(s.equals(numbers.substring(i, i + s.length()))){
                                    isdenominator = true;
                                    i += s.length() - 1;
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
                                    break;
                                }
                            }
                        }
                    }
                    if(!isdenominator) {
                        if (Character.isDigit(x)) {
                            number = number + x;
                        } else if (x == '-') {
                            if (number.isEmpty()) {
                                neg_number = true;
                            } else {
                                throw new WrongDenominator("You've used wrong denominator");
                            }
                        } else {
                            throw new WrongDenominator("You've used wrong denominator");
                        }
                    }
                }
                if (!number.isEmpty()) {
                    if(neg_number){
                        neg_numbers.add(-Integer.parseInt(number));
                    } else {
                        if(Integer.parseInt(number) <= 1000) {
                            sum += Integer.parseInt(number);
                        }
                    }
                }
                if(!neg_numbers.isEmpty()){
                    System.out.println("You've used negative numbers:");
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

    public static ArrayList<String> add_user_denominator(ArrayList<String> denominators, String p){
        Matcher m = p2.matcher(p);
        while(m.find()){
            denominators.add(m.group());
        }
        denominators.sort(Comparator.comparing(String::length));
        Collections.reverse(denominators);
        return denominators;
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