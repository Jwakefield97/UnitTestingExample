package edu.msu.atmmachine.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import edu.msu.atmmachine.dao.AtmDao;
import edu.msu.atmmachine.domain.User;

public class Atm {
	
	//create the HSQLDB 
	static DBInitializer initDB = new DBInitializer();
	//create data access object with the connection from initDB
	static AtmDao dao = new AtmDao(initDB.getConnection());
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Scanner cin = new Scanner(System.in);
		User user = null;
		boolean isContinue = true; //whether or not the user has selected to exit the options screen.
		
		//have the user sign in or register before continuing
		while(user == null) {
			System.out.print("Welcome please sign in or create an account. (signin,create): ");
			String welcomeStr = cin.nextLine();
			if(welcomeStr.equalsIgnoreCase("signin")) {
				user = signIn(cin);
			}else if(welcomeStr.equalsIgnoreCase("create")) {
				user = createAccount(cin);
			}
		}
		
		//loop for presenting options to the user after they login.
		while(isContinue) {
			String option = displayOptions(cin);
			System.out.println();
			switch(option.toLowerCase()) {
				case "q":
					isContinue = false;
					break;
				case "quit": 
					isContinue = false;
					break;
				case "bal":
					System.out.println("Current Balance: "+dao.getBalance(user.getUsername()));
					break;
				case "with":
					Double withAmt = getAmount(cin);
					user.setBalance(user.getBalance() - withAmt);
					dao.updateBalance(user.getUsername(), user.getBalance());
					break;
				case "dep":
					Double depAmt = getAmount(cin);
					user.setBalance(user.getBalance() + depAmt);
					dao.updateBalance(user.getUsername(), user.getBalance());
					break;
			}
		}
		
		cin.close(); //close Scanner object
		initDB.destroy(); //destroy user table
	}
	
	/**
	 * A method for creating a login screen for the user.
	 * @param sc Scanner object used to get user input pertaining to signing in.
	 * @return a User object of the authenticated user.
	 */
	public static User signIn(Scanner sc) {
		User resultUser = null;
		while(resultUser == null) {
			System.out.print("Enter your username: ");
			String usernameStr = sc.nextLine();
			try {
				if(dao.isUserExists(usernameStr)) {
					resultUser = dao.getUser(usernameStr);
				}
			} catch (SQLException e) {
				System.out.println("An unexpected error occurred. Please try again.");
			}
		}
		return resultUser;
	}
	
	/**
	 * A method for creating a account creation screen.
	 * @param sc Scanner object used to get user input pertaining to creating an account.
	 * @return a User object if the user created the account successfully, null if they didn't or exited.
	 */
	public static User createAccount(Scanner sc) {
		User resultUser = null;
		String username = null, firstName, lastName;
		boolean isExit = false; 
		
		//keep prompting the user for a username until they get one that is available or they want to exit
		while(username == null) {
			System.out.print("Enter your username: ");
			String usernameStr = sc.nextLine();
			try {
				if(dao.isUserExists(usernameStr)) {
					System.out.println("This username already exists please try again");
					if(isShouldExit(sc)) { //ask if user wants to exit
						isExit = true;
						break;
					}
				}else {
					username = usernameStr;
				}
			} catch (SQLException e) {
				System.out.println("An unexpected error occurred. Please try again.");
			}
			
		}
		if(!isExit) {
			System.out.print("Enter your first name: ");
			firstName = sc.nextLine();
			System.out.print("Enter your last name: ");
			lastName = sc.nextLine();
			try {
				dao.createUser(username, firstName, lastName);
				resultUser = dao.getUser(username);
			} catch (SQLException e) {
				System.out.println("An unexpected error occurred. Please try again.");
			}
		}
		return resultUser;
	}
	
	/**
	 * A method for asking a user if they want to quit.
	 * @param sc Scanner object used to get user input pertaining to exiting a portion of the app.
	 * @return a boolean specifying if the user wants to exit of not.
	 */
	public static boolean isShouldExit(Scanner sc) {
		System.out.print("Would you like to exit? (exit/e): ");
		String exitStr = sc.nextLine();
		return exitStr.equalsIgnoreCase("exit") || exitStr.equalsIgnoreCase("e");
	}
	
	/**
	 * A method for displaying and collecting user input for selecting options.
	 * @param sc Scanner object used to get user input pertaining to entering in options.
	 * @return a String of the option the user selected ("bal", "with", "dep", "quit/q").
	 */
	public static String displayOptions(Scanner sc) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("- Enter 'bal' to see you balance.                -");
		System.out.println("- Enter 'with' to make a withdrawl.              -");
		System.out.println("- Enter 'dep' to make a deposit.                 -");
		System.out.println("- Enter 'quit' or 'q' to quit.                 -");
		System.out.println("--------------------------------------------------\n");
		System.out.print("Enter an option: ");
		return sc.nextLine();
	}
	
	/**
	 * A method for getting withdraw/deposit amounts.
	 * @param sc Scanner object used to get user input pertaining withdraw/deposit amounts.
	 * @return a Double representation of the amount entered for withdraw/deposit
	 */
	public static Double getAmount(Scanner sc) {
		System.out.print("Enter an amount: ");
		return sc.nextDouble();
	}
    
}
