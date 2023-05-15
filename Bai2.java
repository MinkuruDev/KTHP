package KTHP;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap n: ");
        int n = scanner.nextInt();
        int[] A = generateNumbers(n, 0, 100);
        System.out.println("Mang A gom n phan tu:");
        for(int i: A){
            System.out.print(i + " ");
        }
        System.out.println();
        BinarySearchTree bst = new BinarySearchTree(A);
        System.out.println("Cay nhi phan tim kiem tu mang A duyet tien thu tu:");
        bst.preOrderTraversal(bst.getRoot());
        System.out.println();
        scanner.close();
    }

    public static int[] generateNumbers(int elements, int min, int max){
        int[] res = new int[elements];
        for(int i = 0; i<elements; i++){
            res[i] = (int) (Math.random() * (max - min));
        }
        return res;
    }
}
