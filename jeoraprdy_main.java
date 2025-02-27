package P4;

/* This file includes:
 * 	1. Solution to P3
 *  2. Questions for P4. Comments starting with REQ represent the questions.
 *  
 * Features:
 * 	- We have from 1 to 3 players
 *  - We have many questions. Each player may be asked more than one question.
 *  - User can play many rounds of the game. 
 * 
 * Focus: 
 * 	- Arrays and Methods
 * 
 * Aim:
 * 	- Organize code and avoid code redundancy by the use of methods
 */

public class Main {				
	static Game game;			
	
	//Two arrays for questions and answers (both are global, i.e., accessible by all code in the class).
	//REQ1:	Replace array values with real questions/answers
	static String[] questions = {"What fictional city does Batman live in?", "What is the largest river in the world?", "What planet is closest to the Sun", 
			"What is the capital of France?", "What color is the sky?", "How many countries are in Africa?", 
			"What type of fish is Nemo?", "What year was the moon landing?", "Which direction does the sun rise from?"};
	static String[] answers = 	{"Gotham",   "Nile",   "Mercury",   "Paris",   "Blue",   
			"54",   "Clownfish",   "1969",   "East"};
	
	public static void main(String[] args) {
		String ans;
		do{								
			//Reset the game
			game = new Game();			
			
			//Get number of players (from 1 to 3)
			int numPlayers = game.askForInt("How many players", 1, 3);
			

			//Add up to 3 players to the game
			for (int i = 0; i < numPlayers; i++) {
				String name = game.askForText("What is player " + i + " name?");
				game.addPlayer(name);
			}
			
			
			//REQ2:	Call a method to shuffle questions and answers. For that, you need to create a method with the header: void shuffleQuestions();
			shuffleQuestions(); 
			
			//REQ3:	- Calculate the maximum number of rounds (each player is asked one question per round). The maximum number of rounds should be equal to the number of available questions divided by numPlayers. Store this value in a variable maxRounds
			//	- Ask the user about the number of rounds. The value read from the user should not exceed maxRounds. Store this value in a variable numRounds.
			//		- Ask each player the next unanswered question (e.g., player 0 gets the first question. If it is answered correctly, then player1 gets the next question in the array, otherwise player1 gets the same question again, and so on). 
			// 		  Assume that an incorrectly answered question will keep popping up until it is correctly answered or we finish all the rounds.
			//		  Hint: you need to create a for loop that repeats the below code block numRounds times.
			//		  Hint: you need to have a variable that keeps track of the next question to be offered. 
			
			int maxRounds = questions.length / numPlayers;
			int numRounds = game.askForInt("How many rounds do you want to play? (max " + maxRounds + ")", 1, maxRounds);
			
			int nextQuestionIndex = 0;
			
			
			for (int round = 0; round < numRounds; round++) {
			    for (int i = 0; i < numPlayers; i++) {
			        game.setCurrentPlayer(i); 

			        String answer = game.askForText(questions[nextQuestionIndex]);

			        if (answers[nextQuestionIndex].equalsIgnoreCase(answer)) {
			            game.correct(); 
			            nextQuestionIndex++;
			        } else {
			            game.incorrect(); 
			        }

			        if (nextQuestionIndex >= questions.length) {
			            System.out.println("All questions answered!");
			            break;
			        }
			    }
			    
			    if (nextQuestionIndex >= questions.length) {
			    	break;
			    }
			
			
			for (int i = 0; i < numPlayers; i++) {
				game.setCurrentPlayer(i);//draw rectangle around player 0, and currentPlayer = player0
				String answer = game.askForText(questions[i]);
				if(answers[i].equals(answer))
					game.correct();		//display "Correct", increment score, change frame color to green
				else
					game.incorrect();	//display "incorrect", change frame color of player to red
			}	
			
			do {
                ans = game.askForText("Play again? (Y/N)");
                if (ans == null || (!ans.equalsIgnoreCase("Y") && !ans.equalsIgnoreCase("N"))) {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            } while (ans == null || (!ans.equalsIgnoreCase("Y") && !ans.equalsIgnoreCase("N")));

        } while (ans.equalsIgnoreCase("Y"));//play again if the user answers "Y" or "y"
        
        System.exit(0);// Terminate the program
    }

	private static void shuffleQuestions() {
		// TODO Auto-generated method stub
		for (int i = questions.length - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			
			String tempQuestion = questions[i];
			questions[i] = questions[j];
			questions[j] = tempQuestion;
			
			String tempAnswer = answers[i];
			answers[i] = answers[j];
			answers[j] = tempAnswer;
	}
  }
}
