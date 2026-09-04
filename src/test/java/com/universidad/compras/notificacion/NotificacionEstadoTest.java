package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class NotificacionEstadoTest {

    @Test
    void cambiarEstadoDisparaLasTresReaccionesSinLanzarExcepcion() {
        Solicitud s = new Solicitud("S-020", "ana@udes.edu.co", 2500000, "SOFTWARE", "CC-100");
        GestorNotificaciones mecanismo = new GestorNotificaciones();

        mecanismo.suscribir(new ObservadorCorreo());
        mecanismo.suscribir(new ObservadorContabilidad());
        mecanismo.suscribir(new ObservadorAuditoria());

        assertDoesNotThrow(() -> {
            mecanismo.cambiarEstadoYNotificar(s, "APROBADA", "Aprobación automática por prueba");
        });
        assertEquals("APROBADA", s.getEstado());
    }

    @Test
    void agregarUnCuartoSuscriptorDePruebaNoRequiereModificarElMecanismo() {
        Solicitud s = new Solicitud("S-021", "luis@udes.edu.co", 1200000, "MATERIAL_OFICINA", "CC-200");
        GestorNotificaciones mecanismo = new GestorNotificaciones();

        mecanismo.suscribir(new ObservadorCorreo());
        mecanismo.suscribir(new ObservadorContabilidad());
        mecanismo.suscribir(new ObservadorAuditoria());

        AtomicBoolean cuartoSuscriptorNotificado = new AtomicBoolean(false);
        mecanismo.suscribir((solicitud, estadoAnterior, detalle) -> cuartoSuscriptorNotificado.set(true));

        assertDoesNotThrow(() -> {
            mecanismo.cambiarEstadoYNotificar(s, "EJECUTADA", "Ejecución por compra");
        });

        assertTrue(cuartoSuscriptorNotificado.get());
    }
}
