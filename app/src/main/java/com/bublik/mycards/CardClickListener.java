package com.bublik.mycards;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Bublik on 05-Nov-16.
 */
public class CardClickListener implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnScrollListener{

    private MainActivity creator;

    public CardClickListener(MainActivity creator)
    {
        this.creator = creator;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        creator.unselectAll();
        Log.i("list", "click");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        creator.selectCardFromList(view, position);
        Log.i("list", "long click");

        missScroll = false;
        return true;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
        {
            creator.userScroll = true;
        }
    }


    boolean missScroll = false;
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
       //     creator.unselectAll();
        if (!creator.userScroll) return;
        if (missScroll)
        {
            creator.unselectAll();
            missScroll = false;
        } else
        {
            missScroll = true;
        }
    //    Log.i("list", "scroll");
    }
}
