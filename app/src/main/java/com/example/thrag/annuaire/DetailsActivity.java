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
    Button Details;

    Place item;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Add = (Button)findViewById(R.id.buttonAdd);
        Add.setOnClickListener(listener);
        Menu = (Button)findViewById(R.id.buttonMenu);
        Menu.setOnClickListener(listener);
        Details = (Button)findViewById(R.id.buttonSee);
        Details.setOnClickListener(listener);

        lv = (ListView) findViewById(R.id.detailList);

        Intent intent = getIntent();
        if(intent.hasExtra("requete")) {
            String request = intent.getStringExtra("requete");
            String[] param = request.split("/");
            RemplirList(param[0], param[1]);
        }
        else
        {
            RemplirList("name","");
        }
    }

    private void RemplirList(String category, String param) {

        DBHelper db = new DBHelper(getApplicationContext());

        db.getWritableDatabase();

        ArrayList<Place> places = db.getAllNames(category,param);
        places.toArray();

        ArrayAdapter<Place> arrayAdapter = new ArrayAdapter<Place>(
                this,android.R.layout.simple_list_item_single_choice,places );
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

                    Intent u = new Intent(DetailsActivity.this, AddPlaceActivity.class);
                    startActivity(u);
                    break;
                case R.id.buttonSee:

                    Integer Position = lv.getCheckedItemPosition();
                    item = (Place)lv.getItemAtPosition(Position);

                    Intent v = new Intent(DetailsActivity.this, ShowDetails.class);
                    v.putExtra("nom",item.getName());
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
        item = (Place) adapter.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(), "Test "+item.getName(), Toast.LENGTH_LONG).show();
    }

    private Place getPlace(int Position){
        Place placeFound = new Place();
        placeFound = (Place) lv.getItemAtPosition(Position);
        return placeFound;
    }
}
