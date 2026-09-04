package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public abstract class ManejadorAprobacion {
    protected ManejadorAprobacion siguiente;

    public ManejadorAprobacion setSiguiente(ManejadorAprobacion siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public abstract ResultadoAprobacion evaluar(Solicitud solicitud);

    protected ResultadoAprobacion pasarAlSiguiente(Solicitud solicitud) {
        if (siguiente != null) {
            return siguiente.evaluar(solicitud);
        }
        solicitud.setEstado("RECHAZADA");
        return new ResultadoAprobacion(false, "Ninguno", "Solicitud rechazada: no hubo nivel con autoridad suficiente");
    }
}