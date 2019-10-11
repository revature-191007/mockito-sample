package mocking;

import java.util.HashMap;
import java.util.Map;

/*
 * Data layer class
 * Retrieves data and stores data
 */
public class BankRepository {

	Map<Integer, BankAccount> accountMap = new HashMap<>();
	{
		accountMap.put(1, new BankAccount(100));
		accountMap.put(2, new BankAccount(0));
		accountMap.put(3, new BankAccount(1000));
	}
	
	public BankAccount getAccount(int id) {
		return accountMap.get(id);
	}
	
}
