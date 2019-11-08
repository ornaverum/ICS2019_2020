public class Program{
	public static void main (String[] args){
		FinWallet vault = new FinWallet(32658.00002);
		
		System.out.println(vault);
		
		vault.deposit(4);
		System.out.println(vault);
		
		vault.withdrawl(3);
		System.out.println(vault);
		
		if (vault.withdrawl(60000)){
			System.out.println("Transaction worked!");
		} else {
			System.out.println("Failed");
		}
		System.out.println(vault);
		
		FinWallet mattress = new FinWallet(5);
		
		FinWallet.transfer(vault, mattress, 5000);
		System.out.println("In vault " + vault);
		System.out.println("In mattress " + mattress);
		
		
	}
}
