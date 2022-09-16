package Simple.employeeManagement;

/*
 *Program to illustrate the functionalities of Human Resource Department in any organization like ADD employee , 
 *remove employee and giving hike to employees
*/

import java.sql.*;				//SQL package imported here.

public class hrDao {
	Connection c = null;

	void connect() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver"); 		// Loading JDBC Driver.
		String url = "jdbc:mysql://localhost:3306/anudip";
		String username = "root";							
		String password = "ashu@2407";
		c = DriverManager.getConnection(url, username, password);		//Connection established from database. 					
	}

	int newEmployee(hrModule obj) throws Exception {
		
		//Verifying employee details to prevent duplicacy.
		
		String query = "select * from anudipEmployee where cPhone = '" + obj.cPhone + "' and cDomain= '"+obj.cDomain+ "' ";
		Statement st = c.createStatement();
		ResultSet se = st.executeQuery(query);							
		if (se.next()) {
			return -1;
		} else {
			String query2 = ("INSERT into anudipEmployee(cName,cPhone,cDomain,cSalary,cLocation,cDesignation) values(?,?,?,?,?,?);");
			PreparedStatement ps = c.prepareStatement(query2);
			ps.setString(1, obj.cName);				//Inserting New employee details to the database.
			ps.setString(2, obj.cPhone);
			ps.setString(3, obj.cDomain);
			ps.setInt(4, obj.cSalary);
			ps.setString(5, obj.cLocation);
			ps.setString(6, obj.cDesignation);
			int count = ps.executeUpdate();		//it will return 1 because 1 row will be updated in the database.
			return count;				//count variable is returned to main method.
		}
	}

	int removeEmployee(int cId) throws Exception {
		Statement ds = c.createStatement();
		
		//Checking whether employee details match in the database or not.
		ResultSet rs = ds.executeQuery("select * from anudipEmployee where cId="+cId);
		if(rs.next()) {
			rs.close();
			Statement s = c.createStatement();
			
			//Deleting employee row from the table.
			int count = s.executeUpdate("DELETE from anudipEmployee WHERE cId = "+cId);		
			return 1;
		}
		else {
			return 0;
		}
	}

	public int increaseSalary(int cId, int hike) throws Exception {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("select * from anudipEmployee where cId ="+cId);
		
		if(rs.next()) {
				Statement s = c.createStatement();
				
				//Query to increase the salary of an employee in database. 
				int count = s.executeUpdate("update anudipEmployee SET cSalary=cSalary+((cSalary*" + hike + ")/100) where cId=" + cId);
				return count;		//count variable which carry 1 will be returned to the main method.
		}
		else {
			return 0;
		}
	}
}
