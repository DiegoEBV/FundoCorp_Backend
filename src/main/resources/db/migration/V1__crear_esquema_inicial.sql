CREATE TABLE fundo (
    id_fundo INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    ubicacion VARCHAR(255),
    hectareas DECIMAL(10, 2),
    PRIMARY KEY (id_fundo)
);

CREATE TABLE gateway (
    id_gateway INT NOT NULL AUTO_INCREMENT,
    id_fundo INT NOT NULL,
    modelo VARCHAR(100),
    ip_gateway VARCHAR(20),
    estado VARCHAR(100),
    ubicacion VARCHAR(255),
    PRIMARY KEY (id_gateway),
    CONSTRAINT fk_fundo_gateway
        FOREIGN KEY (id_fundo) REFERENCES fundo (id_fundo)
);

CREATE TABLE controlador (
    id_controlador INT NOT NULL AUTO_INCREMENT,
    id_gateway INT NOT NULL,
    nombre VARCHAR(255),
    modelo VARCHAR(150),
    ubicacion VARCHAR(255),
    PRIMARY KEY (id_controlador),
    CONSTRAINT fk_gateway_controlador
        FOREIGN KEY (id_gateway) REFERENCES gateway (id_gateway)
);

CREATE TABLE lectura_sensor (
    id_lectura BIGINT NOT NULL AUTO_INCREMENT,
    id_controlador INT NOT NULL,
    humedad DECIMAL(5, 2),
    humedad30 DECIMAL(5, 2),
    humedad60 DECIMAL(5, 2),
    humedad90 DECIMAL(5, 2),
    radiacion DECIMAL(8, 2),
    conductividad DECIMAL(5, 2),
    temperatura DECIMAL(5, 2),
    valvula BOOLEAN,
    fecha_hora DATETIME,
    PRIMARY KEY (id_lectura),
    CONSTRAINT fk_controlador_lectura_sensor
        FOREIGN KEY (id_controlador) REFERENCES controlador (id_controlador)
);

CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR(255),
    correo VARCHAR(150) UNIQUE,
    contrasena VARCHAR(255),
    rol VARCHAR(10),
    PRIMARY KEY (id_usuario)
);

CREATE TABLE usuario_fundo (
    id_fundo INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_asignacion DATE,
    PRIMARY KEY (id_fundo, id_usuario),
    CONSTRAINT fk_fundo_usuario_fundo
        FOREIGN KEY (id_fundo) REFERENCES fundo (id_fundo),
    CONSTRAINT fk_usuario_usuario_fundo
        FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
);
