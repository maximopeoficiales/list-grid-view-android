package com.maximoprog.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.maximoprog.views.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        datos a mostrar
        names = new ArrayList<String>() {{
            add("Maximo");
            add("Chicho");
            add("Pancho");
            add("Mirtha");
            add("Mirtha");
            add("Mirtha");
            add("Mirtha");
            add("Mirtha");
            add("Mirtha");
            add("Mirtha");
            add("Mirtha");
            add("Mirtha");
        }};
//        Adaptador  por defecto
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
//        se asigna el adaptador
//        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Mi nombres : " + names.get(position), Toast.LENGTH_LONG).show();
            }
        });
//        se instancia mi adaptador y se setea a la vista
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
        binding.listView.setAdapter(myAdapter);

        binding.btnGoToGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GridActivity.class));
            }
        });
    }
}
