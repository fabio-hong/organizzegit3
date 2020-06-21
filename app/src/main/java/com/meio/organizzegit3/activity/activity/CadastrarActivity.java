package com.meio.organizzegit3.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.meio.organizzegit3.R;
import com.meio.organizzegit3.activity.config.ConfiguracaoFirebase;
import com.meio.organizzegit3.activity.helper.Base64Custom;
import com.meio.organizzegit3.activity.model.Usuario;

public class CadastrarActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    private DatabaseReference primeiroNo;
    private Usuario objUsuario;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        getSupportActionBar().setTitle("Cadastro");

        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.editTextEmail);
        campoSenha = findViewById(R.id.editTextSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {

                            objUsuario = new Usuario();
                            objUsuario.setNome(textoNome);
                            objUsuario.setEmail(textoEmail);
                            objUsuario.setSenha(textoSenha);

                            cadastrarUsuario();

                        } else {
                            Toast.makeText(CadastrarActivity.this, R.string.toast_senha, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CadastrarActivity.this, R.string.toast_email, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CadastrarActivity.this, R.string.toast_nome, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.pegarAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                objUsuario.getEmail(), objUsuario.getSenha()
                ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            String emailUsuario = objUsuario.getEmail();
                            String idUsuario = Base64Custom.codificar(emailUsuario);
                            objUsuario.setIdUsuario(idUsuario);

                            //Mostrar idUsuario - ta ok
                            //Log.i("idUsuario",idUsuario);

                            objUsuario.salvarUsuario();

                            finish();


                        } else {

                            String excecao = "";

                            try {

                                throw task.getException();

                            } catch (FirebaseAuthWeakPasswordException e) {

                                excecao = getString(R.string.senhafraca);

                            } catch (FirebaseAuthInvalidCredentialsException e) {

                                excecao = getString(R.string.email_invalido);

                            } catch (FirebaseAuthUserCollisionException e) {

                                excecao = getString(R.string.ja_existe);

                            } catch (Exception e) {

                                excecao = e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(CadastrarActivity.this, excecao, Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

}