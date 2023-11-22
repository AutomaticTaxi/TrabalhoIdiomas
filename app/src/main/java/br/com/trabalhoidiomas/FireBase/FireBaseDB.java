package br.com.trabalhoidiomas.FireBase;



import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.trabalhoidiomas.Model.User;

public class FireBaseDB {
    private List<Object> lista;
    DatabaseReference databaseReference;
    public void FireBaseRemover(String nome){
        String Nome = nome;
        DatabaseReference cabelereirasRef = databaseReference.child("Cabelereiras");

        Query query = cabelereirasRef.orderByChild("nome").equalTo(Nome);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String chaveDoObjetoEncontrado = data.getKey();

                    DatabaseReference cabelereiraRefToDelete = cabelereirasRef.child(chaveDoObjetoEncontrado);

                    cabelereiraRefToDelete.setValue(null);

                    break;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Lidar com erros, se necessário
                Log.d("br/com/trabalhoidiomas/FireBase", databaseError.toString());
            }
        });
    }


    public void FireBaseUpdate(String nome,Object cabelereiras ){
        String Nome_da_cabelereiara_Antigo = nome;
        DatabaseReference cabelereirasRef = databaseReference.child("Cabelereiras");

        Query query = cabelereirasRef.orderByChild("nome").equalTo(Nome_da_cabelereiara_Antigo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String chaveDoObjetoEncontrado = data.getKey();

                    DatabaseReference cabelereiraRefToUpdate = cabelereirasRef.child(chaveDoObjetoEncontrado);

                    cabelereiraRefToUpdate.setValue(cabelereiras);


                    break;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Lidar com erros, se necessário
            }
        });


    }

    public void FireBaseSalvarUser(String Uid_User){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(Uid_User);
        User mUser = new User();
        mUser.setUid(user.getUid());
        mUser.setNomeDoUsuario(user.getDisplayName());
        mUser.setEmailDoUsuario(user.getEmail());

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("uid", mUser.getUid());
        userInfo.put("nome", mUser.getNomeDoUsuario());
        userInfo.put("email", mUser.getEmailDoUsuario());
        Query query = usersRef.orderByChild("users").equalTo(Uid_User);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String chaveDoObjetoEncontrado = data.getKey();

                    break;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                usersRef.setValue(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Dados do usuário salvos com sucesso
                                Log.d(TAG, "Dados do usuário salvos com sucesso no Realtime Database");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Falha ao salvar dados do usuário
                                Log.w(TAG, "Falha ao salvar dados do usuário", e);
                            }
                        });
            }
        });


    }

    public List FireBaseLer(){
        DatabaseReference Cabelereiras =  databaseReference.child("Cabelereiras");
        Cabelereiras.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!= null) {
                    Log.i("br/com/trabalhoidiomas/FireBase", snapshot.getValue().toString());
                    lista.clear();

                    for (DataSnapshot dados : snapshot.getChildren()) {
                        // Log.i("FireBase",dados.child("01").getValue().toString());


                        lista.add(dados.getValue());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("br/com/trabalhoidiomas/FireBase",error.toString());
            }
        });
        return lista;
    }


}
