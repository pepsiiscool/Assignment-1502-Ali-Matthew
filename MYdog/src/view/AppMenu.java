package view;



import java.util.Scanner;

/**
 * This class is used to display menus and sub-menus to the user,
 * prompt the user for inputs, and validate them.
 *
 * @author Matthew McCusker, Ali Ghazal
 */
public class AppMenu {





    /**
     * The Scanner input is used to take user input.
     */
    private Scanner input;





    /**
     * AppMenu constructor that initializes a Scanner instance.
     *
     * @throws Exception
     * @author Matthew McCusker, Ali Ghazal
     */
    public AppMenu() throws Exception {
        input = new Scanner(System.in);
    }





    /**
     * Prints the welcome message banner for first-time users.
     *
     * @author Matthew McCusker, Ali Ghazal
     */
    public void displayWelcomeMessage() {
        System.out.println(String.format("*****************************************\n" +
                "*     WELCOME TO TOY STORE COMPANY!     *\n" +
                "*****************************************\n"));
    }






    /**
     * Prints the main menu.
     *
     * @author Matthew McCusker, Ali Ghazal
     * @return userInput
     */
    public String displayMainMenu() {
        String userInput = "";
        System.out.println("How May We Help you?\n\n" +
                "(1)\tSearch Inventory and Purchase Toys\n" +
                "(2)\tAdd New Toy\n" +
                "(3)\tRemove Toy\n" +
                "(4)\tSave & Exit\n\n" +
                "Enter an Option:");

        userInput = input.nextLine();
        return userInput;
    }





    /**
     * Prints the search sub-menu.
     *
     * @author Matthew McCusker, Ali Ghazal
     * @return userInput
     */
    public String displaySearchMenu() {
        String userInput = "";
        System.out.println("Find Toys With: \n\n" +
                "(1)\tSerial Number(SN)\n" +
                "(2)\tToy Name\n" +
                "(3)\tToy Type\n" +
                "(4)\tBack to Main Menu");

        userInput = input.nextLine();
        return userInput;
    }




    /**
     * Prints user prompt based on how they want to search for the toy and returns a string.
     *
     * @author Matthew McCusker, Ali Ghazal
     * @param type
     * @return userInput
     */
    public String getToySearchPrompt(String type) {
        String userInput = "";
        System.out.println("Enter " + type + ": ");

        userInput = input.nextLine();
        return userInput;
    }







    /**
     * Prints user prompt based on how they want to search for the toy and returns an int.
     *
     * @author Matthew McCusker, Ali Ghazal
     * @param type
     * @return userInput
     */
    public int getToySearchPromptInt(String type) {
        int userInput = 0;
        userInput = input.nextInt();
        return userInput;
    }



}
