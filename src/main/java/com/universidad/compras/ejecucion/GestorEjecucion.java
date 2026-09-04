package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GestorEjecucion {
    private final Deque<ComandoEjecucion> historialDeshacer = new ArrayDeque<>();
    private final List<ComandoEjecucion> historialCompleto = new ArrayList<>();

    public void ejecutarComando(ComandoEjecucion comando, Solicitud solicitud) {
        comando.ejecutar();
        historialDeshacer.push(comando);
        historialCompleto.add(comando);
        solicitud.setEstado("EJECUTADA");
    }

    public void deshacerUltimoComando() {
        if (!historialDeshacer.isEmpty()) {
            ComandoEjecucion comando = historialDeshacer.pop();
            comando.deshacer();
        }
    }

    public List<ComandoEjecucion> getHistorial() {
        return new ArrayList<>(historialCompleto);
    }
}