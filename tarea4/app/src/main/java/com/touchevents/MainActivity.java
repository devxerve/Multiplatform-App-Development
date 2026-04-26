// FILE: app/src/main/java/com/touchevents/MainActivity.java
package com.touchevents;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tuapp.touchevents.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MainActivity extends AppCompatActivity {

    private Lienzo lienzo;
    private MaterialButton botonLimpiar;
    private MaterialButton botonColor;
    private android.widget.SeekBar barraGrosor;
    private TextView textoValorGrosor;

    // Paleta de 8 colores
    private final int[] paletaColores = {
            Color.parseColor("#FF6B6B"), // Rojo
            Color.parseColor("#FF9F43"), // Naranja
            Color.parseColor("#FECA57"), // Amarillo
            Color.parseColor("#1DD1A1"), // Verde
            Color.parseColor("#48DBFB"), // Azul Claro
            Color.parseColor("#5F27CD"), // Morado
            Color.parseColor("#FF9FF3"), // Rosa
            Color.parseColor("#FFFFFF")  // Blanco
    };

    // Nombres de colores para el diálogo
    private final String[] nombresColores = {
            "Rojo", "Naranja", "Amarillo", "Verde",
            "Azul Claro", "Morado", "Rosa", "Blanco"
    };

    // Índice de color actual
    private int indiceColorActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarVistas();
        configurarEscuchadores();
    }

    private void iniciarVistas() {
        lienzo = findViewById(R.id.touchCanvasView);
        botonLimpiar = findViewById(R.id.clearButton);
        botonColor = findViewById(R.id.colorButton);
        barraGrosor = findViewById(R.id.strokeSeekBar);
        textoValorGrosor = findViewById(R.id.strokeValueText);

        // Establecer progreso inicial de la barra
        barraGrosor.setProgress(8);
        textoValorGrosor.setText("8px");

        // Establecer color inicial
        actualizarBotonColor(paletaColores[0]);
        lienzo.setColorActual(paletaColores[0]);
    }

    private void configurarEscuchadores() {
        // Botón limpiar - limpia el lienzo
        botonLimpiar.setOnClickListener(v -> lienzo.limpiarLienzo());

        // Botón color - muestra el diálogo de selección de color
        botonColor.setOnClickListener(v -> mostrarDialogoSeleccionColor());

        // Barra de grosor - se actualiza en tiempo real
        barraGrosor.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progreso, boolean desdeUsuario) {
                if (desdeUsuario) {
                    float ancho = progreso;
                    lienzo.setGrosorTrazo(ancho);
                    textoValorGrosor.setText(ancho + "px");
                }
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });
    }

    private void mostrarDialogoSeleccionColor() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Seleccionar Color")
                .setItems(nombresColores, (dialog, which) -> {
                    int colorSeleccionado = paletaColores[which];
                    indiceColorActual = which;
                    lienzo.setColorActual(colorSeleccionado);
                    actualizarBotonColor(colorSeleccionado);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void actualizarBotonColor(int color) {
        botonColor.setBackgroundColor(color);
        // Establecer tinte del icono a blanco como se solicitó
        botonColor.setIconTint(android.content.res.ColorStateList.valueOf(Color.WHITE));
    }
}
