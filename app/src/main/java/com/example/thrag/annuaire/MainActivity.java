package com.example.thrag.annuaire;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    Button list;

    // Wesh ma gueule !
    // Kawabounga MA GEULE :D
    // I am Groot
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (Button)findViewById(R.id.buttonList);
        list.setOnClickListener(listener);

        Place place1 = new Place("Test1", "Ceci est un test", 102, 102, "Liège", "Food", "rue des boobs", "911");
        Place place2 = new Place("Test2", "Ceci est un test", 102, 102, "BXL", "Musée", "rue des nichons", "912");
        Place place3 = new Place("Test3", "Ceci est un test", 102, 102, "Liège", "Musée", "rue des boobs", "913");
        Place place4 = new Place("Test4", "Ceci est un test", 102, 102, "BXL", "Food", "rue des nichons", "914");


        DBHelper db = new DBHelper(getApplicationContext());

        db.getWritableDatabase();

        db.addPlace(place1);
        db.addPlace(place2);
        db.addPlace(place3);
        db.addPlace(place4);

        place1 = null;
        place2 = null;
        place3 = null;
        place4 = null;
    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.buttonList:

                    DBHelper db = new DBHelper(getApplicationContext());

                    db.getWritableDatabase();

                    ArrayList<Place> list = db.getAllNames("category", "Food");
                    String result = "";

                    for(int i = 0; i < list.size(); i++ )
                    {

                        result = result + list.get(i).getName()+" / ";
                    }

                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    Intent u = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(u);
                    break;
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
