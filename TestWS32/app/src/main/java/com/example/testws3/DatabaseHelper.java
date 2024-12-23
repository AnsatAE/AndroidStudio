package com.example.testws3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Название базы данных
    private static final String DATABASE_NAME = "nomadApp.db";
    // Версия базы данных
    private static final int DATABASE_VERSION = 1;

    // Конструктор
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы пользователей
        String createUsersTable = "CREATE TABLE Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL);";
        db.execSQL(createUsersTable);

        // Создание таблицы гостиниц
        String createHotelsTable = "CREATE TABLE Hotels (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "description TEXT, " +
                "price REAL NOT NULL, " +
                "address TEXT);";
        db.execSQL(createHotelsTable);

        // Создание таблицы бронирования
        String createBookingsTable = "CREATE TABLE Bookings (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userId INTEGER, " +
                "hotelId INTEGER, " +
                "bookingDate TEXT, " +
                "FOREIGN KEY(userId) REFERENCES Users(id), " +
                "FOREIGN KEY(hotelId) REFERENCES Hotels(id));";
        db.execSQL(createBookingsTable);
    }
    public List<Hotel> getAllHotels() {
        List<Hotel> hotelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Hotels", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                String address = cursor.getString(cursor.getColumnIndex("address"));

                hotelList.add(new Hotel(id, name, description, price, address));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return hotelList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаляем старые таблицы при обновлении базы данных
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Hotels");
        db.execSQL("DROP TABLE IF EXISTS Bookings");
        onCreate(db);
    }

    // Добавление пользователя
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("Users", null, values);
        return result != -1; // true, если добавление успешно
    }

    // Добавление гостиницы
    public boolean addHotel(String name, String description, double price, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        values.put("address", address);

        long result = db.insert("Hotels", null, values);
        return result != -1; // true, если добавление успешно
    }

    public boolean addBooking(int hotelId, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hotel_id", hotelId);
        values.put("date", date);

        long result = db.insert("Bookings", null, values);
        return result != -1;
    }

}

