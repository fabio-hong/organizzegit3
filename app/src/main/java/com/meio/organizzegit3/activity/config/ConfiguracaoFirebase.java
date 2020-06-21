package com.meio.organizzegit3.activity.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference primeiroNo;


    public static FirebaseAuth pegarAutenticacao() {
        // ? Esse método não recebe autenticacao como parâmetro, logo acredito que a autenticacao
        //   deve ser atribuida ou instanciada antes de usar esse método

        //Se não existir autenticacao, crie a autenticacao
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }

    public static DatabaseReference pegarPrimeiroNo() {
        // ? Esse método não recebe primeiroNo como parâmetro, logo acredito que o primeiroNo
        //   deve ser atribuido ou instanciado antes de usar esse método

        //? Não sei pq tem que usar FirebaseDatabase

        //Se não existir primeiro nó, pegue o primeiro nó
        if (primeiroNo == null) {
            primeiroNo = FirebaseDatabase.getInstance().getReference();
        }

        return primeiroNo;

    }

}
