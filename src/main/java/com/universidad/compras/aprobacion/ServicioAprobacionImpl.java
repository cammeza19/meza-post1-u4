package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;
import org.springframework.stereotype.Service;

@Service
public class ServicioAprobacionImpl implements ServicioAprobacion {

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        ManejadorAprobacion cadena = construirCadena(solicitud);
        return cadena.evaluar(solicitud);
    }

    public ManejadorAprobacion construirCadena(Solicitud solicitud) {
        ManejadorAprobacion supervisor = new AprobadorSupervisor();
        ManejadorAprobacion gerente = new AprobadorGerente();
        ManejadorAprobacion director = new AprobadorDirectorFinanciero();

        if ("INTERNACIONAL".equalsIgnoreCase(solicitud.getCategoria())) {
            ManejadorAprobacion cumplimiento = new AprobadorCumplimiento();
            cumplimiento.setSiguiente(supervisor);
            supervisor.setSiguiente(gerente);
            gerente.setSiguiente(director);
            return cumplimiento;
        } else {
            supervisor.setSiguiente(gerente);
            gerente.setSiguiente(director);
            return supervisor;
        }
    }
}
