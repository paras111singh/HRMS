/**
 * @author Paras Singh
 *Program to illustrate the functionalities of Human Resource Department in any organization like ADD employee , 
 *remove employee and giving hike to employees
 */
package Simple.employeeManagement;

import static java.lang.System.*;
import java.util.Scanner;


public class hrmain {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);         			//Scanner object created.
		hrDao hd = new hrDao();									//hrDao method created.
		hd.connect();											//hdDao method connected to main
		hrModule hm = new hrModule();							//hrmodule object created.
		out.println("\t\t\t----Welcome to Sare_Mechanics Pvt Ltd----");
		
		out.println(" Press 1 to add New Employee \n Press 2 to increase Salary of the Employee \n Press 3 to Remove an Employee \n ");
		int choose = sc.nextInt();
		switch (choose) {										
		case 1 -> {												//New employee details adding here.
			out.println("Enter the details of Joinee");
			out.println("Enter Joinee Name:-");
			hm.cName = sc.next();
			out.println("Enter Joinee Phone number:-");
			hm.cPhone = sc.next();
			out.println("Enter Joinee Domain:-");
			hm.cDomain = sc.next();
			out.println("Enter Joinee Salary:-");
			hm.cSalary = sc.nextInt();
			out.println("Enter Joinee Working location:-");
			hm.cLocation = sc.next();
			out.println("Enter Joinee Designation:-");
			hm.cDesignation = sc.next();
			int var1 = hd.newEmployee(hm);
			if (var1 == 1)
				out.println("Employee added Successfully.");
			//Checking if employee already exist in the company.
			else
				out.println("Employee already exist");
		}
		case 2 -> {												//Giving hike to the user entered employee.
			int amount, id;
			out.print("Enter Employee Id:-");
			id = sc.nextInt();						//Taking Employee id as input. 
			out.print("Enter percentage(%) to give salary hike:-");
			amount = sc.nextInt();						//Taking input of how much to give hike. 
			int var3 = hd.increaseSalary(id, amount);     //increaseSalary method is called here. 
			if (var3 == 1)
				out.println("Congratulations your salary is increased.");
			
			//checking if employee exist in the company.
			
			else
				out.println("Employee does not exist.\n Enter a valid input.");
		}
		case 3 ->{												//Removing employee from the database.
			int cid;
			out.print("Enter Employee Id:-");
			cid = sc.nextInt();						//taking input of employee id.
			int var4 = hd.removeEmployee(cid);		
			if (var4==1)
				out.println("Employee removed successfully.");
			//checking whether employee exist in the company or not.
			else
				out.println("Employee does not exist!!!");
		}

		}
		sc.close();							//Scanner object closed.
	}
}
