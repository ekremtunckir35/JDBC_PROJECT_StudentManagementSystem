import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Repository-->Database veritabani ile ilgili islemler
//Database baglantisi,
//Repository hangi metotlar -->cretate table,save,update,delete,findAll
//interface tanimlamak gerekir

public class StudentRepository implements Repository<Student,Integer>{

  // 7.adim--->t_student tablosunu olusturma

    @Override
    public void createTable() {

        //tabloyu olusturnmak icin database gitmek gerek Database gitmek icin  connection olusturmak gerekiyor

        JdbcUtils.setConnection();//bağlantı oluşturuldu
        JdbcUtils.setStatement();//statement başlattık

        try {
            JdbcUtils.st.execute("CREATE TABLE IF NOT EXISTS t_student(\n" +
                    "id SERIAL UNIQUE,\n" +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0),\n" +
                    "lastname VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0),\n" +
                    "city VARCHAR(50) NOT NULL, \n" +
                    "age INTEGER NOT NULL CHECK(age>0)  )");

        } catch (SQLException e) {
            System.out.println(e.getMessage());


        } finally {

            try {
                JdbcUtils.st.close();
                JdbcUtils.connection.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }

    }


 //8-ogrenciyi kaydetme

    @Override
    public void save(Student student) {
        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("INSERT INTO t_student(name,lastname,city,age) VALUES(?,?,?,?)");


        try {
            JdbcUtils.prst.setString(1, student.getName());
            JdbcUtils.prst.setString(2,student.getLastname());
            JdbcUtils.prst.setString(3,student.getCity());
            JdbcUtils.prst.setInt(4,student.getAge());
            JdbcUtils.prst.executeUpdate();
            System.out.println("Basarili bir sekilde kaydedildi.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }finally {

            try {
                JdbcUtils.prst.close();
                JdbcUtils.connection.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    //9-Tum ogrencileri database den cekme
    @Override
    public List<Student> findAll() {

        JdbcUtils.setConnection();
        JdbcUtils.setStatement();

        List<Student> allStudent = new ArrayList<>();
        try {
            ResultSet rs =JdbcUtils.st.executeQuery("SELECT  * FROM t_student");

            while (rs.next()) {
                Student student = new Student(rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("city"),
                        rs.getInt("age"));

                student.setId(rs.getInt("id"));
                allStudent.add(student);

            }
        } catch (SQLException e) {

            e.printStackTrace();

        }finally {


            try {
                JdbcUtils.st.close();
                ;
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();


            }

        }
        return allStudent;

    }



//11-Verilen yeni ogrenci bilgisi ile var olan ogrencinin yeni bilgileri

    @Override
    public void update(Student student) {//student:ogrencinin yeni bilgileri
        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("UPDATE t_student SET name =?,lastname=?,city=?,age=? WHERE id=?");
    try{
        JdbcUtils.prst.setString(1,student.getName());
        JdbcUtils.prst.setString(2,student.getLastname());
        JdbcUtils.prst.setString(3,student.getCity());
        JdbcUtils.prst.setInt(4,student.getAge());
        JdbcUtils.prst.setInt(5,student.getId());
        int updated=JdbcUtils.prst.executeUpdate();//kac tane kayit edildigi bilgisi verir
        if (updated>0){
            System.out.println("Güncelleme başarılı...:");//1
    }
    }catch (SQLException e) {
    throw  new RuntimeException(e);

    }finally {

        try{
            JdbcUtils.prst.close();
            JdbcUtils.connection.close();

        }catch (SQLException e) {
            e.printStackTrace();

        }
    }
    }

  //12- id si verilen ogrenciyi silme
    //burada ID si verilen ogrenciyi sorgu ile silebiliriz.Tablodan silme
    @Override
    public void deleteById(Integer id) {
        JdbcUtils.setConnection();
        //JdbcUtils.setPreparedStatement("DELETE FROM t_student WHERE id=?");
        JdbcUtils.setStatement();

        try {
           int deleted = JdbcUtils.st.executeUpdate("DELETE  FROM t_student WHERE  id="+id);
           //kayit bulunur ise 1 tane,bulunmazsa 0 kayit silinir.
            if(deleted>0){
                System.out.println("Ogrenci silindi,ID:" +id);
            }else {
                System.out.println("Ogrenci bulunamadi....");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JdbcUtils.st.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    //10- id si verilen ogrenciyi bulmma
    @Override
    public Student findById(Integer id) {
        Student student = new Student();
        JdbcUtils.setConnection();

        JdbcUtils.setPreparedStatement("SELECT  * FROM t_student WHERE id=?");
        try {
            JdbcUtils.prst.setInt(1,id);
            ResultSet rs =JdbcUtils.prst.executeQuery();

            if (rs.next()) {
                student =new Student(
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("city")
                        ,rs.getInt("age"));
                student.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }finally {
            try {
                JdbcUtils.prst.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


        return student;
    }
}
