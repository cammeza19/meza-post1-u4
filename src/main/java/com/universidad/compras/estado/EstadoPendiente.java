package com.universidad.compras.estado;

public class EstadoPendiente implements EstadoSolicitud {
    @Override
    public String aprobar(ContextoSolicitud contexto) {
        contexto.setEstado(new EstadoAprobada());
        return "Solicitud aprobada";
    }

    @Override
    public String rechazar(ContextoSolicitud contexto) {
        contexto.setEstado(new EstadoRechazada());
        return "Solicitud rechazada";
    }

    @Override
    public String ejecutar(ContextoSolicitud contexto) {
        return "Error: debe estar aprobada antes de ejecutarse";
    }

    @Override
    public String cancelar(ContextoSolicitud contexto) {
        contexto.setEstado(new EstadoCancelada());
        return "Solicitud cancelada";
    }

    @Override
    public String getNombre() { return "PENDIENTE"; }
}
