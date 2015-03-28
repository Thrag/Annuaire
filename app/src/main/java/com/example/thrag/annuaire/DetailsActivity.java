package com.example.thrag.annuaire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylvain on 27/03/15.
 */
public class DetailsActivity extends Activity{

    Button Add;
    Button Menu;
    Button Edit;
    static Integer inter = 0;
    Place place1 = new Place("Random Place","Random",421,422,"Random City","Random Category","Random Street", "0042");
    Place place2 = new Place("Blop Place","Blop",800,801,"Blop City","Blop Category","Blop Street", "080");
    Place place3 = new Place("Blop Place","Blop",800,801,"Blop City","Blop Category","Blop Street", "080");
    static List<Place> listString = new ArrayList<Place>();
    //------------------------

    private ListView lv;

    //------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Add = (Button)findViewById(R.id.buttonAdd);
        Add.setOnClickListener(listener);
        Menu = (Button)findViewById(R.id.buttonMenu);
        Menu.setOnClickListener(listener);
        Edit = (Button)findViewById(R.id.buttonEdit);
        Edit.setOnClickListener(listener);
        RemplirList();
    }

    private void RemplirList() {

        lv = (ListView) findViewById(R.id.detailList);

        ArrayAdapter<Place> arrayAdapter = new ArrayAdapter<Place>(
                this,android.R.layout.simple_list_item_1,listString );

        lv.setAdapter(arrayAdapter);

    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.buttonAdd:
                    addPlace();
                    Intent u = new Intent(DetailsActivity.this, DetailsActivity.class);
                    startActivity(u);
                    break;
                case R.id.buttonEdit:
                    removePlace();
                    Intent v = new Intent(DetailsActivity.this, DetailsActivity.class);
                    startActivity(v);
                    break;
                case R.id.buttonMenu:
                    Intent i = new Intent(DetailsActivity.this, MainActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addPlace(){
        inter ++;
        listString.add(place1);
        listString.add(place2);
    }
    private void removePlace(){
        inter --;
        listString.remove(listString.size()-1);
    }
    private void editPlace(){

    }
}
