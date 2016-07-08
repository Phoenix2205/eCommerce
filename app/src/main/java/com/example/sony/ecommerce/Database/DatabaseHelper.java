package com.example.sony.ecommerce.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sony.ecommerce.Model.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 7/8/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WOOCOMMERCE.db";
    private static  int ROW_ID=1;

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, 1);
        openDataBase(context);
    }

    public DatabaseHelper (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseSchema.CARTTABLE.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  boolean InsertIntoCartTable(String UserName, int IDItem, String ItemName,int Price, int Quantity,String Image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_ENTRY_ID,ROW_ID);  ROW_ID++;
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_USER_NAME,UserName);
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_ID,IDItem);
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_NAME,ItemName);
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_PRICE,Price);
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_QUANTITIY,Quantity);
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_IMAGE,Image);
        contentValues.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_TOTAL,Quantity*Price);
        db.insert(DatabaseSchema.CARTTABLE.TABLE_NAME,null,contentValues);
        return true;
    }

    public int GetDataCartTablebyID (int ID)
    {
        int id=0;
        SQLiteDatabase db = getReadableDatabase();
        String QueryByID="Select * from " + DatabaseSchema.CARTTABLE.TABLE_NAME +" where "
                +DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_ID +" = " + ID;
        Cursor cursor = db.rawQuery(QueryByID, null);
        try {
            cursor.moveToFirst();
            id=cursor.getInt(cursor.getColumnIndex(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_ID));
            return id;
        }
        catch (IndexOutOfBoundsException e) {return id=-1;}

    }

    public int GetDataQuantityTablebyID (int ID)
    {
        int quantity=0;
        SQLiteDatabase db = getReadableDatabase();
        String QueryByID="Select * from " + DatabaseSchema.CARTTABLE.TABLE_NAME + " where "
                +DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_ID +" = " + ID;
        Cursor cursor = db.rawQuery(QueryByID, null);
     try{
            cursor.moveToFirst();
            quantity=cursor.getInt(cursor.getColumnIndex(DatabaseSchema.CARTTABLE.COLUMN_NAME_QUANTITIY));
            return quantity;
        }
        catch (CursorIndexOutOfBoundsException e) {
            return quantity = -1;
        }

    }

    public List<Product> GetDataTablebyUserName (String username)
    {
        List<Product>temp= new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String QueryByUserName="Select * from " + DatabaseSchema.CARTTABLE.TABLE_NAME + " where "
                +DatabaseSchema.CARTTABLE.COLUMN_NAME_USER_NAME +" = " + "'" +username + "'";
        Cursor cursor = db.rawQuery(QueryByUserName, null);
        try{

            cursor.moveToFirst();
            do {
                String title = cursor.getString(cursor.getColumnIndex(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_NAME));
                String imgSrc = cursor.getString(cursor.getColumnIndex(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_IMAGE));
                int price = cursor.getInt(cursor.getColumnIndex(DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_PRICE));
                int quantity = cursor.getInt(cursor.getColumnIndex(DatabaseSchema.CARTTABLE.COLUMN_NAME_QUANTITIY));
                Product product = new Product();
                product.setTitle(title);
                product.setPrice(String.valueOf(price));
                product.setQuantity(quantity);
                product.setFeaturedSrc(imgSrc);
                temp.add(product);
            }while (cursor.moveToNext());
            return temp;

        }
        catch (CursorIndexOutOfBoundsException e) {
            return temp;
        }

    }


    public boolean UpdateQuantityCartTable(int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int quantity=GetDataQuantityTablebyID(ID);
        if (quantity!=-1)
        {
            quantity=quantity+1;
            ContentValues values = new ContentValues();
            values.put(DatabaseSchema.CARTTABLE.COLUMN_NAME_QUANTITIY, quantity);
            
             db.update(DatabaseSchema.CARTTABLE.TABLE_NAME, values,  DatabaseSchema.CARTTABLE.COLUMN_NAME_ITEM_ID+ " = ? ", new String[] { String.valueOf(ID) });
            return  true;
        }
        else return false;

    }

    public static boolean IsDatabaseExist(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }

    public static  SQLiteDatabase openDataBase(Context context) throws SQLException {
        String packageName = context.getPackageName();
        String DB_PATH = "/data/data/"+ packageName +"/databases/";

        //String DB_PATH = String.format("//data//data//%s//databases//", packageName);
        String path= DB_PATH + DATABASE_NAME;
        SQLiteDatabase database =SQLiteDatabase.openDatabase(path,null,
                SQLiteDatabase.OPEN_READWRITE);
        return database;
    }



}
