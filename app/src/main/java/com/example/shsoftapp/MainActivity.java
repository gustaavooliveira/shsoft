package com.example.shsoftapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;


public class MainActivity extends AppCompatActivity {

    EditText txtUsuario,txtSenha;
    Button btnEntrar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText user = ((EditText) findViewById(R.id.txtUsuario));
        EditText password =((EditText)findViewById(R.id.txtSenha));
        btnEntrar=(Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               validar();
            }
        });



    }

    public void validar(){
        if(txtUsuario.getText().toString().equals("") && txtSenha.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"LOGIN INCORRETO",Toast.LENGTH_SHORT).show();
        }else{
                Toast.makeText(getApplicationContext(),"XXX",Toast.LENGTH_SHORT).show();
            }
        }




    public Connection conexaoBD(){
        Connection conexao=null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexao= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.70;databasename=ROSEFEIJAO;user=sa;password=sshhss2009;");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexao;
    }

    //insere no banco de dados
    /*public void inserirNoBD(){
        try {
            PreparedStatement pst=conexaoBD().prepareStatement("insert into acusuarios2 values (?,?)");
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






}