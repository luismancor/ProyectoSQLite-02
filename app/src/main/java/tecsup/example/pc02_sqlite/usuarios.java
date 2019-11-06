package tecsup.example.pc02_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
    }

    public void regresar_onclick(View v) {
        finish();
    }

    public void grabar_onclick( View v ) {
        String xnom = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
        String xape = ((EditText)findViewById(R.id.txtApellido)).getText().toString();
        String xdir = ((EditText)findViewById(R.id.txtDireccion)).getText().toString();
        String xcel = ((EditText)findViewById(R.id.txtCelular)).getText().toString();
        insertarContacto(xnom,xape,xdir,xcel);
        Toast.makeText(v.getContext(),"Grabando registro",Toast.LENGTH_SHORT).show();
    }

    private void insertarContacto(String xnom, String xape, String xdir, String xcel) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NOMBRE, xnom);
        cv.put(DatabaseHelper.APELLIDO, xape);
        cv.put(DatabaseHelper.DIRECCION, xdir);
        cv.put(DatabaseHelper.CELULAR, xcel);
        db.insert("personas", DatabaseHelper.NOMBRE, cv);
        db.close();
    }
}
