package tecsup.example.pc02_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class modificar extends AppCompatActivity {

    EditText et_nombre, et_apellido, et_direccion, et_celular;
    Button bt_modificar,bt_eliminar;
    int id;
    String nombre;
    String apellido;
    String direccion;
    String celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle b = getIntent().getExtras();
        if( b != null){
            id = b.getInt("id");
            nombre = b.getString("nombre");
            apellido = b.getString("apellido");
            direccion = b.getString("direccion");
            celular = b.getString("celular");
        }
        //vincular los campos con variables
        et_nombre = (EditText)findViewById(R.id.txtNombre);
        et_apellido = (EditText)findViewById(R.id.txtApellido);
        et_direccion = (EditText)findViewById(R.id.txtDireccion);
        et_celular = (EditText)findViewById(R.id.txtCelular);
        bt_modificar = (Button)findViewById(R.id.btnModificar);
        bt_eliminar = (Button)findViewById(R.id.btnBorrar);

        //Setear los datos en campos de texto
        et_nombre.setText(nombre);
        et_apellido.setText(apellido);
        et_direccion.setText(direccion);
        et_celular.setText(celular);

        //funcion del boton
        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(id,et_nombre.getText().toString(),et_apellido.getText().toString(),et_direccion.getText().toString(),et_celular.getText().toString());
                onBackPressed();
            }
        });

        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar(id);
                onBackPressed();
            }
        });


    }



    //funcion modificar con SQL
    private void modificar (int id, String xnom, String xape, String xdir, String xcel){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql ="UPDATE personas set nombre='" + xnom + "',apellido='" + xape + "',direccion='" + xdir + "',celular='"+ xcel +"',tipo='U' where _id="+id;
        db.execSQL(sql);
        db.close();
    }

    //funcion modificar con SQL
    private void Eliminar (int id){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql ="DELETE FROM  personas where _id="+id;
        db.execSQL(sql);
        db.close();
    }
}
