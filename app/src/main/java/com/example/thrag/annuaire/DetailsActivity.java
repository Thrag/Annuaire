package com.example.thrag.annuaire;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylvain on 27/03/15.
 */
public class DetailsActivity extends Activity{

    Button Add;
    Button Menu;
    Button Edit;

    Place place1 = new Place("Random Place","Random",421,422,"Random City","Random Category","Random Street", "0042");
    Place place2 = new Place("Blop Place","Blop",800,801,"Blop City","Blop Category","Blop Street", "080");
    Place place3 = new Place("Kaboum Place","Kaboum",690,691,"Kaboum City","Kaboum Category","Kaboum Street", "69");
    Place[] listPlace = {place1,place2,place3};
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

        lv = (ListView) findViewById(R.id.detailList);
        RemplirList();
    }

    private void RemplirList() {
        ArrayAdapter<Place> arrayAdapter = new ArrayAdapter<Place>(
                this,android.R.layout.simple_list_item_single_choice,listPlace );
        lv.setAdapter(arrayAdapter);
        lv.setItemChecked(0,true);
        lv.requestFocusFromTouch();
        lv.setSelection(0);

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
                    editPlace();
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
    public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
        Place item = (Place) adapter.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(), "Test "+item.getName(), Toast.LENGTH_LONG).show();
    }
    private void addPlace(){
           //PLOP
    }
    private void removePlace(){
        //PLOP
    }
    private void editPlace(){
        Integer Position = lv.getCheckedItemPosition();
        Toast.makeText(getApplicationContext(), "Choix =  "+(getPlace(Position).getCity()), Toast.LENGTH_LONG).show();
    }
    private Place getPlace(int Position){
        Place placeFound = new Place();
        placeFound = (Place) lv.getItemAtPosition(Position);
        return placeFound;
    }
}
