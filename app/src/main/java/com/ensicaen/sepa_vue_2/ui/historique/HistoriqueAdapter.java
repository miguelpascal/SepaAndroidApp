package com.ensicaen.sepa_vue_2.ui.historique;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensicaen.sepa_vue_2.R;
import com.ensicaen.sepa_vue_2.data.model.HistoriqueModel;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// HistoriqueAdapter.java
public class HistoriqueAdapter extends RecyclerView.Adapter<HistoriqueAdapter.ViewHolder> {

    private ArrayList<HistoriqueModel> historiqueModels = new ArrayList<>();

    public void setVirements(ArrayList<HistoriqueModel> historiqueModels) {
        this.historiqueModels = historiqueModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historique, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoriqueModel historiqueModel = historiqueModels.get(position);
        holder.bind(historiqueModel);
    }

    @Override
    public int getItemCount() {
        return historiqueModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView dateTextView;
        private final TextView motifTextView;
        private final TextView montantTextView;
        private final TextView destinataireTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            motifTextView = itemView.findViewById(R.id.motifTextView);
            montantTextView = itemView.findViewById(R.id.montantTextView);
            destinataireTextView = itemView.findViewById(R.id.destinataireTextView);
        }

        public void bind(HistoriqueModel historiqueModel) {
            Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.INFO,"Starting populate RecycleView");
            dateTextView.setText("Date: " +historiqueModel.getTransactionDate().toString());
            motifTextView.setText("Motif: " +historiqueModel.getMotif());
            montantTextView.setText("Montant: " +String.valueOf(historiqueModel.getAmount()));
            destinataireTextView.setText("Destinataire: " +historiqueModel.getDestinataire());
        }
    }
}
