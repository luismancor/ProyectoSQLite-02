package tecsup.example.pc02_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    String tabla = ("CREATE TABLE PERSONAS()");

    private static final String DATABASE_NAME = "prueba2.db";

    //DATOS DE TABLA 1
    private static final String TABLE_NAME = "personas";
    private static final int VERSION = 1;
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO = "apellido";
    public static final String DIRECCION = "direccion";
    public static final String CELULAR = "celular";
    public static final String TIPO = "tipo";
    public static final String PASSWORD = "password";

    //Constructor de tabla 1
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creacion de tabla con id y campos
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, direccion TEXT, celular TEXT, tipo TEXT DEFAULT 'A',password TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        android.util.Log.v("prueba2", "actualizando la base de datos, se destruiran los datos existentes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //FUNCION DE BUSQUEDA DE REGISTROS DE PERSONAS
    public Cursor obtenerListaPersonas() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return datos;
    }

    //login
    public boolean esAdmin(int id){
        boolean validacion =false;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor datos = db.rawQuery("SELECT tipo FROM "+TABLE_NAME+" WHERE _id="+id,null);
        String tipo = datos.getString(1);

        if (tipo.equals("A")){
            validacion = true;
            return true;
        }else{
            return false;
        }
    }

    public String tipoUser(String nombre , String password){
        String tipo="";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor datos = db.rawQuery("SELECT tipo FROM "+TABLE_NAME+" WHERE nombre ='"+nombre+"' AND apellido= '"+password+"'",null);

        if (datos.moveToFirst()) {
            tipo  = datos.getString(0);
            db.close();
        }
        return tipo;
    }


    //AGREGAR PERSONAS
    public boolean agregarPersona(String xnom, String xape, String xdir, String xcel, String xtip) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NOMBRE, xnom);
        contentValues.put(APELLIDO, xape);
        contentValues.put(DIRECCION, xdir);
        contentValues.put(CELULAR, xcel);
        contentValues.put(TIPO, xtip);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }





}


