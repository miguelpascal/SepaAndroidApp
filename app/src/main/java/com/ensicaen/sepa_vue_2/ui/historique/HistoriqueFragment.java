package com.ensicaen.sepa_vue_2.ui.historique;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.R;
import com.ensicaen.sepa_vue_2.databinding.FragmentHistoriqueBinding;
import com.ensicaen.sepa_vue_2.databinding.FragmentVirementBinding;


import java.util.logging.Level;
import java.util.logging.Logger;


public class HistoriqueFragment extends Fragment {
    private FragmentHistoriqueBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HistoriqueViewModel historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historique, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewHistorique);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ajout du Scrollbar vertical
        recyclerView.setVerticalScrollBarEnabled(true);
        HistoriqueAdapter historiqueAdapter = new HistoriqueAdapter();
        recyclerView.setAdapter(historiqueAdapter);
        //Intent intent = new Intent(getContext(),AccueilActivity.class);
        //String user_id = intent.getStringExtra("user_id");
        //Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.INFO,"This user ID: "+ user_id);
        historiqueViewModel.getHistoriqueVirements().observe(getViewLifecycleOwner(), historiqueAdapter::setVirements);

        return root;
    }

}