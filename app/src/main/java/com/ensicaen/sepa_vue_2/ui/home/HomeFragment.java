package com.ensicaen.sepa_vue_2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;
import com.ensicaen.sepa_vue_2.databinding.FragmentHomeBinding;

import java.text.DecimalFormat;
import java.util.logging.Logger;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SepaViewModel sepaViewModel;
    private EditText iban,bic,lastName,firstName,currency, balance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sepaViewModel = new ViewModelProvider(this).get(SepaViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LoggedInUserModel user = getActivity().getIntent().getParcelableExtra("LoggedInUser");
        if (user != null) {
            sepaViewModel.setLoggedInUser(user);
            sepaViewModel.getHistoriqueVirements(user.getUserId());
        }

        iban = binding.editTextIban;
        bic = binding.editTextBic;
        lastName = binding.editTextName;
        firstName = binding.editTextSurname;
        balance = binding.editTextBalance;
        currency = binding.editTextCurrency;

        return root;
    }

    @Override
    public void onResume() {

        sepaViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), user -> {
            Logger.getLogger(HomeFragment.class.getSimpleName()).info("user Iban = "+user.getIban());
            iban.setText(user.getIban());
            bic.setText(user.getBic());
            currency.setText(user.getCurrency());
            lastName.setText(user.getLastName());
            firstName.setText(user.getFirstName());
            balance.setText(new DecimalFormat("##.##").format(user.getBalance()));
        });
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}