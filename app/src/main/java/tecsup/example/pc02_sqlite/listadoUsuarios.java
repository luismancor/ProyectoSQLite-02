package tecsup.example.pc02_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listadoUsuarios extends AppCompatActivity {

    DatabaseHelper myDB = new DatabaseHelper(this);
    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        listView = (ListView) findViewById(R.id.listview2);
        //Cargar listado
        cargarListado();


        //METODO PARA CLICKEAR EN LISTA ITEM Y ENVIAR A MODIFICAR

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id){

                Toast.makeText(listadoUsuarios.this,listado.get(i), Toast.LENGTH_SHORT).show();
                int clave = Integer.parseInt(listado.get(i).split(" ")[0]);
                String nombre = listado.get(i).split(" ")[1];
                String apellido = listado.get(i).split(" ")[2];
                String direccion = listado.get(i).split(" ")[3];
                String celular = listado.get(i).split(" ")[4];

                //Enviar valores a la vista mediante intent
                Intent intent = new Intent(listadoUsuarios.this, modificar.class);
                intent.putExtra("id",clave);
                intent.putExtra("nombre",nombre);
                intent.putExtra("apellido",apellido);
                intent.putExtra("direccion",direccion);
                intent.putExtra("celular",celular);
                startActivity(intent);

            }
        });




        //MOSTRAR EL BOTON DE REGRESAR ATRAS
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    //FUNCION DE CARGAR LISTADO LUEGO DE MODIFICAR
    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarListado();
    }

    //FUNCION PARA REGRESAR EN EL MENU
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //FUNCIONES DE VER LISTADO
    private void cargarListado(){
        listado = ListaUsuarios();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
        //Hace uso de lista usuarios
    }

    //Funcion de listar usuarios
    private ArrayList<String> ListaUsuarios(){
        ArrayList<String>datos = new ArrayList<>();
        Cursor c = myDB.obtenerListaPersonas();


        if (c.getCount() == 0){
            Toast.makeText(listadoUsuarios.this,"La DB esta vacia",Toast.LENGTH_SHORT).show();
        }else{
            //en caso de que si haya registros
            if(c.moveToFirst()){
                do{
                    String linea = c.getInt(0)+" "+c.getString(1)+" "+ c.getString(2)+" "+ c.getString(3)+" "+ c.getString(4);
                    datos.add(linea);
                }while (c.moveToNext());
            }

        }
        myDB.close();
        return datos;
    }



//FUNCIONES DE CREAR USUARIO





}
