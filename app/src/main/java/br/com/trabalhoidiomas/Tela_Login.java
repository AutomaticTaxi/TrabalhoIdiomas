package br.com.trabalhoidiomas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class Tela_Login extends AppCompatActivity {
    GoogleSignInClient googleSignInClient;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cria uma intenção para iniciar a nova Activity
                Intent intent = new Intent(Tela_Login.this, Menu.class);

                // Inicia a nova Activity
                startActivity(intent);
            }
        });
    }
}