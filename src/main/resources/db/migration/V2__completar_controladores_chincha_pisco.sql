-- El seed inicial (DataSeeder) asignó por error el gateway "Límite Norte Pisco" al Fundo
-- Chincha Sur en vez de Fundo Pisco Alto, y no creó ningún controlador para los gateways
-- de Chincha Sur ni Pisco Alto. Esto hacía que esos fundos aparecieran sin controladores
-- activos en el Monitoreo IoT del frontend.

UPDATE gateway
SET id_fundo = (SELECT id_fundo FROM fundo WHERE nombre = 'Fundo Pisco Alto')
WHERE ubicacion = 'Límite Norte Pisco';

INSERT INTO controlador (id_gateway, nombre, modelo, ubicacion)
SELECT id_gateway, 'Controlador Válvula Sector 4', 'Milesight UC511', 'Válvula Sector 4 - Chincha Lote A'
FROM gateway WHERE ubicacion = 'Sector A Chincha';

INSERT INTO controlador (id_gateway, nombre, modelo, ubicacion)
SELECT id_gateway, 'Bomba Pozo Profundo 3', 'Advantech ECU-1251', 'Pozo 3 - Pisco Alto'
FROM gateway WHERE ubicacion = 'Límite Norte Pisco';

-- Telemetría histórica inicial para que Monitoreo y Reportes tengan datos que mostrar.
INSERT INTO lectura_sensor (id_controlador, humedad, humedad30, humedad60, humedad90, radiacion, conductividad, temperatura, valvula, fecha_hora)
SELECT c.id_controlador, 27.00, 30.00, 27.00, 24.00, 420.00, 1.35, 21.80, FALSE, NOW() - INTERVAL n.i HOUR
FROM controlador c
JOIN gateway g ON g.id_gateway = c.id_gateway
JOIN (SELECT 0 AS i UNION SELECT 2 UNION SELECT 4 UNION SELECT 6 UNION SELECT 8 UNION SELECT 10) n
WHERE g.ubicacion = 'Sector A Chincha';

INSERT INTO lectura_sensor (id_controlador, humedad, humedad30, humedad60, humedad90, radiacion, conductividad, temperatura, valvula, fecha_hora)
SELECT c.id_controlador, 41.00, 44.00, 41.00, 38.00, 0.00, 1.55, 20.50, TRUE, NOW() - INTERVAL n.i HOUR
FROM controlador c
JOIN gateway g ON g.id_gateway = c.id_gateway
JOIN (SELECT 0 AS i UNION SELECT 2 UNION SELECT 4 UNION SELECT 6 UNION SELECT 8 UNION SELECT 10) n
WHERE g.ubicacion = 'Límite Norte Pisco';
