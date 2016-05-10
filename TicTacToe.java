import java.awt.*;
import java.util.*;
import javax.swing.*;

/*
*	Class for the Tic-Tac-Toe game
*/
public class TicTacToe extends JFrame{
	private char[][] matrix = new char[3][3];
	public static int HUMAN = -1024;
	public static int COMPUTER = 1024;

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

		TicTacToeUI labelFrame = new TicTacToeUI();
		labelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labelFrame.setSize(350, 400);
		labelFrame.setVisible(true);
		labelFrame.setLocationRelativeTo(null);
		int choice = labelFrame.getUserDialogBox();

		Scanner in = new Scanner(System.in);
		int player;
		char winner;

		System.out.println("Who makes the first move? [-1 - you, 1 - computer]");
		if ((player = (choice == 0)? HUMAN:COMPUTER) == HUMAN) {
			System.out.println("You take the first move.");
		}
		else {
			System.out.println("Computer takes the first move.");
			MakeMove(new Random().nextInt(3), new Random().nextInt(3), player);
			player = (player == HUMAN)? COMPUTER : HUMAN;
		}

		int x=0, y=0; Point computerMove;
		PrintMatrix();
		while((winner = StillPlaying(this.matrix)) == '-'){
			System.out.println("==================================");
			if (player == HUMAN) {
				System.out.println("Enter x coordinate");
				x = in.nextInt();
				System.out.println("Enter y coordinate");
				y = in.nextInt();
				System.out.println("You made a move.");
			}
			else if(player == COMPUTER) {
				computerMove = computer.AnalyzeMatrix(this.matrix);
				x = (int) computerMove.getX();
				y = (int) computerMove.getY();
				System.out.println("Computer made a move.");
			}

			if(MakeMove(x, y, player)){
				// switch player
				player = (player == HUMAN)? COMPUTER : HUMAN;
			}
			PrintMatrix();
		}

		switch (winner) {
			case 'X': return HUMAN;
			case 'O': return COMPUTER;
			case 'D': System.out.println("Game end with a draw!"); return 0;
			default: System.out.println("Game Error!"); return -404;
		}
	}

	public static char StillPlaying(char[][] matrix){
		// horizontal
		if ((matrix[0][0] == 'X' && matrix[0][1] == 'X' && matrix[0][2] == 'X') ||
			(matrix[0][0] == 'O' && matrix[0][1] == 'O' && matrix[0][2] == 'O'))
			return matrix[0][0];
		else if((matrix[1][0] == 'X' && matrix[1][1] == 'X' && matrix[1][2] == 'X') ||
			(matrix[1][0] == 'O' && matrix[1][1] == 'O' && matrix[1][2] == 'O'))
			return matrix[1][0];
		else if((matrix[2][0] == 'X' && matrix[2][1] == 'X' && matrix[2][2] == 'X') ||
			(matrix[2][0] == 'O' && matrix[2][1] == 'O' && matrix[2][2] == 'O'))
			return matrix[2][0];

		// vertical
		else if((matrix[0][0] == 'X' && matrix[1][0] == 'X' && matrix[2][0] == 'X') ||
			(matrix[0][0] == 'O' && matrix[1][0] == 'O' && matrix[2][0] == 'O'))
			return matrix[0][0];
		else if((matrix[0][1] == 'X' && matrix[1][1] == 'X' && matrix[2][1] == 'X') ||
			(matrix[0][1] == 'O' && matrix[1][1] == 'O' && matrix[2][1] == 'O'))
			return matrix[0][1];
		else if((matrix[0][2] == 'X' && matrix[1][2] == 'X' && matrix[2][2] == 'X') ||
			(matrix[0][2] == 'O' && matrix[1][2] == 'O' && matrix[2][2] == 'O'))
			return matrix[0][2];

		// diagonal
		else if((matrix[0][0] == 'X' && matrix[1][1] == 'X' && matrix[2][2] == 'X') ||
			(matrix[0][0] == 'O' && matrix[1][1] == 'O' && matrix[2][2] == 'O'))
			return matrix[0][0];
		else if((matrix[0][2] == 'X' && matrix[1][1] == 'X' && matrix[2][0] == 'X') ||
			(matrix[0][2] == 'O' && matrix[1][1] == 'O' && matrix[2][0] == 'O'))
			return matrix[0][2];
		else {
			for (int i=0; i<3; i+=1)
				for (int j=0; j<3; j+=1)
					if (matrix[i][j] == '-')
						return '-';
			return 'D';
		}
	}

	public boolean MakeMove(int x, int y, int player){
		if(x<3 && y<3 && x>=0 && y>=0 && this.matrix[x][y] == '-'){
			this.matrix[x][y] = (player == HUMAN)? 'X':'O';
			return true;
		}
		else{
			System.out.println("Invalid move!");
			return false;
		}
	}
}
