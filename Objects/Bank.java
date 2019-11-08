public class Bank{
	private double balance;
	private double apr;
	
	
	// Constructors
	public Bank(){
		this.balance = 0;
		this.apr = 0;
	}
	
	public Bank(double initBalance){
		this.balance = initBalance;
		this.apr = 0;
	}
	
	public Bank(double initBalance, double initApr){
		this.balance = initBalance;
		this.apr = initApr;
	}
	
	
	// Getters
	
	public double getBalance(){
		return this.balance;
	}
	
	public double getApr(){
		return this.apr;
	}
	
	// Setters
	public void setBalance(double in){
		this.balance = in;
	}
	
	public void setApr(double in){
		this.apr = in;
	}
	
	// Deposit
	public double deposit(double dep){
		this.balance += dep;
		return this.balance;
	}
	
	// Withdrawl
	public double withdrawl(double wd){
		this.balance -= wd;
		return this.balance;
	}
	
	// Interest calc
	public double interestCalc(int nMonths){
		for(int i = 0; i < nMonths; i ++){
			this.balance += this.balance*(1+this.apr/12.0/100.0);
		}
		return balance;
	}
	
	
	// Savings calc
	public double savingsCalc(int nMonths, double moSave){
		for(int i = 0; i < nMonths; i ++){
			this.balance += moSave + this.balance*(this.apr/12.0/100.0);
		}
		return this.balance;
		
	}
	
	public String toString(){
		return String.format("Balance = $%.2f\n", this.balance);
	}
	
	
	public static void transfer(Bank source, Bank target, double amt){
		source.withdrawl(amt);
		target.deposit(amt);
	}
	
	
}
