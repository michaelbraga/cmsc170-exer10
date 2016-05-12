import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
*	Class for the Tic-Tac-Toe User Interface
*/
public class TicTacToeUI extends JFrame {
	JPanel myPanel = new JPanel();
	JPanel optionPanel = new JPanel();
	JButton button[][] = new JButton[3][3];

	public TicTacToeUI(){

		//initializing panel (frame)
		super("TIC TAC TOE");
		setLayout(new BorderLayout());
		myPanel.setLayout(new GridLayout(3,3));
		myPanel.setSize(350, 350);
		myPanel.setBackground(Color.PINK);
		optionPanel.setLayout(new GridLayout(1,2));
		add(myPanel, BorderLayout.CENTER);
		add(optionPanel, BorderLayout.PAGE_END);

		// adding button
		for(int i=0; i<3; i+=1){
			for(int j=0; j<3; j+=1){
				button[i][j] = new JButton();
				button[i][j].setFont(new Font("Arial", Font.BOLD, 100));
				button[i][j].setText("");
				button[i][j].setBackground(new Color(0, 99,128));
				button[i][j].setForeground(Color.WHITE);
				button[i][j].addActionListener(addAction());
				myPanel.add(button[i][j]);
			}
		}
	}

	public void initialize(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(350, 400);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public ActionListener addAction(){
		ActionListener click = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				for(int i = 0 ; i < 3; i++){
					for(int j = 0 ; j < 3; j++){
						if(e.getSource() == button[i][j]){
							button[i][j].setText("X");
						}
					}
				}
			}
		};
		return click;
	}

	// public Point getPoint(ActionEvent e){
	// 	Object source = e.getSource();
	// 	for(int i = 0 ; i < 3; i++){
	// 		for(int j = 0 ; j < 3; j++){
	// 			if(source == button[i][j]){
	// 				button[i][j].setText("X");
	// 				return new Point(i,j);
	// 			}
	// 		}
	// 	}
	// 	return null;
	// }

	public int getPlayerOption(){
		Object[] options = {"Me, ofcourse!", "Computer (ew, coward!)"};
		int result = JOptionPane.showOptionDialog(myPanel,
										"Who should make the first move? #NoHugot",
										"Things to ponder on",
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										null,
										options,
										options[1]
									);
		return result;
	}
}

/*
make a move method for a computer
mouseListener

*/
