package com.example.lksapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lksapps.Model.DataModel;
import com.example.lksapps.Model.RetrieveResponse;
import com.example.lksapps.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;

    private int totalharga = 0;
    private Listener listener;


    public AdapterData(Context ctx, List<DataModel> listData,Listener listener) {
        this.ctx = ctx;
        this.listData = listData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);
        holder.namabrg.setText(dm.getNama_barang());
        holder.hargabrg.setText("Rp. " + dm.getHarga_item());
        holder.ratingbrg.setText(dm.getRating());
        Picasso.get().load(dm.getGambar()).fit().centerCrop().into(holder.fotobrg);
        holder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String defualtValue = holder.qty.getText().toString();
                int value = Integer.parseInt(defualtValue);
                value++;
                holder.qty.setText(String.valueOf(value));
                listener.onPlus(dm);
            }
        });
        holder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String defualtValue = holder.qty.getText().toString();
                int value = Integer.parseInt(defualtValue);
                value--;
                holder.qty.setText(String.valueOf(value));
                listener.onMinus(dm);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView namabrg,hargabrg,ratingbrg,qty,totalbayar;
        ImageButton btnplus,btnminus,btncart;
        ImageView fotobrg;

        Button btnbayar;
        RelativeLayout rlfooter;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            namabrg = itemView.findViewById(R.id.nama_brg);
            hargabrg = itemView.findViewById(R.id.harga_brg);
            ratingbrg = itemView.findViewById(R.id.rating_brg);
            btnplus = itemView.findViewById(R.id.btn_plus);
            btnminus = itemView.findViewById(R.id.btn_minus);
            btncart = itemView.findViewById(R.id.btn_cart);
            fotobrg = itemView.findViewById(R.id.img_brg);
            qty = itemView.findViewById(R.id.qty_brg);
            totalbayar = itemView.findViewById(R.id.total_bayar);
            btnbayar = itemView.findViewById(R.id.btn_bayar);
            rlfooter = itemView.findViewById(R.id.rl_footer);
        }
    }
    public interface Listener{
        void onPlus(DataModel data);
        void onMinus(DataModel data);
    }

}
