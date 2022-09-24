package com.hasan.realminsertview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class adapter2 extends BaseAdapter {
    List<Tbl_Kisi> list;
    Context context;

    public adapter2(List<Tbl_Kisi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from( context ).inflate( R.layout.layout, parent, false );
        TextView cumle= convertView.findViewById( R.id.Et_Cumle);
        /*TextView Sifre = convertView.findViewById( R.id.Et_KullaniciSifre );
        TextView Ad = convertView.findViewById( R.id.Et_KullaniciIsim );
        TextView Cinsiyet = convertView.findViewById( R.id.Et_KullaniciCinsiyet );*/

        cumle.setText( list.get( position ).getCumle() );
        /*Sifre.setText( list.get( position ).getSifre() );
        Ad.setText( list.get( position ).getAd() );
        Cinsiyet.setText( list.get( position ).getCinsiyet() );*/


        return convertView;
    }
}
