
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of rows: ");
        int rows = sc.nextInt();
        System.out.println("Enter no. of columns: ");
        int col = sc.nextInt();
        System.out.println("Enter the character which you want to print (A-Z/0-9/!-*): ");
        char element = sc.next().charAt(0); 

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < col; j++){
                System.out.print(element + " ");
            }

            System.out.println("");
        }

        sc.close();
    }
}