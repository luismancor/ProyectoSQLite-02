package tecsup.example.pc02_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class login extends Activity {

    Button bt_login;
    int id;
    String username;
    String password;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Stetho.initializeWithDefaults(this);
    }

    public void login_Onclick(View view) {
        DatabaseHelper db = new DatabaseHelper(login.this);
        String username = ((EditText)findViewById(R.id.txtUsuario)).getText().toString();
        String password = ((EditText)findViewById(R.id.txtPassword)).getText().toString();

        String tipoDeUsuario =db.tipoUser(username,password);
        Toast.makeText(this,tipoDeUsuario,Toast.LENGTH_SHORT).show();
        String admin = "A";
        String user = "U";
        if (tipoDeUsuario.equalsIgnoreCase(user)){
            Toast.makeText(login.this,"Login de Usuario",Toast.LENGTH_SHORT).show();
            Intent intentLoginAdmin = new Intent(view.getContext(), listadoUsuarios.class);
            startActivity(intentLoginAdmin);
        }else if(tipoDeUsuario.equalsIgnoreCase(admin)){

            Toast.makeText(login.this,"Login de Administrador",Toast.LENGTH_SHORT).show();
            Intent intentLoginUser = new Intent(view.getContext(), usuarios.class);
            startActivity(intentLoginUser);
        }else{
            Toast.makeText(view.getContext(),"No se reconoce usuario",Toast.LENGTH_SHORT).show();
        }

    }

    public void login_Onclick2(View view) {
        DatabaseHelper db = new DatabaseHelper(login.this);
        username = ((EditText)findViewById(R.id.txtUsuario)).getText().toString();
        password = ((EditText)findViewById(R.id.txtPassword)).getText().toString();

        String tipoDeUsuario =db.tipoUser(username,password);
        Toast.makeText(this,tipoDeUsuario,Toast.LENGTH_SHORT).show();

    }


}

