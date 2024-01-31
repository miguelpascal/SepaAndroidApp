package com.ensicaen.sepa_vue_2.ui.historique;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensicaen.sepa_vue_2.R;


public class HistoriqueFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HistoriqueViewModel historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historique, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewHistorique);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ajout du Scrollbar vertical
        recyclerView.setVerticalScrollBarEnabled(true);

        HistoriqueAdapter historiqueAdapter = new HistoriqueAdapter();
        recyclerView.setAdapter(historiqueAdapter);

        historiqueViewModel.getHistoriqueVirements().observe(getViewLifecycleOwner(), historiqueAdapter::setVirements);

        return root;
    }



}