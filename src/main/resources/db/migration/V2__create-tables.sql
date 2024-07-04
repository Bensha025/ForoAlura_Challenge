CREATE TABLE topico (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    fecha_de_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    usuario_id VARCHAR(50) NOT NULL,
    curso_id VARCHAR(50) NOT NULL
);

CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

CREATE TABLE respuesta (
    id SERIAL PRIMARY KEY,
    mensaje VARCHAR(5000) NOT NULL,
    fecha_de_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    solucion BOOLEAN DEFAULT FALSE,
    eliminado BOOLEAN DEFAULT FALSE,
    usuario_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL
);
