package com.example.organizzeleo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizzeleo.R;
import com.example.organizzeleo.config.ConfiguracaoFirebase;
import com.example.organizzeleo.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome,campoEmail,campoSenha;
    private Button buttonCadastro;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

    campoNome = findViewById(R.id.editNome);
    campoEmail = findViewById(R.id.editEmail);
    campoSenha = findViewById(R.id.editSenha);
    buttonCadastro = findViewById(R.id.buttonCadastrar);



    buttonCadastro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String textoNome = campoNome.getText().toString();
            String textoSenha = campoSenha.getText().toString();
            String textoEmail = campoEmail.getText().toString();

            if( !textoNome.isEmpty()){
                if (!textoEmail.isEmpty()){
                    if (!textoSenha.isEmpty()){


                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setNome(textoNome);
                        usuario.setSenha(textoSenha);
                        cadastrarUsuario();

                    }else{
                        Toast.makeText(
                                CadastroActivity.this,
                                "Preencha os campos primeiro",
                                Toast.LENGTH_SHORT
                        ).show();
                    }



                }else{
                    Toast.makeText(
                            CadastroActivity.this,
                            "Preencha os campos primeiro",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            }else{
             Toast.makeText(
                     CadastroActivity.this,
                     "Preencha os campos primeiro",
                     Toast.LENGTH_SHORT
             ).show();
            }

        }
    });





    }

    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,
                            "Usuario criado com sucesso",
                            Toast.LENGTH_SHORT
                            ).show();
                    finish();

                }else{
                        String exception;
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        exception ="Digite uma senha mais forte!";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        exception = "Email inválido, cheque novamente";
                    }catch (FirebaseAuthUserCollisionException e){
                        exception = "Email já cadastrado, vá para o login";
                    }catch (Exception e){
                        exception = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(
                            CadastroActivity.this,
                            exception,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

}
