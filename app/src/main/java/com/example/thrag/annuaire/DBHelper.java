package com.example.thrag.annuaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Thrag on 26/03/15.
 */
public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "hackathon.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PLACE = "table_place";
    private static final String TABLE_CITY = "table_city";
    private static final String TABLE_CATEGORY = "table_category";

    private static final String COlUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    private static final String REQUETE_CREATION_PLACE = "create table "
            + TABLE_PLACE + " ( "
            + COlUMN_ID + " integer auto_increment, "
            + COLUMN_NAME + " primary key not null, "
            + COLUMN_DESCRIPTION + " , "
            + COLUMN_CITY + " not null, "
            + COLUMN_CATEGORY + " not null, "
            + COLUMN_ADDRESS + " not null, "
            + COLUMN_PHONE + " , "
            + COLUMN_LATITUDE + " , "
            + COLUMN_LONGITUDE
            + " ); ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(REQUETE_CREATION_PLACE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
        onCreate(db);
    }

    public boolean addPlace(Place place) {

        boolean result = false;

        //Adding the values to TABLE_PLACE
        ContentValues placeValue = new ContentValues();

        placeValue.put(COLUMN_NAME, place.getName());
        placeValue.put(COLUMN_DESCRIPTION, place.getDescription());
        placeValue.put(COLUMN_CITY, place.getCity());
        placeValue.put(COLUMN_CATEGORY, place.getCategory());
        placeValue.put(COLUMN_ADDRESS, place.getAddress());
        placeValue.put(COLUMN_PHONE, place.getPhone());
        placeValue.put(COLUMN_LATITUDE, place.getLatitude());
        placeValue.put(COLUMN_LONGITUDE, place.getLongitude());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PLACE, null, placeValue);

        db.close();
        result = true;
        return result;
    }

    public Place getPlace(String name){

        String query = "Select * FROM " + TABLE_PLACE + " WHERE " + COLUMN_NAME + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Place place = new Place();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            //place.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COlUMN_ID))));
            place.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            place.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            place.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)));
            place.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            place.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            place.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
            place.setLatitude(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE))));
            place.setLongitude(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE))));

            cursor.close();
        } else {
            place = null;
        }
        db.close();
        return place;
    }

    public boolean deletePlace(String name) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_PLACE + " WHERE " + COLUMN_NAME + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Place place = new Place();

        if (cursor.moveToFirst()) {
            place.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COlUMN_ID))));
            db.delete(TABLE_PLACE, COlUMN_ID + " = ?",
                    new String[]{String.valueOf(place.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean modifyPlace(String name, Place place) {

        boolean result = false;

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, place.getName());
        values.put(COLUMN_DESCRIPTION, place.getDescription());
        values.put(COLUMN_CITY, place.getCity());
        values.put(COLUMN_CATEGORY, place.getCategory());
        values.put(COLUMN_ADDRESS, place.getAddress());
        values.put(COLUMN_PHONE, place.getPhone());
        values.put(COLUMN_LATITUDE, place.getLatitude());
        values.put(COLUMN_LONGITUDE, place.getLongitude());

        SQLiteDatabase db = this.getWritableDatabase();

        db.update(TABLE_PLACE, values, COlUMN_ID + "=" + place.getID(), null);

        result = true;
        return result;
    }

    public ArrayList<Place> getAllNames(String select, String search){

        ArrayList<Place> places = new ArrayList<Place>();

        String query = "";

        switch (select) {
            case COLUMN_NAME : query = query + "SELECT * FROM " + TABLE_PLACE ;
                break;
            case COLUMN_CATEGORY : query = query + "SELECT * FROM " + TABLE_PLACE + " WHERE " + COLUMN_CATEGORY + " = \"" + search + "\"";
                break;
            case COLUMN_CITY : query = query + "SELECT * FROM " + TABLE_PLACE + " WHERE " + COLUMN_CITY + " = \"" + search + "\"";
                break;
        }

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                Place place = new Place();

                //place.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COlUMN_ID))));
                place.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                place.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                place.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)));
                place.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                place.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                place.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                place.setLatitude(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE))));
                place.setLongitude(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE))));

                cursor.moveToNext();

                places.add(place);
            }
            cursor.close();
        }

        db.close();

        return places;
    }
}
