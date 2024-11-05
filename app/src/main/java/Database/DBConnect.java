package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.put_hranitelya.Users;

public class DBConnect extends SQLiteOpenHelper {
    private static String dbName = "findUsers.db";
    private static String dbTable = "users";
    private static int dbVersion = 1;
    private static String dbTableResults = "results";

    private static String ID = "id";
    private static String emailAddress = "emailAddress";
    private static String password = "password";
    private static String col1 = "col1";
    private static String col2 = "col2";
    private static String col3 = "col3";
    private static String col4 = "col4";
    private static String col5 = "col5";
    private static String col6 = "col6";
    private static String col7 = "col7";


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
        String createResultsTable = "CREATE TABLE " + dbTableResults + " (" +
                ID + " INTEGER, " +
                col1 + " TEXT, " +
                col2 + " TEXT, " +
                col3 + " TEXT, " +
                col4 + " TEXT, " +
                col5 + " TEXT, " +
                col6 + " TEXT, " +
                col7 + " TEXT, " +
                "FOREIGN KEY(" + ID + ") REFERENCES " + dbTable + "(" + ID + "));";
        db.execSQL(createResultsTable) ;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + dbTableResults);
        db.execSQL("DROP TABLE IF EXISTS " + dbTable);
        onCreate(db);
    }


    public static class ResultData {
        public long userId; // Foreign key from users table
        public String col1;
        public String col2;
        public String col3;
        public String col4;
        public String col5;
        public String col6;
        public String col7;

        public ResultData(long userId, String col1, String col2, String col3, String col4, String col5, String col6, String col7) {
            this.userId = userId;
            this.col1 = col1;
            this.col2 = col2;
            this.col3 = col3;
            this.col4 = col4;
            this.col5 = col5;
            this.col6 = col6;
            this.col7 = col7;
        }
    }

    public long getUserIdFromUsersTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        long userId = -1; // Значение по умолчанию, если ID не найден

        try (Cursor cursor = db.query(dbTable, new String[]{ID}, null, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                userId = cursor.getLong(cursor.getColumnIndexOrThrow(ID));
            }
        } catch (Exception e) {
            // Обработка исключений, например, запись в лог
            Log.e("getUserIdFromUsersTable", "Error getting user ID: " + e.getMessage());
        }

        return userId;
    }

    public long insertResult(ResultData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, data.userId);
        values.put(col1, data.col1);
        values.put(col2, data.col2);
        values.put(col3, data.col3);
        values.put(col4, data.col4);
        values.put(col5, data.col5);
        values.put(col6, data.col6);
        values.put(col7, data.col7);

        long newRowId = db.insert(dbTableResults, null, values);
        db.close();
        return newRowId;
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
    /*

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