package com.universidad.compras.estado;

public class EstadoAprobada implements EstadoSolicitud {
    @Override
    public String aprobar(ContextoSolicitud contexto) {
        return "Error: la solicitud ya está aprobada";
    }

    @Override
    public String rechazar(ContextoSolicitud contexto) {
        return "Error: una solicitud aprobada no se puede rechazar directamente";
    }

    @Override
    public String ejecutar(ContextoSolicitud contexto) {
        contexto.setEstado(new EstadoEjecutada());
        return "Ejecutada";
    }

    @Override
    public String cancelar(ContextoSolicitud contexto) {
        contexto.setEstado(new EstadoCancelada());
        return "Solicitud cancelada";
    }

    @Override
    public String getNombre() { return "APROBADA"; }
}