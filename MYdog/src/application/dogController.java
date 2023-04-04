package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.AppManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;
import model.Toy;


public class dogController {

	
    private ArrayList<Toy> toyType = new ArrayList<>();

	@FXML
	private ListView<String> searchResultsListView;

    @FXML
    private Button ClearButton;

    @FXML
    private TextField NameTextField;

    @FXML
    private RadioButton RadioName;

    @FXML
    private RadioButton RadioNumber;

    @FXML
    private RadioButton RadioType;

    @FXML
    private TextField SNTextField;

    @FXML
    private Button SearchButton;

    @FXML
    private ToggleGroup SearchTypes;

    @FXML
    private ListView<?> ShopView;

    @FXML
    private TextField TypeTextField;



//Start
    private void loadToys() throws FileNotFoundException {
        File txt = new File("res/toys.txt");//Creates a File object called txt using FILE_PATH.
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
//End
    
    
void searchItem(ActionEvent event) {
    	ObservableList<Toy>matchingToys = FXCollections.observableArrayList();
    	ObservableList<Toy>matchingSerialNumber = FXCollections.observableArrayList();
    	ObservableList<Toy>matchingType = FXCollections.observableArrayList();
    	
    	
    	for(Toy toy: toyType) {
    		if (RadioName.isSelected() && toy.getName().equalsIgnoreCase(NameTextField.getText())) {
    			matchingToys.add(toy);
    		}
    		if (RadioNumber.isSelected() && toy.getSerialNumber().equals(SNTextField.getText())){
    			matchingSerialNumber.add(toy);
    		}
    		if (RadioType.isSelected()) {
    			char SerialNumber = toy.getSerialNumber().charAt(0);
    			int firstDigit = SerialNumber;
    			
    			switch (firstDigit) {
    			case 0:
    			case 1:
    				if (TypeTextField.getText().equalsIgnoreCase("figure")) {
    					matchingType.add(toy);
    				}
    				break;
    			case 2:
    			case 3:
    				if (TypeTextField.getText().equalsIgnoreCase("Animals")) {
    					matchingType.add(toy);
    			}
    				break;
    			case 4:
    			case 5:
    			case 6:
    				if (TypeTextField.getText().equalsIgnoreCase("Puzzle")) {
    					matchingType.add(toy);
    		}
    				break;
    			case 7:
    			case 8:
    			case 9:
    				if (TypeTextField.getText().equalsIgnoreCase("BoardGame")) {
    					matchingType.add(toy);
    	}break;
    	}
    		}
    		}
    
    		
    	}
    
    

    public void handleSearchSerialButtonClick() throws Exception {
        AppManager ap = new AppManager();
        String serialNumber = SNTextField.getText(); // Get the serial number from the TextField in your FXML file

        // Call the serialSearch() function with the entered serial number or an empty string if it's null
        List<String> searchResults = ap.serialSearch(serialNumber == null ? "" : serialNumber);

        // Update the ListView with the search results
        if (searchResults != null) {
            searchResultsListView.getItems().setAll(searchResults);
        } else {
            searchResultsListView.getItems().clear();
        }
    }






    public void handleRadioButtonSelection() {
        if (RadioNumber.isSelected()) {
            TypeTextField.setDisable(true);
            NameTextField.setDisable(true);
            SNTextField.setDisable(false);
        } else if (RadioName.isSelected()) {
            TypeTextField.setDisable(true);
            SNTextField.setDisable(true);
            NameTextField.setDisable(false);
        } else if (RadioType.isSelected()) {
            NameTextField.setDisable(true);
            SNTextField.setDisable(true);
            TypeTextField.setDisable(false);
        } else { // No radio button is selected
            NameTextField.setDisable(true);
            SNTextField.setDisable(true);
            TypeTextField.setDisable(true);
        }
    }
    
    
    
    @FXML
    public void initialize() {
        // Disable all TextFields by default
        NameTextField.setDisable(true);
        SNTextField.setDisable(true);
        TypeTextField.setDisable(true);

        RadioNumber.selectedProperty().addListener((observable, oldValue, newValue) -> handleRadioButtonSelection());
        RadioName.selectedProperty().addListener((observable, oldValue, newValue) -> handleRadioButtonSelection());
        RadioType.selectedProperty().addListener((observable, oldValue, newValue) -> handleRadioButtonSelection());
    }
    
    
    
    public void handleClearButtonClick() {
        NameTextField.clear();
        SNTextField.clear();
        TypeTextField.clear();
    }






}














