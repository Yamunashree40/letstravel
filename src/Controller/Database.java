package Controller;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName="travelsql";
        String databaseUser="root";
        String databasePassword="Tn67%f3197";
        String url="jdbc:mysql://localhost/" + databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
