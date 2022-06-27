package com.example.organizzeleo.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;

    public static FirebaseAuth getAutenticacao() {

        if (autenticacao == null ){
            autenticacao = FirebaseAuth.getInstance();
        }


        return autenticacao;
    }
}
