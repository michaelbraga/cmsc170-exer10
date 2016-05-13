import java.awt.*;
import java.util.*;
/*
*	Class for the Tic-Tac-Toe AI that uses minimax algo and alpha beta pruning
*/

public class TicTacToeAI {
	public Point AnalyzeMatrix(char[][] matrix){
		if (TicTacToe.StillPlaying(matrix) != '-') {
			return null;
		}
		Point bestChoice;

		// alpha beta minimax
		bestChoice = MinimaxAlphaBetaPruning(0, matrix, Integer.MIN_VALUE, Integer.MAX_VALUE);

		return bestChoice;
	}

	public Point MinimaxAlphaBetaPruning(int depth, char[][] matrix, int alpha, int beta){
		ArrayList<Point> availableMoves = getAvailableMoves(matrix);
		Point p;
		int max = Integer.MIN_VALUE;
		int holder, eval1=Integer.MIN_VALUE, eval2; Point best= availableMoves.get(0);
		for (int i=0; i<availableMoves.size(); i+=1) {
			p = availableMoves.get(i);

			// make move
			matrix[(int)p.getX()][(int)p.getY()] = 'O';
			holder = value(matrix, depth+1, alpha, beta);
			eval2 = evaluate(matrix);
			System.out.println(holder + " " + eval2);
			PrintMatrix(matrix);
			if (max < holder || (max==holder && eval1 < eval2)) {
				max = holder;
				best = p;
				eval1 = eval2;
			}

			// reset move
			matrix[(int)p.getX()][(int)p.getY()] = '-';

		}
		return best;

	}
	public void PrintMatrix(char[][] matrix){

		for (int i = 0; i < 3; i += 1){
			for (int j = 0; j < 3; j += 1){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("---");
	}

	public ArrayList<Point> getAvailableMoves(char[][]  matrix){
		ArrayList<Point> moves = new ArrayList<Point>();

		// get legal moves
		for (int i=0; i<3; i+=1) {
			for (int j=0; j<3; j+=1) {
				if (matrix[i][j] == '-') {
					moves.add(new Point(i,j));
				}
			}
		}
		return moves;
	}

	public int value(char[][] matrix, int depth, int alpha, int beta){
		char result = TicTacToe.StillPlaying(matrix);

		// if cutoff
		if(depth == 4){
			return evaluate(matrix);
		}

		// if utility or box
		else if (result == 'X' || result == 'O'){
			return ((result == 'X')? -100:100);
		}
		else if (result == 'D'){
			return 0;
		}

		else if (result == '-'){
			// if maximization
			if (depth%2 == 0) {
				return maxValue(matrix, depth, alpha, beta);
			}

			// if minimization
			else if (depth%2 == 1) {
				return minValue(matrix, depth, alpha, beta);
			}
		}
		return -404;
	}

	public int maxValue(char[][] matrix, int depth, int alpha, int beta){
		int v = Integer.MIN_VALUE;
		ArrayList<Point> availableMoves = getAvailableMoves(matrix);
		Point p;
		for (int i=0; i<availableMoves.size(); i+=1) {
			p = availableMoves.get(i);
			// make move
			matrix[(int)p.getX()][(int)p.getY()] = 'O';

			v = Math.max(v, value(matrix, depth+1, alpha, beta));
			// reset
			matrix[(int)p.getX()][(int)p.getY()] = '-';

			if(v >= beta) return v;
			alpha = Math.max(alpha, v);

		}
		return v;
	}

	public int minValue(char[][] matrix, int depth, int alpha, int beta){
		int v = Integer.MAX_VALUE;
		ArrayList<Point> availableMoves = getAvailableMoves(matrix);
		Point p;
		for (int i=0; i<availableMoves.size(); i+=1) {
			p = availableMoves.get(i);
			// make move
			matrix[(int)p.getX()][(int)p.getY()] = 'X';

			v = Math.min(v, value(matrix, depth+1, alpha, beta));

			// reset
			matrix[(int)p.getX()][(int)p.getY()] = '-';

			if (v <= alpha) return v;
			beta = Math.min(beta, v);

		}
		return v;
	}

	public int evaluate(char[][] matrix){
		int score = 0;
		char[] moves = new char[2];
		moves[0] = 'X'; moves[1] = 'O';

		for(int m=0; m<2; m+=1) {
			char move = moves[m];
			int toAdd = ((move=='X')? -10:10), extras = ((move=='X')? -5:5);

			// horizontal and vertical evaluation
			for (int i=0; i<3; i+=1) {
				if ((matrix[i][0]==move && matrix[i][1]==move && matrix[i][2]=='-'))
					score += toAdd;
				if ((matrix[i][0]=='-' && matrix[i][1]==move && matrix[i][2]==move))
					score += toAdd;
				if ((matrix[i][0]==move && matrix[i][1]=='-' && matrix[i][2]==move))
					score += toAdd;
				if ((matrix[0][i]==move && matrix[1][i]==move && matrix[2][i]=='-'))
					score += toAdd;
				if ((matrix[0][i]=='-' && matrix[1][i]==move && matrix[2][i]==move))
					score += toAdd;
				if ((matrix[0][i]==move && matrix[1][i]=='-' && matrix[2][i]==move))
					score += toAdd;
			}

			// diagonal evaluation
			if((matrix[0][0]==move && matrix[1][1]=='-' && matrix[2][2]==move))
				score += toAdd;
			else if((matrix[0][0]=='-' && matrix[1][1]==move && matrix[2][2]==move))
				score += toAdd;
			else if((matrix[0][0]==move && matrix[1][1]==move && matrix[2][2]=='-'))
				score += toAdd;

			if((matrix[2][0]==move && matrix[1][1]=='-' && matrix[0][2]==move))
				score += toAdd;
			else if((matrix[2][0]=='-' && matrix[1][1]==move && matrix[0][2]==move))
				score += toAdd;
			else if((matrix[2][0]==move && matrix[1][1]==move && matrix[0][2]=='-'))
				score += toAdd;

			char s = TicTacToe.StillPlaying(matrix);
			if (s != '-' && s != 'D') {
				score += (TicTacToe.StillPlaying(matrix) == 'O')? 100:-100;
			}
			// check for blocking
			for (int i=0; i<3; i+=3) {
				score += checkForBlocking(matrix[i][0], matrix[i][1], matrix[i][2]);
				score += checkForBlocking(matrix[0][i], matrix[1][i], matrix[2][i]);
			}
		}
		return score;
	}
	private int checkForBlocking(char x, char y, char z){
		int score = 0;
		if(
			(x == 'X' && y == 'O' && z == '-') ||
			(x == '-' && y == 'O' && z == 'X')
		){
			score += 2;
		}

		if(
			(x == 'O' && y == 'X' && z == '-') ||
			(x == '-' && y == 'X' && z == 'O')
		){
			score += -2;
		}
		return score;
	}
}
