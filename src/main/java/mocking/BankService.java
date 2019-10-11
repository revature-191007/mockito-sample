package mocking;

public class BankService {
	
	BankRepository bankRepository;
	
	public BankService(BankRepository bankRepository) {
		super();
		this.bankRepository = bankRepository;
	}

	public BankAccount withdraw(int accountId, int amount) {
		BankAccount account = bankRepository.getAccount(accountId);
		synchronized (account) {
			int balance = account.getBalance();
			balance -= amount;
			account.setBalance(balance);
		}
		return account;
	}
	
	public BankAccount deposit(int accountId, int amount) {
		BankAccount account = bankRepository.getAccount(accountId);
		synchronized (account) {
			int balance = account.getBalance();
			balance += amount;
			account.setBalance(balance);
		}
		return account;
	}
	
	public BankAccount transfer(int a, int b, int amount) {

		BankAccount account = withdraw(a, amount);
		deposit(b, amount);
		return account;
	}
}
