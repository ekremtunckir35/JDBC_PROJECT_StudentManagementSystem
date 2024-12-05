//controller: istemci ile ilestisime gececek.Normalde fronted kismi istemci ile iletisime gecer.
//Fakat suan bir fronted yuzu olmadigi icin controller katmanini yaptik.
//uygulmayi baslatan ayni zamanda controller class i
//controler ile sadece service classlari ile gorusur.

import java.util.Scanner;

public class Runner {

     public static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) {

        start();

    }

    private static void start() {

        StudentService service =new StudentService();

        //7.adim  c - uygulama basladiginda tablo olusturulsun
        service.createStudentTable();

        //Kullaniciya bir menu sunmak gerekir.Bunun icin kullanici ile iletime gecmek gerekir.
        //Scanner obj.ihtiyac var.
        //Kullanicin tekrar tekrar menuyu gormesini kullanmasini istersek do-while kullanabiliriz.
        //Bu dongu kullanici uygulamadan cikmak isteyene kadar devam edecek.Bunun icin switch case kullaniriz.

        int select;
        int id;
        do {
            System.out.println("======================================================" );

            System.out.println("Öğrenci Yönetim Paneli");
            System.out.println("1-Öğrenci Kaydetme");
            System.out.println("2-Tüm Öğrencileri Görüntüleme");
            System.out.println("3-Öğrenciyi Güncelleme");
            System.out.println("4-Öğrenciyi Silme");
            System.out.println("5-Tek Bir Öğrenciyi Görüntüleme");
            System.out.println("6-Tum ogrencilerin AS_SOYAD bilgilerini rapora yazdirma");
            System.out.println("0-ÇIKIŞ");
            System.out.println("İşlem Seçiniz : ");
            select = inp.nextInt();
            inp.nextLine();


            switch (select){
                case 1:
                    //bilgileri verilen ogrenciyi kaydetme
                    Student newStudent =service.getStudentInfo(); //kullanicidan ogrenci bilgisi alir
                    new Thread(()->{
                        service.saveStudent(newStudent);
                    }).start();
                    break;
                case 2:
                    //ogrencileri konsola yazdirma
                    service.printAllStudents();
                    break;
                case 3:
                    //id si verilen ogrenciyi guncelleme
                    id =getIdInfo();
                    service.updateById(id);
                    break;
                case 4:
                    //id si verilen ogrenciyi silme
                    id =getIdInfo();
                    service.deleteById(id);
                    break;
                case 5:
                    //tek bir ogrenciyi goruntulemek icn id verilen ogrenci olmasi gerekir
                    //id i verilen ogrenciyi goruntuleme
                    id=getIdInfo();
                    service.printStudentById(id);
                case 6:
                    new Thread(()->{
                        service.generateReport();
                    }).start();
                    break;
                case 0:
                    System.out.println("Iyi gunler...");
                    break;
                default:
                    System.out.println("Hatali giris!!!");
                    break;
            }
        }while(select != 0);

    }

    //ogrencinin id sini tekrar tekrar sormak yerine method yapilir
     private static int getIdInfo(){
         System.out.println("Ogrenci id: ");
         int id = inp.nextInt();
         inp.nextLine();
         return id;
     }
}
