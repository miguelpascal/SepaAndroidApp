package com.ensicaen.sepa_vue_2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensicaen.sepa_vue_2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView ibantextView = binding.editTextIban;
        homeViewModel.getText().observe(getViewLifecycleOwner(), ibantextView::setText);
        final TextView bictextView = binding.editTextBic;
        final TextView nametextView = binding.editTextName;
        final TextView surnametextView = binding.editTextSurname;
        final TextView amounttextView = binding.editTextAmount;
        bictextView.setText("BNPAFRPPXXX");
        nametextView.setText("Kamdem");
        surnametextView.setText("Pascal Miguel");
        amounttextView.setText("20000");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}