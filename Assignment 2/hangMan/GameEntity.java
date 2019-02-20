package hangMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameEntity {

	private Scanner input = new Scanner(System.in);
	private int countRounds = 7;									// counts how many characters are right to the random word		
	private Object[] saveResult;
	private String randomWord;
	private String chooseNumberFromMenu = "";
	private boolean isChar = false;
	private Player newPlayer;
	private ArrayList<Player> playerList = new ArrayList<>();
	private String args[];
	
	private	String[] returnStringArgs() {
		return args;
	}

	protected void startTheGame(String args[]) {
		this.args = args;
		randomWord = generateRandomWord(args);
	}

	@SuppressWarnings("resource")
	protected String generateRandomWord(String args[]) {
		File file = new File(args[0]);
		Random random = new Random();
		int randomNumber = random.nextInt(90);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext() && randomNumber != 0) {
				randomWord = scan.next();
				randomNumber--;
			}
			randomWord = randomWord.toLowerCase();							// convert our random word to lower case
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		saveResult = new Object[randomWord.length()];					
		for(int i = 0; i <= saveResult.length-1; i++)
			saveResult[i] = "-";

		return randomWord;
	}

	public void printUI() {
		System.out.println("choose a number");
		System.out.println("1.Strat the game");
		System.out.println("2.Result");
		System.out.println("3.Quite the game");

	}


	private void quite() {
		System.out.println("The game is Finished");
		System.exit(0);
	}

	private void result() {
		System.out.print("Name\t\t\tEmail\t\t\t\tResult\n");
		for(Player pl :playerList) {
			System.out.println(pl.getName()+"\t\t\t"+pl.getEmail()+"\t\t\t"+pl.getResultOfGame());
		}

	}

	private void startGuessing() {
		System.out.println("Guess a word which consists of "+randomWord.length()+" Characters");
		do {
			String userInput = input.next();
			char userCharInput = userInput.charAt(0);
			isChar = Character.isLetter(userCharInput);
			if(userInput.length() == 1 && isChar)
			{
				countRounds--;												// reduce the number of rounds <--> when our input is char.		
				checkIfCharsEqual(userCharInput);							// check if char input equal to a char which exist in the random word
				System.out.println(+countRounds+" rounds left");		
			}
			else
				System.out.println("please enter a character"); 	// if input is not a char

		}while(countRounds != 0 && checkResult() == false);

		if(checkResult() == true)	{							// Result of the game
			System.out.println("You Win");
			newPlayer.setResultOfGame("Win");
			playerList.add(newPlayer);
			restart();
		}
		else {
			System.out.println("You Lose");
			newPlayer.setResultOfGame("Lose");
			playerList.add(newPlayer);
			restart();
		}
	}

	private void restart() {
		System.out.println("Do you want to restart the game");
		System.out.println("1.yes");
		System.out.println("2.No");
		System.out.println("3.Show Result");
		String restart;
		do {
			restart = input.next();
			if(restart.equals("1"))
			{
				countRounds = 7;
				chooseNumberFromMenu = "";
				isChar = false;
				registerPlayer();
				startTheGame(returnStringArgs());
				printUI();
				chooseFromMenu();					
			}
			else if(restart.equals("2"))
			{
				quite();
			}
			else if(restart.equals("3"))
			{
				result();
			}
			else {
				System.out.println("Invalid Input, please press choose (1 or 2 or 3)");
			}
		}while(!restart.equals("1") && !restart.equals("2") && !restart.equals("3"));
	}
	private boolean checkResult() {
		for(Object o : saveResult)
			if(o.equals("-")) {
				return false;
			}
		return true;
	}

	private void checkIfCharsEqual(char userInput) {
		for(int index = 0; index < randomWord.length() ; index++)
		{
			Character ch = randomWord.charAt(index);
			if(ch.equals(userInput)) {
				saveResult[index] = userInput;
			}
		}

		System.out.print("Result Of Guessing : ");
		System.out.println(randomWord);
		for(Object ch : saveResult)
			System.out.print(ch);
		System.out.println();
	}

	public void chooseFromMenu() {
		do {
			chooseNumberFromMenu = input.next();
			if(chooseNumberFromMenu.equals("1"))
			{
				startGuessing();
			}
			else if(chooseNumberFromMenu.equals("2"))
			{
				result();
			}
			else if(chooseNumberFromMenu.equals("3"))
			{
				quite();
			}		
			else {
				System.out.println("please choose a valid number");
			}
		}
		while((!chooseNumberFromMenu.equals("1")) && (!chooseNumberFromMenu.equals("2")) && (!chooseNumberFromMenu.equals("3")));

	}

	public void registerPlayer() {
		System.out.println("Enter your name : ");
		String name = input.next();
		System.out.println("Enter your email : ");
		String email = input.next();	
		newPlayer = new Player(name,email);
	}

}
