package com.meio.organizzegit3.activity.helper;

import android.util.Base64;

public class Base64Custom {

    public static String codificar(String texto){
        // ? Não sei pq tem que ser static

        byte[] textoEmArrayDeBytes = texto.getBytes();

        String textoCodificado = Base64.encodeToString(textoEmArrayDeBytes, Base64.DEFAULT)
                .replaceAll("\\n|\\r","");

        return textoCodificado;
    }


    public static String decodificar(String texto){
        // ? Não sei pq tem que ser static

        byte[] textoEmArrayDeBytes = texto.getBytes();

        byte[] textoDecodificadoEmByte = Base64.decode(textoEmArrayDeBytes,Base64.DEFAULT);


        //Transformar byte em string
        String textoDecodificadoEmString = new String(textoDecodificadoEmByte);

        return textoDecodificadoEmString;
    }

}
