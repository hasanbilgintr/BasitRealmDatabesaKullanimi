package com.hasan.realminsertview;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Tbl_Kisi extends RealmObject {
    private String cumle;
   /* private String Sifre;
    private String Cinsiyet;
    private String Ad;*/

    /*set ve get metotları oluşturuldu*/
    public String getCumle() {
        return cumle;
    }

    public void setCumle(String cumle) {
        this.cumle = cumle;
    }

    /*public String getSifre() {
        return Sifre;
    }

    public void setSifre(String sifre) {
        Sifre = sifre;
    }

    public String getCinsiyet() {
        return Cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        Cinsiyet = cinsiyet;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }
*/
    /*toString metodu oluşturuldu*/

    @Override
    public String toString() {
        return "Tbl_Kisi{" +
                "cumle='" + cumle + '\'' +
                '}';
    }
}

/*java klasörüne sağ tık new =>java class=> isim verilir entere basılır*/
