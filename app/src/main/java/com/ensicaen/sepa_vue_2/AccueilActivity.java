package com.ensicaen.sepa_vue_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.ensicaen.sepa_vue_2.data.model.LoggedInUserModel;
import com.ensicaen.sepa_vue_2.databinding.ActivityAccueilBinding;
import com.ensicaen.sepa_vue_2.ui.home.HomeViewModel;
import com.ensicaen.sepa_vue_2.ui.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AccueilActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityAccueilBinding binding;

    private HomeViewModel homeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String welcome = getString(R.string.welcome) + intent.getStringExtra("lastName");
        EditText iban,bic,lastName,firstName,currency,amount,userId,ibanVir;

        iban = findViewById(R.id.editTextIban);
        ibanVir = findViewById(R.id.editVirTextIban);
        bic = findViewById(R.id.editTextBic);
        lastName = findViewById(R.id.editTextName);
        firstName = findViewById(R.id.editTextSurname);
        amount = findViewById(R.id.editTextAmount);
        currency = findViewById(R.id.editTextCurrency);
        userId = findViewById(R.id.userId);


        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        iban.setText(intent.getStringExtra("iban"));
        bic.setText(intent.getStringExtra("bic"));
        currency.setText(intent.getStringExtra("currency"));
        lastName.setText(intent.getStringExtra("lastName"));
        firstName.setText(intent.getStringExtra("firstName"));
        amount.setText(intent.getStringExtra("amount"));
//        userId.setText(intent.getStringExtra("user_id"));
//        LoggedInUserModel user = new LoggedInUserModel()
//        homeViewModel.setHomeData();
//
//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        homeViewModel.getLoggedInUser().observe(this, user -> {
//            iban.setText(user.getIban());
//            ibanVir.setText(user.getIban());
//            bic.setText(user.getBic());
//            currency.setText(user.getCurrency());
//            lastName.setText(user.getLastName());
//            firstName.setText(user.getFirstName());
//            amount.setText(new DecimalFormat("##.##").format(user.getAmount()));
//            userId.setText(user.getUserId().toString());
//
//        });

        setSupportActionBar(binding.appBarAccueil.toolbar);
        binding.appBarAccueil.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, welcome, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_virement, R.id.nav_historique)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_accueil);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_accueil);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }

}