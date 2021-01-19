package todolist;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB 
{
	public static void main(String args[]) {
		DB.dbconnect();
	}
	java.sql.PreparedStatement pst;
	Connection con = null;
	public static Connection dbconnect()
	{
	try
	{
		
		Class.forName("org.postgresql.Driver");
		Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/todolist","postgres","password");
		if(conn!=null)
		{
			System.out.println("Connection OK");
		}
		else
		{
			System.out.println("Connection Failed");
		}
	return conn;
	}
	catch (Exception e)
	{
		System.out.println(e);
	}
	return null;
}
}
