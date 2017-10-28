package com.labs.maverick.projectevo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.labs.maverick.projectevo.R;
import com.labs.maverick.projectevo.model.Usuario;
import com.labs.maverick.projectevo.services.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    TextView loginregisterTextView;
    EditText nameEditText;
    EditText passwordEditText;
    Button registerButton;
    Usuario usuario;
    String baseURL = "http://msaldarriaga-001-site7.htempurl.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = (EditText)findViewById(R.id.input_name_register);
        passwordEditText = (EditText)findViewById(R.id.input_password_register);
        loginregisterTextView = (TextView)findViewById(R.id.LoginRegistertextView);
        loginregisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registerButton = (Button)findViewById(R.id.btn_signup);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setLogin(nameEditText.getText().toString());
                usuario.setPassword(passwordEditText.getText().toString());

                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UsuarioService usuarioService = retrofit.create(UsuarioService.class);
                Call<Boolean> respuesta = usuarioService.AddUsers(usuario);
                respuesta.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Se registró correctamente",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"No se puedo Registrar",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });




    }
}
