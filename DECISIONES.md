# Decisiones de metadata y configuración

## @ConfigurationProperties en lugar de @Value

AppInfoProperties agrupa todos los datos de pp.info en un único objeto tipado. Esto mantiene juntas las propiedades relacionadas, admite binding relajado y permite validar los valores al iniciar la aplicación. En este caso @Email valida mi correo de desarrollador y @NotBlank evita que la metadata se vaya vacía.

## Sincronización de la versión con pom.xml

pp.info.version usa @project.version@. El spring-boot-starter-parent configura el filtrado Maven de los archivos pplication*.properties, así que Maven sustituye ese token con ${project.version} durante el procesamiento de recursos. De esta manera, la versión no se copia manualmente en cada perfil y se mantiene centralizada. El test del endpoint comprueba que el valor se esté cargando correctamente.

## Qué ocurre al quitar @Component

La clase está registrada como bean mediante la anotación @Component. Si se elimina esa anotación sin añadir otra forma de registro, el escaneo de componentes no crea AppInfoProperties; al iniciar, Spring falla porque no puede inyectar esa dependencia requerida en InfoController. Lo comprobé quitando temporalmente la anotación y ejecutando la prueba del controlador; el contexto falla con No qualifying bean of type 'org.example.proyecto1.config.AppInfoProperties' available. Restauré la anotación para que vuelva a funcionar.

## /api/info y /actuator/info

GET /api/v1/info (también disponible en /api/info) es el contrato público y estable de la aplicación. Devuelve metadata propia a través de un DTO, incluyendo mi información como desarrollador principal y el entorno de ejecución.

/actuator/info pertenece a Spring Boot Actuator. Su contenido se construye desde las propiedades info.* y se orienta a diagnóstico interno y operación; no es el contrato de metadata definido por el proyecto. Adicionalmente, el endpoint /actuator/configprops permite explorar las propiedades enlazadas; debido a que añadí Spring Security, requiere autenticación y muestra valores solo si te logueas con credenciales.

## Perfiles

pplication-dev.properties identifica mi entorno de desarrollo (dev); pplication-prod.properties identifica el entorno de producción (prod). Ambos heredan los campos comunes y la versión directamente de pplication.properties.

La configuración actual con credenciales ndy / ndy123 es de uso exclusivo para desarrollo y pruebas locales. No debe reutilizarse ni subirse como contraseña en un entorno real de producción por motivos de seguridad.
