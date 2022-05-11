/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DepartmanClasslari;

public class CHareketler {
      private  int islem_id;
      private  int k_id;
      private  String kaynak;
      private  String hedef;
      private  String işlem;
      private  int tutar;
      private  int kaynak_bakiye;
      private  int hedef_bakiye;
      private  String tarih;

    public CHareketler(int islem_id, int k_id,String kaynak,String hedef, String işlem, int tutar, int kaynak_bakiye, int hedef_bakiye, String tarih) {
        this.islem_id = islem_id;
        this.k_id = k_id;
        this.kaynak = kaynak;
        this.hedef = hedef;
        this.işlem = işlem;
        this.tutar = tutar;
        this.kaynak_bakiye = kaynak_bakiye;
        this.hedef_bakiye = hedef_bakiye;
        this.tarih = tarih;
    }

    public int getIslem_id() {
        return islem_id;
    }

    public void setIslem_id(int islem_id) {
        this.islem_id = islem_id;
    }

    public int getK_id() {
        return k_id;
    }

    public void setK_id(int k_id) {
        this.k_id = k_id;
    }

    public String getKaynak() {
        return kaynak;
    }

    public void setKaynak(String kaynak) {
        this.kaynak = kaynak;
    }

    public String getHedef() {
        return hedef;
    }

    public void setHedef(String hedef) {
        this.hedef = hedef;
    }

    public String getIşlem() {
        return işlem;
    }

    public void setIşlem(String işlem) {
        this.işlem = işlem;
    }

    public int getTutar() {
        return tutar;
    }

    public void setTutar(int tutar) {
        this.tutar = tutar;
    }

    public int getKaynak_bakiye() {
        return kaynak_bakiye;
    }

    public void setKaynak_bakiye(int kaynak_bakiye) {
        this.kaynak_bakiye = kaynak_bakiye;
    }

    public int getHedef_bakiye() {
        return hedef_bakiye;
    }

    public void setHedef_bakiye(int hedef_bakiye) {
        this.hedef_bakiye = hedef_bakiye;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
               
}
