package com.universidad.compras.estado;

public class EstadoRechazada implements EstadoSolicitud {
    @Override
    public String aprobar(ContextoSolicitud contexto) { return "Error: no se puede aprobar una solicitud rechazada"; }
    @Override
    public String rechazar(ContextoSolicitud contexto) { return "Error: ya está rechazada"; }
    @Override
    public String ejecutar(ContextoSolicitud contexto) { return "Error: no se puede ejecutar una solicitud RECHAZADA"; }
    @Override
    public String cancelar(ContextoSolicitud contexto) { return "Error: ya está rechazada"; }
    @Override
    public String getNombre() { return "RECHAZADA"; }
}