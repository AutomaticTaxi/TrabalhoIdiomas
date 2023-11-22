package FireBase;

import android.content.DialogInterface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

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
                Log.d("FireBase", databaseError.toString());
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

    public void FireBaseSalvar(Object cabelereiras){
        DatabaseReference cabelereirasbd =databaseReference.child("Cabelereiras");
        cabelereirasbd.push().setValue(cabelereiras);

    }

    public List FireBaseLer(){
        DatabaseReference Cabelereiras =  databaseReference.child("Cabelereiras");
        Cabelereiras.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!= null) {
                    Log.i("FireBase", snapshot.getValue().toString());
                    lista.clear();

                    for (DataSnapshot dados : snapshot.getChildren()) {
                        // Log.i("FireBase",dados.child("01").getValue().toString());


                        lista.add(dados.getValue());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("FireBase",error.toString());
            }
        });
        return lista;
    }


}
