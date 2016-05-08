import java.awt.*;
import java.util.*;
/*
*	Class for the Tic-Tac-Toe AI that uses minimax algo and alpha beta pruning
*/
public class TicTacToeAI {

	public Point AnalyzeMatrix(char[][] matrix){
		ArrayList<Point> choices = new ArrayList<Point>();
		for (int i=0; i<3; i+=1) {
			for (int j=0; j<3; j+=1) {
				if (matrix[i][j] == '-') {
					choices.add(new Point(i,j));
				}
			}
		}
		return choices.get(new Random().nextInt(choices.size()));
	}

}
