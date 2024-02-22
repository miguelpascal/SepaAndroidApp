package com.ensicaen.sepa_vue_2.ui.virement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.R;
import com.ensicaen.sepa_vue_2.data.RetrofitService;
import com.ensicaen.sepa_vue_2.data.SepaApi;
import com.ensicaen.sepa_vue_2.data.model.PhoneCredit;
import com.ensicaen.sepa_vue_2.databinding.FragmentVirementBinding;
import com.ensicaen.sepa_vue_2.ui.home.HomeViewModel;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VirementFragment extends Fragment {

    private FragmentVirementBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VirementViewModel galleryViewModel =
                new ViewModelProvider(this).get(VirementViewModel.class);
        binding = FragmentVirementBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        EditText amount = binding.editTextVirAmount;
        EditText motif = binding.editTextMotif;
        EditText phone = binding.editTextPhone;
        EditText ibanOrder = binding.editVirTextIban;
        ProgressBar progressBarVir = binding.progressBarVirement;
        Button btnVir = binding.btnVir;
//        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        homeViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), user -> {
//                    ibanOrder.setText(user.getIban());
//                });
        btnVir.setOnClickListener(v -> {
            double montant = 0.0;
            if (!amount.getText().toString().equals("") && !phone.getText().equals("") && !motif.getText().equals("")){
                montant = Double.parseDouble(amount.getText().toString());
                String motifVirement = String.valueOf(motif.getText());
                String iban = "FR76 3000 1141 0000 0011 0023 136";
                String phoneVirement = String.valueOf(phone.getText());
                PhoneCredit phoneCredit = new PhoneCredit();
                phoneCredit.setAmount(montant);
                phoneCredit.setBenefPhoneNumber(phoneVirement);
                phoneCredit.setMotif(motifVirement);
                phoneCredit.setIbanOrder(iban);
                RetrofitService retrofitService = new RetrofitService();
                SepaApi sepaApi = retrofitService.getRetrofit().create(SepaApi.class);

                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            //set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                        .setTitle("Are you sure to proceed the credit")
    //set message
                        .setMessage("Do you really want to proceed this transaction?")
    //set positive button
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                binding.progressBarVirement.setVisibility(View.VISIBLE);
                                //set what would happen when positive button is clicked
                                sepaApi.creditAccountWithNumber(phoneCredit).enqueue(new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        if (response.isSuccessful()){
                                            Toast.makeText(getActivity(), "Credit transaction success", Toast.LENGTH_LONG).show();
                                            progressBarVir.setVisibility(View.INVISIBLE);
                                            amount.setText("");
                                            motif.setText("");
                                            phone.setText("");

                                        } else {
                                            Toast.makeText(getActivity(), "Credit transaction failed", Toast.LENGTH_LONG).show();
                                            Logger.getLogger(getActivity().toString()).log(Level.SEVERE,response.message());
                                            Logger.getLogger(getActivity().toString()).log(Level.SEVERE,response.errorBody().toString());
                                            progressBarVir.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getActivity(), "Credit transaction failed", Toast.LENGTH_LONG).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Toast.makeText(getActivity(), "Credit transaction failed", Toast.LENGTH_LONG).show();
                                        progressBarVir.setVisibility(View.INVISIBLE);
                                    }
                                });
                            }
                        })
    //set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked
                                Toast.makeText(getActivity(),"Credit Avoid",Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            } else Toast.makeText(getActivity(),"Veuillez remplir tous les champs du formulaire" + ibanOrder.getText(),Toast.LENGTH_LONG).show();
        });



        final TextView textView = binding.textVirement;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}