package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class ObservadorContabilidad implements ObservadorEstadoSolicitud {
    @Override
    public void alCambiarEstado(Solicitud solicitud, String estadoAnterior, String detalle) {
        ClientesNotificacion.actualizarDashboardContabilidad(
            solicitud.getId(),
            solicitud.getEstado(),
            solicitud.getMonto()
        );
    }
}