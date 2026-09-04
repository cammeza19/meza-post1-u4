package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class AprobadorDirectorFinanciero extends ManejadorAprobacion {
    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        solicitud.setEstado("APROBADA");
        solicitud.setNivelResolutor("Director Financiero");
        return new ResultadoAprobacion(true, "Director Financiero", "Aprobado por dirección financiera sin límite de monto");
    }
}