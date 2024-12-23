package com.example.testws3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private List<Hotel> hotelList;

    public HotelAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.nameTextView.setText(hotel.getName());
        holder.descriptionTextView.setText(hotel.getDescription());
        holder.priceTextView.setText("Цена: " + hotel.getPrice() + " ТГ");
        holder.addressTextView.setText(hotel.getAddress());

        // Обработчик нажатия на элемент
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), HotelDetailsActivity.class);
            intent.putExtra("hotel_name", hotel.getName());
            intent.putExtra("hotel_description", hotel.getDescription());
            intent.putExtra("hotel_price", hotel.getPrice());
            intent.putExtra("hotel_address", hotel.getAddress());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView, priceTextView, addressTextView;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
        }
    }

}

