package com.maximoprog.views;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.maximoprog.views.databinding.ActivityGridBinding;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {
    private ActivityGridBinding binding;
    private List<String> names;
    private MyAdapter myAdapter;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGridBinding.inflate(getLayoutInflater());
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
        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Mi nombres : " + names.get(position), Toast.LENGTH_LONG).show();
            }
        });
//        se instancia mi adaptador y se setea a la vista
        this.myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        binding.gridView.setAdapter(this.myAdapter);
//        se registrar un context menu , para que pueda ser visualizado
        registerForContextMenu(binding.gridView);
    }

    //    crea menu - se infla el menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    //    se maneja eventos click en el menu de  opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        cuando se haga clik en determinado elemento
        switch (item.getItemId()) {
            case R.id.add_item:
//                notifica que se actualizado la data
                this.names.add("Nombre: N° " + (counter++));
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //    inflamos layout del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));
        inflater.inflate(R.menu.context_menu, menu);

    }

    //    manejamos eventos click del context menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_item:
//                borramos  el item clickeado
                this.names.remove(info.position);
//notifico los datos
                this.myAdapter.notifyDataSetChanged();
                return true;
            case R.id.show_msg_item:
                Toast.makeText(GridActivity.this, "Un mensaje Ramdom", Toast.LENGTH_SHORT);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}