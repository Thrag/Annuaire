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

import java.util.ArrayList;

/**
 * Created by Thrag on 28/03/15.
 */
public class AddPlaceActivity extends Activity {

    Button cancel;
    Button valid;
    TextView editName;
    TextView editDescription;
    TextView editCategory;
    TextView editCity;
    TextView editAddress;
    TextView editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplace);

        cancel = (Button)findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(listener);

        valid = (Button)findViewById(R.id.buttonValid);
        valid.setOnClickListener(listener);

        editName = (TextView)findViewById(R.id.editName);
        editDescription = (TextView)findViewById(R.id.editDescription);
        editCategory = (TextView)findViewById(R.id.editCategory);
        editCity = (TextView)findViewById(R.id.editCity);
        editAddress = (TextView)findViewById(R.id.editAddress);
        editPhone = (TextView)findViewById(R.id.editPhone);

    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.buttonCancel:

                    Intent i = new Intent(AddPlaceActivity.this, MainActivity.class);
                    startActivity(i);

                    break;

                case R.id.buttonValid:

                    boolean validation = false;

                    String name = editName.getText().toString();
                    String description = editDescription.getText().toString();
                    String category = editCategory.getText().toString();
                    String city = editCity.getText().toString();
                    String address = editAddress.getText().toString();
                    String phone = editPhone.getText().toString();

                    if(!name.equals("") && !address.equals("") && !category.equals("") && !city.equals("") ) validation = true;

                    if(validation == true)
                    {
                        Place place = new Place();

                        place.setName(name);
                        if(description != "") {place.setDescription(description);}else{place.setDescription("");}
                        place.setCategory(category);
                        place.setCity(city);
                        place.setAddress(address);
                        if(phone != "") {place.setPhone(phone);}else{place.setPhone("");}

                        DBHelper db = new DBHelper(getApplicationContext());

                        db.getWritableDatabase();

                        if(db.addPlace(place)) {
                            Toast.makeText(getApplicationContext(), "Ajout", Toast.LENGTH_LONG).show();
                        }

                        Intent intent = new Intent(AddPlaceActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        new AlertDialog.Builder(AddPlaceActivity.this)
                                .setMessage("Veuillez complèter les champs marqués en orange.")
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

}
