package controller;

import model.*;
import view.AppMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AppManager {


    static int list_size; // variable to store the size list.
    private final String FILE_PATH = "./res/toys.txt";
    private AppMenu storeMenu = new AppMenu();
    private ArrayList<Toy> toyType = new ArrayList<>();


    public AppManager() throws Exception {
        loadToys();
        storeMenu.displayWelcomeMessage();
        launchApplication();
    }

    private void loadToys() throws FileNotFoundException {
        File txt = new File(FILE_PATH);//Creates a File object called txt using FILE_PATH.
        if (txt.exists()) {

            Scanner inputFile = new Scanner(txt);//Reads the file
            while (inputFile.hasNext()) {
                String thisLine = inputFile.nextLine();
                String[] separateLine = thisLine.split(";"); //separates thisLine with ";" into an array of strings
                Toy toy = createToyInstance(separateLine); //Uses createToyInstance to make toy data type
                if (toy != null) {
                    toyType.add(toy);
                }
            }
        }
    }

    private Toy createToyInstance(String[] separateLine) {
        int firstDigit = Integer.parseInt(separateLine[0].substring(0, 1)); // First digit of the serialNum as an int
        if (firstDigit == 0 || firstDigit == 1) {
            return createFiguresInstance(separateLine);// method that creates figure based on the separateLine array
        } else if (firstDigit >= 4 && firstDigit <= 6) {
            return createPuzzlesInstance(separateLine);
        } else if (firstDigit == 2 || firstDigit == 3) {
            return createAnimalsInstance(separateLine);
        } else if (firstDigit >= 7 && firstDigit <= 9) {
            return createBoardGamesInstance(separateLine);
        }
        return null;
    }


    private Figures createFiguresInstance(String[] separateLine) {
        char classification = separateLine[6].charAt(0);
        return new Figures(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), classification);
    }

    private Puzzles createPuzzlesInstance(String[] separateLine) {
        char puzzleType = separateLine[6].charAt(0);
        return new Puzzles(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), puzzleType);
    }

    private Animals createAnimalsInstance(String[] separateLine) {
        char size = ' ';// takes variable type Char
        if (separateLine.length > 7 && separateLine[7] != null) {
            size = separateLine[7].charAt(0);
        }
        return new Animals(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), separateLine[6], size);
    }

    private BoardGames createBoardGamesInstance(String[] separateLine) {
        List<String> list = new ArrayList<>();
        if (separateLine.length > 7 && separateLine[7] != null) {
            if (separateLine[7].contains(",")) {
                String[] st = separateLine[7].split(",");
                list.addAll(Arrays.asList(st));
            } else {
                list.add(separateLine[7]);
            }
        }
        return new BoardGames(separateLine[0], separateLine[1], separateLine[2], Float.parseFloat(separateLine[3]), Integer.parseInt(separateLine[4]), Integer.parseInt(separateLine[5]), separateLine[6], list);
    }


    public void launchApplication() throws IOException {

        String userInput = storeMenu.displayMainMenu(); //prompts the main menu and returns a string digit number 1-4

        int userInputInt = Integer.parseInt(userInput); //Digit number is used as param for option

        if (userInputInt < 1 || userInputInt > 4) {
            System.out.println("\n" + userInputInt + " is not a valid option! Try again.\n");
            launchApplication();
        }//^^  if  statement verifies that correct options are picked


        switch (userInputInt) {
            case 1:
                search();
                break;
            case 2: 
                    addToy();
                break;
            case 3: 
                    removeToy();
                break;
            case 4: 
                    save();
                break;
        }
    }









//    public void search() throws IOException {
//
//
//        String userInput = storeMenu.displaySearchMenu(); // get user input
//        int userInputInt = Integer.parseInt(userInput); // parse from string to int (convert)
//        if (userInputInt < 1 || userInputInt > 4) { // checking input range...
//            System.out.println("\n" + userInputInt + " is not a valid option! Try again.\n");
//            search();
//        }
//        switch (userInputInt) {
//            case 1: // if input 1
//                serialSearch();
//                break;
//            case 2: // if input 2
//                nameSearch();
//                break;
//            case 3: // if input 3
//                typeSearch();
//                break;
//            case 4: // if input 4
//                launchApplication();
//                break;
//        }
//    }
    
    
    public List<String> serialSearch(String serialNumberString) {
        List<String> searchResults = new ArrayList<>();
        Toy toyFound = null;

        // If serialNumberString is empty or null, add all the items to the searchResults list
        if (serialNumberString == null || serialNumberString.isEmpty()) {
            for (Toy toy : toyType) {
                searchResults.add("[" + (toyType.indexOf(toy) + 1) + "] " + toy.toString());
            }
        } else {
            // If serialNumberString is not empty or null, search for the specific serial number
            for (Toy toy : toyType) {
                if (toy.getSerialNumber().equals(serialNumberString)) {
                    toyFound = toy;
                    searchResults.add("[" + (toyType.indexOf(toy) + 1) + "] " + toy.toString());
                    break;
                }
            }

            if (toyFound == null) {
                searchResults.add("Toy not found");
            } else {
                searchResults.add("[" + (toyType.size() + 1) + "] Back To SearchMenu");
                searchResults.add("Enter option number to purchase: ");
                // You can decide what to do with the rest of the code regarding user input, purchase, and stock availability
                // as it doesn't affect the search results directly.
            }
        }

        return searchResults;
    }


//    public void serialSearch() throws IOException { // defining a method to search a toy by its serial number
//        String serialNumberString = storeMenu.getToySearchPrompt("Toy Serial Number:"); // taking user input for toy serial number
//        Toy ToyFound = null; // initializing a variable to store the selected toy object
//        for (Toy toy : toyType) { // iterating through the toy list to find the toy with the serial number
//            if (toy.getSerialNumber().equals(serialNumberString)) { // checking if the toy serial number matches the input
//                ToyFound = toy; // if found, storing the selected toy object
//                System.out.println("[" + (toyType.indexOf(toy) + 1) + "] " + toy.toString()); // displaying the toy information on the console
//                break; // breaking out of the loop as soon as the toy is found
//            }
//        }
//        if (ToyFound == null) { // if the selected toy object is null
//            System.out.println("Toy not found"); // displaying a message that the toy was not found
//            launchApplication(); // launching the main menu again
//        } else {
//            System.out.println("[" + (toyType.size() + 1) + "] Back To SearchMenu"); // displaying a menu option to go back to search menu
//            System.out.println("Enter option number to purchase: "); // prompting the user to enter an option number to purchase the selected toy
//            int input = storeMenu.getToySearchPromptInt(String.valueOf(toyType.size() + 1)); // taking user input for the option number
//            if (input == toyType.size() + 1) { // if the user selects the option to go back to search menu
//                search(); // invoking the search() method to go back to the search menu
//            } else {
//                boolean isSerialFound = false; // initializing a boolean variable to check if the selected toy serial number exists
//                for (int i = 0; i < toyType.size(); i++) { // iterating through the toy list to check if the selected toy serial number exists
//                    if (String.valueOf(ToyFound.getSerialNumber()).equals(toyType.get(i).getSerialNumber())) { // checking if the selected toy serial number matches with the current toy's serial number
//                        isSerialFound = true; // if found, setting the boolean variable to true
//                        if (ToyFound.getAvailableCount() != 0) { // checking if the selected toy is available for purchase
//                            ToyFound.setAvailableCount(ToyFound.getAvailableCount() - 1); // if available, decreasing the available count by 1
//                            System.out.println("Enter Transaction Successfully Terminated!\n"); // displaying a message for a successful transaction
//                        } else { // if not available
//                            System.out.println("Item is out of stock!\n"); // displaying a message that the item is out of stock
//                        }
//                        break; // breaking out of the loop as soon as the selected toy is found
//                    }
//                }
//                if (!isSerialFound) { // if the selected toy serial number is not found
//                    System.out.println("Item is out of stock!\n"); // displaying a message that the item is out of stock
//                }
//                storeMenu.getToySearchPrompt("Press Enter Key to Continue..."); // prompting the user to press Enter key to continue
//                launchApplication(); // launching the main menu again
//            }
//        }
//    }

    
    
    
    
    
    
    public void nameSearch() throws IOException {
        // Take user input for toy name
        String name = storeMenu.getToySearchPrompt("Toy Name:");
        // Create a temporary list to hold data
        List<Toy> tempList = new ArrayList<>();
        // Count the number of toys found with the given name
        int count = 0;
        // Set a flag to determine if the toy was found or not
        boolean isFound = false;

        // Iterate through the list of toys and look for ones that match the given name
        for (Toy toy : toyType) {
            if (toy.getName().equalsIgnoreCase(name) || toy.getName().contains(name)) {
                // Increment the count of toys found with the given name
                count++;
                // Add the toy to the temporary list
                tempList.add(toy);
                // Determine the category of the toy
                String category = "Category:";
                if (toy instanceof BoardGames) {
                    category += "BoardGames";
                } else if (toy instanceof Animals) {
                    category += "Animals";
                } else if (toy instanceof Figures) {
                    category += "Figures";
                } else if (toy instanceof Puzzles) {
                    category += "Puzzles";
                }
                // Print the category and information of the toy found
                System.out.println("[" + count + "] " + category + ", " + toy.toString());
                // Set the flag to true to indicate that a toy was found
                isFound = true;
            }
        }

        // If the toy was found
        if (isFound == true) {
            // Increment the count of toys found with the given name
            count++;
            // Print the option to go back to the search menu
            System.out.println("[" + count + "] Back To SearchMenu");
            System.out.println("Enter option number to purchase: ");
            // Take user input for the option number
            int input = storeMenu.getToySearchPromptInt(String.valueOf(count));
            // If the user chooses to go back to the search menu
            if (input == count) {
                // Invoke the search method to go back to the search menu
                search();
            } else {
                try {
                    // Get the selected toy from the temporary list
                    Toy selectedToy = tempList.get(input - 1);
                    // Check if the toy is available
                    if (selectedToy.getAvailableCount() != 0) {
                        // Decrease the available count of the toy by 1
                        selectedToy.setAvailableCount(selectedToy.getAvailableCount() - 1);
                        // Print a success message
                        System.out.println("Enter Transaction Successfully Terminated!\n");
                    } else {
                        // Print an out of stock message
                        System.out.println("Item is out of stock!\n");
                    }
                } catch (IndexOutOfBoundsException ex) {
                    // Print an out of stock message if the selected toy is not found
                    System.out.println("Item is out of stock!\n");
                }
            }
            // Prompt the user to press Enter key to continue
            storeMenu.getToySearchPrompt("Press Enter Key to Continue...");
            // Launch the application again
            launchApplication();
        }

        // If the toy was not found
        if (isFound == false) {
            // Print a message that the toy was not found
            System.out.println("Toy not found");
            // Launch the application again
            launchApplication();
        }
    }

    public void typeSearch() throws IOException {
// Ask the user what type of toy they want to find
        String toyTypeInput = storeMenu.getToySearchPrompt("What type of toy are you looking for?");

// Create a list to store the toys that match the user's choice
        List<Toy> matchingToys = new ArrayList<>();

// A number to represent the type of toy the user wants
        int toyTypeIndex = 0;

// Check if the user wants animal toys
        if (toyTypeInput.equalsIgnoreCase("animal") || toyTypeInput.equalsIgnoreCase("animals")) {
            toyTypeIndex = 1; // Set the number to 1 for animal toys
        } else if (toyTypeInput.equalsIgnoreCase("boardGames") || toyTypeInput.equalsIgnoreCase("boardGame")) {
            toyTypeIndex = 2; // Set the number to 2 for board game toys
        } else if (toyTypeInput.equalsIgnoreCase("Figures") || toyTypeInput.equalsIgnoreCase("Figure")) {
            toyTypeIndex = 3; // Set the number to 3 for figure toys
        } else if (toyTypeInput.equalsIgnoreCase("puzzles") || toyTypeInput.equalsIgnoreCase("puzzle")) {
            toyTypeIndex = 4; // Set the number to 4 for puzzle toys
        }

// A number to keep track of how many toys we've found
        int count = 1;

// A way to remember if we found any matching toys
        boolean isFound = false;

// Go through the list of all toys
        for (Toy toy : toyType) {
            // If the user wants animal toys (number 1)
            if (toyTypeIndex == 1) {
                // Check if the toy is an animal toy
                if (toy instanceof Animals) {
                    // Add the toy to the matching toys list
                    matchingToys.add(toy);

                    // Show the toy on the screen
                    System.out.println("[" + count + "] " + toy.toString());

                    // Increase the count by 1
                    count++;

                    // Remember that we found a matching toy
                    isFound = true;
                }
            } else if (toyTypeIndex == 2) { // If the user wants board game toys (number 2)
                // Check if the toy is a board game toy
                if (toy instanceof BoardGames) {
                    // (Same logic as for animal toys)
                    matchingToys.add(toy);
                    System.out.println("[" + count + "] " + toy.toString());
                    count++;
                    isFound = true;
                }
            } else if (toyTypeIndex == 3) { // If the user wants figure toys (number 3)
                // Check if the toy is a figure toy
                if (toy instanceof Figures) {
                    // (Same logic as for animal toys)
                    matchingToys.add(toy);
                    System.out.println("[" + count + "] " + toy.toString());
                    count++;
                    isFound = true;
                }
            } else if (toyTypeIndex == 4) { // If the user wants puzzle toys (number 4)
                // Check if the toy is a puzzle toy
                if (toy instanceof Puzzles) {
                    // (Same logic as for animal toys)
                    matchingToys.add(toy);
                    System.out.println("[" + count + "] " + toy.toString());
                    count++;
                    isFound = true;
                }
            }
        }

// If we found matching toys
        if (isFound) {

            System.out.println("[" + count +"] Back To SearchMenu");
            System.out.println("Enter option number to purchase: ");
            int selectedToyIndex = new Scanner(System.in).nextInt();
            if(selectedToyIndex == count)
            {
                search();
            }
            else
            {
                Toy selectedToy = matchingToys.get(selectedToyIndex - 1);
                if (selectedToyIndex <= count) {
                    if(selectedToy.getAvailableCount() > 0) {
                        selectedToy.setAvailableCount(selectedToy.getAvailableCount() - 1);
                        System.out.println("Your transaction was successful!\n");
                    }
                    else
                        System.out.println("Sorry, this toy is currently out of stock.\n");
                } else {
                    System.out.println("Sorry, this toy is currently out of stock.\n");
                }
            }
            String enterkey = storeMenu.getToySearchPrompt("Press Enter Key to Continue...");
            if(enterkey.equals("")){
                launchApplication();
            }
        }

        if(!isFound)
        {
            System.out.println("Sorry toy not found!");

        }

        launchApplication(); // calling the method for the menu..
    }




    public void addToy() throws IOException {
        // Ask for toy details
        String serialNumber = storeMenu.getToySearchPrompt("Enter Serial Number (10 digits only):");

        // Check if the Serial Number is valid
        boolean serialInvalid = false;
        if (serialNumber.length() != 10) {
            serialInvalid = true;
        } else {
            for (int i = 0; i < serialNumber.length(); i++) {
                if (Character.isAlphabetic(serialNumber.charAt(i))) {
                    serialInvalid = true;
                    break;
                }
            }
        }

        for (Toy toy : toyType) {
            if (serialNumber.equals(toy.getSerialNumber())) {
                serialInvalid = true;
                break;
            }
        }

        if (serialInvalid) {
            System.out.println("Oops! Something is wrong with the Serial Number. Try again.");
            addToy();
            return;
        }

        // Ask for more toy details
        String name = storeMenu.getToySearchPrompt("Enter Toy Name:");
        System.out.println("Enter Brand Name:");
        String brand = new Scanner(System.in).nextLine();
        System.out.println("Enter Toy Price:");
        float price = new Scanner(System.in).nextFloat();
        System.out.println("Enter Available Count:");
        int availableCount = new Scanner(System.in).nextInt();
        System.out.println("Enter Appropriate Age:");
        int age = new Scanner(System.in).nextInt();
        System.out.println("Enter Minimum Number Of Players:");
        int minNumberOfPlayers = new Scanner(System.in).nextInt();
        System.out.println("Enter Maximum Number Of Players:");
        int maxNumberOfPlayers = new Scanner(System.in).nextInt();

        // Ask for the names of the designers
        System.out.println("Enter Designers (Use ',' to separate names if there are more than one):");
        String numOfDesigners = new Scanner(System.in).nextLine();
        String[] list = numOfDesigners.trim().split(",");
        List<String> designers = new ArrayList<>(Arrays.asList(list));

        // Make a new toy with the details
        BoardGames boardGames = new BoardGames(serialNumber, name, brand, price, availableCount, age, minNumberOfPlayers + "-" + maxNumberOfPlayers, designers);
        toyType.add(boardGames); // Add the toy to the list

        System.out.println("\nToy Added.\n");
        launchApplication(); // Show the menu again
    }



    public void removeToy() throws IOException {
        // Ask for the toy's special number
        String toySerialNumber = storeMenu.getToySearchPrompt("Enter Toy Serial Number:"); // Ask for toy number

        // Try to find the toy
        boolean foundToy = false;       // We haven't found the toy yet
        Toy toyToTakeOut = null;        // This will hold the toy we find
        for (Toy toy : toyType) {       // Look at every toy in the list
            if (toy.getSerialNumber().equals(toySerialNumber)) { // If we find the toy
                foundToy = true;        // We found the toy!
                toyToTakeOut = toy;     // Save the toy we found
                System.out.println("We found this toy:"); // Tell we found the toy

                // Show the toy's details
                char firstDigit = toySerialNumber.charAt(0); // Look at the first number
                if (firstDigit == '0' || firstDigit == '1') { // If it's a figure
                    Figures figure = (Figures) toy; // Treat it like a figure
                    System.out.println(figure.toString()); // Show the figure's details
                } else if (firstDigit == '4' || firstDigit == '5' || firstDigit == '6') { // If it's a puzzle
                    Puzzles puzzles = (Puzzles) toy; // Treat it like a puzzle
                    System.out.println(puzzles.toString()); // Show the puzzle's details
                } else if (firstDigit == '2' || firstDigit == '3') { // If it's an animal
                    Animals animals = (Animals) toy; // Treat it like an animal
                    System.out.println(animals.toString()); // Show the animal's details
                } else if (firstDigit == '7' || firstDigit == '8' || firstDigit == '9') { // If it's a board game
                    BoardGames boardGames = (BoardGames) toy; // Treat it like a board game
                    System.out.println(boardGames.toString()); // Show the board game's details
                }

                break; // Stop looking for the toy
            }
        }

        if (foundToy) { // If we found the toy
            // Ask if they want to take out the toy
            System.out.println("Do you want to remove it? (Y/N)"); // Ask if we should take it out
            String choice = new Scanner(System.in).nextLine(); // Wait for their answer
            switch (choice.toUpperCase()) { // Look at their answer
                case "Y": // If they said yes
                    toyType.remove(toyToTakeOut); // Take the toy out
                    System.out.println("Toy removed."); // Say we took it out
                    break;
                default: // If they said anything else
                    System.out.println("Toy was not removed."); // Say we didn't take it out
            }
        } else { // If we didn't find the toy
            System.out.println("We couldn't find this toy. Try again."); // Say we couldn't find it
        }

        // Wait for them to press enter
        storeMenu.getToySearchPrompt("Press Enter to Continue..."); // Tell them to press enter
        launchApplication(); // Go back to the main menu
    }




    public void save() throws IOException {
        // First, we clear the file, like erasing a chalkboard.
        FileWriter chalkboardEraser = new FileWriter(FILE_PATH, false);
        PrintWriter chalkboardCleaner = new PrintWriter(chalkboardEraser, false);
        chalkboardCleaner.flush();
        chalkboardCleaner.close();
        chalkboardEraser.close();

        // Then, we tell the computer we're saving data, like putting toys away in a toy box.
        System.out.println("Saving data into Database....");

        // Now, we check how many toys we have.
        int numberOfToys = toyType.size();

        // We go through each toy.
        for (int toyIndex = 0; toyIndex < numberOfToys; toyIndex++) {
            // And we save each toy to the file, like putting them away in the toy box.
            toyType.get(toyIndex).save(FILE_PATH);
        }

        // We say "thank you" and finish.
        System.out.println("************* THANKS FOR VISITING US ! *************");
        System.exit(0); // This stops the program, like turning off a toy.
    }






}




















