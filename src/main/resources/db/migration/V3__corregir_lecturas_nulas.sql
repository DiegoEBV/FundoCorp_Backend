-- El endpoint POST /api/control/valvula generaba lecturas con campos numéricos en NULL
-- cuando el controlador aún no tenía ninguna lectura previa (p. ej. un controlador recién
-- creado desde el módulo de administración). Se corrige el código en ControlController y
-- aquí se normalizan a 0 los registros que ya hayan quedado con NULL.
UPDATE lectura_sensor
SET humedad = COALESCE(humedad, 0),
    humedad30 = COALESCE(humedad30, 0),
    humedad60 = COALESCE(humedad60, 0),
    humedad90 = COALESCE(humedad90, 0),
    radiacion = COALESCE(radiacion, 0),
    conductividad = COALESCE(conductividad, 0),
    temperatura = COALESCE(temperatura, 0)
WHERE humedad IS NULL OR humedad30 IS NULL OR humedad60 IS NULL OR humedad90 IS NULL
   OR radiacion IS NULL OR conductividad IS NULL OR temperatura IS NULL;
