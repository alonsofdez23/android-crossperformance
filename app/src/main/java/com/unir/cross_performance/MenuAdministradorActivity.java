package com.unir.cross_performance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.unir.cross_performance.Model.*;
import com.unir.cross_performance.Utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MenuAdministradorActivity extends AppCompatActivity {

    Button CerrarSesion;

    // Conexión API REST
    ApiClient apiclient = new ApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);

        CerrarSesion = findViewById(R.id.CerrarSesion);

        CerrarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //SalirAplicacion();
                listaTareas();
            }
        });
    }

    private void SalirAplicacion() {
        apiclient.logoutUser(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MenuAdministradorActivity.this, "Token borrado correctamente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MenuAdministradorActivity.this, MainActivity.class));
                } else {
                    LogoutResponse logoutResponse = response.body();
                    System.out.println(logoutResponse);
                    System.out.println(response.errorBody());
                    Toast.makeText(MenuAdministradorActivity.this, "Logout ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable throwable) {
                Toast.makeText(MenuAdministradorActivity.this, "Request ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listaTareas() {
        apiclient.getClases(new Callback<List<Clase>>() {
            @Override
            public void onResponse(Call<List<Clase>> call, Response<List<Clase>> response) {
                if (response.isSuccessful()) {
                    List<Clase> clases = response.body();
                    for (Clase clase : clases) {
                        System.out.println(clase.getAtletas());
                        for (Atleta atleta : clase.getAtletas()) {
                            System.out.println(atleta.toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Clase>> call, Throwable throwable) {

            }
        });
    }
}