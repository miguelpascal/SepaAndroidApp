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
import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;
import com.ensicaen.sepa_vue_2.databinding.FragmentHistoriqueBinding;


public class HistoriqueFragment extends Fragment {
    private FragmentHistoriqueBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HistoriqueViewModel historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);
        final LoggedInUserModel user = getActivity().getIntent().getParcelableExtra("LoggedInUser");
        historiqueViewModel.chargerHistoriqueVirements(user.getUserId());
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

    public void onResume() {

        super.onResume();
        View root = getView();
        if (root != null) {
            final LoggedInUserModel user = getActivity().getIntent().getParcelableExtra("LoggedInUser");
            HistoriqueViewModel historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);
            RecyclerView recyclerView = root.findViewById(R.id.recyclerViewHistorique);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Ajout du Scrollbar vertical
            recyclerView.setVerticalScrollBarEnabled(true);
            HistoriqueAdapter historiqueAdapter = new HistoriqueAdapter();
            recyclerView.setAdapter(historiqueAdapter);
            historiqueViewModel.getHistoriqueVirements().observe(getViewLifecycleOwner(), historiqueAdapter::setVirements);

        }
    }

}