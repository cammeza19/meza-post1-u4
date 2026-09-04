package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;

public class ComandoReservarPresupuesto implements ComandoEjecucion {
    private final PresupuestoService presupuestoService;
    private final Solicitud solicitud;

    public ComandoReservarPresupuesto(PresupuestoService presupuestoService, Solicitud solicitud) {
        this.presupuestoService = presupuestoService;
        this.solicitud = solicitud;
    }

    @Override
    public void ejecutar() {
        presupuestoService.reservar(solicitud.getCentroCosto(), solicitud.getMonto());
    }

    @Override
    public void deshacer() {
        presupuestoService.liberar(solicitud.getCentroCosto(), solicitud.getMonto());
    }
}