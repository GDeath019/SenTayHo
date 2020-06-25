package com.example.thi1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HoaDon> arrayList;
    private ArrayList<HoaDon> filterList;
    private CustomFilter filter;

    public ResAdapter(Context context, ArrayList<HoaDon> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.filterList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater =LayoutInflater.from(context);
            view = inflater.inflate(R.layout.custom_res, null);
        }
        HoaDon tx = (HoaDon) getItem(position);
        if(tx != null){
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvDiaChi = view.findViewById(R.id.tvDiaChi);
            TextView tvRate = view.findViewById(R.id.tvRate);
            /////
            tvName.setText(arrayList.get(position).getBienSoXe());
            tvDiaChi.setText("Quãng đường: "+arrayList.get(position).getQuangDuong().toString()+" km");
            Double dongia =  Double.parseDouble(arrayList.get(position).getDonGia().toString())*arrayList.get(position).getQuangDuong()*(100-arrayList.get(position).getGiamGia())/100;
            tvRate.setText(dongia.toString());
        }
        return view;
    }
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter();
        }
        return filter;
    }
    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                //CONSTRAINT TO UPPER
                constraint = constraint.toString().toUpperCase();
                ArrayList<HoaDon> filters = new ArrayList<HoaDon>();
                //get specific item
                for (int i = 0; i < filterList.size(); i++) {
                    Double dg = filterList.get(i).getDonGia()*filterList.get(i).getQuangDuong()*(100-filterList.get(i).getGiamGia())/100;
                    if (dg<Double.parseDouble(constraint.toString())) {
                        HoaDon p = new HoaDon(filterList.get(i).getMa(), filterList.get(i).getBienSoXe(), filterList.get(i).getQuangDuong(), filterList.get(i).getDonGia(), filterList.get(i).getGiamGia());
                        filters.add(p);
                    }

                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList = (ArrayList<HoaDon>)results.values;
            notifyDataSetChanged();
        }
    }
}
