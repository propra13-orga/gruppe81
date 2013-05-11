import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class DCMenü extends JFrame implements ActionListener{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	public DCMenü() {
		super("Menü");
		/*
		 * JFrame.
		 */
		setSize(600,600);//Size of JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);//Sets if its visible.
		/*
		 * JButton.
		 */
		JButton startButton = new JButton("Start");//The JButton name.
		getContentPane().add(startButton);
		startButton.setLabel("Start");
		startButton.setSize(200,70);
		startButton.setLocation(200,150);
		startButton.setVisible(true);
		
		JButton sButton = new JButton("Beenden");//The JButton name.
		getContentPane().add(sButton);
		
		sButton.setLabel("Beenden");
		sButton.setSize(200,70);
		sButton.setLocation(200,250);
		sButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			dispose();
		}}
				);
		sButton.setVisible(true);
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}





	
}

