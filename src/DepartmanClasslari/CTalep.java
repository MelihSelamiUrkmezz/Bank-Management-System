package DepartmanClasslari;

public class CTalep {
    
    private int id;
    private int kullanici_id;
    private String islem_tipi;
    private int onay;
    private int temsilci_id;
    private int kredi_miktari;
    private int hesap_id;

    public CTalep(int id, int kullanici_id, String islem_tipi, int onay, int temsilci_id, int kredi_miktari, int hesap_id) {
        this.id = id;
        this.kullanici_id = kullanici_id;
        this.islem_tipi = islem_tipi;
        this.onay = onay;
        this.temsilci_id = temsilci_id;
        this.kredi_miktari = kredi_miktari;
        this.hesap_id = hesap_id;
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
