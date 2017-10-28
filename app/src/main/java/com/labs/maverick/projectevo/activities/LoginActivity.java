package com.labs.maverick.projectevo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.maverick.projectevo.R;
import com.labs.maverick.projectevo.model.Usuario;
import com.labs.maverick.projectevo.services.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextView signupTextView;
    TextView passwordTextView;
    EditText nameEditText;
    EditText passwordEditText;
    Button signInButton;
    Usuario usuario;
    String baseURL = "http://msaldarriaga-001-site7.htempurl.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        nameEditText = (EditText)findViewById(R.id.input_email_login);
        passwordEditText = (EditText)findViewById(R.id.input_password_login);
        signInButton = (Button)findViewById(R.id.btn_signin);
        passwordTextView = (TextView)findViewById(R.id.PasswordtextView);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setLogin(nameEditText.getText().toString());
                usuario.setPassword(passwordEditText.getText().toString());

                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UsuarioService usuarioService = retrofit.create(UsuarioService.class);
                Call<Boolean> respuesta = usuarioService.Login(usuario);
                respuesta.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful()){

                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        nameEditText.setText("");
                        passwordEditText.setText("");
                        Toast.makeText(getApplicationContext(),"No se establecio conección",Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,FogetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signupTextView = (TextView) findViewById(R.id.SignUptextView);
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
