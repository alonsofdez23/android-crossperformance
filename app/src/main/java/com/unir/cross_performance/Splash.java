package com.unir.cross_performance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash extends AppCompatActivity {
//declaramos variable de firebase
   // FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       // firebaseAuth = FirebaseAuth.getInstance();
        
        int Tiempo = 3000;

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run(){
                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();
               // verificarUsuario();
            }
        },Tiempo);
    }

/*
    private void verificarUsuario() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {
            // Si no hay usuario autenticado, llevarlo a la pantalla de inicio de sesión
            startActivity(new Intent(Splash.this, MainActivity.class));
            finish();
        } else {
            DatabaseReference usuariosRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(firebaseUser.getUid());

            usuariosRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Si el usuario está registrado en la base de datos
                        String rol = snapshot.child("rol").getValue(String.class);
                        if (rol != null) {
                            if (rol.equals("administrador")) {
                                // Si el usuario es administrador, llevarlo a la pantalla de administrador
                                startActivity(new Intent(Splash.this, MenuAdministradorActivity.class));
                            } else {
                                // Si el usuario es normal, llevarlo a la pantalla de usuario normal
                                startActivity(new Intent(Splash.this, MenuPrincipal.class));
                            }
                            finish();
                        } else {
                            // Si no se encontró información sobre el rol del usuario, llevarlo a la pantalla principal
                            startActivity(new Intent(Splash.this, MainActivity.class));
                            finish();
                        }
                    } else {
                        // Si el usuario no está registrado en la base de datos, llevarlo a la pantalla principal
                        startActivity(new Intent(Splash.this, MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Manejar el error si la lectura de datos se cancela
                    Toast.makeText(Splash.this, "Error al verificar el usuario", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/

}