package com.bublik.mycards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
    public Card getItem(int position) {
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
           convertView = layoutInflater.inflate(R.layout.cardlist_item, null);
        }
        ((TextView)convertView.findViewById(R.id.name)).setText(cards.get(position).name);
        ((ImageView)convertView.findViewById(R.id.card_image)).setBackgroundColor(cards.get(position).color);
        return convertView;
    }
}
