import java.util.Scanner;


public class Menu{
//
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Menu");

        System.out.println("S - SinglePlayer");
        System.out.println("M - Multiplayer");
        System.out.println("O - Options");
        System.out.println("C - Controls");
        System.out.println("E - Exit");

        String option = scanner.nextLine();
        switch (option.toUpperCase()) {
            case "S": //singleplay

            
                break;

            case "M": //multi

                Renderer.start();

                break;

            case "O": //options

                Menu.Options();

                break;

            case "C": //controls

                System.out.println("CONTROLS PLAYER 1");
                System.out.println("--------------------------------");
                System.out.println("WASD - Move");
                System.out.println("F - Use Upgrades");
                System.out.println("--------------------------------");
                System.out.println("CONTROLS PLAYER 2");
                System.out.println("Arrow Keys - Move");
                System.out.println("0 - Use Upgrades");
                System.out.println("--------------------------------");

                break;

            case "E": //exit

                Menu.Exit();

                break;
        
            default:
                System.out.println("Invalid Input");
                break;
        }

        scanner.close();

    }

    public static void Exit(){

        System.exit(0);

    }

    public static void Options(){


    }

}