// Author Name: Sam Kopkin
// Date: 10/27/21
// Program Name: Kopkin_module8_unit_test
// Purpose: Use Junit testing to verify that the scanFile method in the
// 	Kopkin_Word_Occurence class is properly reading the file, counting
//	the number of word occurences, and outputting the expected results

package module7;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import module7.Kopkin_Word_Occurence.WordOccurence;

class Kopkin_module8_unit_test {

	@Test
	void test() {
		// Create an instance of the word occurence class
		Kopkin_Word_Occurence testWO = new Kopkin_Word_Occurence();
		
		// Create an ArrayList and populate it with WordOccurence objects
		// 	that should reflect the expected output
		List<WordOccurence> expectedResults = new ArrayList<WordOccurence>();
		expectedResults.add(new WordOccurence("one", 9));
		expectedResults.add(new WordOccurence("two", 8));
		expectedResults.add(new WordOccurence("three", 7));
		expectedResults.add(new WordOccurence("four", 6));
		expectedResults.add(new WordOccurence("five", 5));
		expectedResults.add(new WordOccurence("six", 4));
		expectedResults.add(new WordOccurence("seven", 3));
		expectedResults.add(new WordOccurence("eight", 2));
		expectedResults.add(new WordOccurence("nine", 1));
		
		
		// Create a file object for the test input
		File testFile = new File("src/module7/testTextFile.txt");
		
		// Run the test and check if the output matches the expected results
		Assertions.assertEquals(expectedResults, testWO.scanFile(testFile));
	}

}
