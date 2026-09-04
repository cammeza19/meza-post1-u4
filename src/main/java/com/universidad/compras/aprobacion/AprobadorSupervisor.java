package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class AprobadorSupervisor extends ManejadorAprobacion {
    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if (solicitud.getMonto() <= 2000000) {
            solicitud.setEstado("APROBADA");
            solicitud.setNivelResolutor("Supervisor de Área");
            return new ResultadoAprobacion(true, "Supervisor de Área", "Aprobado por monto menor o igual a $2.000.000");
        }
        return pasarAlSiguiente(solicitud);
    }
}
