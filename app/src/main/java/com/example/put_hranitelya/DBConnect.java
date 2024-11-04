package com.example.put_hranitelya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBConnect extends SQLiteOpenHelper {

    private static String dbName = "findUsers";
    private static String dbTable = "users";
    private static int dbVersion = 1;

    private static String ID = "id";
    private static String emailAddress = "emailAddress";
    private static String password = "password";


    public DBConnect(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + dbTable + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + emailAddress + " TEXT, "
                + password + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + dbTable);
        onCreate(db);
    }

    public boolean addUser(Users user) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(emailAddress, user.getEmailAddress());
        values.put(password, user.getPassword());
        long result = database.insert(dbTable, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + dbTable + " WHERE emailAddress=? AND password=?", new String[]{emailAddress, password});
        return cursor.getCount() > 0;
    }

    public String getEmail(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {emailAddress};
        String selection = ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(dbTable, columns, selection, selectionArgs, null, null, null);

        String email = null;
        if (cursor != null && cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndexOrThrow(emailAddress));
            cursor.close();
        } else {
            // Обработка случая, когда запись не найдена
            Log.e("DBConnect", "User with ID " + userId + " not found.");
        }

        db.close();
        return email;
    }
    /*public Users getUser(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(dbTable, new String[]{ID, emailAddress, password},
                ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        Users user = new Users(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));
        return user;
    }


    public List<Users> getAllUsers (){
        SQLiteDatabase database = this.getReadableDatabase();
        List<Users> usersList = new ArrayList<>();
        String selectAllUsers = "Select * from " + dbTable;
        Cursor cursor = database.rawQuery(selectAllUsers, null);
        if (cursor.moveToFirst()){
            do{
                Users user = new Users();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setEmailAddress(cursor.getString(1));
                user.setPassword(cursor.getString(2));

                usersList.add(user);
            } while (cursor.moveToNext());
        }
        return usersList;
    }


     */
}