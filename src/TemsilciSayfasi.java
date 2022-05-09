
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
    Veritabani_Konfigrasyon db=new Veritabani_Konfigrasyon();
    int kullanici_id=-1;
    
    public TemsilciSayfasi() {
         initComponents();
        model=(DefaultTableModel)musteri_Tablosu.getModel();
        model2=(DefaultTableModel)talep_tablosu.getModel();
       
    }
    
    public TemsilciSayfasi(CTemsilci temsilci){
        initComponents();
        this.temsilci=temsilci;
        isimsoyisim.setText(temsilci.getAdSoyad());
        kullaniciadi.setText(temsilci.getKullaniciadi());
        model=(DefaultTableModel)musteri_Tablosu.getModel();
        model2=(DefaultTableModel)talep_tablosu.getModel();
        calisan_goruntule();
        talepleri_goruntule();
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
            
            if(musteriler!=null){
                
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
            
            String sorgu="Select * from talepler";
            
            ResultSet res=db.sqlquery.executeQuery(sorgu);
            int temsilciid=0;
            while(res.next()){
                
                int id=res.getInt("id");
                int kullanici_id=res.getInt("kullanici_id");
                int islem_tipi=res.getInt("islem_tipi");
                int onay=res.getInt("onay");
                temsilciid=res.getInt("temsilciid");
                int kredimiktari=res.getInt("KrediMiktari");
                int hesap_id=res.getInt("hesap_id");
                
                talepler.add(new CTalep(id, kullanici_id, islem_tipi, onay, temsilciid, kredimiktari, hesap_id));       
            }
            
            if(talepler!=null){
                
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
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
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
                .addGap(385, 385, 385)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(29, 29, 29))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(message2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sil, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20))
                                        .addGap(14, 14, 14)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(g_eposta, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(g_adres, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(g_tc, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(g_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(g_ad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addComponent(guncelle))))
                            .addComponent(guncelleme_silme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(248, 248, 248)
                        .addComponent(jLabel15)
                        .addGap(153, 153, 153))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel15))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(g_ad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(g_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(g_tc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(g_adres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(g_eposta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(104, 104, 104)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sil)
                            .addComponent(guncelle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(message2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guncelleme_silme))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(412, 412, 412))
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sifre, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telefonno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(adsoyad, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tcno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(adres, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(k_adi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(image))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(message4, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(105, 137, Short.MAX_VALUE))
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
                .addComponent(message4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(59, 59, 59))
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

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("İşlemler");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("1)Hesap Silme");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("2)Hesap Ekleme");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("3)Kredi Talep Etme");

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
        talep_mesaj.setText("jLabel34");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(176, 176, 176)
                .addComponent(Reddet)
                .addGap(93, 93, 93))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(talep_isim)
                                    .addComponent(talep_tc)
                                    .addComponent(talep_miktar)
                                    .addComponent(talep_faiz))
                                .addGap(85, 85, 85))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(71, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(396, 396, 396))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(talep_mesaj, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel25)
                        .addGap(24, 24, 24)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(59, 59, 59))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(Reddet))
                .addGap(27, 27, 27)
                .addComponent(talep_mesaj, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Müşteri Taleplerini Görüntüleme", jPanel5);

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
                .addGap(336, 336, 336))
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
        int islem_no=(int)model2.getValueAt(row,3);
        
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
        
        
        
        if(islem_no<3){
           //Silinmek istenen hesap no alınıyor
            int hesap_no=(int)model2.getValueAt(row,5); 
            
        }
        else{
            
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
    private javax.swing.JTextField email;
    private javax.swing.JTextField g_ad;
    private javax.swing.JTextField g_adres;
    private javax.swing.JTextField g_eposta;
    private javax.swing.JTextField g_tc;
    private javax.swing.JTextField g_tel;
    private javax.swing.JButton guncelle;
    private javax.swing.JLabel guncelleme_silme;
    private javax.swing.JLabel image;
    private javax.swing.JLabel isimsoyisim;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField k_adi;
    private javax.swing.JLabel kullaniciadi;
    private java.awt.Label label1;
    private javax.swing.JLabel message;
    private javax.swing.JLabel message2;
    private javax.swing.JLabel message4;
    private javax.swing.JTable musteri_Tablosu;
    private javax.swing.JTextField sifre;
    private javax.swing.JButton sil;
    private javax.swing.JTextField talep_faiz;
    private javax.swing.JTextField talep_isim;
    private javax.swing.JLabel talep_mesaj;
    private javax.swing.JTextField talep_miktar;
    private javax.swing.JTable talep_tablosu;
    private javax.swing.JTextField talep_tc;
    private javax.swing.JTextField tcno;
    private javax.swing.JTextField telefonno;
    // End of variables declaration//GEN-END:variables
}
