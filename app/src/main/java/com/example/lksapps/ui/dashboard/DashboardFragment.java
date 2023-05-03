package com.example.lksapps.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lksapps.R;
import com.example.lksapps.databinding.FragmentDashboardBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView tvNama = root.findViewById(R.id.tv_nama);
        TextView tvTelepon = root.findViewById(R.id.tv_telepon);
        TextView tvAlamat = root.findViewById(R.id.tv_alamat);
        ImageView fotouser = root.findViewById(R.id.img_user);
        Button btnlogout = root.findViewById(R.id.btn_logout);

        SharedPreferences sp = requireActivity().getSharedPreferences("DataUser", Context.MODE_PRIVATE);
        String nama = sp.getString("nama_user", "");
        String alamat = sp.getString("alamat", "");
        String telepon = sp.getString("telepon", "");
        String foto = sp.getString("foto","");


        tvNama.setText(nama);
        tvTelepon.setText(telepon);
        tvAlamat.setText(alamat);
        Picasso.get().load(foto).fit().centerCrop().into(fotouser);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().apply();
                getActivity().finish();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}