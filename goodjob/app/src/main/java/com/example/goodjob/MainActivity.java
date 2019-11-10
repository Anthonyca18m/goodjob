package com.example.goodjob;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.goodjob.classes.ValidSession;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private FloatingActionButton publicarActividad;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // boton flotante
        publicarActividad = findViewById(R.id.fabPublicarActividad);
        publicarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidSession.usuarioLogueado != null && ValidSession.usuarioLogueado.getId() == 1)
                    startActivity(new Intent(MainActivity.this, PublicarActividadActivity.class));
                else
                    Toast.makeText(getApplicationContext(), "No puedes realizar esta acción", Toast.LENGTH_LONG).show();
            }
        });

        // setting the initial fragment on app start
        Fragment initialFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, initialFragment).commit();

    }



    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.navigation_premiun:
                    startActivity(new Intent(MainActivity.this, SuscriptionActivity.class));
                    return true;
                case R.id.navigation_estado_mis_actividades:
                    if (ValidSession.usuarioLogueado == null) {
                        cuadroDialogo();
                        return true;
                    }
                    selectedFragment = new PreMyActivitesFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, selectedFragment).commit();
            return true;
        }
    };

    private void cuadroDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.inicio_sesion);
        builder.setMessage(R.string.iniciar_sesion);
        builder.setPositiveButton(R.string.ok_sesion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setMenu(menu);
        SearchView search = getSearchView(menu);
        setSearchViewHint(search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ImgProfile:
                sendToLoginOrProfile();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    }

    private SearchView getSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.buscador);
        return (SearchView) item.getActionView();
    }

    private void setSearchViewHint(SearchView search) {
        search.setQueryHint("Buscar...");
    }

    private void sendToLoginOrProfile(){
        if (ValidSession.usuarioLogueado == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            navigation.setSelectedItemId(R.id.navigation_profile);
        }
    }
}
