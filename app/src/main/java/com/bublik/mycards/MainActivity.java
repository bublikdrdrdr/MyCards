package com.bublik.mycards;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        testFill();

    }


    int previousSelected = -1;
    View previousSelectedView = null;
    public void selectCardFromList(View view, int index)
    {
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
        if (previousSelectedView!=null)
        {
            setItemSelection(previousSelectedView, false);
            previousSelectedView = null;
        }
        fab.show();
    }

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
            myCards.add(new Card(getRandomString(), Color.RED));
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
