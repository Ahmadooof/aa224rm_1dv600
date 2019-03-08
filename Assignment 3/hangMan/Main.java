package hangMan;

import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		GameEntity game = new GameEntity();
		game.registerPlayer();
		game.startTheGame(args);
		game.printUI();
		game.chooseFromMenu();
	}

}
