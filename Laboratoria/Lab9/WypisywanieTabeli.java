package Lab9;


import java.sql.*;

public class WypisywanieTabeli {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:h2:./data/nazwabazy", "sa",
					"");


			Statement statement = conn.createStatement();
			
			//Wyswietlanie calej tabeli:
			statement.execute("SELECT * FROM waluty");
			
			//Ograniczenie do 10 pierwszych rekordow
			//statement.execute("SELECT * FROM waluty limit 10" );
			
			// Przykladowe kwerendy z dodatkwoym warunkiem:
			statement.execute("SELECT data FROM waluty where usd > eur");
			//statement.execute("SELECT * FROM waluty where data < '2001-03-27'");
			//statement.execute("SELECT * FROM waluty where usd > 4.50");
			//statement.execute("SELECT * FROM waluty where usd > 4.50 and eur < 3.83");
			
			ResultSet rs = statement.getResultSet();
			
			ResultSetMetaData md  = rs.getMetaData();
					

			for (int ii = 1; ii <= md.getColumnCount(); ii++){
				System.out.print(md.getColumnName(ii)+ " | ");						
				
			}
			System.out.println();
			
			while (rs.next()) {
				for (int ii = 1; ii <= md.getColumnCount(); ii++){
					System.out.print( rs.getObject(ii) + " | ");							
				}
				System.out.println();
			}
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		

	}

}
