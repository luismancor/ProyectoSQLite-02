package tecsup.example.pc02_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }


    //creados en clase
    public void usuarios_onclick(View v){
        Intent intentUsuarios = new Intent(v.getContext(),
                usuarios.class);
        startActivity(intentUsuarios);
    }

    public void listarUsuarios_onclick(View v){
        Intent intentListarUsuarios = new Intent(v.getContext(),
                listadoUsuarios.class);
        startActivity(intentListarUsuarios);
    }

    public void irA_login_onclick(View v){
        Intent intentLogin = new Intent(v.getContext(),
                login.class);
        startActivity(intentLogin);
    }



}
