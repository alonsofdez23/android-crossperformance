package com.unir.cross_performance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unir.cross_performance.Model.AuthResponse;
import com.unir.cross_performance.Model.User;
import com.unir.cross_performance.Utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

    EditText NombreEt,CorreoEt,ContraseñaEt,ConfirmarContaseñaEt;
    Button RegistrarUsuario,TengounacuentaTXT;

    // Conexión API REST
    ApiClient apiclient = new ApiClient();
    // Respuesta autenticación (status, message, token)
    AuthResponse authResponse = new AuthResponse();
    // Usuario a registrar
    User user = new User();

    FirebaseAuth firebaseAuth;

    String nombre = "", correo = "", password = "", confirmarpassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        NombreEt = findViewById(R.id.NombreEt);
        CorreoEt = findViewById(R.id.CorreoEt);
        ContraseñaEt = findViewById(R.id.ContaseñaEt);
        ConfirmarContaseñaEt = findViewById(R.id.ConfirmarContaseñaEt);
        RegistrarUsuario = findViewById(R.id.RegistrarUsuario);
        TengounacuentaTXT = findViewById(R.id.TengounacuentaTXT);

        firebaseAuth = FirebaseAuth.getInstance();

        RegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validacion();
            }
        });

        TengounacuentaTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registro.this, Login.class));
            }
        });
    }

    private void validacion() {
        // Obtener el rol seleccionado
        String rol = obtenerRolSeleccionado();

        nombre = NombreEt.getText().toString();
        correo = CorreoEt.getText().toString();
        password = ContraseñaEt.getText().toString();
        confirmarpassword = ConfirmarContaseñaEt.getText().toString();

        if (TextUtils.isEmpty(nombre) || !Patterns.EMAIL_ADDRESS.matcher(correo).matches() ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmarpassword) ||
                !password.equals(confirmarpassword)) {
            Toast.makeText(this, "Por favor complete todos los campos correctamente", Toast.LENGTH_LONG).show();
            return;
        }

        // Prepara request
        user.setName(nombre);
        user.setEmail(correo);
        user.setPassword(password);

        // Registra usuario
        registerUsuario();
    }

    private void registerUsuario() {
        apiclient.createUser(user, new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    authResponse = response.body();
                    System.out.println(authResponse.toString());
                    Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Registro.this, "Error al registrar el usuario", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }

    private String obtenerRolSeleccionado() {
        RadioGroup radioGroup = findViewById(R.id.roleSelection);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.radioAdmin) {
            return "Administrador";
        } else {
            return "Usuario Normal";
        }
    }

    private void GuardarInformacion() {
        //Obtener la identificación de usuario actual
        String uid = firebaseAuth.getUid();
        String rol = obtenerRolSeleccionado();

        HashMap<String, String> datos = new HashMap<>();
        datos.put("uid", uid);
        datos.put("correo", correo);
        datos.put("nombres", nombre);
        datos.put("password", password);
        datos.put("rol", rol);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");
        databaseReference.child(uid)
                .setValue(datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Registro.this,"Cuenta creada con éxito",Toast.LENGTH_SHORT).show();

                        // Verificar si el usuario es administrador
                        if (rol.equals("Administrador")) {
                            // Si el usuario es administrador, enviarlo a la actividad de administrador
                            startActivity(new Intent(Registro.this, MenuAdministradorActivity.class));
                        } else {
                            // Si el usuario no es administrador, enviarlo a la actividad de usuario normal
                            startActivity(new Intent(Registro.this, MenuPrincipal.class));
                        }
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
