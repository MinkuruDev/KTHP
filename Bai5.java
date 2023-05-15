package KTHP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bai5 {
    static int m = 0;
    static String gene = "";
    public static void main(String[] args) {
        
    }

    public static boolean isPureGene(String gene, int m){
        int n = gene.length();
        for(int i = 1; i<=m; i++){
            String seg = "";
            int j = 0;
            for(; j<i; j++){
                seg += gene.charAt(j);
            }
            while(j < n){
                if(seg.charAt(j % i) == gene.charAt(j))
                    continue;
                j++;
            }
            if(j == n){
                
                return;
            }
        }
        return false;
    }

    public static void readFile(){
        String path = "KTHP/PURE.INP";
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            gene = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
