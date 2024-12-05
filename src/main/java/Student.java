
//domain-entity(varlik-kalici hale getirmek istedigimz bilgiler)
//
public class Student {

    private  Integer id;//eger deger atanmamissa null olarak kalsin.
    private  String name;
    private String lastname;
    private String city;
    private int age;

    //parametreli/parametresiz const. olusturma
    //Elimizde ogr bilgileri var ise dogrudan parametreli const.olusturmak isteyebiliriz.
    //ogrenci bilgileri yok fakat bir student objesine ihtiyac var ise parametresiz conts. olusturabiliriz.


    //Tabloyu olustururken SQL komutlari ile id in otomatik olusturulmasini saglayablilriz.SERIAL data tipi ile
    //

    public Student() {           //parametresiz cont.
    }

    public Student(String name, String lastname, String city, int age) {
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.age = age;
    }

    //private erisim tipine sahip olan fieldlera ulasmak icin publixc olarak


    //getter -setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //bir arayuz olmadigindan konsol uzerinde calistigimiz icin ogrenciyi dogrudan konsola yazdirmak isteyebiliriz.
    //Ogrenciyi dogrudan konsola yazdirabilmek icib student objesini String e donustirmek icin toString methoduna
    //ihtiyac olabilir.Bu methodu gercek uygulmalarda uygulanan blr yontem degil.Cunku gercek projelerde consola
    //yazdirma yoktur.

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }
}
