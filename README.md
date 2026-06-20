# FundoCorp Backend — API REST

Backend del sistema de monitoreo de riego **FundoCorp**, desarrollado con Spring Boot 3 y MySQL.

---

## Requisitos previos

| Herramienta | Versión mínima |
|-------------|----------------|
| Java (JDK)  | 17             |
| Maven       | 3.8+           |
| MySQL       | 8.0+           |

---

## 1. Crear la base de datos

Ejecuta el script SQL en tu servidor MySQL:

```sql
CREATE DATABASE agroexpor;
USE agroexpor;
```

Luego ejecuta el resto del archivo `agroexpor.sql` (disponible en el repositorio [FundoCorp](https://github.com/DiegoEBV/FundoCorp)).

> **Nota:** La columna original del SQL es `contraseña` (con tilde). El backend usa `contrasena` (sin tilde).
> Si ya creaste la BD con el SQL original, renombra la columna:
>
> ```sql
> ALTER TABLE usuario CHANGE contraseña contrasena VARCHAR(255);
> ```

---

## 2. Configurar la conexión

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agroexpor?useSSL=false&serverTimezone=America/Lima&allowPublicKeyRetrieval=true&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
```

Cambia `root` y `TU_CONTRASEÑA` por las credenciales de tu MySQL.

---

## 3. Ejecutar el proyecto

```bash
# Clonar el repositorio
git clone https://github.com/DiegoEBV/FundoCorp_Backend.git
cd FundoCorp_Backend

# Compilar y ejecutar
mvn spring-boot:run
```

El servidor arranca en `http://localhost:8080`.

Al iniciar por primera vez, el sistema inserta automáticamente datos de prueba (fundos, gateways, controladores, usuarios y lecturas de sensor).

---

## 4. Endpoints disponibles

### Autenticación
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/auth/login` | Iniciar sesión |

**Body login:**
```json
{
  "correo": "agronomo@fundocorp.com",
  "contrasena": "123456"
}
```

### Fundos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/fundos` | Listar todos |
| GET | `/api/fundos/{id}` | Obtener por ID |
| POST | `/api/fundos` | Crear |
| PUT | `/api/fundos/{id}` | Actualizar |
| DELETE | `/api/fundos/{id}` | Eliminar |

### Gateways
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/gateways` | Listar todos |
| GET | `/api/gateways/fundo/{idFundo}` | Por fundo |
| POST | `/api/gateways` | Crear |
| PUT | `/api/gateways/{id}` | Actualizar |
| DELETE | `/api/gateways/{id}` | Eliminar |

### Controladores
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/controladores` | Listar todos |
| GET | `/api/controladores/gateway/{idGateway}` | Por gateway |
| POST | `/api/controladores` | Crear |
| PUT | `/api/controladores/{id}` | Actualizar |
| DELETE | `/api/controladores/{id}` | Eliminar |

### Telemetría (lecturas de sensor)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/telemetry` | Todas las lecturas |
| GET | `/api/telemetry/controlador/{id}` | Por controlador |
| GET | `/api/telemetry/controlador/{id}/ultima` | Última lectura |
| POST | `/api/telemetry` | Registrar lectura |

### Control de dispositivos
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/control/valvula` | Abrir/cerrar válvula |
| POST | `/api/control/bomba` | Ajustar velocidad bomba |

**Body válvula:**
```json
{ "idControlador": 1, "abrir": true }
```

**Body bomba (Hz, rango 0–60):**
```json
{ "idControlador": 3, "velocidadValida": 45.0 }
```

### Usuarios
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/usuarios` | Listar todos |
| GET | `/api/usuarios/{id}` | Por ID |
| POST | `/api/usuarios` | Crear |
| PUT | `/api/usuarios/{id}` | Actualizar |
| DELETE | `/api/usuarios/{id}` | Eliminar |
| GET | `/api/usuarios/fundo/{idFundo}` | Usuarios de un fundo |
| POST | `/api/usuarios/fundo/{idFundo}/asignar/{idUsuario}` | Asignar usuario a fundo |
| DELETE | `/api/usuarios/fundo/{idFundo}/desasignar/{idUsuario}` | Desasignar |

---

## 5. Usuarios de prueba (datos semilla)

| Correo | Contraseña | Rol |
|--------|------------|-----|
| agronomo@fundocorp.com | 123456 | AGRONOMO |
| regulador@ana.gob.pe | 123456 | REGULADOR |
| gerente@fundocorp.com | 123456 | GERENTE |
| agronomo2@fundocorp.com | 123456 | AGRONOMO |

---

## 6. Conectar con el frontend Angular

El backend ya tiene CORS configurado para `http://localhost:4200`.

En los servicios Angular del proyecto **FundoCorp**, descomenta las llamadas HTTP reales y comenta los mocks. Por ejemplo en `auth.service.ts`:

```typescript
// Descomentar esta línea:
return this.http.post<Usuario>(`${this.apiUrl}/login`, { correo, contrasena });

// Comentar el bloque de simulación mock
```

---

## 7. Estructura del proyecto

```
src/main/java/com/fundocorp/backend/
├── config/
│   ├── CorsConfig.java        # CORS para Angular
│   └── DataSeeder.java        # Datos de prueba iniciales
├── controller/                # 7 controllers REST
├── dto/                       # LoginRequest, ValveCommand, BombaCommand
├── entity/                    # 6 entidades JPA
├── repository/                # 6 repositorios Spring Data
└── service/                   # 6 servicios de negocio
```

---

## 8. Tecnologías

- **Spring Boot 3.3** — Framework principal
- **Spring Data JPA + Hibernate** — Persistencia
- **MySQL 8** — Base de datos
- **Lombok** — Reducción de boilerplate
- **Maven** — Gestión de dependencias
