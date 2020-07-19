package com.example.shsoftapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    EditText txtUsers, txtPassword;
    Button login;
    ProgressBar progressBar;

    Connection con;
    String un,pass,database,ip;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsers = (EditText) findViewById(R.id.txtUsers);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        login = (Button) findViewById(R.id.btnEntrar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Declaring Server ip, username, database name and password
        ip = "192.168.100.14";
        database = "teste";
        un = "sa";
        pass = "sshhss2009";


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckLogin  checkLogin = new CheckLogin();
                checkLogin.execute();
            }


        });
    }

    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(MainActivity.this , "LOGIN EFETUADO COM SUCESSO" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }

        @Override
        protected String doInBackground(String... params)
        {
            String usernam = txtUsers.getText().toString();
            String passwordd = txtPassword.getText().toString();
            if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                z = "ENTRE COM USUARIO E SENHA";
            else
            {
                try
                {
                    con = connectionclass(un, pass, database, ip);        // Connect to database
                    if (con == null)
                    {
                        z = "VERIFIQUE SUA CONEXÃO";
                    }
                    else
                    {
                        // Change below query according to your own database.
                        String query = "select * from usuario where usuario= '" + usernam.toString() + "' and senha = '"+ passwordd.toString() +"'  ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            z = "LOGIN EFETUADO COM SUCESSO";
                            isSuccess=true;
                            con.close();
                        }
                        else
                        {
                            z = "USUARIO OU SENHA INVALIDOS";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }
    }
    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server +";databasename="+ database + ";user=" + user+ ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}









/*
        public void validar(){
            if(txtUsers.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")){
                Toast.makeText(getApplicationContext(),"LOGIN CORRETO",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"LOGIN INCORRETO",Toast.LENGTH_SHORT).show();
            }
        }



    }




    public Connection conexaoBD(){
        Connection conexao=null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexao= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.100.14;databasename=teste;user=sa;password=sshhss2009;");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexao;
    }
/*
    //insere no banco de dados
    public void inserirNoBD(){
        try {
            PreparedStatement pst=conexaoBD().prepareStatement("insert into usuario values (?,?)");
            pst.setString(1, getTxtUsuario().getText().toString());
            pst.setString(2, getTxtSenha().getText().toString());
            pst.executeQuery();
            Toast.makeText(getApplicationContext(),"REGISTRO GRAVADO",Toast.LENGTH_SHORT).show();

        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }





        // validar usuario
        public void validar(){
        if(txtUsuario.getText().toString().equals("") && txtSenha.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"LOGIN EFETUADO COM SUCESSO",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"LOGIN INCORRETO",Toast.LENGTH_SHORT).show();
        }
    }

    */


