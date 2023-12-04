import java.util.Scanner;

public class Shop{

    public static int Shop1(){

        int speed = 5;
        int length = 10;
        int balls = 10;
        int fire = 10;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Shop!");
        System.out.println("--------------------------------'");
        System.out.println("Select the desired upgrade:");
        System.out.println("1 - Speed Upgrade");
        System.out.println("2 - Double Lenght Upgrade");
        System.out.println("3 - Triple Balls Upgrade");
        System.out.println("4 - Fire balls Upgrade");

        switch (scanner.next()) {
            case "1":

                if (Player.Currency() >= speed){


                }
                
                break;

            case "2":

                break;

            case "3":

                break;

            case "4":

                break;
        
            default:
                break;
        }

        return 0;

    }


    }
