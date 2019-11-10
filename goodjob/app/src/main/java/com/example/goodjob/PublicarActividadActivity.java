package com.example.goodjob;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.util.DatePickerFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PublicarActividadActivity extends AppCompatActivity {

    private EditText nombreActividad;
    private EditText descripcionActividad;
    private EditText fechaFin;
    private EditText cantidadParticipantes;
    private EditText recompensa;
    private Button publicarActividad;
    private TextInputLayout tilNombreActividad, tilDescripcionActividad,
            tilFecha, tilParticipantesRequeridos, tilRecompensa;
    private ImageView imagenActividad;
    private Spinner tipoSeleccion, tipoRecompensa;
    private Bitmap bitmap;

    private final int CAMPO_VACIO = 0;
    final int NOMBRE_ACTIVIDAD_TOPE_LONGITUD = 30;
    final int DESCRIPCION_ACTIVIDAD_TOPE_LONGITUD = 140;
    final int PARTICIPANTES_MINIMOS = 2;
    final int REQUEST_CODE_GALLERY = 78;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_actividad);

        mapearCampos();
        establecerAdaptadorSpinnerSeleccion();
        establecerAdaptadorSpinnerRecompensa();
        asignarEventoDeClickParaLaFecha();
        asignarEventoDeClickParaImagen();
        asignarEventoDeClickEnBotonPublicarActividad();
        establecerEventosDeChekeoDeCampos();
    }

    private void mapearCampos() {
        nombreActividad = findViewById(R.id.txtNombreActividad);
        descripcionActividad = findViewById(R.id.txtDescripcionActividad);
        fechaFin = findViewById(R.id.txtFechaFin);
        cantidadParticipantes = findViewById(R.id.txtCantidadIntegrantes);
        recompensa = findViewById(R.id.txtRecompensa);
        publicarActividad = findViewById(R.id.btnPublicar);
        tilNombreActividad = findViewById(R.id.tilNombreActividad);
        tilDescripcionActividad = findViewById(R.id.tilDescripcionActividad);
        tilFecha = findViewById(R.id.tilFecha);
        tilParticipantesRequeridos = findViewById(R.id.tilCantidadIntegrantes);
        imagenActividad = findViewById(R.id.ivImagenActividad);
        tilRecompensa = findViewById(R.id.tilRecompensa);
        tipoSeleccion = findViewById(R.id.spinnerTipoSeleccion);
        tipoRecompensa = findViewById(R.id.spinnerTipoRecompensa);
    }

    private void establecerAdaptadorSpinnerSeleccion() {
        ArrayAdapter<CharSequence> adapterSeleccion =
                ArrayAdapter.createFromResource(this,
                        R.array.tipo_seleccion_array,
                        R.layout.item_spinner_publicar_actividad);
        tipoSeleccion.setAdapter(adapterSeleccion);
    }

    private void establecerAdaptadorSpinnerRecompensa() {
        ArrayAdapter<CharSequence> adapterRecompensa =
                ArrayAdapter.createFromResource(this,
                        R.array.tipo_recompensa_array,
                        R.layout.item_spinner_publicar_actividad);
        tipoRecompensa.setAdapter(adapterRecompensa);
    }

    private void asignarEventoDeClickEnBotonPublicarActividad() {
        publicarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validarDatos()) {
                    return;
                }

                String fin = fechaFin.getText().toString();
                String[] format = fin.split("/");
                final String f_fin = format[2] + "-" + format[1] + "-" + format[0];

                String url = ValidSession.IP_ACTIVITIES_IMAGES + "/ws_publicarActividad.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        System.out.println(response);
                        startActivity(new Intent(PublicarActividadActivity.this, MainActivity.class));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println(error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("foto", imageToString(bitmap));
                        params.put("titulo", nombreActividad.getText().toString());
                        params.put("descripcion", descripcionActividad.getText().toString());
                        params.put("id_usuario", ValidSession.usuarioLogueado.getId().toString());
                        params.put("fecha_fin", f_fin);
                        params.put("participantes_requeridos", cantidadParticipantes.getText().toString());
                        params.put("recompensa", recompensa.getText().toString());
                        return params;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
            }
        });
    }

    private boolean validarDatos() {
        return esNombreActividadValido(nombreActividad.getText().toString())
                && esDescripcionValida(descripcionActividad.getText().toString())
                && esFechaValida(fechaFin.getText().toString())
                && esCantidadParticipantesValida(cantidadParticipantes.getText().toString())
                && esRecompensaValida(recompensa.getText().toString());
    }

    private void establecerEventosDeChekeoDeCampos() {
        nombreActividad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esNombreActividadValido(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        descripcionActividad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esDescripcionValida(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cantidadParticipantes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esCantidadParticipantesValida(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        recompensa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esRecompensaValida(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fechaFin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                esFechaValida(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void asignarEventoDeClickParaLaFecha() {
        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerFragment().show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    private boolean esNombreActividadValido(String nombreActividad) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombreActividad).matches()
                || nombreActividad.length() > NOMBRE_ACTIVIDAD_TOPE_LONGITUD
                || nombreActividad.trim().length() == CAMPO_VACIO) {
            tilNombreActividad.setError("Nombre invalido");
            return false;
        }
        tilNombreActividad.setError(null);
        return true;
    }

    private boolean esDescripcionValida(String descripcionActividad) {
        if (descripcionActividad.length() > DESCRIPCION_ACTIVIDAD_TOPE_LONGITUD) {
            tilDescripcionActividad.setError("Descripción muy larga");
            return false;
        } else if (descripcionActividad.trim().length() == CAMPO_VACIO) {
            tilDescripcionActividad.setError("Ingrese una descripción");
            return false;
        }
        tilDescripcionActividad.setError(null);
        return true;
    }

    private boolean esFechaValida(String fecha) {
        if (fecha.length() == CAMPO_VACIO) {
            tilFecha.setError("Ingrese una fecha");
            return false;
        }
        tilFecha.setError(null);
        return true;
    }

    private boolean esCantidadParticipantesValida(String cantidadParticipantes) {
        if (cantidadParticipantes.trim().length() == CAMPO_VACIO) {
            tilParticipantesRequeridos.setError("Ingrese cantidad de participantes");
            return false;
        } else if (!cantidadParticipantes.trim().matches("^[0-9]+$")) {
            tilParticipantesRequeridos.setError("Solo se admiten números");
            return false;
        } else if (Integer.valueOf(cantidadParticipantes) < PARTICIPANTES_MINIMOS) {
            tilParticipantesRequeridos.setError("El mínimo de participantes es 2");
            return false;
        }
        tilParticipantesRequeridos.setError(null);
        return true;
    }

    private boolean esRecompensaValida(String recompensa) {
        if (recompensa.trim().length() == CAMPO_VACIO) {
            tilRecompensa.setError("Ingrese recompensa");
            return false;
        } else if (!recompensa.trim().matches("^[0-9]+$")) {
            tilRecompensa.setError("Ingrese una recompensa valida");
            return false;
        }
        tilRecompensa.setError(null);
        return true;
    }

    private void asignarEventoDeClickParaImagen() {
        imagenActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarPermisoLectura();
            }
        });
    }

    private void solicitarPermisoLectura() {
        ActivityCompat.requestPermissions(PublicarActividadActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                Toast.makeText(this, "Permisos no concedidos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            try {
                setearImagenSeleccionada(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setearImagenSeleccionada(Intent data) throws FileNotFoundException {
        InputStream input = getContentResolver().openInputStream(data.getData());
        bitmap = BitmapFactory.decodeStream(input);
        imagenActividad.setImageBitmap(bitmap);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imgBytes = baos.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
