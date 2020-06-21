package com.meio.organizzegit3.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.meio.organizzegit3.R;
import com.meio.organizzegit3.activity.config.ConfiguracaoFirebase;
import com.meio.organizzegit3.activity.model.Usuario;

//Verificar se de fato existe uma conta com esse emaiç
    //Verificar se email e senha batem
        //se sim, entrar na activity principal
        //se não, mostrar erro (try catch)


public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    private Usuario objUsuario;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editTextNome);
        campoSenha = findViewById(R.id.editTextSenha);
        buttonLogin = findViewById(R.id.buttonLogin);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            //checar se preencheu email e senha
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {


                        //Toast.makeText(LoginActivity.this, R.string.a, Toast.LENGTH_LONG).show();
                        objUsuario = new Usuario();
                        objUsuario.setSenha(textoSenha);
                        objUsuario.setEmail(textoEmail);

                        validarLogin();

                    } else {
                        Toast.makeText(LoginActivity.this, R.string.preencha_senha, Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, R.string.preencha_email, Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void validarLogin() {

        autenticacao = ConfiguracaoFirebase.pegarAutenticacao();

        autenticacao.signInWithEmailAndPassword(objUsuario.getEmail(), objUsuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Log.i("LOGIN", "SUCESSO");

                    abrirTelaPrincipal();

                } else {

                    try {

                        throw task.getException();

                    } catch (FirebaseAuthInvalidCredentialsException e) {//email e senha nao batem

                        Toast.makeText(LoginActivity.this, R.string.email_senha_nao_batem, Toast.LENGTH_LONG).show();

                    } catch (FirebaseAuthInvalidUserException e) {//nao existe essa conta

                        Toast.makeText(LoginActivity.this, R.string.nao_existe_essa_conta, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {//erros gerais

                        e.printStackTrace();

                    }
                }
            }
        });

    }


    public void abrirTelaPrincipal(){

        Intent objIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
        startActivity(objIntent);


    }
}



