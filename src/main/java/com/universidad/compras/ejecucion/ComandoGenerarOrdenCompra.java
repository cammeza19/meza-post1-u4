package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;

public class ComandoGenerarOrdenCompra implements ComandoEjecucion {
    private final OrdenCompraService ordenCompraService;
    private final Solicitud solicitud;
    private final String proveedor;
    private String numeroOrden;

    public ComandoGenerarOrdenCompra(OrdenCompraService ordenCompraService, Solicitud solicitud, String proveedor) {
        this.ordenCompraService = ordenCompraService;
        this.solicitud = solicitud;
        this.proveedor = proveedor;
    }

    @Override
    public void ejecutar() {
        this.numeroOrden = ordenCompraService.generar(solicitud.getId(), proveedor);
    }

    @Override
    public void deshacer() {
        if (numeroOrden != null) {
            ordenCompraService.cancelar(numeroOrden);
        }
    }
}