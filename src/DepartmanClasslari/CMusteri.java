/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DepartmanClasslari;

/**
 *
 * @author melih
 */
public class CMusteri {
        private int id;
    private String AdSoyad;
    private String TelNo;
    private String TcNo;
    private String Adres;
    private String Eposta;
    private String kullaniciadi;
    private String sifre;
    private int temsilciid;

    public CMusteri(int id, String AdSoyad, String TelNo, String TcNo, String Adres, String Eposta, String kullaniciadi, String sifre, int temsilciid) {
        this.id = id;
        this.AdSoyad = AdSoyad;
        this.TelNo = TelNo;
        this.TcNo = TcNo;
        this.Adres = Adres;
        this.Eposta = Eposta;
        this.kullaniciadi = kullaniciadi;
        this.sifre = sifre;
        this.temsilciid = temsilciid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdSoyad() {
        return AdSoyad;
    }

    public void setAdSoyad(String AdSoyad) {
        this.AdSoyad = AdSoyad;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String TelNo) {
        this.TelNo = TelNo;
    }

    public String getTcNo() {
        return TcNo;
    }

    public void setTcNo(String TcNo) {
        this.TcNo = TcNo;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String Adres) {
        this.Adres = Adres;
    }

    public String getEposta() {
        return Eposta;
    }

    public void setEposta(String Eposta) {
        this.Eposta = Eposta;
    }

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public int getTemsilciid() {
        return temsilciid;
    }

    public void setTemsilciid(int temsilciid) {
        this.temsilciid = temsilciid;
    }
  


       
    
}
