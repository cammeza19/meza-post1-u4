package com.universidad.compras.estado;

public class EstadoEjecutada implements EstadoSolicitud {
    @Override
    public String aprobar(ContextoSolicitud contexto) { return "Error: ya fue ejecutada"; }
    @Override
    public String rechazar(ContextoSolicitud contexto) { return "Error: ya fue ejecutada"; }
    @Override
    public String ejecutar(ContextoSolicitud contexto) { return "Error: ya fue ejecutada"; }
    @Override
    public String cancelar(ContextoSolicitud contexto) { return "Error: ya fue ejecutada"; }
    @Override
    public String getNombre() { return "EJECUTADA"; }
}
