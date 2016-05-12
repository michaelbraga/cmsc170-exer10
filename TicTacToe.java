import java.awt.*;
import java.util.*;
import javax.swing.*;

/*
*	Class for the Tic-Tac-Toe game
*/
public class TicTacToe{
	char[][] matrix = new char[3][3];
	TicTacToeAI computer;
	static int HUMAN = -1024;
	static int COMPUTER = 1024;

	public TicTacToe(){
		InitializeMatrix();

		StartGame();
	}

	public void StartGame(){
		// computer ai
		computer = new TicTacToeAI();
		// ui class
		TicTacToeUI labelFrame = new TicTacToeUI(this);
		labelFrame.initialize();

		int player = labelFrame.getPlayerOption();
		
		if(player != 0){
			int randMiddle = new Random().nextInt(3);
			matrix[1][randMiddle] = 'O';
			labelFrame.button[1][randMiddle].setText("O");
		}
	}

	public void InitializeMatrix(){
		for (int i = 0; i < 3; i += 1)
			for (int j = 0; j < 3; j += 1)
				this.matrix[i][j] = '-';
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
}
