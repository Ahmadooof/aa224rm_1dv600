package hangMan;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameEntityTest {

	private String validInput = "f";
	private String inValidInput = "3";
	private String manyCharsInvalid = "sdfsdfgvsdvdsv";
	
	private String validInputToQuit = "3";
	private String notValidInputToQuit = "4";
	private String ManyCharactersInputToQuit = "sadfiojasdf";
	private String emptyString = "";

	private int ExpectedResultOfRounds = 6;  // all rounds are 7
	private char userInput = 'å';
	private String randomWord = "ahmad";
	
	GameEntity game = new GameEntity();
	
	// this method return 7 (rounds) if the player has guessed the letter,
	// otherwise it reduce the rounds by one
	// the bug is when the player input 'å'  so,
	// the program understand that's is similar to 'a' but it should not be the same.
	@Test
	void testcalculateRounds() {
		// no bug
		assertEquals(7,game.calculateRounds('a',randomWord));		
		
		// here is the bug test
		assertEquals(ExpectedResultOfRounds,game.calculateRounds(userInput,randomWord));

	}
	
	@Test
	void testvalidInputQuit() {
		assertEquals(true,game.validInputQuit(validInputToQuit));
		assertEquals(false,game.validInputQuit(notValidInputToQuit));
		assertEquals(false,game.validInputQuit(ManyCharactersInputToQuit));
		assertEquals(false,game.validInputQuit(emptyString));
	}

	@Test
	void testcheckValidInputWhileGuessing() {
		assertEquals(true,game.checkValidInputWhileGuessing(validInput));
		assertEquals(false,game.checkValidInputWhileGuessing(manyCharsInvalid));
		assertEquals(false,game.checkValidInputWhileGuessing(inValidInput));
		assertEquals(false,game.checkValidInputWhileGuessing(emptyString));
	}
	
	@Test
	void testcheckInputNameTrueForLetters() {
		assertEquals(true, game.checkInputName("Fredrik"));
		assertEquals(true, game.checkInputName("whatEverLettersAreOk"));
	}
	
	@Test
	void testcheckInputNameFalseForNumbers() {
		assertEquals(false, game.checkInputName("Fredrik123"));
		assertEquals(false, game.checkInputName("whatEverLettersAreOk3254"));
		assertEquals(false, game.checkInputName("2"));
	}
	
	@Test
	void testcheckInputNameWithBug() {
		// this should return true because, all of string are letters
		// but we have made this bug because of the requirements.  
		assertEquals(true, game.checkInputName("Fredrik Fredo"));
	}
	
	
		

	


	
}
