package master.dao;
import java.sql.*;

import master.dto.FoodDto;
import master.utilities.ConnectionFactory;
public class FoodDao {
	private Connection cn=null;
	private Statement st=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private String select_sql="select * from food order by fname";
	private String insert_sql="insert into food values(?,?,?)";
	private String delete_sql="delete from food where fid=?";
	private String update_sql="update food set fname=?,price=? where fid=?";
	public void insertData(FoodDto fdto)
	{
		try
		{
		
			ConnectionFactory con=new ConnectionFactory();
			cn=con.getConn();
			ps=cn.prepareStatement(insert_sql);
		 ps.setString(1,fdto.getFid());
		 ps.setString(2,fdto.getFname());
		 //ps.setInt();
		 ps.setDouble(3,fdto.getPrice());
		 ps.executeUpdate();
		}
		
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	public void deleteData(FoodDto fdto)
	{
		try
		{
		
			ConnectionFactory con=new ConnectionFactory();
			cn=con.getConn();
			ps=cn.prepareStatement(delete_sql);
	

		 ps.setString(1,fdto.getFid());
		 
		 ps.executeUpdate();
		}
		
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	public void updateData(FoodDto fdto)
	{
		try
		{

			ConnectionFactory con=new ConnectionFactory();
			cn=con.getConn();
			ps=cn.prepareStatement(update_sql);
		
		 ps.setString(1,fdto.getFname());
		 //ps.setInt();
		 ps.setDouble(2,fdto.getPrice());
		 ps.setString(3,fdto.getFid());
		 ps.executeUpdate();
		}
		
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	public void getData()
	{
		try
		{
		//Class.forName("com.mysql.cj.jdbc.Driver");//REGISTER AND LOAD THE JDBC DRIVER
		//ESTABLISH THE CONNECTION
		 //cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBJEE","root","arindam");
		ConnectionFactory con=new ConnectionFactory();
		cn=con.getConn();
		st=cn.createStatement();//CREATEB THE STATEMENT
		 rs=st.executeQuery(select_sql);//EXECUTE THE QUERY AND STORE THE DATA INTO RESULTSET
		 while(rs.next())
		 {
			 //System.out.println(rs.getString(1));
			 //System.out.println(rs.getString(2));
			 //System.out.println(rs.getInt(3));
			 //System.out.println(rs.getDouble(4));
			System.out.println(rs.getString(1)+"==>"+rs.getString(2)+"===>"+rs.getInt(3)+"===>"+rs.getDouble(4));
		 }
		}
		
		/*catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}*/
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		}
}
