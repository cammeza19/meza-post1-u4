package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class ObservadorAuditoria implements ObservadorEstadoSolicitud {
    @Override
    public void alCambiarEstado(Solicitud solicitud, String estadoAnterior, String detalle) {
        ClientesNotificacion.registrarAuditoria(
            solicitud.getId(),
            solicitud.getEstado(),
            detalle
        );
    }
}
