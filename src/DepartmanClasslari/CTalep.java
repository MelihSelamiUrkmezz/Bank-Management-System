package DepartmanClasslari;

public class CTalep {
    
    private int id;
    private int kullanici_id;
    private String islem_tipi;
    private int onay;
    private int temsilci_id;
    private int kredi_miktari;
    private int hesap_id;
    private String para_birimi;
    private double faiz_orani;

    public CTalep(int id, int kullanici_id, String islem_tipi, int onay, int temsilci_id, int kredi_miktari, int hesap_id,String para_birimi,double faiz_orani) {
        this.id = id;
        this.kullanici_id = kullanici_id;
        this.islem_tipi = islem_tipi;
        this.onay = onay;
        this.temsilci_id = temsilci_id;
        this.kredi_miktari = kredi_miktari;
        this.hesap_id = hesap_id;
        this.para_birimi=para_birimi;
        this.faiz_orani=faiz_orani;
                
    }

    public double getFaiz_orani() {
        return faiz_orani;
    }

    public void setFaiz_orani(double faiz_orani) {
        this.faiz_orani = faiz_orani;
    }

    
    public String getPara_birimi() {
        return para_birimi;
    }

    public void setPara_birimi(String para_birimi) {
        this.para_birimi = para_birimi;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getIslem_tipi() {
        return islem_tipi;
    }

    public void setIslem_tipi(String islem_tipi) {
        this.islem_tipi = islem_tipi;
    }

    public int getOnay() {
        return onay;
    }

    public void setOnay(int onay) {
        this.onay = onay;
    }

    public int getTemsilci_id() {
        return temsilci_id;
    }

    public void setTemsilci_id(int temsilci_id) {
        this.temsilci_id = temsilci_id;
    }

    public int getKredi_miktari() {
        return kredi_miktari;
    }

    public void setKredi_miktari(int kredi_miktari) {
        this.kredi_miktari = kredi_miktari;
    }

    public int getHesap_id() {
        return hesap_id;
    }

    public void setHesap_id(int hesap_id) {
        this.hesap_id = hesap_id;
    }
    
    
    
    
    
}
