package br.com.trabalhoidiomas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class Tela_Login extends AppCompatActivity {
    private GoogleSignInClient googleSignInClient;
    private SignInButton signInButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        signInButton = findViewById(R.id.Id_Bt_Google_Login);
        mAuth = FirebaseAuth.getInstance();
        googleSignInClient gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("")
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirloginGoogle();
            }
        });



    }

    public void abrirloginGoogle() {
        Intent intent = googleSignInClient.getSignInIntent();
        abreActivity.launch(intent);
    }
    ActivityResultLauncher<Intent> abreActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result->{
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent intent = result.getData();

                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                    try {
                        GoogleSignInAccount conta = task.getResult(ApiException.class);
                        Login_Google(conta.getIdToken());
                    }catch (ApiException apiException){
                        Toast.makeText(getApplicationContext(), "Nenhum Usuário Google logado .",
                                Toast.LENGTH_LONG).show();
                        Log.d("Erro: ",apiException.toString());
                    }
                }else if (result.getResultCode() == Activity.RESULT_CANCELED){
                    Toast.makeText(getApplicationContext(), "Nenhum Usuário Google logado .",
                            Toast.LENGTH_LONG).show();
                    Log.d("Erro: ",result.toString());

                }

            }

    );
    private void Login_Google(String token){
        AuthCredential credential = GoogleAuthProvider.getCredential(token,null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(Tela_Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Tela_Login.this, "Usuario Logado com sucesso",
                            Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(Tela_Login.this,Menu.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}