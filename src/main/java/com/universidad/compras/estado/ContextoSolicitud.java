package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class ContextoSolicitud {
    private final Solicitud solicitud;
    private EstadoSolicitud estadoActual;

    public ContextoSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
        this.estadoActual = mapearEstadoInicial(solicitud.getEstado());
    }

    private EstadoSolicitud mapearEstadoInicial(String estado) {
        if ("APROBADA".equalsIgnoreCase(estado)) return new EstadoAprobada();
        if ("EJECUTADA".equalsIgnoreCase(estado)) return new EstadoEjecutada();
        if ("RECHAZADA".equalsIgnoreCase(estado)) return new EstadoRechazada();
        if ("CANCELADA".equalsIgnoreCase(estado)) return new EstadoCancelada();
        return new EstadoPendiente();
    }

    public void setEstado(EstadoSolicitud nuevoEstado) {
        this.estadoActual = nuevoEstado;
        this.solicitud.setEstado(nuevoEstado.getNombre());
    }

    public Solicitud getSolicitud() { return solicitud; }
    public EstadoSolicitud getEstadoActual() { return estadoActual; }

    public String aprobar() { return estadoActual.aprobar(this); }
    public String rechazar() { return estadoActual.rechazar(this); }
    public String ejecutar() { return estadoActual.ejecutar(this); }
    public String cancelar() { return estadoActual.cancelar(this); }
}