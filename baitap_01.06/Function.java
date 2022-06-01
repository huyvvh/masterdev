import java.util.*;
import java.util.Scanner;
public class Function {

    /* Function1:
        INPUT : int[] arr
        OUTPUT : Sum of all elements in the array
    */
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    /* Function2:
        INPUT : String[] arr
        OUTPUT : Elements occuring most in the String array
    */
    public static void mostFrequentElement(String s){
        int max = 0;
        String ans = "";
        for(int i = 0; i<s.length(); i++){
            int count = 0;
            for(int j = i; j<s.length(); j++){
                if(s.charAt(i) == s.charAt(j)){
                    count++;
                }
            }
            if(count > max){
                max = count;
                ans = s.charAt(i) + "";
            }
        }
        System.out.print(ans);
    }
    
    /*
    FUNCTION 3:
        INPUT: int[] arr
        OUTPUT: arr after sorting
    */
    public static int[] sortArray(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    /*
    FUNCTION 4:
        INPUT: int number
        OUTPUT: TRUE if number is prime, FALSE otherwise
    */
    public static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /*
    FUNCTION 5:
        INPUT: int a,b,c
        OUTPUT: Triangle area with a,b,c, Exception if not a triangle
    */
    public static double triangleArea(int a, int b, int c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Not a triangle");
        }
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    /*
    FUNCTION 6:
        INPUT: radius
        OUTPUT: Draw a circle with * and radius
    */
    public static void printCircle(int r){
        double dist;
        int diameter = 2 * r;
        for(int i=0; i<=diameter; i++){
            for(int j=0; j<=diameter; j++){
                dist = Math.sqrt(Math.pow(i-r,2) + Math.pow(j-r,2));
                if(dist > (r-0.5) && dist < (r+0.5)){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    //Print choice
    public static void printChoices(){
        System.out.println("Function: \n1. Sum of all elements in the array \n2. Elements occuring most in the String array \n3. Sort the array \n4. Check if number is prime \n5. Area of a triangle \n6. Print Circle \nYour choice:");
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        printChoices();
        int choice = scan.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter the size of the array");
                int[] arr = new int[scan.nextInt()];
                for(int i = 0; i<arr.length; i++){
                    System.out.println("Input element " + (i+1) + ":");
                    arr[i] = scan.nextInt();
                }
                System.out.println("Sum of this array = "+ sumArray(arr));
                break;
            case 2:
                System.out.println("Enter the string");
                String s = scan.next();
                System.out.print("Most frequent element = ");
                mostFrequentElement(s);
                break;
            case 3:
                System.out.println("Enter the size of the array");
                int[] arr1 = new int[scan.nextInt()];
                for(int i = 0; i<arr1.length; i++){
                    System.out.println("Input element " + (i+1) + ":");
                    arr1[i] = scan.nextInt();
                }
                System.out.println("Sorrting : " + Arrays.toString(sortArray(arr1)));
                break;
            case 4:
                System.out.print("Enter the number: ");
                int number = scan.nextInt();
                if(isPrime(number)){
                    System.out.println("True");
                }
                else{
                    System.out.println("False");
                }
                break;
            case 5:
                System.out.println("Enter the sides of the triangle");
                int a = scan.nextInt();
                int b = scan.nextInt();
                int c = scan.nextInt();
                System.out.println("The area of triangle = " + triangleArea(a,b,c));
                break;
            case 6:
                System.out.println("Enter the radius of the circle");
                int r = scan.nextInt();
                printCircle(r);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
