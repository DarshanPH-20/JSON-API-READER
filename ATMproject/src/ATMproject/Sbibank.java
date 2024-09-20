package ATMproject;

import java.util.Scanner;
public class Sbibank {
	
	public static void main(String[] args) {
			int ATMpin=1234;
		int balance =10000,withdraw,deposit,num;
		System.out.println("WELCOME TO SBI BANK");
		Scanner sc=new Scanner(System.in);
		System.out.println("ENTER THE ATM pin");
		int pin1=sc.nextInt();
		if ((pin1==ATMpin))
				{
			System.out.println("ENTERED CORRECT PIN");
				}
				else{
					System.out.println("SORRY!! YOU ENTERED WRONG PIN");
				}
				while(true)
		{
					System.out.println("MENU");
					System.out.println("1.DEPOSIT");
					System.out.println("2.WITHDRAW");
					System.out.println("3.CHECK BALANCE");
					System.out.println("4.EXIT");
		num=sc.nextInt();
		switch(num)
			{
		case 1:
			System.out.println("ENTER THE AMOUNT TO Deposit");
			deposit=sc.nextInt();
			balance=balance+deposit;
			System.out.println("AMOUNT DEPOSIT SUCCUSSFULLY");
			//System.out.println(" ");
			break;
			
		case 2:
			System.out.println("ENTER THE AMOUNT TO WITHDRAW");
			withdraw=sc.nextInt();
				balance=balance-withdraw;
				System.out.println("AMOUNT WITHDRAWN SUCCUSSFULLY");
				System.out.print("Available Balance Is: "+balance);
				//System.out.println(" ");
				break;

				
		case 3:
				System.out.println("ENTER THE KEY TO CHECK THE BALANCE");
			System.out.println("Balance :"+balance);
		//	System.out.println("  ");
			break;

		case 4:
			System.out.println(" THANK YOU!! ");
			System.exit(0);

			}
		}
	}
}