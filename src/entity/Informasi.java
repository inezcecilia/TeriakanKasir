package entity;
// Generated May 16, 2019 1:40:28 PM by Hibernate Tools 4.3.1



/**
 * Informasi generated by hbm2java
 */
public class Informasi  implements java.io.Serializable {


     private Integer id;
     private String nama;
     private String tipebis;
     private String kategori;
     private String kota;
     private String provinsi;
     private String kodepos;

    public Informasi() {
    }

    public Informasi(String nama, String tipebis, String kategori, String kota, String provinsi, String kodepos) {
       this.nama = nama;
       this.tipebis = tipebis;
       this.kategori = kategori;
       this.kota = kota;
       this.provinsi = provinsi;
       this.kodepos = kodepos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNama() {
        return this.nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getTipebis() {
        return this.tipebis;
    }
    
    public void setTipebis(String tipebis) {
        this.tipebis = tipebis;
    }
    public String getKategori() {
        return this.kategori;
    }
    
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public String getKota() {
        return this.kota;
    }
    
    public void setKota(String kota) {
        this.kota = kota;
    }
    public String getProvinsi() {
        return this.provinsi;
    }
    
    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    public String getKodepos() {
        return this.kodepos;
    }
    
    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }




}

