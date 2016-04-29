package coinmachine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinMachineUI extends JFrame {
	
	/** label for saying "Balance : " */
	private JLabel stringBalance;
	/** label for saying total balance */
	private JLabel inputBalance;
	/** lebel for saying "Status : " */
	private JLabel statusLabel;
	/** bar for showing how full is the coin machine */
	private JProgressBar coinAmount;
	/** total balance */
	private int balance;
	/** coin machine object from CoinMachine class */
	private CoinMachine coinMachine;

	
	/**
	 * constructor of coin machine GUI
	 * 
	 * @param coinMachine coin machine object from CoinMachine class
	 */
	public CoinMachineUI( CoinMachine coinMachine, CoinMachineObserver machineObserver ) {
		super( "Coin Machine" );
		balance = 0;
		this.coinMachine = coinMachine;
		this.coinMachine.addObserver( machineObserver );
		initComponent();
		setDefaultCloseOperation( EXIT_ON_CLOSE );
	}
	

	/**
	 * components in GUI
	 * 
	 */
	public void initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new FlowLayout() );
		stringBalance = new JLabel( "Balance : " );
		inputBalance = new JLabel( this.balance + "" );
		statusLabel = new JLabel( "Status : " );
		coinAmount = new JProgressBar( 0, 10 );
		coinAmount.setForeground( Color.green );
		coinAmount.setStringPainted( true );
		coinAmount.setString( "" + coinMachine.getCount() );
		topPanel.add( stringBalance );
		topPanel.add( inputBalance );
		topPanel.add( statusLabel );
		topPanel.add( coinAmount );
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout() );
		Border border = BorderFactory.createRaisedBevelBorder();
		bottomPanel.setBorder( new TitledBorder( border, "Insert Money" ) );
		bottomPanel.add( makeCoinButtons( 1 ) );
		bottomPanel.add( makeCoinButtons( 5 ) );
		bottomPanel.add( makeCoinButtons( 10 ) );
		
		panel.add( topPanel );
		panel.add( bottomPanel );
		
		this.add( panel );
	}
	
	/**
	 * create buttons for coins
	 * 
	 * @param value is the value of the coin
	 * @return button that show the value and currency of a coin.
	 */
	public JButton makeCoinButtons( final int value ) {
		
		ImageIcon image = new ImageIcon( this.getClass().getClassLoader().getResource( "images/" + value + "baht.png") );
		JButton button = new JButton( image );
		button.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				if ( coinMachine.getCount() < 10 ) {
					coinMachine.notifyObservers();
					coinMachine.insert( new Coin( value, "Baht" ) );
					balance = coinMachine.getBalance();
					
					inputBalance.setText( "" + balance );
					coinAmount.setValue( coinMachine.getCount() );
					coinAmount.setString( coinMachine.getCount() + "" );
					System.out.println( "" );
				}
				
				if ( coinMachine.getCount() == 10 ) {
					coinAmount.setForeground( Color.red );
					System.out.println( "Machine is FULL." );
				}
			}
		});
		return button;
	}
	
	/**
	 * run this GUI
	 * 
	 */
	public void run() {
		pack();
		setVisible( true );
	}
	
	public CoinMachine getCoinMachine() {
		return this.coinMachine;
	}
	
	public JProgressBar getProgressBar() {
		return this.coinAmount;
	}
	
	public JLabel getInputBalance() {
		return this.inputBalance;
	}
	
	public double getBalance() {
		return this.balance;
	}
}

