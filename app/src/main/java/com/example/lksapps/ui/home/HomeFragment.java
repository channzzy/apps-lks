package com.example.lksapps.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lksapps.API.APIRequestData;
import com.example.lksapps.API.RetroServer;
import com.example.lksapps.Adapter.AdapterData;
import com.example.lksapps.Model.DataModel;
import com.example.lksapps.Model.RetrieveResponse;
import com.example.lksapps.R;
import com.example.lksapps.databinding.FragmentHomeBinding;
import com.example.lksapps.ui.dashboard.DashboardViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements AdapterData.Listener{
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;

    private AdapterData adapter;
    private RecyclerView.LayoutManager lmData;

    private TextView tv_totalharga;
    private int hargatotal = 0;

    private List<DataModel> listData = new ArrayList<>();

    private AdapterData.Listener listener;

    @Override
    public void onPlus(DataModel data) {
        // kode yang akan dijalankan ketika item pada RecyclerView di-klik
        int hargaItem = Integer.parseInt(data.getHarga_item()); // mengambil harga_item dari objek DataModel
        int qty = data.getQty() + 1; // menambahkan qty sebanyak 1
        int total = hargaItem * qty;
        adData.notifyDataSetChanged(); // memperbarui tampilan RecyclerView
        hargatotal = hargatotal + total; // menambahkan hargaItem ke variabel hargatotal
        tv_totalharga.setText(String.valueOf(hargatotal)); // menampilkan harga total dalam TextView
    }
    @Override
    public void onMinus(DataModel data){
        int hargaItem = Integer.parseInt(data.getHarga_item()); // mengambil harga_item dari objek DataModel
        int qty = -1; // menambahkan qty sebanyak 1
            // kode yang akan dijalankan ketika item pada RecyclerView di-klik
            int total = hargaItem * qty;
            adData.notifyDataSetChanged(); // memperbarui tampilan RecyclerView
            hargatotal = hargatotal + total; // menambahkan hargaItem ke variabel hargatotal
            tv_totalharga.setText(String.valueOf(hargatotal)); // menampilkan harga total dalam TextView
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rvData = root.findViewById(R.id.rv_data);
        listener = this;
        tv_totalharga = root.findViewById(R.id.total_bayar);
        lmData = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        retrievebrg();
        EditText search = root.findViewById(R.id.te_search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String keyword = charSequence.toString();
                List<DataModel> filteredlist = new ArrayList<>();
                if (keyword.isEmpty()) {
                    filteredlist = listData;
                } else {
                    for (DataModel data : listData) {
                        if (data.getNama_barang().toLowerCase().contains(keyword.toLowerCase())) {
                            filteredlist.add(data);
                        }
                    }
                }
                adData = new AdapterData(getActivity(), filteredlist, listener);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return root;
    }

    public void retrievebrg() {
        APIRequestData ardData = RetroServer.conRetro().create(APIRequestData.class);
        Call<RetrieveResponse> ambil = ardData.retrieveData();

        ambil.enqueue(new Callback<RetrieveResponse>() {
            @Override
            public void onResponse(Call<RetrieveResponse> call, Response<RetrieveResponse> response) {
                listData = response.body().getData();
                adData = new AdapterData(getActivity(), listData, listener);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RetrieveResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}