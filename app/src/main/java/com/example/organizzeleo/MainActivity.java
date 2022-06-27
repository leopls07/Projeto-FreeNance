package com.example.organizzeleo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizzeleo.activity.CadastroActivity;
import com.example.organizzeleo.activity.LoginActivity;
import com.example.organizzeleo.activity.PrincipalActivity;
import com.example.organizzeleo.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

        FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_main);

        verificarUsuarioLogado();

        setButtonBackVisible(false);
        setButtonNextVisible(false);
        setButtonCtaVisible(false);



        addSlide(new SimpleSlide.Builder()
                        .title("Bem vindo ao Organizze versão leopls")
                        .description("Feito a mão pelo humilde aprendiz")
                        .image(R.drawable.um)
                        .background(android.R.color.white)
                .build()

        );



        addSlide(new SimpleSlide.Builder()
                .title("Observação")
                .description("Se algo nao funcionar, se vira ")
                .image(R.drawable.dois)
                .background(android.R.color.white)
                .build()
        );

        addSlide(new SimpleSlide.Builder()
                    .title("Tres")
                    .description("treis")
                    .image(R.drawable.tres)
                    .background(android.R.color.white)
                    .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("QUATRO")
                .description("quato")
                .image(R.drawable.quatro)
                .background(android.R.color.white)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(R.color.startColorBackgroumd)
                .fragment(R.layout.pre_login)
                .canGoForward(false)
                .build()

        );



    }

    public void btEntrar(View view){

        startActivity(new Intent(this, LoginActivity.class));

    }

    public void btCadastro(View view){

        Intent intentCadastro = new Intent(this, CadastroActivity.class);

        startActivity(intentCadastro);

    }


    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        if(autenticacao.getCurrentUser() != null){

           // abrirTelaPrincipal();

        }
    }


    public void abrirTelaPrincipal(){

        startActivity(new Intent(this, PrincipalActivity.class));


    }

}
