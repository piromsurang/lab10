package coinmachine;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinMachineObserver extends JFrame implements Observer {
	
	/** number of coins */
	private int numOfCoins = 0;
	/** label of "#coin : " */
	private JLabel stringCoins;
	/** space for putting number of coins */
	private JTextField numCoins;
	/** message indicate whether machine is still available or not */
	private JLabel message;
	
	/**
	 * constructor for coin machine GUI
	 * 
	 * @author Piromsurang Rungserichai
	 */
	public CoinMachineObserver() {
		super( "" );
		initComponent();
		setDefaultCloseOperation( EXIT_ON_CLOSE );
	}
	
	/**
	 * to run GUI
	 * 
	 */
	public void run() {
		pack();
		setVisible( true );
	}
	
	/**
	 * component in GUI
	 * 
	 */
	public void initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout( new GridLayout( 2, 1) );
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new FlowLayout() );
		stringCoins = new JLabel( "#Coins : ");
		stringCoins.setHorizontalAlignment( 0 );
		numCoins = new JTextField( 10 );
		numCoins.setText( numOfCoins + "" );
		numCoins.setEnabled( false );
		numCoins.setHorizontalAlignment( JTextField.RIGHT );
		topPanel.add( stringCoins );
		topPanel.add( numCoins );
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout( new FlowLayout() );
		message = new JLabel( "Wating for Coins" );
		message.setForeground( Color.green );
		bottomPanel.add( message );
		
		panel.add( topPanel );
		panel.add( bottomPanel );
		
		this.add( panel );
		
	}
	
	/**
	 * update when there is a change occur in coin machine
	 * 
	 * @param o is the thing that can be observed
	 * @param arg an argument passes to notify method
	 * 
	 */
	public void update( Observable o, Object arg ) {
		numOfCoins++;
		numCoins.setText( numOfCoins + "" );
		message.setText( "Accepting Coins" );
		
		if ( numOfCoins >= 10 ) {
			message.setText( "Machine is FULL." );
			message.setForeground( Color.red );
		}
	}
}
