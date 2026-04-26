// FILE: app/src/main/java/com/touchevents/TouchCanvasView.java
package com.touchevents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;

public class Lienzo extends View {

    // Caminos activos indexados por ID de puntero
    private final SparseArray<Path> caminosActivos = new SparseArray<>();
    private final SparseArray<Integer> coloresPuntero = new SparseArray<>();
    private final SparseArray<Float> grosoresPuntero = new SparseArray<>();

    // Caminos completados para persistencia
    private final List<Path> caminosCompletados = new ArrayList<>();
    private final List<Integer> coloresCompletados = new ArrayList<>();
    private final List<Float> grosoresCompletados = new ArrayList<>();

    // Pincel para dibujar caminos
    private final Paint pincelTrazo;

    // Color y grosor de dibujo actual
    private int colorActual = Color.parseColor("#FF6B6B");
    private float grosorTrazoActual = 8f;

    public Lienzo(Context contexto) {
        super(contexto);
        pincelTrazo = iniciarPincel();
    }

    public Lienzo(Context contexto, AttributeSet atributos) {
        super(contexto, atributos);
        pincelTrazo = iniciarPincel();
    }

    public Lienzo(Context contexto, AttributeSet atributos, int estiloDefecto) {
        super(contexto, atributos, estiloDefecto);
        pincelTrazo = iniciarPincel();
    }

    private Paint iniciarPincel() {
        Paint pincel = new Paint();
        pincel.setAntiAlias(true);
        pincel.setStrokeWidth(8f);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeCap(Paint.Cap.ROUND);
        pincel.setStrokeJoin(Paint.Join.ROUND);
        pincel.setColor(colorActual);
        return pincel;
    }

    public void setGrosorTrazo(float grosor) {
        this.grosorTrazoActual = grosor;
        invalidate();
    }

    public void setColorActual(int color) {
        this.colorActual = color;
    }

    public int getColorActual() {
        return colorActual;
    }

    public void limpiarLienzo() {
        caminosActivos.clear();
        coloresPuntero.clear();
        grosoresPuntero.clear();
        caminosCompletados.clear();
        coloresCompletados.clear();
        grosoresCompletados.clear();
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas lienzo) {
        super.onDraw(lienzo);

        // Dibujar fondo
        lienzo.drawColor(Color.parseColor("#1A1A2E"), PorterDuff.Mode.SRC);

        // Dibujar caminos completados
        for (int i = 0; i < caminosCompletados.size(); i++) {
            pincelTrazo.setColor(coloresCompletados.get(i));
            pincelTrazo.setStrokeWidth(grosoresCompletados.get(i));
            lienzo.drawPath(caminosCompletados.get(i), pincelTrazo);
        }

        // Dibujar caminos activos
        for (int i = 0; i < caminosActivos.size(); i++) {
            int idPuntero = caminosActivos.keyAt(i);
            Path camino = caminosActivos.valueAt(i);
            int color = coloresPuntero.get(idPuntero);
            float grosor = grosoresPuntero.get(idPuntero);
            pincelTrazo.setColor(color);
            pincelTrazo.setStrokeWidth(grosor);
            lienzo.drawPath(camino, pincelTrazo);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent evento) {
        int accion = evento.getActionMasked();
        int conteoPunteros = evento.getPointerCount();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                int indicePuntero = obtenerIndicePunteroAccion(evento);
                int idPuntero = evento.getPointerId(indicePuntero);
                float x = evento.getX(indicePuntero);
                float y = evento.getY(indicePuntero);

                // Iniciar nuevo camino
                Path nuevoCamino = new Path();
                nuevoCamino.moveTo(x, y);
                caminosActivos.put(idPuntero, nuevoCamino);

                // Asignar color y grosor actual a este puntero
                coloresPuntero.put(idPuntero, colorActual);
                grosoresPuntero.put(idPuntero, grosorTrazoActual);

                // Dibujar círculo marcador en el punto de contacto
                pincelTrazo.setColor(colorActual);
                pincelTrazo.setStrokeWidth(grosorTrazoActual);
                pincelTrazo.setStyle(Paint.Style.FILL);
                invalidate();
                pincelTrazo.setStyle(Paint.Style.STROKE);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                for (int i = 0; i < conteoPunteros; i++) {
                    int idPuntero = evento.getPointerId(i);
                    float x = evento.getX(i);
                    float y = evento.getY(i);

                    Path camino = caminosActivos.get(idPuntero);
                    if (camino != null) {
                        camino.lineTo(x, y);
                    }
                }
                invalidate();
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                int indicePuntero = obtenerIndicePunteroAccion(evento);
                int idPuntero = evento.getPointerId(indicePuntero);
                float x = evento.getX(indicePuntero);
                float y = evento.getY(indicePuntero);

                Path camino = caminosActivos.get(idPuntero);
                if (camino != null) {
                    // Mover a la posición final para asegurar que la línea termine en el punto de contacto
                    camino.lineTo(x, y);
                    // Transferir a caminos completados
                    caminosCompletados.add(camino);
                    coloresCompletados.add(coloresPuntero.get(idPuntero));
                    grosoresCompletados.add(grosoresPuntero.get(idPuntero));
                    // Eliminar de activos
                    caminosActivos.remove(idPuntero);
                    coloresPuntero.remove(idPuntero);
                    grosoresPuntero.remove(idPuntero);
                }
                invalidate();
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                caminosActivos.clear();
                coloresPuntero.clear();
                grosoresPuntero.clear();
                invalidate();
                break;
            }
        }

        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    private int obtenerIndicePunteroAccion(MotionEvent evento) {
        int accion = evento.getAction();
        return (accion & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
    }
}
