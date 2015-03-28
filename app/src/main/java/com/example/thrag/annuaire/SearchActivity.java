package com.example.thrag.annuaire;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sylvain on 28/03/15.
 */
public class SearchActivity extends Activity{

    TextView keyWords ;
    TextView adresse ;
    private ListView lv;
    Button search;
    Button cancel;
    private String listCat[] ={"Restaurant","Parking","Musée","Administration","Snack","Magasin"};
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // get the listview
        search = (Button)findViewById(R.id.buttonSearch);
        search.setOnClickListener(listener);

        cancel = (Button)findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(listener);

        lv = (ListView) findViewById(R.id.ListeCategorie);
        keyWords = (TextView) findViewById(R.id.txtMotCle);
        adresse = (TextView) findViewById(R.id.txtAdresse);

        RemplirList();
    }

    private void RemplirList() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_single_choice,listCat );
        lv.setAdapter(arrayAdapter);
        lv.setItemChecked(-1,true);
        lv.requestFocusFromTouch();
        lv.setSelection(-1);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonCancel:

                    Intent u = new Intent(SearchActivity.this, MainActivity.class);
                    startActivity(u);
                    break;

                case R.id.buttonSearch:

                    Integer Position = lv.getCheckedItemPosition();

                    if(adresse.getText().length()!=0)
                    {
                        Intent intent1 = new Intent(SearchActivity.this, DetailsActivity.class);
                        intent1.putExtra("requete", "adresse/"+adresse.getText().toString());
                        startActivity(intent1);
                    }
                    else if(keyWords.getText().length()!=0)
                    {
                        Intent intent2 = new Intent(SearchActivity.this, DetailsActivity.class);
                        intent2.putExtra("requete", "description/" + keyWords.getText().toString());
                        startActivity(intent2);
                    }
                    else if(Position != -1)
                    {
                        item = lv.getItemAtPosition(Position).toString();
                        Intent intent1 = new Intent(SearchActivity.this, DetailsActivity.class);
                        intent1.putExtra("requete", "category/"+item);
                        startActivity(intent1);
                    }
                    else
                    {
                        new AlertDialog.Builder(SearchActivity.this)
                                .setMessage("Aucun champs de recherche sélectionné.")
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }
                    break;
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
        item = (String) adapter.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(),item ,Toast.LENGTH_LONG);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}