package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;
import java.util.ArrayList;
import java.util.List;

public class GestorNotificaciones {
    private final List<ObservadorEstadoSolicitud> observadores = new ArrayList<>();

    public void suscribir(ObservadorEstadoSolicitud observador) {
        observadores.add(observador);
    }

    public void desuscribir(ObservadorEstadoSolicitud observador) {
        observadores.remove(observador);
    }

    public void cambiarEstadoYNotificar(Solicitud solicitud, String nuevoEstado, String detalle) {
        String estadoAnterior = solicitud.getEstado();
        solicitud.setEstado(nuevoEstado);
        for (ObservadorEstadoSolicitud obs : observadores) {
            obs.alCambiarEstado(solicitud, estadoAnterior, detalle);
        }
    }
}
