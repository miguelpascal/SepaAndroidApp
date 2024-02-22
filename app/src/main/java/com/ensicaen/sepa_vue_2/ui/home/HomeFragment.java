package com.ensicaen.sepa_vue_2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.R;
import com.ensicaen.sepa_vue_2.databinding.FragmentHomeBinding;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private EditText iban,bic,lastName,firstName,currency,amount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Intent intent = new Intent(getActivity(), AccueilActivity.class);

        String welcome = getString(R.string.welcome) + intent.getStringExtra("lastName");

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        EditText iban,bic,lastName,firstName,currency,amount;
        iban = binding.editTextIban;

        bic = binding.editTextBic;
        lastName = binding.editTextName;
        firstName = binding.editTextSurname;
        amount = binding.editTextAmount;
        currency = binding.editTextCurrency;

        homeViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), user -> {
            iban.setText(user.getIban());
            bic.setText(user.getBic());
            currency.setText(user.getCurrency());
            lastName.setText(user.getLastName());
            firstName.setText(user.getFirstName());
            amount.setText(new DecimalFormat("##.##").format(user.getAmount()));

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}