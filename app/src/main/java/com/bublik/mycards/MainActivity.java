package com.bublik.mycards;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CardClickListener cardClickListener;
    UIClickListener uiClickListener;
    ListView cardsListView;
    CardListAdapter cardListAdapter;
    FloatingActionButton fab;
    int selectedItem = -1;
    View options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cardClickListener = new CardClickListener(this);
        uiClickListener = new UIClickListener();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(uiClickListener);


        cardsListView = (ListView) findViewById(R.id.cardsListView);
        cardsListView.setOnItemClickListener(cardClickListener);
        cardsListView.setOnItemLongClickListener(cardClickListener);
        cardsListView.setOnScrollListener(cardClickListener);

        options = LayoutInflater.from(this).inflate(R.layout.selected_item_options, null);
        testFill();

    }



    View previousSelectedView = null;
    public void selectCardFromList(View view, int index)
    {
        selectedItem = index;
        if (previousSelectedView == view) return;
        if (previousSelectedView!=null)
        {
            setItemSelection(previousSelectedView, false);
        }
        previousSelectedView = view;
        setItemSelection(view, true);
    }

    public void unselectAll()
    {
        selectedItem = -1;
        if (previousSelectedView!=null)
        {
            setItemSelection(previousSelectedView, false);
            previousSelectedView = null;
        }
        fab.show();
    }




    public boolean userScroll = true;
    private void setItemSelection(View view, boolean select)
    {
        //TODO make selection by setting elevation 10-20dp
        int color;
        if (select)
        {
            color = Color.BLUE;
            fab.hide();
        } else {
            color = 0;
        }

        view.setBackgroundColor(color);

        //((LinearLayout) view).addView(options);



        if (cardsListView.getLastVisiblePosition()==selectedItem) {
            int to = selectedItem + 1;
            userScroll = false;
            if (to==cardsListView.getAdapter().getCount())
            {
                cardsListView.scrollListBy(view.getHeight());
            }

            cardsListView.smoothScrollToPosition(to);
        }

        if (cardsListView.getFirstVisiblePosition()==selectedItem)
        {
            int to = selectedItem -1;
            userScroll = false;

            if (selectedItem == 0)
            {
                cardsListView.scrollListBy(-view.getHeight());
            }

            cardsListView.smoothScrollToPosition(to);
        }

     /*   int[] loc = new int[2];
        view.getLocationInWindow(loc);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.mainRelative);
        int[] loc2 = new int[2];
        relativeLayout.getLocationInWindow(loc2);
        LinearLayout v2 = new LinearLayout(this);
        v2.setBackgroundColor(Color.BLUE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = loc[1] - loc2[1];
        v2.setLayoutParams(layoutParams);
        v2.setTop(200);
        v2.setLeft(loc[0]);
        relativeLayout.addView(v2, layoutParams);
        v2.setElevation(20);*/


    }


    Random length = new Random();

    private String getRandomString()
    {
        char[] chars = new char[2+length.nextInt(10)];
        for (int i = 0; i < chars.length; i++)
        {
            chars[i] = (char)(length.nextInt(26) + 'a');
        }
        return new String(chars);
    }
    private void testFill()
    {
        ArrayList<Card> myCards = new ArrayList<>();

        for (int i = 0; i < 20; i++)
        {
            myCards.add(new Card(Integer.toString(i), Color.RED));
        }
        cardListAdapter = new CardListAdapter(this, myCards);
        cardsListView.setAdapter(cardListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;//TODO it was true
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Toast.makeText(this, "dd", Toast.LENGTH_SHORT).show();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return true;
       // return super.onOptionsItemSelected(item);
    }
}
