package com.unir.cross_performance;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.unir.cross_performance.Model.Atleta;
import com.unir.cross_performance.Model.Clase;
import com.unir.cross_performance.Utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClaseLista extends AppCompatActivity {
    private ListView listViewClases;
    private List<String> listaClases = new ArrayList<>();
    private ArrayAdapter<String> mAdapterClases;

    // Conexión API REST
    ApiClient apiclient = new ApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase_lista);

        listViewClases = findViewById(R.id.listClases);

        actualizarUI();
    }

    private void actualizarUI() {
        //Limpiar lista
        //listaClases.clear();

        apiclient.getClases(new Callback<List<Clase>>() {
            @Override
            public void onResponse(Call<List<Clase>> call, Response<List<Clase>> response) {
                if (response.isSuccessful()) {
                    List<Clase> clases = response.body();
                    // Rellena la lista con los datos
                    for (Clase clase : clases) {
                        System.out.println(clase.toString());

                        //Formateo Date
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String formatDate = formatter.format(clase.getFechaHora());

                        StringBuilder atletasText = new StringBuilder();
                        for (Atleta atleta : clase.getAtletas()) {
                            System.out.println(atleta.toString());
                            atletasText.append(atleta.getName()).append("\n");
                        }

                        //Añade a la lista
                        listaClases.add(formatDate + "\n" + atletasText.toString());
                    }

                    if (!listaClases.isEmpty()) {
                        mAdapterClases = new ArrayAdapter<>(ClaseLista.this, R.layout.item_tarea, R.id.textViewTarea, listaClases);
                        listViewClases.setAdapter(mAdapterClases);
                    } else {
                        listViewClases.setAdapter(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Clase>> call, Throwable throwable) {

            }
        });
    }
}