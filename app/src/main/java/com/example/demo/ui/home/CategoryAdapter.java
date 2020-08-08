package com.example.demo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.demo.R;

public class CategoryAdapter extends BaseAdapter {

    Context context;
    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return cat_Image.length;
    }

    @Override
    public Object getItem(int position) {
        return cat_Image.length;
    }

    @Override
    public long getItemId(int position) {
        return cat_Image.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.categorylayout,null);

        CardView cardView;
        ImageView catimage;
        TextView cattxt;

        catimage=convertView.findViewById(R.id.cat1);
        cattxt=convertView.findViewById(R.id.txtcat1);

        catimage.setImageResource(cat_Image[position]);
        cattxt.setText(cat_name[position]);

        return convertView;
    }


    String cat_name[] = {"History Book","Comic Book","Sic-fi Book","Children Book","Business Book"};

    Integer cat_Image[] = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four, R.drawable.five};

}
