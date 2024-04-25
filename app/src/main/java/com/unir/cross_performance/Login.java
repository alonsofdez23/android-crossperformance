package com.unir.cross_performance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.unir.cross_performance.Model.AuthResponse;
import com.unir.cross_performance.Model.LoginRequest;
import com.unir.cross_performance.Utils.ApiClient;
import com.unir.cross_performance.Utils.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText CorreoLogin, ContrasenaLogin;
    Button BotonLogin, BotonLoginReg;
    String correo = "", password = "";

    // Conexión API REST
    ApiClient apiclient = new ApiClient();
    // Request (email, password)
    LoginRequest loginRequest = new LoginRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CorreoLogin = findViewById(R.id.CorreoLogin);
        ContrasenaLogin = findViewById(R.id.ContrasenaLogin);
        BotonLoginReg = findViewById(R.id.BotonLoginReg);
        BotonLogin = findViewById(R.id.BotonLogin);

        BotonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valirDatos();
            }
        });
        BotonLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });
    }

    private void valirDatos() {
        correo = CorreoLogin.getText().toString();
        password = ContrasenaLogin.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Introduzca Contraseña", Toast.LENGTH_SHORT).show();
        } else {
            // Prepara loginRequest
            loginRequest.setEmail(correo);
            loginRequest.setPassword(password);

            LoginUsuarios();
        }
    }

    private void LoginUsuarios() {
        apiclient.loginUser(loginRequest, new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    System.out.println(response.code());
                    System.out.println(authResponse.getToken());

                    // Almacena Bearer token
                    TokenManager.getInstance().setToken(authResponse.getToken());

                    Toast.makeText(Login.this, "Bienvenid@: " + correo, Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(Login.this, MenuAdministradorActivity.class));
                    startActivity(new Intent(Login.this, ClaseLista.class));
                } else {
                    AuthResponse authResponse = response.body();
                    System.out.println(authResponse);
                    System.out.println(response.errorBody());
                    Toast.makeText(Login.this, "No ha introducido sus datos correctamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                System.out.println("Fallo en la solicitud");
                Toast.makeText(Login.this, "Fallo en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
