public class ObjectsPrac{
	public static void main(String[] args){
		Bank acct = new Bank(1000, 5.9);
		System.out.print(acct);
		
		//~ System.out.printf("I have $%.2f.", acct.balance);
		
		//~ acct.balance = 4000;
		//~ System.out.println();
		//~ System.out.printf("I have $%.2f.", acct.balance);
		acct.savingsCalc(12, 100);
		
		System.out.print(acct);
		
		Bank chk = new Bank(50, 0);
		
		System.out.print(chk);
		Bank.transfer(acct, chk, 500);
		
		System.out.print(acct);
		System.out.print(chk);
		
	}
}
