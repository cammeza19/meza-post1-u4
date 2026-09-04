package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public interface ObservadorEstadoSolicitud {
    void alCambiarEstado(Solicitud solicitud, String estadoAnterior, String detalle);
}
