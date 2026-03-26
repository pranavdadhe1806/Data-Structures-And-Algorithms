import java.util.*;

public class LargestNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c;
        System.out.println("Enter 1st number: ");
        a = sc.nextInt();
        System.out.println("Enter 2nd number: ");   
        b = sc.nextInt();
        System.out.println("Enter 3rd number: ");
        c = sc.nextInt();

        if (a >= b && a >= c) {
            System.out.println("The largest number is: " + a);
        } else if (b >= a && b >= c) {
            System.out.println("The largest number is: " + b);
        } else {
            System.out.println("The largest number is: " + c);
        }
        sc.close();
    }
}