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
    private ArrayList<restaurant> arrayList;
    private ArrayList<restaurant> filterList;
    private CustomFilter filter;

    public ResAdapter(Context context, ArrayList<restaurant> arrayList) {
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
        restaurant res = (restaurant) getItem(position);
        if(res != null){
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvDiaChi = view.findViewById(R.id.tvDiaChi);
            TextView tvRate = view.findViewById(R.id.tvRate);
            /////
            tvName.setText(arrayList.get(position).getName());
            tvDiaChi.setText(arrayList.get(position).getLocal());
            tvRate.setText(arrayList.get(position).getRate().toString());
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
                ArrayList<restaurant> filters = new ArrayList<restaurant>();
                //get specific item
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getName().toUpperCase().contains(constraint) || filterList.get(i).getLocal().toUpperCase().contains(constraint)) {
                        restaurant p = new restaurant(filterList.get(i).getMa(), filterList.get(i).getName(), filterList.get(i).getLocal(), filterList.get(i).getRate());
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
            arrayList = (ArrayList<restaurant>)results.values;
            notifyDataSetChanged();
        }
    }
}
