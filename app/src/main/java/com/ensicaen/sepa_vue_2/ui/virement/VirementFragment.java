package com.ensicaen.sepa_vue_2.ui.virement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensicaen.sepa_vue_2.databinding.FragmentVirementBinding;

public class VirementFragment extends Fragment {

    private FragmentVirementBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VirementViewModel galleryViewModel =
                new ViewModelProvider(this).get(VirementViewModel.class);

        binding = FragmentVirementBinding.inflate(inflater, container, false);
        View root = binding.getRoot();





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