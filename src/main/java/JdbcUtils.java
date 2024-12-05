import java.sql.*;
import java.util.Collection;

//jdbc icin gerekli olan nesnelerin hazirligi

// 4-jdbc icin gerekli olan nesnelerin hazirlanmasi


public class JdbcUtils {

    //herkes icin ayni ve yeterli gerekli oldugu icin  statik olusturalim istedigimiz classtan bu nesnelere ulasabilmek
    //icin oublic

    public static Connection connection;
    public static Statement st;
    public static PreparedStatement prst;

    //statik olan uc tane nesne var.Bu nesnelerin degerlerini olusturmam gerek.Bu nesnenin degerlerini uygulama
    //basladiginda baslatmayacagim.Ne zaman baslatacagim,ihtiyacim oldugunda connection ac deyecegim.
    //Ihtiyac bittyiginde connection kapatiyorum.Ihtiyac aninda kullanabilmek icin herbiri icin method olusturmaliyiz.

    //4a- Connection olusturma methodu
    //url,username,password

    public static void setConnection(){

        try {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcStudentManagement",
                    "techpro","password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }




    ////4b- ihtiyac aninda statement olusturma
    ///

    public static void setStatement(){
        try {
            st=connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

      //4c- preparedStatement
     //preperstatement olusturabilmek icin elimzde SQL ifademizin hazir olmasi gerekir.

    public static void setPreparedStatement(String sql){
        try {
            prst = connection.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }



}
