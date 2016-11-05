package com.bublik.mycards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bublik on 05-Nov-16.
 */
public class CardListAdapter extends BaseAdapter {

    private ArrayList<Card> cards;
    private LayoutInflater layoutInflater;

    public CardListAdapter(Context aContext, ArrayList<Card> list)
    {
        this.cards = list;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
           // convertView = new LinearLayout();
        } else
        {

        }
        return null;
    }


    static class ViewHolder
    {
        TextView textView;

    }
}
