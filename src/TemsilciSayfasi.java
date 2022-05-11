
import DepartmanClasslari.CHareketler;
import DepartmanClasslari.CMusteri;
import DepartmanClasslari.CTalep;
import DepartmanClasslari.CTemsilci;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author melih
 */
public class TemsilciSayfasi extends javax.swing.JFrame {

    CTemsilci temsilci;
    DefaultTableModel model;
    DefaultTableModel model2;
    DefaultTableModel model3;
    Veritabani_Konfigrasyon db=new Veritabani_Konfigrasyon();
    int kullanici_id=-1;
    
    public TemsilciSayfasi() {
         initComponents();
        model=(DefaultTableModel)musteri_Tablosu.getModel();
        model2=(DefaultTableModel)talep_tablosu.getModel();
        model3=(DefaultTableModel)islemler_Tablosu.getModel();
    }
    
    public TemsilciSayfasi(CTemsilci temsilci){
        initComponents();
        this.temsilci=temsilci;
        isimsoyisim.setText(temsilci.getAdSoyad());
        kullaniciadi.setText(temsilci.getKullaniciadi());
        model=(DefaultTableModel)musteri_Tablosu.getModel();
        model2=(DefaultTableModel)talep_tablosu.getModel();
        model3=(DefaultTableModel)islemler_Tablosu.getModel();
        calisan_goruntule();
        talepleri_goruntule();
    }
    public void islemleri_goruntule(int kullanici_id){
        
        model3.setRowCount(0);
        ArrayList<CHareketler> hareketler=new ArrayList<>();
        
        String query="SELECT * FROM hareketler,kullanici_islemleri where hareketler.işlem=kullanici_islemleri.islem_id and hareketler.kullanici_id= ?";
        
        try {
            db.psqlquery=db.con.prepareStatement(query);
            db.psqlquery.setInt(1,kullanici_id);
            
            ResultSet res=db.psqlquery.executeQuery();
            
            while(res.next()){
                
               int islem_id=res.getInt("islem_id");
               int k_id=res.getInt("kullanici_id");
               String kaynak=res.getString("kaynak");
               String hedef=res.getString("hedef");
               String islem=res.getString("İslem_tipi");
               int tutar=res.getInt("tutar");
               int kaynak_bakiye=res.getInt("kaynak_bakiye");
               int hedef_bakiye=res.getInt("hedef_bakiye");
               String tarih=res.getString("tarih");
               
               hareketler.add(new CHareketler(islem_id, k_id, kaynak, hedef, islem, tutar, kaynak_bakiye, hedef_bakiye, tarih));
               
            }
            if(!hareketler.isEmpty()){
                
                for(CHareketler hareket: hareketler){
                    
                    Object[] eklenecek={hareket.getIslem_id(),hareket.getK_id(),hareket.getKaynak(),hareket.getHedef(),hareket.getIşlem(),hareket.getTutar(),hareket.getKaynak_bakiye(),hareket.getHedef_bakiye(),hareket.getTarih()};
                    
                    model3.addRow(eklenecek);
                }
                
            }   
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void calisan_goruntule(){
        
        model.setRowCount(0);
        
        ArrayList<CMusteri> musteriler=new ArrayList<>();
        
        try {
            db.sqlquery=db.con.createStatement();
            
            String query="Select * from musteri";
            
            ResultSet res=db.sqlquery.executeQuery(query);
            
            while(res.next()){
                
                String adsoyad=res.getString("AdSoyad");
                String telefonn=res.getString("Telefon");
                String tcnoo=res.getString("TcNo");
                String adress=res.getString("Adres");
                String epostaa=res.getString("Eposta");
                String kullaniciadii=res.getString("KullaniciAdi");
                String sifree=res.getString("Sifre");
                int temsilciidd=res.getInt("temsilciid");
                int id=res.getInt("id");
                
                musteriler.add(new CMusteri(id, adsoyad, telefonn, tcnoo,adress,epostaa,kullaniciadii,sifree,temsilciidd));
            }
            
            if(!musteriler.isEmpty()){
                
                for(CMusteri musteri:musteriler){
                    
                    if(musteri.getTemsilciid()==temsilci.getId()){
                        
                        Object[] eklenecek={musteri.getId(),musteri.getAdSoyad(),musteri.getTelNo(),musteri.getTcNo(),musteri.getAdres(),musteri.getEposta(),musteri.getKullaniciadi(),musteri.getSifre(),musteri.getTemsilciid()};
                        
                        model.addRow(eklenecek);
                    } 
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void talepleri_goruntule(){
        ArrayList<CTalep> talepler=new ArrayList<>();
        model2.setRowCount(0);
        
        try {
            db.sqlquery=db.con.createStatement();
            
            String sorgu="Select talepler.kullanici_id,islemler.islem_tipi,talepler.id,talepler.onay,talepler.temsilciid,talepler.KrediMiktari,talepler.hesap_id from talepler JOIN islemler ON talepler.islem_tipi=islemler.id";
            
            ResultSet res=db.sqlquery.executeQuery(sorgu);
            int temsilciid=0;
            while(res.next()){
                
                int id=res.getInt("id");
                int kullanici_id=res.getInt("kullanici_id");
                String islem_tipi=res.getString("islem_tipi");
                int onay=res.getInt("onay");
                temsilciid=res.getInt("temsilciid");
                int kredimiktari=res.getInt("KrediMiktari");
                int hesap_id=res.getInt("hesap_id");
                
                talepler.add(new CTalep(id, kullanici_id, islem_tipi, onay, temsilciid, kredimiktari, hesap_id));       
            }
            
            if(!talepler.isEmpty()){
                
                for(CTalep talep:talepler){
                    
                    if(temsilciid==temsilci.getId()){
                        
                        Object[] talepeden={talep.getId(),talep.getKullanici_id(),talep.getTemsilci_id(),talep.getIslem_tipi(),talep.getKredi_miktari(),talep.getHesap_id(),talep.getOnay()};
                        
                        model2.addRow(talepeden);
                    }                
                }         
            }
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        isimsoyisim = new javax.swing.JLabel();
        kullaniciadi = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        musteri_Tablosu = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        g_ad = new javax.swing.JTextField();
        g_tel = new javax.swing.JTextField();
        g_tc = new javax.swing.JTextField();
        g_adres = new javax.swing.JTextField();
        g_eposta = new javax.swing.JTextField();
        guncelle = new javax.swing.JButton();
        sil = new javax.swing.JButton();
        message2 = new javax.swing.JLabel();
        guncelleme_silme = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        adsoyad = new javax.swing.JTextField();
        telefonno = new javax.swing.JTextField();
        tcno = new javax.swing.JTextField();
        adres = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        k_adi = new javax.swing.JTextField();
        sifre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        message4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        talep_tablosu = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        Reddet = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        talep_isim = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        talep_tc = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        talep_miktar = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        talep_faiz = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        talep_mesaj = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        bilgi_tc = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        islemler_Tablosu = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        bilgi_mesaj = new javax.swing.JLabel();
        hesap_Adeti = new javax.swing.JTextField();
        toplam_bakiye = new javax.swing.JTextField();
        kredi_bilgisi = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        toplamkrediborcu = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        odenen = new javax.swing.JTextField();
        guncel_faiz = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        verilis_tarihi = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        kalanmiktar = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        talep = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        label1.setText("label1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TEMSİLCİ SAYFASI");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resimler/user (2).png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Temsilci Bilgileri");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kullanıcı Adı:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("İsim:");

        isimsoyisim.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        isimsoyisim.setForeground(new java.awt.Color(255, 255, 255));

        kullaniciadi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        kullaniciadi.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(isimsoyisim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kullaniciadi, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(isimsoyisim))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(kullaniciadi))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resimler/refresh.png"))); // NOI18N

        musteri_Tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "AdSoyad", "Telefon", "TcNo", "Adres", "Eposta", "KullanıcıAdı", "Şifre", "TemsilciId"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        musteri_Tablosu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                musteri_TablosuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(musteri_Tablosu);
        if (musteri_Tablosu.getColumnModel().getColumnCount() > 0) {
            musteri_Tablosu.getColumnModel().getColumn(0).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(1).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(2).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(3).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(4).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(5).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(6).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(7).setResizable(false);
            musteri_Tablosu.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("BİLGİLER");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Ad Soyad:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Telefon:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Tc No:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Adres:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Eposta:");

        g_adres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g_adresActionPerformed(evt);
            }
        });

        guncelle.setBackground(new java.awt.Color(102, 102, 102));
        guncelle.setText("Güncelle");
        guncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guncelleActionPerformed(evt);
            }
        });

        sil.setBackground(new java.awt.Color(102, 102, 102));
        sil.setText("Sil");
        sil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silActionPerformed(evt);
            }
        });

        message2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        guncelleme_silme.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(message2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(g_eposta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(g_adres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(g_tc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(g_tel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(g_ad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(guncelleme_silme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(136, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(sil, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guncelle)
                        .addGap(66, 66, 66))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(468, 468, 468))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel15)
                .addGap(83, 83, 83)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(g_ad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(g_tel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(g_tc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(g_adres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(g_eposta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(guncelleme_silme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sil)
                    .addComponent(guncelle))
                .addGap(39, 39, 39)
                .addComponent(message2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        jTabbedPane1.addTab("Müşteri Güncelleme", jPanel4);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resimler/add-user.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Müşteri Ekleme");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Ad Soyad:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Telefon No:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Tc No:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Adres:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Eposta:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Kullanıcı Adı:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Şifre:");

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setText("Müşteri Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        message.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        message4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(message4, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sifre, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(k_adi, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addComponent(email, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(adres, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tcno, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(telefonno, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(adsoyad, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(image, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(454, 454, 454)
                        .addComponent(jLabel6)))
                .addGap(105, 186, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(adsoyad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(message))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(telefonno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tcno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(adres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(k_adi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(sifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addComponent(message4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Müşteri Ekleme", jPanel3);

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resimler/interview.png"))); // NOI18N

        talep_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Istek Id", "Kullanıcı Id", "Temsilci Id", "Islem Tipi", "Kredi Miktarı", "Hesap Id", "Onay"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        talep_tablosu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                talep_tablosuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(talep_tablosu);
        if (talep_tablosu.getColumnModel().getColumnCount() > 0) {
            talep_tablosu.getColumnModel().getColumn(0).setResizable(false);
            talep_tablosu.getColumnModel().getColumn(1).setResizable(false);
            talep_tablosu.getColumnModel().getColumn(2).setResizable(false);
            talep_tablosu.getColumnModel().getColumn(3).setResizable(false);
            talep_tablosu.getColumnModel().getColumn(4).setResizable(false);
            talep_tablosu.getColumnModel().getColumn(5).setResizable(false);
            talep_tablosu.getColumnModel().getColumn(6).setResizable(false);
        }

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Onayla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Reddet.setBackground(new java.awt.Color(102, 102, 102));
        Reddet.setText("Reddet");
        Reddet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReddetActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Müşterinin ismi:");

        talep_isim.setEditable(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Müşterinin Tc No'su:");

        talep_tc.setEditable(false);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Talep edilen kredi miktarı:");

        talep_miktar.setEditable(false);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Kredi Faiz Oranı:");

        talep_faiz.setEditable(false);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Kredi veya Hesap işlemi yapmak isteyen müşterinin;");

        talep_mesaj.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(talep_mesaj, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(441, 441, 441)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(55, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(42, 42, 42)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(talep_isim)
                                    .addComponent(talep_tc)
                                    .addComponent(talep_miktar)
                                    .addComponent(talep_faiz)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Reddet)))
                                .addGap(85, 85, 85))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(talep_isim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(talep_tc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(talep_miktar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(talep_faiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(Reddet)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(talep_mesaj, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Müşteri Taleplerini Görüntüleme", jPanel5);

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        jPanel9.setBackground(new java.awt.Color(102, 102, 102));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resimler/user.png"))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Bilgilerini görüntülemek istediğiniz müşterinin TC No'sunu giriniz");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Müşteri TC No:");

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setText("Ara");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(bilgi_tc)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(174, 174, 174))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(bilgi_tc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        islemler_Tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "İşlem No", "Kullanıcı_Id", "Kaynak", "Hedef", "İşlem", "Tutar", "Kaynak Bakiye", "Hedef Bakiye", "Tarih"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(islemler_Tablosu);
        if (islemler_Tablosu.getColumnModel().getColumnCount() > 0) {
            islemler_Tablosu.getColumnModel().getColumn(0).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(1).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(2).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(3).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(4).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(5).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(6).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(7).setResizable(false);
            islemler_Tablosu.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Kullanıcının Gerçekleştirdiği İşlemler");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Kullanıcı Bilgileri");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Kullanıcı Hesap Adeti:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Kullanıcı Toplam Hesap Bakiyesi:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Kullanıcı Kredi Bilgisi:");

        bilgi_mesaj.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        hesap_Adeti.setEditable(false);

        toplam_bakiye.setEditable(false);

        kredi_bilgisi.setEditable(false);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Toplam Kredi Borcu:");

        toplamkrediborcu.setEditable(false);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Ödenen Miktar:");

        odenen.setEditable(false);

        guncel_faiz.setEditable(false);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Güncel Faiz Oranı:");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Kredi Veriliş Tarihi:");

        verilis_tarihi.setEditable(false);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Kalan Miktar:");

        kalanmiktar.setEditable(false);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Kullanıcının Talep Ettiği Miktar:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hesap_Adeti, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kredi_bilgisi, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toplam_bakiye, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(talep, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toplamkrediborcu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(odenen, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kalanmiktar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(guncel_faiz, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verilis_tarihi, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(68, 68, 68))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bilgi_mesaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bilgi_mesaj)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel34))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(hesap_Adeti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(toplam_bakiye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(kredi_bilgisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(talep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(toplamkrediborcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(odenen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(kalanmiktar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(guncel_faiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(verilis_tarihi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Müşteri Bilgileri ve İşlemleri Görüntüleme", jPanel6);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(384, 384, 384))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String k_ad=adsoyad.getText();
        String telefon=telefonno.getText();
        String tcno=this.tcno.getText();
        String adres=this.adres.getText();
        String eposta=email.getText();
        String k_adi=this.k_adi.getText();
        String password=sifre.getText();
        int temsilciid=this.temsilci.getId();
        
        if(k_ad.isBlank() || telefon.isBlank() || tcno.isBlank() || adres.isBlank() || eposta.isBlank() || k_adi.isBlank() || password.isBlank()){
            
            message.setText("Lütfen eksik bilgileri doldurunuz!");
            
        }
        
        else{
        
        String query="Insert into musteri (AdSoyad,Telefon,TcNo,Adres,Eposta,Kullaniciadi,Sifre,temsilciid) VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            db.psqlquery=db.con.prepareStatement(query);
            db.psqlquery.setString(1,k_ad);
            db.psqlquery.setString(2,telefon);
            db.psqlquery.setString(3,tcno);
            db.psqlquery.setString(4,adres);
            db.psqlquery.setString(5,eposta);
            db.psqlquery.setString(6,k_adi);
            db.psqlquery.setString(7,password);
            db.psqlquery.setInt(8,temsilciid);
            
            
            
            
            
            db.psqlquery.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        message4.setText("Müşteri başarıyla eklendi.");
        calisan_goruntule();
        
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void g_adresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g_adresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_g_adresActionPerformed

    private void musteri_TablosuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musteri_TablosuMouseClicked
        
        int row=musteri_Tablosu.getSelectedRow();
        kullanici_id=(int)model.getValueAt(row,0);
        g_ad.setText(model.getValueAt(row,1).toString());
        g_tel.setText(model.getValueAt(row,2).toString());
        g_tc.setText(model.getValueAt(row,3).toString());
        g_adres.setText(model.getValueAt(row,4).toString());
        g_eposta.setText(model.getValueAt(row,5).toString());
 
    }//GEN-LAST:event_musteri_TablosuMouseClicked

    private void guncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guncelleActionPerformed
        
        String sorgu="Update musteri set AdSoyad = ?, Telefon = ?, TcNo= ? , Adres = ?, Eposta = ? where id = ?";
        
        String g_ad=this.g_ad.getText();
        String g_tel=this.g_tel.getText();
        String g_tc=this.g_tc.getText();
        String g_adres=this.g_adres.getText();
        String g_eposta=this.g_eposta.getText();
        
        if(kullanici_id==-1){
            
            guncelleme_silme.setText("Lütfen güncellemek istediğiniz müşteriyi seçiniz!");
            
        }
        else{
        try {
            db.psqlquery=db.con.prepareStatement(sorgu);
            db.psqlquery.setString(1,g_ad);
            db.psqlquery.setString(2,g_tel); 
            db.psqlquery.setString(3,g_tc);
            db.psqlquery.setString(4,g_adres);
            db.psqlquery.setString(5,g_eposta);
            db.psqlquery.setInt(6,kullanici_id);
            
            db.psqlquery.executeUpdate();
            guncelleme_silme.setText("Kullanıcı bilgileri başarıyla güncellendi.");
            calisan_goruntule();
            
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }  
    }//GEN-LAST:event_guncelleActionPerformed

    private void silActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silActionPerformed
        
        if(kullanici_id==-1){
            
            guncelleme_silme.setText("Lütfen silmek istediğiniz müşteriyi tablodan seçiniz!");
            
        }
        else{
        String sorgu="Delete from musteri where id = ?";
        
        try {
            db.psqlquery=db.con.prepareStatement(sorgu);
            db.psqlquery.setInt(1,kullanici_id);
            db.psqlquery.executeUpdate();
            
            guncelleme_silme.setText("Müşteri başarıyla silindi.");
            
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_silActionPerformed

    private void talep_tablosuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_talep_tablosuMouseClicked
        
        int row=talep_tablosu.getSelectedRow();
        
      
        
        int talep_kid=(int)model2.getValueAt(row,1);
        
        String sorgu2="Select * from musteri where id = ?";
        
        try {
            db.psqlquery=db.con.prepareStatement(sorgu2);
            db.psqlquery.setInt(1,talep_kid);
            ResultSet res2=db.psqlquery.executeQuery();
            
            while(res2.next()){
                
                talep_isim.setText(res2.getString("AdSoyad"));
                talep_tc.setText(res2.getString("TcNo"));
   
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
       
           //Silinmek istenen hesap no alınıyor
            int hesap_no=(int)model2.getValueAt(row,5);     
            int kredi_miktari=(int)model2.getValueAt(row,4);
            System.out.println(kredi_miktari);
            double faiz=0;
            String sorgu="Select * from krediorani";
            
            try {
                db.sqlquery=db.con.createStatement();
                ResultSet res=db.sqlquery.executeQuery(sorgu);
                while(res.next()){
                    
                     faiz=res.getDouble("kredi_orani");
                     
                }
                
                talep_miktar.setText(Integer.toString(kredi_miktari));
                talep_faiz.setText(Double.toString(faiz));
                
                
            } catch (SQLException ex1) {
                Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex1);
            }
               
    }//GEN-LAST:event_talep_tablosuMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
     
            int row=talep_tablosu.getSelectedRow();
            
            if(row==-1){
                
                if(talep_tablosu.getRowCount()==0){
                    
                    talep_mesaj.setText("Henüz istek bulunmuyor.");
                    
                }
                else{
                    
                    talep_mesaj.setText("Lütfen onaylamak istediğiniz talebi seçiniz!");
                    
                } 
                
               
            }
            else{
            
            int k_id=(int)model2.getValueAt(row,1);
            
            String sorgu="Update talepler set onay = ? where kullanici_id = ?";            
            try {
                db.psqlquery=db.con.prepareStatement(sorgu);
                db.psqlquery.setInt(1,1);
                db.psqlquery.setInt(2,k_id);
                db.psqlquery.executeUpdate();
                talepleri_goruntule();
                
            } catch (SQLException ex) {
                Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ReddetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReddetActionPerformed
           int row=talep_tablosu.getSelectedRow();
            
            if(row==-1){
                
                if(talep_tablosu.getRowCount()==0){
                    
                    talep_mesaj.setText("Henüz istek bulunmuyor.");
                    
                }
                else{
                    
                    talep_mesaj.setText("Lütfen reddetmek istediğiniz talebi seçiniz!");
                    
                } 
                
               
            }
            else{
            
            int k_id=(int)model2.getValueAt(row,1);
            
            String sorgu="Update talepler set onay = ? where kullanici_id = ?";            
            try {
                db.psqlquery=db.con.prepareStatement(sorgu);
                db.psqlquery.setInt(1,0);
                db.psqlquery.setInt(2,k_id);
                db.psqlquery.executeUpdate();
                talepleri_goruntule();
                
            } catch (SQLException ex) {
                Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }//GEN-LAST:event_ReddetActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      
        String sorgu="Select * from musteri where TcNo = ?";
        
        try {
            db.psqlquery=db.con.prepareStatement(sorgu);
            db.psqlquery.setString(1,bilgi_tc.getText());
            
            ResultSet res=db.psqlquery.executeQuery();
            
            int id=-1;
            
            while(res.next()){
                
                id=Integer.parseInt(res.getString("id"));
                
            }
            
        int adet=0;
        int bakiye_toplami=0;
        if(id!=-1){
        islemleri_goruntule(id);
        sorgu="Select * from hesaplar where kullanici_id = ?";
        db.psqlquery=db.con.prepareStatement(sorgu);
        db.psqlquery.setInt(1,id);
        ResultSet set2=db.psqlquery.executeQuery();
        
        while(set2.next()){
            
            bakiye_toplami+=set2.getInt("bakiye");
            adet++;
        }
        hesap_Adeti.setText(Integer.toString(adet));
        toplam_bakiye.setText(Integer.toString(bakiye_toplami));
        
        
            
        sorgu="Select * from kredi_bilgileri where kullanici_id= ?";
        db.psqlquery=db.con.prepareStatement(sorgu);
        db.psqlquery.setInt(1, id);
        ResultSet set3=db.psqlquery.executeQuery();
        
        
        
        if(set3.next()){
            bilgi_mesaj.setText("");
            kredi_bilgisi.setText("Var");
            
            int talep2=set3.getInt("kredi_miktari");
            int odenen_miktar=set3.getInt("odenen_miktar");
            String tarih=set3.getString("verilis_tarihi");
            talep.setText(Integer.toString(talep2));
            odenen.setText(Integer.toString(odenen_miktar));
           
            verilis_tarihi.setText(tarih);
            
            sorgu="Select * from krediorani";
            
            db.sqlquery=db.con.createStatement();
            
            ResultSet faiz=db.sqlquery.executeQuery(sorgu);
            double faiz_orani=0;
            while(faiz.next()){
                
                faiz_orani=faiz.getDouble("kredi_orani");
                break;
            }
            guncel_faiz.setText(Double.toString(faiz_orani));
            double toplam_borc=talep2+(talep2*faiz_orani);
            toplamkrediborcu.setText(Double.toString(toplam_borc));
            kalanmiktar.setText(Double.toString(toplam_borc-odenen_miktar));
        }
        
        else{
            talep.setText("------");
            kalanmiktar.setText("0");
            kredi_bilgisi.setText("Yok");
            toplamkrediborcu.setText("0");
            guncel_faiz.setText("------");
            verilis_tarihi.setText("------");
            odenen.setText("------");
        }
        }
        else{
            bilgi_mesaj.setText("Girilen TC'ye ait bir müşteri bulunamadı.");
        }
        
            
        } catch (SQLException ex) {
            Logger.getLogger(TemsilciSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TemsilciSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TemsilciSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TemsilciSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TemsilciSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TemsilciSayfasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reddet;
    private javax.swing.JTextField adres;
    private javax.swing.JTextField adsoyad;
    private javax.swing.JLabel bilgi_mesaj;
    private javax.swing.JTextField bilgi_tc;
    private javax.swing.JTextField email;
    private javax.swing.JTextField g_ad;
    private javax.swing.JTextField g_adres;
    private javax.swing.JTextField g_eposta;
    private javax.swing.JTextField g_tc;
    private javax.swing.JTextField g_tel;
    private javax.swing.JTextField guncel_faiz;
    private javax.swing.JButton guncelle;
    private javax.swing.JLabel guncelleme_silme;
    private javax.swing.JTextField hesap_Adeti;
    private javax.swing.JLabel image;
    private javax.swing.JLabel isimsoyisim;
    private javax.swing.JTable islemler_Tablosu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField k_adi;
    private javax.swing.JTextField kalanmiktar;
    private javax.swing.JTextField kredi_bilgisi;
    private javax.swing.JLabel kullaniciadi;
    private java.awt.Label label1;
    private javax.swing.JLabel message;
    private javax.swing.JLabel message2;
    private javax.swing.JLabel message4;
    private javax.swing.JTable musteri_Tablosu;
    private javax.swing.JTextField odenen;
    private javax.swing.JTextField sifre;
    private javax.swing.JButton sil;
    private javax.swing.JTextField talep;
    private javax.swing.JTextField talep_faiz;
    private javax.swing.JTextField talep_isim;
    private javax.swing.JLabel talep_mesaj;
    private javax.swing.JTextField talep_miktar;
    private javax.swing.JTable talep_tablosu;
    private javax.swing.JTextField talep_tc;
    private javax.swing.JTextField tcno;
    private javax.swing.JTextField telefonno;
    private javax.swing.JTextField toplam_bakiye;
    private javax.swing.JTextField toplamkrediborcu;
    private javax.swing.JTextField verilis_tarihi;
    // End of variables declaration//GEN-END:variables
}
