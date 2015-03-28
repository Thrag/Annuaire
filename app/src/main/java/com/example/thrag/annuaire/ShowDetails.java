package com.example.thrag.annuaire;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Thrag on 28/03/15.
 */
public class ShowDetails extends Activity {

    Button menu;
    Button modify;

    TextView name;
    TextView category;
    TextView address;
    TextView description;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetails);

        menu = (Button)findViewById(R.id.buttonMenu);
        menu.setOnClickListener(listener);

        modify = (Button)findViewById(R.id.buttonEdit);
        modify.setOnClickListener(listener);

        name = (TextView)findViewById(R.id.textViewName);
        category = (TextView)findViewById(R.id.textViewCategory);
        address = (TextView)findViewById(R.id.textViewAddress);
        description = (TextView)findViewById(R.id.textViewDescription);
        phone = (TextView)findViewById(R.id.textViewPhone);

        Intent intent = getIntent();
        String value = intent.getStringExtra("nom");

        DBHelper db = new DBHelper(getApplicationContext());

        db.getWritableDatabase();

        Place place = db.getPlace(value);

        name.setText(place.getName());
        category.setText(place.getCategory());
        address.setText(place.getAddress()+","+place.getCity());
        description.setText(place.getDescription());
        phone.setText(place.getPhone());

    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.buttonMenu:

                    Intent i = new Intent(ShowDetails.this, MainActivity.class);
                    startActivity(i);

                    break;

                case R.id.buttonEdit:

                    Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ShowDetails.this, AddPlaceActivity.class);
                    //TODO intent.putExtra("nom", )
                    startActivity(intent);

                    break;
            }
        }
    };
}
