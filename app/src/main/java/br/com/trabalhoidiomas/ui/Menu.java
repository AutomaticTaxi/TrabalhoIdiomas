package br.com.trabalhoidiomas.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import br.com.trabalhoidiomas.R;
import br.com.trabalhoidiomas.ui.fragmentos.*;
import br.com.trabalhoidiomas.ui.fragmentos.FragmentHome;


public class Menu extends AppCompatActivity {
    private int IdItem;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Trocarfragmento(new FragmentHome());
        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Faça algo com o ID do item clicado, por exemplo:
                if (itemId == R.id.id_bt_menu_home) {
                    Trocarfragmento(new FragmentHome());

                } else if (itemId == R.id.id_bt_menu_atv) {
                    Trocarfragmento(new FragmentAtividades());

                } else if (itemId == R.id.id_bt_menu_logout) {

                }

                return true; // Retorne true para indicar que o item foi selecionado

            }
        });

    }
    private void Trocarfragmento(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}