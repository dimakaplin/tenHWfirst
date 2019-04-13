package com.dimakaplin143.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SampleAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    private List<Sample> samples;

    public SampleAdapter(Context ctx, List<Sample> samples) {
        this.samples = samples;
        this.ctx = ctx;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return samples.size();
    }

    @Override
    public Object getItem(int position) {
        return samples.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Sample getSample(int position) {
        return ((Sample) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.sample, parent, false);
        }

        Sample s = getSample(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.sample_name)).setText(s.getName());
        ((TextView) view.findViewById(R.id.sample_target)).setText(s.getTarget());
        ((ImageView) view.findViewById(R.id.icon_elem)).setImageResource(s.getImgId());

        CheckBox checkNothing = (CheckBox) view.findViewById(R.id.cbBox);

        checkNothing.setOnCheckedChangeListener((v, is)-> {
            getSample((Integer) v.getTag()).setChecked(is);
        });

        checkNothing.setTag(position);
        checkNothing.setChecked(s.isChecked());
        return view;
    }

}
