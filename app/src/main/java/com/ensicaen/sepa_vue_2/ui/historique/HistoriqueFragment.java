package com.ensicaen.sepa_vue_2.ui.historique;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensicaen.sepa_vue_2.R;
import com.ensicaen.sepa_vue_2.databinding.FragmentHistoriqueBinding;

public class HistoriqueFragment extends Fragment {

    private FragmentHistoriqueBinding binding;
    private HistoriqueViewModel historiqueViewModel;
    private RecyclerView recyclerView;
    private HistoriqueAdapter historiqueAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historique, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewHistorique);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        historiqueAdapter = new HistoriqueAdapter();
        recyclerView.setAdapter(historiqueAdapter);

        historiqueViewModel.getHistoriqueVirements().observe(getViewLifecycleOwner(), historiqueAdapter::setVirements);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}