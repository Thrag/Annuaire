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
    Button details;
    Button add;
    Button search;

    // Wesh ma gueule !
    // Kawabounga MA GEULE :D
    // I am Groot
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (Button)findViewById(R.id.buttonList);
        list.setOnClickListener(listener);

        details = (Button)findViewById(R.id.buttonDetails);
        details.setOnClickListener(listener);

        add = (Button)findViewById(R.id.buttonAdd);
        add.setOnClickListener(listener);

        search = (Button)findViewById(R.id.buttonSearch);
        search.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.buttonList:

                    Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(i);
                    break;

                case R.id.buttonDetails:

                    Intent intent = new Intent(MainActivity.this, ShowDetails.class);
                    intent.putExtra("nom","Anata");
                    startActivity(intent);
                    break;

                case R.id.buttonAdd:

                Intent u = new Intent(MainActivity.this, AddPlaceActivity.class);
                startActivity(u);
                break;

                case R.id.buttonSearch:

                    Intent w = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(w);
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
