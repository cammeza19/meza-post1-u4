package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class AprobadorCumplimiento extends ManejadorAprobacion {
    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if ("INTERNACIONAL".equalsIgnoreCase(solicitud.getCategoria())) {
            solicitud.setEstado("APROBADA");
            solicitud.setNivelResolutor("Revisor de Cumplimiento Normativo");
            return new ResultadoAprobacion(true, "Revisor de Cumplimiento Normativo", "Aprobado por revisión de cumplimiento normativo internacional");
        }
        return pasarAlSiguiente(solicitud);
    }
}