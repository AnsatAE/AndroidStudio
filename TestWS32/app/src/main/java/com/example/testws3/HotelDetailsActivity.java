package com.example.testws3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class HotelDetailsActivity extends AppCompatActivity {
    private TextView nameTextView, descriptionTextView, priceTextView, addressTextView;
    private Button bookButton;
    private DatePicker datePicker;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        // Получение данных из Intent
        String name = getIntent().getStringExtra("hotel_name");
        String description = getIntent().getStringExtra("hotel_description");
        double price = getIntent().getDoubleExtra("hotel_price", 0);
        String address = getIntent().getStringExtra("hotel_address");
        int hotelId = getIntent().getIntExtra("hotel_id", -1); // Получаем ID отеля

        // Инициализация элементов интерфейса
        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        priceTextView = findViewById(R.id.priceTextView);
        addressTextView = findViewById(R.id.addressTextView);
        datePicker = findViewById(R.id.datePicker);
        bookButton = findViewById(R.id.bookButton);

        // Установка значений
        nameTextView.setText(name);
        descriptionTextView.setText(description);
        priceTextView.setText("Цена: " + price + " USD");
        addressTextView.setText(address);

        // Обработчик кнопки бронирования
        bookButton.setOnClickListener(v -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1; // Месяцы начинаются с 0
            int year = datePicker.getYear();
            // Формирование строки даты
            String date = day + "/" + month + "/" + year;

            // Сохраняем бронирование в базу данных
            boolean isBooked = databaseHelper.addBooking(hotelId, date);

            if (isBooked) {
                Toast.makeText(this, "Бронирование успешно на " + date, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ошибка при бронировании", Toast.LENGTH_SHORT).show();
            }
        });
    }
}