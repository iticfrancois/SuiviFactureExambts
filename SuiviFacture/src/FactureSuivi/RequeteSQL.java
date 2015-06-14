package FactureSuivi;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class RequeteSQL 
{
	
	private String result = "error";
	
	public String toString(int id, String table, String colonne)
	{
		Connection c = null;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost/suivifacture",
        		   user = "root",
        		   password = "";
        	c = DriverManager.getConnection(url, user, password);
                
        	String req = "select " + colonne + " from " + table + " where id_" + table + " = " + id + ";";
        	Statement s = c.createStatement();
        	ResultSet rs = s.executeQuery(req);
                
        	while (rs.next())
        	{
        		return rs.getString(1);
        	}
        }
        
        catch (ClassNotFoundException e)
        {
        	return "Pilote JDBC non installé.";
        }
        
        catch (SQLException e)
        {
        	return result;
        }
        
        finally
        {
        	try
            {
        		if (c != null)
                   	c.close();
            }
                
            catch(SQLException e)
            {
            	return "Impossible de fermer la connection.";
            }
        }
        
        return result;
	}
	
	public String etatfacture (int id)
	{
		RequeteSQL salle = new RequeteSQL();
		return salle.toString(id, "ligue", "etat_facture");
	}

	public void update_table(int id_ligue, String etat_facture)
	{
		Connection c = null;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost/suivifacture",
        		   user = "root",
        		   password = "";
        	c = DriverManager.getConnection(url, user, password);
                
        	String req1 = "update ligue set etat_facture =  (" + "\"" + etat_facture + "\"" + ") where id_ligue = " + id_ligue + "";                
        	
        	Statement s = c.createStatement();
        	s.executeUpdate(req1);
        	
        }
        
        catch (ClassNotFoundException e)
        {
        	System.out.println("Pilote JDBC non installé.");
        }
        
        catch (SQLException e)
        {
        	System.out.println(e);
        }
        
        finally
        {
        	try
            {
        		if (c != null)
                  	c.close();
            }
                
            catch(SQLException e)
            {
            	System.out.println("Impossible de fermer la connection.");
            }
        }
	}
	
    public static void main(String[] args)
    {
    	
    }
}
