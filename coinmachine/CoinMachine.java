package coinmachine;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.ArrayList;

/** 
 *  A coin purse contains coins.
 *  It has a capacity (maximum number of coins it can hold),
 *  and methods for inserting coins, getting the balance,
 *  and withdrawing coins.
 *  @author BTS and MRT
 */
public class CoinMachine extends Observable {
	/** the coins it contains */
	private List<Coin> coins;
	/** max number of coins you can put in the machine */
	private int capacity;
	
	/** 
	 * Create a new coin machine with a fixed capacity.
	 */
	public CoinMachine( int capacity ) {
		this.capacity = capacity;
		coins = new java.util.ArrayList<Coin>( );
	}

	/** 
	 * Get the number of coins in the machine. 
	 * @return the number of coins in the machine
	 */
	public int getCount() { 
		return coins.size();
	}
	
	/** 
	 * Get the value of all the coins in the machine.
	 *  @return the total value of the coins
	 */
	public int getBalance() {
		int balance = 0;
		for (Coin c : coins) balance += c.getValue();
		return balance;
	}
	
	/** 
	 * isFull tests whether the machine is full of coins.
	 *  @return true if the machine is full to capacity
	 */
	public boolean isFull() { return getCount() >= capacity; }


	/** 
	 *  Insert a coin into the machine.
	 *  If the machine is full it returns false.
	 *  If the coin is invalid, throw an exception.
	 *  @param m is a coin object to insert. Must not be null.
	 *  @return true if coins successfully added, false if failure (machine full)
	 *  @throws 
	 */
	public boolean insert( Coin m ) {
		if ( isFull() ) return false;
		if (m.getValue() <= 0) throw new IllegalArgumentException("Coin must have positive value");
		boolean result = coins.add(m);
		super.notifyObservers();
		super.setChanged();

		
		return result;
	}
	
	/**
	 * Short string description of the coin machine.
	 * @return string description of the coin machine
	 */
	public String toString() {
		return String.format("Coin machine with capacity %d", capacity );
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/** 
	 * Get a view of the coins, but don't allow the receiver to change the list.
	 * @return an unmodifiable list of the coins in machine
	 */
	public List<Coin> getCoins() { 
		return Collections.unmodifiableList(coins); 
	}
}