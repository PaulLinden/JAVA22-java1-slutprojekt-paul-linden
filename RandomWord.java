package randomWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomWord {


	public static String randomizer() throws FileNotFoundException {
	
	Scanner scannerForRandomizer = new Scanner(new File("random.txt"));
	
	List<String> randomWords = new ArrayList<>();
	
	while(scannerForRandomizer.hasNext()) {
		randomWords.add(scannerForRandomizer.nextLine());
	}
		Random random = new Random();
		String word = randomWords.get(random.nextInt(randomWords.size()));
		return word;
	}
	
	
	
}
