// Sam Kopkin
// 9/8/2021
// Kopkin_Spell_Checker
// Reads a dictionary file and another text file and outputs which words are not in the dictionary

package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kopkin_Spell_Checker {
	
	// Prompt User for two text files
	// Process each file into their own ArrayList collections
	// Loop through the text file collection to and compare each word to the dictionary file collection
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Prompt user to input file paths for a text
		System.out.print("Please enter the file path for the text file that you want to spell check: ");
		String wordListFileName = input.nextLine();
		
		System.out.print("Please enter the file path for the text file that contains the dictionary that you would like to use: ");
		String dictionaryFileName = input.nextLine();
		
		input.close();
		
		// Create a file object for both file paths
		File wordListFile = new File(wordListFileName);
		File dictionaryFile = new File(dictionaryFileName);
		
		// Create ArrayList objects to store the text from each file
		ArrayList<String> wordList = null;
		ArrayList<String> dictionary = null;
		
		// Pass the file objects into the processTextFile method in order to parse each text file
		// 	into an ArrayList storing each line as a String
		if (wordListFile != null) {
			wordList = processTextFile(wordListFile);
		}
		if (dictionaryFile != null) {
			dictionary = processTextFile(dictionaryFile);
		}
		
		// Pass the newly created ArrayLists to the spellCheck method to check the
		// 	wordList and check each word to see if it is in the dictionary
		if (wordList != null && dictionary != null) {
			spellCheck(wordList, dictionary);
		} else {
			// Error: invalid lists
		}
	}
	
	// Parses the text file into an ArrayList of strings that can be iterated through
	private static ArrayList<String> processTextFile(File textFile) {
		// Create an ArrayList containing Strings to store the text data from the file
		ArrayList<String> list = new ArrayList<String>();
		try {
			// Begin reading file
			Scanner input = new Scanner(textFile);
			
			// Iterate through the text file storing each line as a String in the ArrayList
			while (input.hasNext()) {
				list.add(input.nextLine());
			}
			input.close();
		} catch (FileNotFoundException e) {
			// If the filepath for the file is not valid, print an error
			System.out.println("Error: Invalid file path for " + textFile.getName());
			list = null;
		}
		
		// Return the list
		return list;
	}
	
	// Accepts an ArrayList for a word list and a dictionary
	// 	iterates through the word list and prints out any word that is not in the dictionary
	private static void spellCheck(ArrayList<String> wordList, ArrayList<String> dictionary) {
		System.out.println("The following words were not found in the dictionary:");
		
		// Iterate through the wordList
		// 	For each word, iterate through the dictionary in order to find a match
		//	If the word is not in the dictionary, print it out
		for(String w: wordList) {
			boolean match = false;
			for (String d: dictionary) {
				if (w.equalsIgnoreCase(d) ) {
					match = true;
					break;
				}
			}
			if (match == false) {
				System.out.println(w + " is an unknown word");
			}
		}
	}

}
