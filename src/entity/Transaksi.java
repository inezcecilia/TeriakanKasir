package entity;
// Generated May 16, 2019 1:40:28 PM by Hibernate Tools 4.3.1



/**
 * Transaksi generated by hbm2java
 */
public class Transaksi  implements java.io.Serializable {


     private Integer id;
     private String menu;
     private String harga;

    public Transaksi() {
    }

    public Transaksi(String menu, String harga) {
       this.menu = menu;
       this.harga = harga;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMenu() {
        return this.menu;
    }
    
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public String getHarga() {
        return this.harga;
    }
    
    public void setHarga(String harga) {
        this.harga = harga;
    }




}


