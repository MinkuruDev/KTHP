package KTHP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = readFile();
        if(numbers.size() == 0){
            System.out.println("File khong co so hop le");
            return;
        }
        System.out.println("Cac so hoan hao:");
        for(int number : numbers){
            if(isPerfect(number))
                System.out.println(number);
        }
    }

    public static boolean isPerfect(int number){
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;
                if (i * i != number) {
                    sum += number / i;
                }
            }
        }
        return sum == number;
    }

    public static ArrayList<Integer> readFile(){
        String path = "KTHP/INPUT.DAT";
        File file = new File(path);
        ArrayList<Integer> numbers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
        
            while(scanner.hasNext()){
                numbers.add(scanner.nextInt());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return numbers;
    }
}
