package com.hasan.realminsertview;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText Et_Cumle;/*, Et_Sifre, Et_Ad;*/
    /*RadioGroup RdGrp_Cinsiyet;*/
    Button Btn_KayitOl, Btn_Guncelle, Btn_Sil;
    ListView listView;
    Integer secposition = 0;
    /* RadioButton RdBtn_Erkek, RdBtn_Kadin;*/
    int selectrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmTanimla();
        Tanimla();
        KayitEkle();
        Goster();
        pozisyonBul();
        Guncelle();
        Sil();

    }

    private void Sil() {
        Btn_Sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!Et_Cumle.getText().toString().equals("") && selectrow!=-1) {
                    kayitSil(selectrow);
                }

            }
        });


    }

    public void RealmTanimla() {
        realm = Realm.getDefaultInstance();
    }

    public void Tanimla() {
        Et_Cumle = (EditText) findViewById(R.id.Et_KulAdi);
       /* Et_Sifre = (EditText) findViewById(R.id.Et_Sifre);
        Et_Ad = (EditText) findViewById(R.id.Et_Ad);
        RdBtn_Erkek = findViewById(R.id.RdBtn_Erkek);
        RdBtn_Kadin = findViewById(R.id.RdBtn_Kadin);*/
        /*RdGrp_Cinsiyet = (RadioGroup) findViewById(R.id.RdGrp_Cinsiyet);*/
        Btn_KayitOl = (Button) findViewById(R.id.Btn_KayitOl);
        Btn_Guncelle = (Button) findViewById(R.id.Btn_Guncelle);
        listView = (ListView) findViewById(R.id.listview_x);
        Btn_Sil = findViewById(R.id.Btn_Sil);
    }

    public void KayitEkle() {
        Btn_KayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Et_Cumle.getText().toString().equals("")) {
                    Yaz(Et_Cumle.getText().toString()/*, Et_Sifre.getText().toString(), Et_Ad.getText().toString(), ((RadioButton) findViewById(RdGrp_Cinsiyet.getCheckedRadioButtonId())).getText().toString()*/);
                    //((RadioButton) findViewById( RdGrp_Cinsiyet.getCheckedRadioButtonId() )).getText().toString()
                    // anlamı radio grupta seçili olanın id alıp onu tanımlayıp getTexti (mesela Erkek)
                    // almıştır
                }

            }
        });

    }

    public void Guncelle() {
        Btn_Guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Tbl_Kisi> list = realm.where(Tbl_Kisi.class).findAll();
                final Tbl_Kisi tbl_kisi = list.get(secposition);
                if (!Et_Cumle.getText().toString().equals("") && selectrow!=-1) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {


                            tbl_kisi.setCumle(Et_Cumle.getText().toString());
                       /* tbl_kisi.setSifre(Et_Sifre.getText().toString());
                        tbl_kisi.setAd(Et_Ad.getText().toString());*/
                            Goster();
                            YeniKayit();
                            selectrow=-1;

                        }
                    });
                }

            }
        });
    }

    public void YeniKayit() {
        Et_Cumle.setText("");
        /*Et_Sifre.setText("");
        Et_Ad.setText("");*/


       /* if (RdBtn_Erkek.isChecked()) {
            RdBtn_Erkek.setChecked(false);
        } else if (RdBtn_Kadin.isChecked()) {

            RdBtn_Kadin.setChecked(false);
        }*/

    }

    public void Yaz(final String cumle/*, final String sifre, final String isim, final String cinsiyet*/) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // realm.beginTransaction();    realm.commitTransaction(); bunlara gerek kalmadı nasılsametot içindehazır herşey
                Tbl_Kisi tbl_kisi = realm.createObject(Tbl_Kisi.class);
                tbl_kisi.setCumle(cumle);
                /*set edildiği için final olmak zorunda (inner class olduğu içinmiş )*/
              /*  tbl_kisi.setSifre(sifre);
                tbl_kisi.setAd(isim);
                tbl_kisi.setCinsiyet(cinsiyet);*/


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                /*Başarılı olduğunda çalışçak kod*/


                YeniKayit();
                Goster();        //sanırsamüstte metotdan realm den alıyo ondan dolayı burasını kullanmakşart

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                /*Başarısız olduğunda çalışçak kod*/

            }
        });
    }

    public void Goster() {
        RealmResults<Tbl_Kisi> realmResults = realm.where(Tbl_Kisi.class).findAll();
        if (realmResults.size() > 0) {
            adapter2 adapter = new adapter2(realmResults, getApplicationContext());
            listView.setAdapter(adapter);
        }
    }

    public void pozisyonBul() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RealmResults<Tbl_Kisi> list = realm.where(Tbl_Kisi.class).findAll();
                /* Log.i("pozisyonGelen", "sdasdsad" + position);*/
                selectrow = position;
                /* alertAc(position);*/

                  /* ClipData myClip;
                String text = "hello world";
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);*/

                getir(list, position);
                ClipboardManager clipboard = (ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("asd", Et_Cumle.getText());
                clipboard.setPrimaryClip(clip);
            }
        });

    }

    private void getir(RealmResults<Tbl_Kisi> list, int position) {


        Et_Cumle.setText(list.get(position).getCumle());

        secposition = position;
    }

    public void kayitSil(final int position) {


        final RealmResults<Tbl_Kisi> gelenlist = realm.where(Tbl_Kisi.class).findAll();


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Tbl_Kisi kisi = gelenlist.get(position);
                kisi.deleteFromRealm();
                selectrow=-1;
                Goster();
                YeniKayit();
            }
        });

    }

    public void alertAc(final int position) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alertlayout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //getapplication vs kabul etmez this kabul eder
        Button evet = (Button) view.findViewById(R.id.Btn_Evet);
        Button hayir = (Button) view.findViewById(R.id.Btn_Hayir);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();     /*view ekrana gelmesi*/
        evet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitSil(position);
                dialog.cancel();
            }
        });
        hayir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        builder.setCancelable(false);    /*boş yere tıklandığında kapanmasın */

    }
}

