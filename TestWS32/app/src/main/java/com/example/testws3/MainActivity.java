package com.example.testws3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);

// Получение данных из базы
        List<Hotel> hotelList = databaseHelper.getAllHotels();

// Настройка RecyclerView
        HotelAdapter adapter = new HotelAdapter(hotelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        // Добавление тестовых данных
        //databaseHelper.addUser("testUser", "1234");
        //databaseHelper.addHotel("Отель Sunrise", "Прекрасный вид на море", 150.0, "Улица Морская, 1");



    }
}