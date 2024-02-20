package com.ensicaen.sepa_vue_2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.R;
import com.ensicaen.sepa_vue_2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
//    private EditText iban,bic,lastName,firstName,currency,amount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Intent intent = new Intent(getActivity(), AccueilActivity.class);

        String welcome = getString(R.string.welcome) + intent.getStringExtra("lastName");
//        iban = binding.editTextIban;
//        bic = binding.editTextBic;
//        lastName = binding.editTextName;
//        firstName = binding.editTextSurname;
//        amount = binding.editTextAmount;
//        currency = binding.editTextCurrency;

//        iban.setText(getArguments().getString("iban"));
//        bic.setText(getArguments().getString("bic"));
//        currency.setText(getArguments().getString("currency"));
//        lastName.setText(getArguments().getString("lastName"));
//        firstName.setText(getArguments().getString("firstName"));
//        amount.setText(getArguments().getString("amount"));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}