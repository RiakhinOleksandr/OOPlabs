package org.task;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Comparator;
import java.util.Collections;

public class StringCalculator{

    // Цей регулярний вираз шукає чи задав користувач роздільник, чи ні
    public static Pattern p = Pattern.compile("//(\\[.*\\])*\\n");
    // Цей регулярний вираз шукає усі роздільники, що задав користувач
    public static Pattern p2 = Pattern.compile("[^\\[\\]]+");

    public static void main(String[] args){
        String exit = "n";
        StringCalculator calc = new StringCalculator();
        Scanner sc = new Scanner(System.in);
        String numbers;
        System.out.println("This is a String Calculator program");
        while(!exit.equals("y")) { // Програма закінчить роботу коли користувач введе "y"
            System.out.println("Enter numbers, separated by comma or \\n symbol: ");
            numbers = sc.nextLine(); // Отримуємо рядок від користувача
            numbers = numbers.replace("\\n", "\n");
            int sum = calc.add(numbers);
            if (sum != -1) { // Якщо не виникла помилка, то виводимо результат
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
        boolean neg_number = false; // Ця змінна відповідає за наявність у рядку негативних чисел
        boolean denominators_added = false; // Ця змінна відповідає за додання користувачем розділювачем
        boolean isdenominator = false; // Ця змінна показує чи є наш символ роздільником чи ні
        ArrayList<Integer> neg_numbers = new ArrayList<>(); // Тут зберігаються усі від'ємні числа
        ArrayList<String> denominators = new ArrayList<>(); // Тут зберігаються роздільники, задані користувачем
        Matcher m = p.matcher(numbers);
        if(numbers.isEmpty()) {
            return sum;
        } else {
            try {
                int z; // Ця змінна відповідає за те, з якого елемента починаємо рахувати суму
                if(m.find()){
                    z = m.group().length();
                    denominators = StringCalculator.add_user_denominator(denominators, m.group(1));
                    denominators_added = true;
                } else {
                    z = 0;
                }
                for (int i = z; i < numbers.length(); i++) {
                    isdenominator = false;
                    x = numbers.charAt(i); // Проходимось по кожному символу з рядка
                    if (x == '\n' | x == ',') {
                        isdenominator = true;
                        if (!number.isEmpty()) {
                            if(neg_number){ // Якщо число від'ємне, додаємо його у список негативних чисел
                                neg_numbers.add(-Integer.parseInt(number));
                                neg_number = false;
                            } else { // В іншому випадку додаємо його до суми
                                if(Integer.parseInt(number) <= 1000){
                                    sum += Integer.parseInt(number);
                                }
                            }
                            number = ""; // Обнуляємо число
                        } else { // Якщо до роздільника не було числа, видаємо помилку
                            throw new TwoDenominatorsInARow("There can't be two denominators in a row");
                        }
                    } else if(denominators_added){
                        for(String s : denominators){ // Проходимось по роздільникам, доданими користувачем
                            if(numbers.length() >= i + s.length()){ // Роздільник має "вміститись" у залишений рядок
                                if(s.equals(numbers.substring(i, i + s.length()))){
                                    isdenominator = true;
                                    i += s.length() - 1;
                                    if (!number.isEmpty()) {
                                        if(neg_number){ // Якщо число від'ємне, додаємо його у список негативних чисел
                                            neg_numbers.add(-Integer.parseInt(number));
                                            neg_number = false;
                                        } else { // В іншому випадку додаємо його до суми
                                            if(Integer.parseInt(number) <= 1000){
                                                sum += Integer.parseInt(number);
                                            }
                                        }
                                        number = ""; // Обнуляємо число
                                    } else { // Якщо до роздільника не було числа, видаємо помилку
                                        throw new TwoDenominatorsInARow("There can't be two denominators in a row");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if(!isdenominator) { // Якщо символ - не роздільник, дивимось чи він цифра
                        if (Character.isDigit(x)) {
                            number = number + x;
                        } else if (x == '-') {
                            if (number.isEmpty()) { // Якщо знак "-" стоїть перед числом і не є роздільником, число від'ємне
                                neg_number = true;
                            } else { // Якщо знак "-" стоїть посеред числа, видаємо помилку
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
                if(!neg_numbers.isEmpty()){ // Якщо в нас було хоч одне від'ємне число видаємо помилку
                    System.out.println("You've used negative numbers:");
                    for(int i : neg_numbers){
                        System.out.printf("%d ", i); // Виводимо всі від'ємні числа
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