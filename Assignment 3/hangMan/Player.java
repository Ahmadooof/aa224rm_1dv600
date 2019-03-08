package hangMan;

public class Player {
	private String name;

	private String email;
	private String resultOfGame;


	public Player(String EnterName, String EnterEmail) {
		name = EnterName;
		email = EnterEmail;
	}
	
	
	protected String getName() {
		return name;
	}

	
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	

	protected String getResultOfGame() {
		return resultOfGame;
	}

	protected void setResultOfGame(String resultOfGame) {
		this.resultOfGame = resultOfGame;
	}
	
	
}
