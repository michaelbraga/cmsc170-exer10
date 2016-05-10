import java.awt.*;
import java.util.*;
/*
*	Class for the Tic-Tac-Toe AI that uses minimax algo and alpha beta pruning
*/

public class TicTacToeAI {
	public Point AnalyzeMatrix(char[][] matrix){
		Point bestChoice;
		// alpha beta minimax
		bestChoice = MinimaxAlphaBetaPruning(0, matrix, Integer.MIN_VALUE, Integer.MAX_VALUE);

		return bestChoice;
	}

	public Point MinimaxAlphaBetaPruning(int depth, char[][] matrix, int alpha, int beta){
		ArrayList<Point> availableMoves = getAvailableMoves(matrix);
		Point p;
		int max = Integer.MIN_VALUE;
		int holder; Point best= availableMoves.get(0);
		for (int i=0; i<availableMoves.size(); i+=1) {
			p = availableMoves.get(i);

			// make move
			matrix[(int)p.getX()][(int)p.getY()] = 'O';
			holder = value(matrix, depth+1, alpha, beta);

			// printing for testing!
			System.out.println("Value: "+holder);
			pMatrix(matrix);
			// end of printing!

			// reset move
			matrix[(int)p.getX()][(int)p.getY()] = '-';


			if (max < holder) {
				max = holder;
				best = p;
			}
		}
		return best;

	}
	public void pMatrix(char[][] matrix){

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

		// if utility or box
		if (result == 'X' || result == 'O'){
			return ((result == 'X')? -1:1);
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
}
