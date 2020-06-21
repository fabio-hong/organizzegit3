package com.meio.organizzegit3.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.meio.organizzegit3.R;
import com.meio.organizzegit3.activity.config.ConfiguracaoFirebase;
import com.meio.organizzegit3.activity.model.Usuario;

//Usuario
//Cadastrar
//Login
//Verificar se já tem conta - ok
//




public class MainActivity extends IntroActivity {

    private ConfiguracaoFirebase autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Usuario objUsuario = new Usuario();

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(R.color.branco)
                .backgroundDark(R.color.branco)
                .fragment(R.layout.slide1, R.style.AppTheme)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.branco)
                .backgroundDark(R.color.branco)
                .fragment(R.layout.ultimo_slide, R.style.AppTheme)
                .build());


    }

    public void cadastrar (View view){

        Intent objIntent = new Intent(this, CadastrarActivity.class);
        startActivity(objIntent);
    }

    public void logar(View view){

        Intent objIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(objIntent);
    }





}