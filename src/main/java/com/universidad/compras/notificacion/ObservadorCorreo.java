package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class ObservadorCorreo implements ObservadorEstadoSolicitud {
    @Override
    public void alCambiarEstado(Solicitud solicitud, String estadoAnterior, String detalle) {
        ClientesNotificacion.enviarCorreo(
            solicitud.getSolicitanteEmail(),
            "Cambio de estado en solicitud " + solicitud.getId(),
            "Su solicitud pasó de " + estadoAnterior + " a " + solicitud.getEstado()
        );
    }
}