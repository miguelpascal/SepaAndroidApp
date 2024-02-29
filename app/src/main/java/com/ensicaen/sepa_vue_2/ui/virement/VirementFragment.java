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
import androidx.lifecycle.ViewModelProvider;


import com.ensicaen.sepa_vue_2.exceptions.ErrorHandlingSepaException;
import com.ensicaen.sepa_vue_2.service.RetrofitService;
import com.ensicaen.sepa_vue_2.api.SepaApi;
import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;
import com.ensicaen.sepa_vue_2.data.model.PhoneCredit;
import com.ensicaen.sepa_vue_2.databinding.FragmentVirementBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
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
        final LoggedInUserModel user = getActivity().getIntent().getParcelableExtra("LoggedInUser");
        View root = binding.getRoot();
        EditText amount = binding.editTextVirAmount;
        EditText motif = binding.editTextMotif;
        EditText phone = binding.editTextPhone;
        EditText ibanOrder = binding.editVirTextIban;
        ProgressBar progressBarVir = binding.progressBarVirement;
        Button btnVir = binding.btnVir;
        btnVir.setOnClickListener(v -> {
            double montant;
            if (!amount.getText().toString().equals("") && !phone.getText().toString().equals("") && !motif.getText().toString().equals("")) {
                montant = Double.parseDouble(amount.getText().toString());
                String motifVirement = String.valueOf(motif.getText());
                String iban = user.getIban();
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
                        .setTitle("VIREMENT")
                        //set message
                        .setMessage("Voulez vous effectuer ce virement?")
                        //set positive button
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                binding.progressBarVirement.setVisibility(View.VISIBLE);
                                //set what would happen when positive button is clicked
                                sepaApi.creditAccountWithNumber(phoneCredit).enqueue(new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        String message = "";
                                        Gson gson = new GsonBuilder().create();
                                        ErrorHandlingSepaException exception = new ErrorHandlingSepaException();
                                        if (response.isSuccessful()) {
                                            message ="Virement effectué avec succès";
                                            progressBarVir.setVisibility(View.INVISIBLE);
                                            amount.setText("");
                                            motif.setText("");
                                            phone.setText("");

                                        } else {

                                            // error case
                                            switch (response.code()) {
                                                case 404:
                                                    Toast.makeText(getActivity(), " Request not found", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case 500:
                                                    try {
                                                        exception = gson.fromJson(response.errorBody().string(), ErrorHandlingSepaException.class);
                                                        message = exception.getErrorMessage();
                                                        Toast.makeText(getActivity(), exception.getErrorMessage(), Toast.LENGTH_SHORT).show();
                                                    } catch (IOException e) {
                                                        throw new RuntimeException(e);
                                                    }

                                                    Logger.getLogger(getActivity().toString()).log(Level.SEVERE,response.message());
                                                    Logger.getLogger(getActivity().toString()).log(Level.SEVERE,response.errorBody().toString());
                                                    break;
                                                default:
                                                    Toast.makeText(getActivity(), "Unknown error", Toast.LENGTH_SHORT).show();
                                                    break;
                                            }
                                        }
                                        progressBarVir.setVisibility(View.INVISIBLE);
                                        notification(message);

                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Toast.makeText(getActivity(), "Server Unavailable, please verify your network", Toast.LENGTH_LONG).show();
                                        progressBarVir.setVisibility(View.INVISIBLE);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                            //set what should happen when negative button is clicked
                            Toast.makeText(getActivity(),"Virement annulé",Toast.LENGTH_LONG).show();
                        })
                        .show();
            } else Toast.makeText(getActivity(),"Veuillez remplir tous les champs du formulaire" + ibanOrder.getText(),Toast.LENGTH_LONG).show();
        });



        final TextView textView = binding.textVirement;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void notification(String message){
        View root = binding.getRoot();
        Snackbar snackbar = Snackbar.make(root,message,Snackbar.LENGTH_LONG);
        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE);
        snackbar.show();
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}