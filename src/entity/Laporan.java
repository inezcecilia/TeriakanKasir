package entity;
// Generated May 16, 2019 1:40:28 PM by Hibernate Tools 4.3.1



/**
 * Laporan generated by hbm2java
 */
public class Laporan  implements java.io.Serializable {


     private Integer id;
     private String lphari;
     private String lpbulan;

    public Laporan() {
    }

    public Laporan(String lphari, String lpbulan) {
       this.lphari = lphari;
       this.lpbulan = lpbulan;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLphari() {
        return this.lphari;
    }
    
    public void setLphari(String lphari) {
        this.lphari = lphari;
    }
    public String getLpbulan() {
        return this.lpbulan;
    }
    
    public void setLpbulan(String lpbulan) {
        this.lpbulan = lpbulan;
    }




}

