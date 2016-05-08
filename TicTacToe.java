import java.awt.*;
import java.util.*;
/*
*	Class for the Tic-Tac-Toe game
*/
public class TicTacToe {
	private char[][] matrix = new char[3][3];
	private static int HUMAN = -1;
	private static int COMPUTER = 1;

	public TicTacToe(){
		InitializeMatrix();

		int result = StartGame();
		if(result == HUMAN){
			System.out.println("You win!");
		} else if (result == COMPUTER) {
			System.out.println("Computer wins!");
		}
	}

	public void InitializeMatrix(){
		for (int i = 0; i < 3; i += 1)
			for (int j = 0; j < 3; j += 1)
				this.matrix[i][j] = '-';
	}

	public void PrintMatrix(){
		for (int i = 0; i < 3; i += 1){
			for (int j = 0; j < 3; j += 1){
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public int StartGame(){
		TicTacToeAI computer = new TicTacToeAI();
		Scanner in = new Scanner(System.in);
		int player;
		char winner;

		System.out.println("Who makes the first move? [-1 - you, 1 - computer]");
		if ((player = in.nextInt()) == HUMAN) {
			System.out.println("You take the first move.");
		}
		else {
			System.out.println("Computer takes the first move.");
		}

		int x=0, y=0; Point computerMove;
		PrintMatrix();
		while((winner = StillPlaying()) == '-'){
			System.out.println("==================================");
			if (player == HUMAN) {
				System.out.println("Enter x coordinate");
				x = in.nextInt();
				System.out.println("Enter y coordinate");
				y = in.nextInt();
				System.out.println("You made a move.");
			}
			else if(player == 1) {
				computerMove = computer.AnalyzeMatrix(this.matrix);
				x = (int) computerMove.getX();
				y = (int) computerMove.getY();
				System.out.println("Computer made a move.");
			}

			if(MakeMove(x, y, player)){
				// switch player
				player = (player == HUMAN)? COMPUTER : HUMAN;
			}
			System.out.println("==================================");
			PrintMatrix();
		}

		switch (winner) {
			case 'X': return HUMAN;
			case 'O': return COMPUTER;
			case 'D': System.out.println("Game end with a draw!"); return 0;
			default: System.out.println("Game Error!"); return -404;
		}
	}

	public char StillPlaying(){
		// horizontal
		if((this.matrix[0][0] == 'X' && this.matrix[0][1] == 'X' && this.matrix[0][2] == 'X') || (this.matrix[0][0] == 'O' && this.matrix[0][1] == 'O' && this.matrix[0][2] == 'O'))
			return this.matrix[0][0];
		else if((this.matrix[1][0] == 'X' && this.matrix[1][1] == 'X' && this.matrix[1][2] == 'X') || (this.matrix[1][0] == 'O' && this.matrix[1][1] == 'O' && this.matrix[1][2] == 'O'))
			return this.matrix[1][0];
		else if((this.matrix[2][0] == 'X' && this.matrix[2][1] == 'X' && this.matrix[2][2] == 'X') || (this.matrix[2][0] == 'O' && this.matrix[2][1] == 'O' && this.matrix[2][2] == 'O'))
			return this.matrix[2][0];

		// vertical
		else if((this.matrix[0][0] == 'X' && this.matrix[1][0] == 'X' && this.matrix[2][0] == 'X') || (this.matrix[0][0] == 'O' && this.matrix[1][0] == 'O' && this.matrix[2][0] == 'O'))
			return this.matrix[0][0];
		else if((this.matrix[0][1] == 'X' && this.matrix[1][1] == 'X' && this.matrix[2][1] == 'X') || (this.matrix[0][1] == 'O' && this.matrix[1][1] == 'O' && this.matrix[2][1] == 'O'))
			return this.matrix[0][1];
		else if((this.matrix[0][2] == 'X' && this.matrix[1][2] == 'X' && this.matrix[2][2] == 'X') || (this.matrix[0][2] == 'O' && this.matrix[1][2] == 'O' && this.matrix[2][2] == 'O'))
			return this.matrix[0][2];

		// diagonal
		else if((this.matrix[0][0] == 'X' && this.matrix[1][1] == 'X' && this.matrix[2][2] == 'X') || (this.matrix[0][0] == 'O' && this.matrix[1][1] == 'O' && this.matrix[2][2] == 'O'))
			return this.matrix[0][0];
		else if((this.matrix[0][2] == 'X' && this.matrix[1][1] == 'X' && this.matrix[2][0] == 'X') || (this.matrix[0][2] == 'O' && this.matrix[1][1] == 'O' && this.matrix[2][0] == 'O'))
			return this.matrix[0][2];
		else {
			for (int i=0; i<3; i+=1) {
				for (int j=0; j<3; j+=1) {
					if (this.matrix[i][j] == '-') {
						return '-';
					}
				}
			}
			return 'D';
		}
	}

	public boolean MakeMove(int x, int y, int player){
		if(this.matrix[x][y] == '-'){
			this.matrix[x][y] = (player == HUMAN)? 'X':'O';
			return true;
		}
		else{
			System.out.println("Invalid move!");
			return false;
		}
	}
}
