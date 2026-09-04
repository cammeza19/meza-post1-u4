package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EjecucionSolicitudTest {

    @Test
    void ejecutarReservaPresupuestoGeneraOrden() {
        Solicitud s = new Solicitud("S-010", "ana@udes.edu.co", 3000000, "SOFTWARE", "CC-100");
        s.setEstado("APROBADA");

        GestorEjecucion ejecutor = new GestorEjecucion();
        PresupuestoService presupuestoService = new PresupuestoService();
        OrdenCompraService ordenCompraService = new OrdenCompraService();

        ejecutor.ejecutarComando(new ComandoReservarPresupuesto(presupuestoService, s), s);
        ejecutor.ejecutarComando(new ComandoGenerarOrdenCompra(ordenCompraService, s, "Proveedor ACME"), s);

        assertEquals("EJECUTADA", s.getEstado());
    }

    @Test
    void deshacerSoloLaUltimaOperacionNoAfectaLaAnterior() {
        Solicitud s = new Solicitud("S-011", "luis@udes.edu.co", 4000000, "MATERIAL_OFICINA", "CC-200");
        s.setEstado("APROBADA");

        GestorEjecucion ejecutor = new GestorEjecucion();
        PresupuestoService presupuestoService = new PresupuestoService();
        OrdenCompraService ordenCompraService = new OrdenCompraService();

        assertDoesNotThrow(() -> {
            ejecutor.ejecutarComando(new ComandoReservarPresupuesto(presupuestoService, s), s);
            ejecutor.ejecutarComando(new ComandoGenerarOrdenCompra(ordenCompraService, s, "Proveedor ACME"), s);
            ejecutor.deshacerUltimoComando();
        });
    }

    @Test
    void elHistorialConservaTodasLasOperacionesNoSoloLaUltima() {
        Solicitud s = new Solicitud("S-012", "ana@udes.edu.co", 2000000, "SOFTWARE", "CC-100");
        s.setEstado("APROBADA");

        GestorEjecucion ejecutor = new GestorEjecucion();
        PresupuestoService presupuestoService = new PresupuestoService();
        OrdenCompraService ordenCompraService = new OrdenCompraService();

        assertDoesNotThrow(() -> {
            ejecutor.ejecutarComando(new ComandoReservarPresupuesto(presupuestoService, s), s);
            ejecutor.ejecutarComando(new ComandoGenerarOrdenCompra(ordenCompraService, s, "Proveedor ACME"), s);
            assertEquals(2, ejecutor.getHistorial().size());
        });
    }
}
