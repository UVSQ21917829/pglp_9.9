package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public  abstract class DAO <T>{
	
	//crud
	protected Connection connection=null;
	String databaseURL = "jdbc:derby:db_dessi;create=true";
	public abstract T create(T obj) throws IOException;
    public abstract T read(String id) throws ClassNotFoundException, IOException;
    public abstract T update(T obj) throws ClassNotFoundException, IOException;
    public abstract void delete(String id) throws IOException;
	
    // la creation de la connexion avec un SGBD derby en mode embarqué
    public Connection createConnection() {
    	try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            connection = DriverManager.getConnection(databaseURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
		return connection;
    }
    
    //fermer la connection
    public  void closeConnexion()
    {
    	try
        {
                
                if(connection!=null)
                connection.close();
                     
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }

}

