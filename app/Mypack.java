import java.util.Scanner;
import java.sql.*;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;

public class Mypack {
	
	public void show_employees(Connection conn)
	{
		try
		{
                        // calling the function in package func_package
			CallableStatement cs = conn.prepareCall("{? = call func_package.show_emp}");    
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// execute the query
			cs.executeQuery();
			//retrieve the result
			ResultSet rs = (ResultSet)cs.getObject(1);

			//if the table is not empty
				System.out.println("EID   ENAME      TELEPHONE#");
				System.out.println("----------------------------");

			while(rs.next()){
				System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3));
			}
			
			cs.close();
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}

	public void show_customers(Connection conn)
	{
		try
		{
			CallableStatement cs = conn.prepareCall("{? = call func_package.show_cust}");
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// execute query 
			cs.executeQuery();
			//retrieve the result
			ResultSet rs = (ResultSet)cs.getObject(1);
			// if the record is not empty
		System.out.println("CID   CNAME    TELEPHONE#     VISITS_MADE LAST_VISIT");	
		System.out.println("----------------------------------------------------");

			while(rs.next()){
				System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
			}
			cs.close();
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}
	
	
	public void show_products(Connection conn)
	{
		try
		{
			CallableStatement cs = conn.prepareCall("{? = call func_package.show_prod}");
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// execute query
			cs.executeQuery();
			// retrieve the result
			ResultSet rs = (ResultSet)cs.getObject(1);
			System.out.println("PID PNAME   QOH  THRESHOLD  ORIG_PRICE  DISC");
			System.out.println("--------------------------------------------");
			while(rs.next()){
				System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
			}
			cs.close();
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}

	
	public void show_purchases(Connection conn)
	{
		try
		{
			CallableStatement cs = conn.prepareCall("{? = call func_package.show_pur}");
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// execute the query
			cs.executeQuery();
			//retrieve the result
			ResultSet rs = (ResultSet)cs.getObject(1);
			System.out.println("PUR# EID PID CID QTY PTIME   TOTAL_PRICE");
			System.out.println("----------------------------------------");
			while(rs.next()){
				System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
			}
			cs.close();
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}

	
	public void show_suppliers(Connection conn)
	{
		try
		{
			CallableStatement cs = conn.prepareCall("{? = call func_package.show_sup}");
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// execute the query
			cs.executeQuery();
			//retrieve the result
			ResultSet rs = (ResultSet)cs.getObject(1);
			System.out.println("SID  SNAME  CITY    TELEPHONE#");
			System.out.println("------------------------------");
			while(rs.next()){
				System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
			}
			
			cs.close();
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught hello there***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}

	
	public void show_supply(Connection conn)
	{
		try
		{
			CallableStatement cs = conn.prepareCall("{? = call func_package.show_supply}");
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// execute the query
			cs.executeQuery();
			//retrieve the result
			ResultSet rs = (ResultSet)cs.getObject(1);
   			System.out.println("SUP#  PID  SID  SDATE  QUANTITY");
			System.out.println("-------------------------------");
			while(rs.next()){
				System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
			}
			cs.close();
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught heyyyloooo***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}

        
        public void show_logs(Connection conn)
        {
                try
                {  
                        CallableStatement cs = conn.prepareCall("{? = call func_package.show_logs}");
                        cs.registerOutParameter(1, OracleTypes.CURSOR);
                        // execute the query
                        cs.executeQuery();
			ResultSet rs = (ResultSet)cs.getObject(1);
			//retrieve the result
			System.out.println("LOG#  WHO  OTIME  TABLE_NAME   OPERAT KEY");
			System.out.println("-----------------------------------------");
			while(rs.next()){
			System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
			}
                        cs.close();
                }
                catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
                catch (Exception e) {System.out.println (e);}
        }

	
	public void report_monthly_sale(Connection conn)
	{
		Scanner in = new Scanner(System.in);  //prompting the user for pid
		try
		{		
			System.out.print("Enter the pid: \t");
			String pid = in.nextLine();
			
			CallableStatement cs = conn.prepareCall("{? = call func_package.report_monthly_sale(?)}"); //calling package_name.funtion_name
 
			cs.setString(2, pid);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			// execute the query
			cs.executeQuery();
			//retrieve the result
			ResultSet rs = (ResultSet)cs.getObject(1);
			System.out.println("PNAME   MONTH  YEAR  QTY AVG  TOTAL");
			System.out.println("-----------------------------------");
			while(rs.next()){
				System.out.println(rs.getString(1)  +" " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getString(4)+ " " + rs.getString(5)+ " " + rs.getString(6));
			}
			if(!rs.next())
			{
				System.out.println("PID not found");
			}
			
			cs.close();
		}
                
		catch (SQLException ex) { 
		if(ex.getErrorCode()==20001) {
     		System.out.println("PID not found");
		}
		System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
				
	}

	
	public void add_products(Connection conn)
	{
		Scanner in = new Scanner(System.in);
		try
		{		
			//prompting the user for all the required product entries
			System.out.print("Enter the pid: \t");
			String pid = in.nextLine();
			System.out.print("Enter the pname: \t");
			String pname = in.nextLine();
			System.out.print("Enter the quantity: \t");
			String qty = in.nextLine();
			System.out.print("Enter the threshold: \t");
			String threshold = in.nextLine();
			System.out.print("Enter the original price: \t");
			String price = in.nextLine();
			System.out.print("Enter the discount rate: \t");
			String Drate = in.nextLine();
                        //Prepare to call stored procedure:
			CallableStatement cs = conn.prepareCall("begin insert_purchases.add_products(:1,:2,:3,:4,:5,:6); end;");
			//setting the variables to the entered values
			cs.setString(1, pid);
			cs.setString(2, pname);
			cs.setString(3, qty);
			cs.setString(4, threshold);
			cs.setString(5, price);
			cs.setString(6, Drate);
			//cs.registerOutParameter(7, Types.VARCHAR);
			// execute and retrieve the result set
			cs.executeQuery();
			cs.close();
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}

	
	public void add_purchases(Connection conn)
	{
		Scanner in = new Scanner(System.in);
		try
		{		// prompting the user for reuired entries
			System.out.print("Enter eid: \t");
			String eid = in.nextLine();
			System.out.print("Enter pid: \t");
			String prodid = in.nextLine();
			System.out.print("Enter cid: \t");
			String cid = in.nextLine();
			System.out.print("Enter purchase quantity: \t");
			String quantity = in.nextLine();

			String query = "SELECT qoh FROM products where pid like ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,prodid);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int val =  ((Number) rs.getObject(1)).intValue();
			int qty = Integer.parseInt(quantity);
                        
			if (qty > val) {  //if quantity is greater than threshold add it into the purchase table
				CallableStatement cs = conn.prepareCall("begin insert_purchases.add_purchases(:1,:2,:3,:4); end;");

                                cs.setString(1, eid);
                                cs.setString(2, prodid);
                                cs.setString(3, cid);
                                cs.setString(4, quantity);
                                cs.executeQuery();
                                cs.close();
			}
			else {   //if quantity is less than therhold , update the values
			int remQty = val - qty;
			query = "SELECT qoh_threshold FROM products where pid like ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,prodid);
			ResultSet rss = pstmt.executeQuery();
			rss.next();
			int thres =  ((Number) rss.getObject(1)).intValue();
				if ( thres > remQty) {  //trigger statement
					System.out.println("quantity less than threshold, new supply is needed");
				}

				CallableStatement cs = conn.prepareCall("begin insert_purchases.add_purchases(:1,:2,:3,:4); end;");
				// setting the entries to the corresponding values
				cs.setString(1, eid);
				cs.setString(2, prodid);
				cs.setString(3, cid);
				cs.setString(4, quantity);
				//cs.registerOutParameter(5, Types.VARCHAR);
				// execute and retrieve the result set
				cs.executeQuery();
				cs.close();
			}
		}
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}

}

