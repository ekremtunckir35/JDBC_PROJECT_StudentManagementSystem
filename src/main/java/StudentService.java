
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;



// Service-Business Katmani --->Mantiksal islemler,gerekli duzenlemeler bu katmanda yapilir.
//servis classlari repo classlari ile gorusur.Yani servis classlarinin methodlarini repo classlarinda kullaniriz.


public class StudentService {

    Scanner input = Runner.inp;

    //repositorynin methodlarını kullanabilmek için obje oluşturalım

    private Repository repo = new StudentRepository();

    //7a - Student Tablosunu olusturma

    public void createStudentTable() {

        repo.createTable();
    }

    //8-a verilen bilglier ile ogrenciyi  kaydetme

    //ogrencinin bilgilerini almak icin metot

    public Student getStudentInfo() {
        System.out.println("AD :");
        String name = input.next();
        System.out.println("SOYAD :");
        String lastname = input.next();
        System.out.println("SEHIR :");
        String city = input.next();
        System.out.println("YAS :");
        int age = input.nextInt();
        input.nextLine();//

        return new Student(name, lastname, city, age);//olusan ogrencinin id si null
    }
    // ogrencibilgileri aldik simdi kaydetmek gerekiyor
    public void saveStudent(Student newStudent) {
        repo.save(newStudent);
    }

    //9-a -tum ogrencileri konsola yazdirma

    public void printAllStudents() {
        List<Student> students = repo.findAll();

        System.out.println("==========================TUM OGRENCILER===============================");

        for (Student student : students) {

            System.out.println("id : " + student.getId() +
                    "  ad: " + student.getName() +
                    "  soyad: " + student.getLastname() +
                    "  yaş : " + student.getAge() +
                    " şehir :" + student.getCity());
        }
    }

//10-a- id si verilen ogrenciyi getirme yani service tarafina alma
    public Student getStudentById(int id) {
        Student student = (Student) repo.findById(id);
        return student;
    }
    //10-b -- ID si verilen ogrenciyi goruntuleme-yazdirma
    public void printStudentById(int id) {
        //bu id ile ogrenci var mi?
        Student foundStudent = getStudentById(id);
        if (foundStudent != null) {
            System.out.println("ID si verilen ogrenci bulunamadi." +id);
        } else {
            System.out.println(foundStudent);

                    }

    }

    //11-a - ID si verilen ogrencinin bilgilerini yeni bilgiler ile degistirme
    public void updateById(int id) {  //id i 3 olan ogrenciyi bulma
        //oncelikle bu id ile ogrenci var mi tabloya bakilacak
        Student foundStudent = getStudentById(id);
        if (foundStudent != null) {
            System.out.println("ID si verilen ogrenci bulunamadi.ID:" + id);
        } else {//harry potter
            //girilecek yeni bilgiler nedir?yeni name, yeni lastname, yeni city,yeni yas ne olacak
            //bu bilgleri  getStudentInfo ile alabiliyorduk.
            Student newStudent = getStudentInfo();//yeni ogrenci bilgilerini verir
            //var olan ogrenci bilgilerini yeni ogrenci bilgileri ile degistirelim
            foundStudent.setName(newStudent.getName());
            foundStudent.setLastname(newStudent.getLastname());
            foundStudent.setCity(newStudent.getCity());
            foundStudent.setAge(newStudent.getAge());
            //ID si ayni kalacak
            //foundStudent :name:yeni  lastname:yeni city:yeni  age:yeni ,ID ise eski ID
            repo.update(foundStudent);
        }

    }
    //12-a -  ID si verilen ogrenciyi silme
    public void deleteById(int id) {
        repo.deleteById(id);
    }
    //13-Tum ogrencilerin ad-soyad bilgilerin rapora yazdirma
    //text dosyasi olusturacagiz
    public void generateReport() {
        List<Student> allStudents = repo.findAll();
        System.err.println("Report is loading....");
        try {
            Thread.sleep(10000);
            FileWriter writer = new FileWriter("student_report.txt");
            writer.write("*** Student Report ***\n");
            writer.write("---------------------------------------------------------\n");
            for (Student student : allStudents) {
                writer.write("AD: " + student.getName() + "SOYAD:" + student.getLastname() + "\n");
            }

            writer.close();
            System.err.println(" Report generated and printed to student_report.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
