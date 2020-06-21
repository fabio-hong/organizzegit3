package com.meio.organizzegit3.activity.model;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.meio.organizzegit3.activity.config.ConfiguracaoFirebase;
import com.meio.organizzegit3.activity.helper.Base64Custom;

//Terminar idUsuario

public class Usuario {

    private String nome;
    private String email;
    private String senha;

    private String idUsuario;
    private Double despesaTotal = 0.00;
    private Double receitaTotal = 0.00;

    public Usuario() {

    }

    public void salvarUsuario(){
        //Apa
        //Ahá, falei que teria que instanciar antes
        DatabaseReference primeiroNo = ConfiguracaoFirebase.pegarPrimeiroNo();

         primeiroNo.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);


    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

}
