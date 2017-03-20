package brejas.com.br.brejas.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import brejas.com.br.brejas.helper.Constants;
import brejas.com.br.brejas.model.Beer;

/**
 * Created by rnas on 19/03/17.
 */

public class BeersDatabase extends SQLiteOpenHelper {

    public static int DB_VERSION = 1;

    public BeersDatabase(Context context) {
        super(context, Constants.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + Constants.DB_BEERS_TABLE + " ( "
                + Beer.ID       + " integer primary key autoincrement, "
                + Beer.NAME     + " text, "
                + Beer.BRAND    + " text, "
                + Beer.TYPE     + " text, "
                + Beer.CONTENT  + " integer, "
                + Beer.UNITS    + " integer "
                + " )";

        sqLiteDatabase.execSQL(query);

//        // add mocked data
//        addItem(new Beer("Heineken", "Heineken", "Bottle", 600, 2));
//        addItem(new Beer("Heineken", "Heineken", "Bottle", 600, 2));
//        addItem(new Beer("Sol", "Heineken", "Bottle", 600, 10));
//        addItem(new Beer("Bavária", "Heineken", "Bottle", 1000, 2));
//        addItem(new Beer("Kaiser", "Heineken", "Pack", 320, 16));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        return;
    }

    public List<Beer> getItems() {

        List<Beer> items = new ArrayList<>();

        String query = "SELECT * FROM " + Constants.DB_BEERS_TABLE + " order by " + Beer.ID + " DESC ";
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        if (cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    Beer beer = new Beer();

                    beer.setId(cursor.getInt(cursor.getColumnIndex(Beer.ID)));
                    beer.setName( cursor.getString( cursor.getColumnIndex(Beer.NAME) ) );
                    beer.setBrand( cursor.getString( cursor.getColumnIndex(Beer.BRAND) ) );
                    beer.setType( cursor.getString( cursor.getColumnIndex(Beer.TYPE) ) );

                    beer.setContent(cursor.getInt(cursor.getColumnIndex(Beer.CONTENT)));
                    beer.setUnits(cursor.getInt(cursor.getColumnIndex(Beer.UNITS)));

                    items.add(beer);
                } while(cursor.moveToNext());
            }
        }

        return items;

    }

    public void addItem(Beer beer) {

        ContentValues fields = new ContentValues();

        fields.put(Beer.NAME, beer.getName());
        fields.put(Beer.BRAND, beer.getBrand());
        fields.put(Beer.TYPE, beer.getType());
        fields.put(Beer.CONTENT, beer.getContent());
        fields.put(Beer.UNITS, beer.getUnits());

        getWritableDatabase().insert(Constants.DB_BEERS_TABLE, null, fields);

    }

    public void updateItem(Beer beer) {

        ContentValues fields = new ContentValues();
        String[] args;

        fields.put(Beer.NAME, beer.getName());
        fields.put(Beer.BRAND, beer.getBrand());
        fields.put(Beer.TYPE, beer.getType());
        fields.put(Beer.CONTENT, beer.getContent());
        fields.put(Beer.UNITS, beer.getUnits());

        args = new String[] {String.valueOf(beer.getId())};

        getWritableDatabase().update(Constants.DB_BEERS_TABLE, fields, "id=?", args);

    }

}
