package mocking.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import mocking.BankAccount;
import mocking.BankRepository;
import mocking.BankService;

/*
 * Unit Testing
 * -------------
 * Testing individual methods isolated from dependent code
 * 
 * The BankService calls methods on the BankAccount (and the BankService itself)
 * 
 * The BankAccount is fairly simple, but lets imagine that there was complex
 * logic within this class. For instance, imagine that in retrieval of account
 * data, this data would come from a database. Should tests on the service require
 * a database? Is that part of the code we're testing?
 * 
 * No!
 * 
 * We shouldn't be reliant on other code to test the service. And errors in
 * other code, should not break service tests - if they did, then the test errors
 * won't point to the right place.
 * 
 * This can accomplished by mocking and stubbing dependencies.
 * 
 * A mock a replaces the object we would normally use with a fake one.
 * 
 * Stubbing replaces method implementations with fake implementations.
 */
public class BankServiceTest {
	
	BankRepository repository = Mockito.mock(BankRepository.class);
	BankService bankService = new BankService(repository);
	
	
	@Test
	public void depositTest() {
		/* Setup */
		BankAccount retrievedAccount = new BankAccount(1000);
		int accountId = 20;
		// stub the repo method
		// I don't need to worry about what the repo logic is
		// I know exactly what the method return - the bank account i
		//  created above
		Mockito.when(repository.getAccount(accountId))
				.thenReturn(retrievedAccount);
		
		/* Run operation */
		bankService.deposit(accountId, 100);
		
		/* Validate operation results */
		
		// verify that the repository method was called
		// Test will fail if getAccount wasn't called with id 20
		Mockito.verify(repository).getAccount(accountId);
		
		// Validate that the deposit method correctly added 100 to the
		// account balance
		assertEquals("1000 + 100 = 1100", retrievedAccount.getBalance(), 1100);
	}
}












