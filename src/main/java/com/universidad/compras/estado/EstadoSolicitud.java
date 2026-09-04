package com.universidad.compras.estado;

public interface EstadoSolicitud {
    String aprobar(ContextoSolicitud contexto);
    String rechazar(ContextoSolicitud contexto);
    String ejecutar(ContextoSolicitud contexto);
    String cancelar(ContextoSolicitud contexto);
    String getNombre();
}