# BankSystem Project With Java and MySql

Okulda verilen proje üzerine veritabanlı ve arayüzlü bir banka sistemi tasarlamamızı istendi. Ben de bunun için uygulamayı Java dilinde Swing arayüz kütüphanesiyle ve MySql veritabanıyla geliştirmeye karar verdim.


### Proje Kodlarında bulunan classların özellikleri

- GirisSayfasi.java
Proje bizi bir giriş sayfasıyla karşılar. Bu giriş sayfasının kodları bir JFrameForm içerisinde bulunur. İşte bu giriş sayfasının JFrame kodlarının bulunduğu dosyanın ismidir.

-  MudurSayfasi.java
Giriş panelinde müdür rolünü seçtikten sonra gerekli olan bilgileri doğru girince yönlendirilen JFrameForm kodlarının bulunduğu dosyanın ismidir. Bu kod içerisinde müdür bilgileri ve müdürün yapabileceği işlevlerinin kodları bulunur.

- TemsilciSayfasi.java
Giriş panelinde temsilci rolünü seçtikten sonra ve gerekli olan bilgileri doğru girince yönlendirilen JFrameForm kodlarının bulunduğu dosyanın ismidir. Bu kod içerisinde temsilciye ait bilgiler ve temsilcinin yapabileceği işlevlerin kodları bulunmaktadır.

- MusteriSayfasi.java
Giriş panelinde müşteri rolünü seçtikten sonra ve gerekli olan bilgileri doğru girince yönlendirilen JFrameForm kodlarının bulunduğu dosyanın ismidir. Bu kod içerisinde müşteriye ait bilgiler ve müşterinin yapabileceği işlevlerin kodları bulunmaktadır.

- VeritabaniKonfigrasyon.java
Projemizde MYSQL veritabanı bulunmaktadır. Bu veritabanında sorgular çalıştırabilmek veya yeni veriler eklemek için kod içerisinde bu veritabanına bağlanmamız gerekir. Bu class veritabanına bağlanıp sorgular çalıştırmayı sağlayan classtır. Hangi java dosyasının içerisinde veritabanına ulaşmak istiyorsak bu classtan bir obje oluşturup kolayca veritabanına bağlanmış olabiliriz. Bu da bize kod tekrarını engellemeyi sağlar.

 - CHareketler.java
Müşterinin yaptığı işlemler bir veritabanında tutulur. Bunların daha düzenli durmasını sağlamak için müşterinin yaptığı işlemlerin parametrelerini tutan bir class oluşturduk.

- CMudur.java
Müdür bilgilerinin daha düzenli durmasını sağlamak için ve kod tekrarını engellemek için müdür parametrelerini tutan bir class oluşturduk.

- CTemsilci.java
Temsilci bilgilerinin daha düzenli durmasını sağlamak için ve kod tekrarını engellemek için temsilci parametrelerini tutan bir class oluşturduk.

- CMusteri.java
Müşteri bilgilerinin daha düzenli durmasını sağlamak için ve kod tekrarını engellemek için müşteri parametrelerini tutan bir class oluşturduk.

- CTalep.java
Müşteri bazı işlemler için temsilciye talepte bulunması gerekir. Bu talepler tabloda gösterilmektedir. Bunların daha düzenli durmasını sağlamak için ve kod tekrarını engellemek için taleplerin parametrelerini tutan bir class oluşturduk.

Projenin Örnek Görselleri

### Giriş Paneli Örnek Görseli

![Giriş Panel](https://github.com/MelihSelamiUrkmezz/BankSystem/blob/master/proje_resimleri/giris.png)

## Müdür Paneli Örnek Görseli

![Müdür Paneli](https://github.com/MelihSelamiUrkmezz/BankSystem/blob/master/proje_resimleri/mudur.png)

## Temsilci Paneli Örnek Görseli

![Temsilci Paneli](https://github.com/MelihSelamiUrkmezz/BankSystem/blob/master/proje_resimleri/temsilci.png)

## Müşteri Paneli Örnek Görseli

![Müşteri Paneli](https://github.com/MelihSelamiUrkmezz/BankSystem/blob/master/proje_resimleri/musteri.png)




