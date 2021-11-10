// Author Name: Sam Kopkin
// Date: 10/10/21
// Program Name: Kopkin_Word_Occurence
// Purpose: Text analyzer that reads a file and outputs the top 20 most occuring words

package module7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @author Sam Kopkin
 * Text analyzer that reads a file and outputs the top 20 most occuring words
 */
public class Kopkin_Word_Occurence  extends Application{

	/**
	 * @param args default arguments for main method
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Override of the JavaFX start method
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		// Create a FileChooser object that filters for .txt files
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open text file");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		
		// Create a TableView with two columns for words and the # of occurences
		TableView<WordOccurence> resultsTable = new TableView();
		TableColumn wordCol = new TableColumn("Word");
		wordCol.setMinWidth(15);
		wordCol.setCellValueFactory(new PropertyValueFactory<>("word"));
		TableColumn countCol = new TableColumn("# of Occurences");
		countCol.setCellValueFactory(new PropertyValueFactory<>("occurence"));
		countCol.setMinWidth(15);
		resultsTable.getColumns().addAll(wordCol, countCol);
		resultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		resultsTable.prefHeightProperty().bind(stage.heightProperty());
        resultsTable.prefWidthProperty().bind(stage.widthProperty());
		
		// Create an alert window that asks for confirmation
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("RESET RESULTS");
		alert.setContentText("Are you sure you want to clear the results?");
		
		// Create a button that activates the fileChooser and then scans the txt file,
		// 	counts the # of occurences of each word, and then populates the table
		Button fileButton = new Button("Choose File");
		fileButton.setOnAction(e -> {
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				populateTable(scanFile(selectedFile), resultsTable);
			}
		});
		
		// Create a button that empties the table and selected file
		Button resetButton = new Button("Reset");
		resetButton.setOnAction(e -> {
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					resultsTable.getItems().clear();
				}
			});
			
			
		});
		
		// Create an HBow to contain the buttons
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(5);
		buttonBox.setPadding(new Insets(10, 10, 10, 10));
		buttonBox.getChildren().addAll(fileButton, resetButton);
		
		// Create a VBox that will be the primary box that contains all other nodes
		VBox mainBox = new VBox();
		mainBox.setSpacing(5);
		mainBox.setPadding(new Insets(10, 10, 10, 10));
		
		// Add other nodes to the mainBox
		mainBox.getChildren().addAll(buttonBox, resultsTable);
		
		// Add the mainBox to the scene
		Scene scene = new Scene(mainBox, 300, 575);
		
		// Configure and display the stage
		stage.setTitle("Word Occurence");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	// Reads a text file, counts how many times each word occurs in the file,
	// 	and then sorts them into a list by the highest count
	/**
	 * @param file A .txt file object to be scanned
	 * @return A list of WordOccurence objects sorted by greatest occurrences
	 */
	List<WordOccurence> scanFile(File file) {
		// Creates a list of WordOccurence objects
		List<WordOccurence> list = new ArrayList<WordOccurence>();
		
		try {
			@SuppressWarnings("resource")
			// Create a scanner for the file
			Scanner scanner = new Scanner(file);
			
			// For each word in the txt file, check if it is already in the list.
			// 	If it is already there, increment the count. Otherwise, add it.
			while (scanner.hasNext()) {
				String word = scanner.next();
				WordOccurence wo = list.stream().filter(w -> w.getWord().equals(word)).findFirst().orElse(null);
				if (wo == null) {
					list.add(new WordOccurence(word, 1));
				} else {
					wo.incrementOccurence();
				}
				
			}
			
			// Sorts the list by highest occurence
			list.sort(null);
			
			// Return the sorted list
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// Populates a TableView with a given list of WordOccurence objects
	/**
	 * @param list A list of WordOccurence objects
	 * @param table A TableView object that the list will be displayed onto
	 */
	private void populateTable(List<WordOccurence> list, TableView<WordOccurence> table) {
		
		for (WordOccurence wo: list) {
			table.getItems().add(wo);
		}
	}
	
	// Defines a WordOccurence object that contains a word and a number for the amount of times it occurs
	/**
	 * @author Sam
	 * Custom class that defines a pair of a string word and integer for the number of occurrences
	 */
	public static class WordOccurence implements Comparable<WordOccurence>{
		
		private String word;
		private int occurence;
		
		/**
		 * Constructor for a WordOccurence object
		 * @param w A string for the word
		 * @param o An integer for the # of occurrences
		 */
		public WordOccurence(String w, int o) {
			word = w;
			occurence = o;
		}
		
		/**
		 * @return The string word value
		 */
		public String getWord() {
			return word;
		}
		
		/**
		 * @return The int occurrence value
		 */
		public int getOccurence() {
			return occurence;
		}
		
		/**
		 * Setter method for the word value
		 * @param w A string for the word
		 */
		public void setWord(String w) {
			word = w;
		}
		
		/**
		 * Setter method for the occurrence value
		 * @param o An int value for the occurence #
		 */
		public void setOccurence(int o) {
			occurence = o;
		}
		
		/**
		 * Increments the occurrence value
		 */
		public void incrementOccurence() {
			occurence++;
		}
		
		/**
		 * Overrides the compareTo method for this class
		 * Determines value based on higher occurrence
		 */
		@Override
		public int compareTo(WordOccurence wo) {
			return wo.getOccurence() - this.occurence;
			
		}
		
		/**
		 * Override the equals method for thiws class
		 * Determines equality based on the word string and occurrence number
		 */
		@Override
		public boolean equals(Object other) {
			if (!(other instanceof WordOccurence)) {
				return false;
			}
			
			WordOccurence wo = (WordOccurence) other;
			
			if (wo.getWord().equals(this.word) && wo.getOccurence() == this.occurence) {
				return true;
			} else {
				return false;
			}
		}
	}
}
