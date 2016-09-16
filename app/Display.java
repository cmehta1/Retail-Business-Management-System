import java.sql.*;

import java.util.ArrayList;
import java.util.Scanner;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;

public class Display {
	public static void main (String args []) throws SQLException {
		try
		{
			//connecting to oracle server
			OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			Connection conn = ds.getConnection("jnuti1","a1b2c3d4SRI");  //opening the connection

			Scanner in = new Scanner(System.in);
			
			if(conn!=null){
				//make an array of valid choices
				ArrayList<String> validChoices = new ArrayList<String>();
				for(int i = 0; i < 11; i++){
					validChoices.add(Integer.toString(i));
				}
				String choice = "";
				while(!validChoices.equals("0")){
					while(!validChoices.contains(choice)){
						System.out.print("\n0: Exit\n" +
								"1: Display employees\n2: Display customers\n3: Display products\n4: Display purchases\n5: Dsiplay suppliers\n6: Display supply\n" +
								"7: Display logs\n8: Display monthly sale information\n9: insert into products\n10: insert into purchases\n\n" +
								"Enter your choice: \t \n");
						choice = in.nextLine();
					}

					Mypack mp = new Mypack();
					
					// Displaying options
				if(choice.equals("1")){
						mp.show_employees(conn);
					}else if(choice.equals("2")){
						mp.show_customers(conn);
					}else if(choice.equals("3")){
						mp.show_products(conn);
					}else if(choice.equals("4")){
						mp.show_purchases(conn);
					}else if(choice.equals("5")){
						mp.show_suppliers(conn);
					}else if(choice.equals("6")){
						mp.show_supply(conn);
					}else if(choice.equals("7")){
                        			mp.show_logs(conn);
                    			}else if(choice.equals("8")){
						mp.report_monthly_sale(conn);
					}else if(choice.equals("9")){
						mp.add_products(conn);
					}else if(choice.equals("10")){
						mp.add_purchases(conn);
					}
					if(!choice.equals("0")){
						choice = "";
					}else{
						break;
					} 
				}
			}
			conn.close(); //closing the connection
		} 
		catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
		catch (Exception e) {System.out.println (e);}
	}
} 


