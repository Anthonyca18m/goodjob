package com.example.goodjob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageAdminActivitiesActivity extends AppCompatActivity {

    private Button btn_Aceptar, btnRechazar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_admin_activities);


        btn_Aceptar = (Button) findViewById(R.id.btnAceptarActividadAdmin);
        btnRechazar = (Button) findViewById(R.id.btnRechazarActividadAdmin);

        btn_Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder preguntaSeguridad = new AlertDialog.Builder(ManageAdminActivitiesActivity.this);

                preguntaSeguridad.setMessage("¿Está seguro de Aceptar la publicación?")
                        .setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog  tittle =   preguntaSeguridad.create();
                tittle.setTitle("Pregunta de Seguridad");
                tittle.show();
            }
        });


    }
}
