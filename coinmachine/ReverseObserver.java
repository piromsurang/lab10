package coinmachine;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class ReverseObserver implements Observer {
	
	private CoinMachineUI machineUI;
	private double balance;
	
	public ReverseObserver( CoinMachineUI machineUI ) {
		this.machineUI = machineUI;
		balance += 0;
	}
	public void update( Observable o, Object arg ) {
		this.balance = machineUI.getCoinMachine().getBalance();
		machineUI.getInputBalance().setText( "" + this.balance );
		machineUI.getProgressBar().setValue( machineUI.getCoinMachine().getCount() );
		machineUI.getProgressBar().setString( machineUI.getCoinMachine().getCount() + "" );
		if ( machineUI.getCoinMachine().getCount() == 10 ) {
			machineUI.getProgressBar().setForeground( Color.red );
		}
	}
}
