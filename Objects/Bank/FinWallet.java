public class FinWallet{
	private double balance;
	
	public FinWallet(){
		this.balance = 0;
	}
	
	public FinWallet(double initBalance){
		this.balance = initBalance;
	}
	
	// getters
	public double getBalance() { return this.balance; }
	
	// setters
	public void setBalance(double balance) { this.balance = balance; }
	
	public void deposit(double amt){
		this.balance += amt;
	}
	
	public boolean withdrawl(double amt){
		if (this.balance < amt){
			return false;
		}
		else{
			this.balance -= amt;
			return true;
		}
	}
	
	public String toString(){
		return String.format("You have %.6f fn", this.balance);
	}
	
	public static boolean transfer (FinWallet source, FinWallet target, double amt){
		if(source.withdrawl(amt)){
			target.deposit(amt);
			return true;
		} else {
			return false;
		}
	}
	
	
}
