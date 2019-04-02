package hangMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class Main {
	static String randomWord;
	static String saveResult[];

	public static void main(String[] args) throws FileNotFoundException {
		GameEntity game = new GameEntity();
		File file = new File(args[0]);
		
		Random random = new Random();
		int randomNumber = random.nextInt(90);
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(file);
			while (scan.hasNext() && randomNumber != 0) {
				randomWord = scan.next();
				randomNumber--;
			}
			randomWord = randomWord.toLowerCase();							// convert our random word to lower case
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		saveResult = new String[randomWord.length()];					
		for(int i = 0; i <= saveResult.length-1; i++)
			saveResult[i] = "-";
		
		game.registerPlayer();
		game.printUI();
		game.chooseFromMenu(randomWord,saveResult);
	}

}
