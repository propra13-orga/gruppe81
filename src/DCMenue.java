import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * 
 */

public class DCMenue extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	





	@SuppressWarnings("deprecation")
	public DCMenue() {
		super("Menu");
		/*
		 * JFrame.
		 */
		setSize(1000,600);//Size of JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);//Sets if its visible.
		setLayout(null);
		setLocationRelativeTo(null);
		/*
		 * JButton.
		 */
		JButton startButton = new JButton("Start");//The JButton name.
		add(startButton);
		//getContentPane().add(startButton);
		startButton.setLabel("Start");
		startButton.setSize(400,70);
		startButton.setLocation(300,150);
		startButton.setVisible(true);
		startButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			new MainWindow();
			setVisible(false);
		}});
		
		JButton sButton = new JButton("Beenden");//The JButton name.
		//getContentPane().add(sButton);
		add(sButton);
		sButton.setLabel("Beenden");
		sButton.setSize(400,70);
		sButton.setLocation(300,250);
		sButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			dispose();
		}}
				);
		sButton.setVisible(true);
	}





	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}





	
}







