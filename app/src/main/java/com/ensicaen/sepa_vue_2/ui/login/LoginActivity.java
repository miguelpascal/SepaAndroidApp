package com.ensicaen.sepa_vue_2.ui.login;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ensicaen.sepa_vue_2.AccueilActivity;
import com.ensicaen.sepa_vue_2.R;
import com.ensicaen.sepa_vue_2.data.Result;
import com.ensicaen.sepa_vue_2.data.RetrofitService;
import com.ensicaen.sepa_vue_2.data.SepaApi;
import com.ensicaen.sepa_vue_2.data.model.LoggedInUser;
import com.ensicaen.sepa_vue_2.databinding.ActivityLoginBinding;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    Log.d(TAG,"onActivityResult: ");
                }
            }

    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(usernameEditText.getText().toString(),
//                            passwordEditText.getText().toString());
                }
                return false;
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                Logger.getLogger("Demarrage du login");
                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Demarrage de la requête "+ usernameEditText.getText().toString());
                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO,"Demarrage de la requête "+ passwordEditText.getText().toString());
                try {
                    // TODO: handle loggedInUser authentication
                    String username = usernameEditText.getText().toString();
                    RetrofitService retrofitService = new RetrofitService();
                    SepaApi sepaApi = retrofitService.getRetrofit().create(SepaApi.class);
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), username);
                    Call<LoggedInUser> callAsync = sepaApi.getUserAccount(body);
                    try {
                        callAsync.enqueue(new Callback<LoggedInUser>() {
                            @Override
                            public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
                                if (response.isSuccessful()) {
                                    // status code 2xx. start welcome activity
                                    LoggedInUser user = new LoggedInUser(response.body().getUserId(), response.body().getLastName(), response.body().getFirstName(), response.body().getIban(), response.body().getBic(), response.body().getAmount(), response.body().getCurrency());
                                    Intent intent = new Intent(getApplicationContext(),AccueilActivity.class);
                                    intent.putExtra("user_id",user.getUserId());
                                    intent.putExtra("iban",user.getIban());
                                    intent.putExtra("bic",user.getBic());
                                    intent.putExtra("currency",user.getCurrency());
                                    intent.putExtra("lastName",user.getLastName());
                                    intent.putExtra("firstName",user.getFirstName());
                                    intent.putExtra("amount",String.valueOf(user.getAmount()));
                                    Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, String.valueOf(user.getAmount()));
                                    activityResultLauncher.launch(intent);

                                } else {
                                    // Recieved a response but not 2xx.
                                    loadingProgressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                                    Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE,"This user does not existed");
                                    // Possibly authentication error. Show error message
                                }
                            }
                            @Override
                            public void onFailure(Call<LoggedInUser> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Server Unavailable", Toast.LENGTH_SHORT).show();
                                loadingProgressBar.setVisibility(View.INVISIBLE);
                                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE,t.toString());
                            }
                        });
                    } catch (RuntimeException ex) {
                        Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, ex.toString());
                    }
                    // TODO: handle loggedInUser Done

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}