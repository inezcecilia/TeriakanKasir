package entity;
// Generated May 16, 2019 1:40:28 PM by Hibernate Tools 4.3.1



/**
 * Kasir generated by hbm2java
 */
public class Kasir  implements java.io.Serializable {


     private Integer id;
     private String nama;
     private String hp;
     private String alamat;

    public Kasir() {
    }

    public Kasir(String nama, String hp, String alamat) {
       this.nama = nama;
       this.hp = hp;
       this.alamat = alamat;
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
    public String getHp() {
        return this.hp;
    }
    
    public void setHp(String hp) {
        this.hp = hp;
    }
    public String getAlamat() {
        return this.alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }




}


