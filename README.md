# Backend REST API Template (Spring Boot, JWT, Role & Permission)

Proyek ini adalah template dasar untuk membangun RESTful API menggunakan Spring Boot, dilengkapi dengan sistem otentikasi berbasis JWT (JSON Web Token) dan otorisasi berbasis Role-Based Access Control (RBAC) yang granular dengan Role dan Permission.

## Daftar Isi

1.  [Fitur Utama](#fitur-utama)
2.  [Teknologi yang Digunakan](#teknologi-yang-digunakan)
3.  [Struktur Proyek](#struktur-proyek)
4.  [Persiapan & Instalasi](#persiapan--instalasi)
    *   [Prasyarat](#prasyarat)
    *   [Kloning Repositori](#kloning-repositori)
    *   [Konfigurasi Database](#konfigurasi-database)
    *   [Konfigurasi JWT Secret Key](#konfigurasi-jwt-secret-key)
    *   [Konfigurasi CORS](#konfigurasi-cors)
5.  [Menjalankan Aplikasi](#menjalankan-aplikasi)
6.  [Endpoint API (Contoh)](#endpoint-api-contoh)
    *   [Otentikasi](#otentikasi)
    *   [Pengguna (User)](#pengguna-user)
7.  [Sistem Role & Permission](#sistem-role--permission)
    *   [Definisi Permission](#definisi-permission)
    *   [Penerapan Otorisasi](#penerapan-otorisasi)
8.  [Penyesuaian untuk Proyek Anda](#penyesuaian-untuk-proyek-anda)
    *   [Mengubah Nama Package](#mengubah-nama-package)
    *   [Menambahkan Entitas/Model Baru](#menambahkan-entitasmodel-baru)
    *   [Menambahkan Repositori Baru](#menambahkan-repositori-baru)
    *   [Menambahkan Service Baru](#menambahkan-service-baru)
    *   [Menambahkan Controller Baru](#menambahkan-controller-baru)
    *   [Manajemen Data Awal (Seeding)](#manajemen-data-awal-seeding)
    *   [Penanganan Error Kustom](#penanganan-error-kustom)
9.  [Kontribusi](#kontribusi)
10. [Lisensi](#lisensi)

---

## 1. Fitur Utama

*   **Otentikasi JWT**: Aman dan stateless, ideal untuk aplikasi Single Page Application (SPA) dan mobile.
*   **Otorisasi RBAC Granular**: Menggunakan konsep Role dan Permission untuk mengontrol akses ke endpoint API secara spesifik.
*   **Auditing Otomatis**: Kolom `createdBy`, `createdOn`, `modifiedBy`, `updatedOn`, dan `isDeleted` (soft delete) diwarisi oleh semua entitas dari `BaseEntity`.
*   **Validasi Input**: Menggunakan Jakarta Bean Validation untuk memastikan integritas data.
*   **Global Exception Handling**: Memberikan respons error yang konsisten dan informatif kepada klien.
*   **Database Relasional**: Kompatibel dengan database relasional (PostgreSQL, MySQL, H2, dll.) melalui Spring Data JPA.
*   **Tanpa Lombok**: Semua boilerplate code ditulis secara eksplisit untuk transparansi.

## 2. Teknologi yang Digunakan

*   **Java 17+**
*   **Spring Boot 3.2+**
*   **Spring Data JPA**
*   **Spring Security 6.2+**
*   **JWT (jjwt library)**
*   **Jakarta Validation (Hibernate Validator)**
*   **PostgreSQL** (Database default yang dikonfigurasi, dapat diubah)
*   **Maven** (Build Tool)

## 3. Struktur Proyek

```
src/main/java/com/yourcompany/yourapp
├── config/                  # Konfigurasi Spring (Security, CORS)
├── controller/              # REST API Endpoints
│   ├── AuthController.java
│   └── UserController.java
├── dto/                     # Data Transfer Objects (Request/Response)
│   ├── auth/
│   │   ├── LoginRequest.java
││   └── LoginResponse.java
│   └── user/
│       ├── UserRegisterRequest.java
│       └── UserResponse.java
├── exceptions/              # Custom Exceptions dan Global Exception Handler
│   ├── AuthException.java
│   ├── ErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── models/                  # Definisi Entitas JPA
│   ├── BaseEntity.java
│   ├── Permissions.java     # Peran
│   ├── Roles.java           # Izin
│   ├── RolePermissions.java # Tabel join untuk relasi Roles-Permissions
│   └── Users.java           # Pengguna
├── repositories/            # Spring Data JPA Repositories
│   ├── PermissionsRepository.java
│   ├── RolesRepository.java
│   ├── RolePermissionsRepository.java
│   └── UserRepo.java
├── security/                # Komponen Keamanan
│   ├── CustomUserDetailsService.java
│   └── jwt/                 # Komponen JWT
│       ├── JwtAuthenticationFilter.java
│       └── JwtService.java
├── service/                 # Business Logic
│   ├── AuthService.java
│   └── UserService.java
├── util/                    # Utilitas (mis. SecurityUtil untuk @PreAuthorize)
│   └── SecurityUtil.java
├── YourApplication.java     # Main Application Class
└── DataSeeder.java          # (Opsional) Untuk mengisi data awal
```

## 4. Persiapan & Instalasi

### Prasyarat

*   Java Development Kit (JDK) 17 atau lebih tinggi.
*   Maven.
*   Database relasional (misalnya PostgreSQL) terinstal dan berjalan.

### Kloning Repositori

```bash
git clone <URL_REPOSitori_ANDA>
cd <nama_folder_proyek_anda>
```

### Konfigurasi Database

1.  Buka file `src/main/resources/application.properties`.
2.  Ubah detail koneksi database sesuai dengan pengaturan Anda:

    ```properties
    # Database Configuration (PostgreSQL example)
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_database_username
    spring.datasource.password=your_database_password
    spring.datasource.driver-class-name=org.postgresql.Driver

    # JPA Configuration
    spring.jpa.hibernate.ddl-auto=update # Gunakan "create" atau "create-drop" untuk setup awal, lalu "update" atau "none"
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```
    **PENTING:** Untuk *deployment* awal, Anda bisa mengatur `spring.jpa.hibernate.ddl-auto=create` atau `create-drop` untuk otomatis membuat skema database. **PASTIKAN untuk mengubahnya kembali ke `update` atau `none` di lingkungan produksi** untuk menghindari kehilangan data atau perubahan skema yang tidak disengaja.

### Konfigurasi JWT Secret Key

JWT memerlukan *secret key* yang kuat dan aman. **JANGAN GUNAKAN KUNCI DEFAULT DI PRODUKSI.**

1.  Buka `src/main/resources/application.properties`.
2.  Ganti nilai `application.security.jwt.secret-key` dengan kunci yang dihasilkan secara kriptografis (Base64-encoded).
    Anda bisa menghasilkan kunci baru dengan menjalankan program Java sementara:

    ```java
    import io.jsonwebtoken.io.Encoders;
    import io.jsonwebtoken.security.Keys;
    import java.security.Key;

    public class JwtKeyGenerator {
        public static void main(String[] args) {
            Key key256 = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
            String base64Key256 = Encoders.BASE64.encode(key256.getEncoded());
            System.out.println("HS256 Secret Key (Base64): " + base64Key256);
        }
    }
    ```
    Salin output dari program di atas dan tempelkan ke `application.properties`.

    ```properties
    application.security.jwt.secret-key=YOUR_GENERATED_HS256_BASE64_KEY_HERE
    application.security.jwt.expiration=86400000 # Masa berlaku token dalam milidetik (contoh: 24 jam)
    ```

### Konfigurasi CORS

Jika frontend Anda berjalan di domain/port yang berbeda, Anda perlu mengkonfigurasi CORS (Cross-Origin Resource Sharing).

1.  Buka `src/main/java/com/yourcompany/yourapp/config/SecurityConfig.java`.
2.  Temukan bean `corsConfigurationSource()` dan ubah `setAllowedOrigins()` dengan domain frontend Anda.

    ```java
    // ...
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Ganti dengan domain frontend Anda. Gunakan "*" untuk semua (tidak direkomendasikan untuk produksi)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://your-frontend-domain.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    // ...
    ```

## 5. Menjalankan Aplikasi

Setelah semua konfigurasi selesai, Anda bisa menjalankan aplikasi:

```bash
mvn clean install
mvn spring-boot:run
```

Aplikasi akan berjalan di `http://localhost:8080` (port dapat diubah di `application.properties`).

## 6. Endpoint API (Contoh)

### Otentikasi

*   **Login Pengguna**
    *   `POST /api/auth/login`
    *   **Body (JSON):**
        ```json
        {
            "usernameOrEmail": "admin",
            "password": "password"
        }
        ```
    *   **Respons (200 OK):**
        ```json
        {
            "jwtToken": "eyJhbGciOiJIUzI1Ni...",
            "username": "admin",
            "role": "ADMIN"
        }
        ```
    *   **Error (401 Unauthorized):**
        ```json
        {
            "timestamp": "...",
            "status": 401,
            "error": "Unauthorized",
            "message": "Invalid username or password",
            "path": "/api/auth/login"
        }
        ```

*   **Daftar Pengguna Baru**
    *   `POST /api/auth/register`
    *   **Body (JSON):**
        ```json
        {
            "username": "newuser",
            "password": "strongpassword123",
            "email": "newuser@example.com",
            "roleId": 2 # ID peran (misal: 1 untuk ADMIN, 2 untuk USER)
        }
        ```
    *   **Respons (201 Created):**
        ```json
        {
            "id": 3,
            "username": "newuser",
            "email": "newuser@example.com",
            "roleName": "USER"
        }
        ```

### Pengguna (User)

**CATATAN:** Endpoint ini memerlukan JWT di header `Authorization: Bearer <TOKEN>`.

*   **Mendapatkan Semua Pengguna**
    *   `GET /api/users`
    *   **Diperlukan Permission:** `USER_READ`
    *   **Respons (200 OK):** List `UserResponse` DTO

*   **Mendapatkan Pengguna Berdasarkan ID**
    *   `GET /api/users/{id}`
    *   **Diperlukan Permission:** `USER_READ` (ditambah kondisi `hasUserId` jika user hanya bisa melihat profilnya sendiri)
    *   **Respons (200 OK):** `UserResponse` DTO

*   **Menghapus (Soft Delete) Pengguna**
    *   `DELETE /api/users/{id}`
    *   **Diperlukan Permission:** `USER_DELETE`
    *   **Respons (200 OK):** `UserResponse` DTO dari pengguna yang dihapus

## 7. Sistem Role & Permission

Aplikasi ini menggunakan model `Roles`, `Permissions`, dan entitas perantara `RolePermissions` untuk mengelola hak akses.

### Definisi Permission

Permissions didefinisikan sebagai string unik di database (kolom `name` di tabel `permissions`). **Sangat disarankan menggunakan format `{RESOURCE}_{ACTION}`**, contohnya:

*   `USER_READ`
*   `USER_CREATE`
*   `USER_UPDATE`
*   `USER_DELETE`
*   `PRODUCT_READ`
*   `PRODUCT_CREATE`
*   `ORDER_VIEW`
*   `REPORT_GENERATE`

`DataSeeder.java` menyediakan contoh permission dan role awal.

### Penerapan Otorisasi

Spring Security `hasAuthority()` digunakan untuk membatasi akses ke endpoint.

*   Pada metode controller, gunakan anotasi `@PreAuthorize`:
    ```java
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<List<UserResponse>> getAllUsers() { /* ... */ }
    ```
*   Untuk otorisasi yang lebih kompleks (misalnya, pengguna hanya dapat melihat datanya sendiri), gunakan Spring Expression Language (SpEL) dengan bean utilitas (`SecurityUtil`):
    ```java
    @PreAuthorize("hasAuthority('USER_READ') and @securityUtil.hasUserId(#id)")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) { /* ... */ }
    ```

## 8. Penyesuaian untuk Proyek Anda

### Mengubah Nama Package

Anda harus mengubah nama package default (`com.yourcompany.yourapp` atau `com.frezneel.starter`) ke nama package yang sesuai dengan proyek Anda.

1.  Gunakan fitur "Refactor" -> "Rename" di IDE Anda pada package root (misalnya `com.frezneel.starter`). Ini akan secara otomatis memperbarui semua import di seluruh proyek.
2.  Perbarui `groupId` dan `artifactId` di `pom.xml`.

### Menambahkan Entitas/Model Baru

1.  Buat kelas entitas baru di package `models/` (misalnya `Product.java`).
2.  Pastikan entitas baru meng-extend `BaseEntity` untuk mendapatkan fitur auditing dan soft delete.
3.  Definisikan kolom dan relasi menggunakan anotasi JPA (`@Entity`, `@Table`, `@Id`, `@Column`, `@ManyToOne`, `@OneToMany`, dll.).

### Menambahkan Repositori Baru

1.  Buat interface repositori baru di package `repositories/` (misalnya `ProductRepository.java`).
2.  Extend `JpaRepository<YourEntity, YourIdType>`.
3.  Tambahkan metode query turunan (derived query methods) sesuai kebutuhan (misalnya `findByName()`, `findByCategory()`).

### Menambahkan Service Baru

1.  Buat kelas service baru di package `services/` (misalnya `ProductService.java`).
2.  Anotasi dengan `@Service`.
3.  Inject repositori yang diperlukan melalui constructor.
4.  Implementasikan logika bisnis Anda.

### Menambahkan Controller Baru

1.  Buat kelas controller baru di package `controllers/` (misalnya `ProductController.java`).
2.  Anotasi dengan `@RestController` dan `@RequestMapping("/api/products")`.
3.  Inject service yang diperlukan melalui constructor.
4.  Definisikan endpoint REST API (`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`) dan terapkan anotasi `@PreAuthorize` untuk otorisasi.
5.  Buat DTO (`Request` dan `Response`) yang sesuai di package `dto/` untuk payload API.

### Manajemen Data Awal (Seeding)

Kelas `DataSeeder.java` adalah contoh untuk mengisi data awal (roles, permissions, admin user).

*   Anda bisa memodifikasi atau menghapus kelas ini setelah deployment awal.
*   Untuk lingkungan produksi, pertimbangkan untuk menonaktifkan `DataSeeder` atau menggunakan tool migrasi database seperti Flyway/Liquibase untuk manajemen data yang lebih canggih.

### Penanganan Error Kustom

*   Kelas `exceptions/GlobalExceptionHandler.java` menangani berbagai jenis error dan mengembalikan respons yang seragam.
*   Anda dapat menambahkan `ExceptionHandler` baru untuk tipe exception spesifik yang mungkin muncul di aplikasi Anda.

## 9. Kontribusi

Jika Anda ingin berkontribusi pada template ini, silakan fork repositori dan ajukan Pull Request.

## 10. Lisensi

Proyek ini dilisensikan di bawah MIT License. Lihat file `LICENSE` untuk detail lebih lanjut.

---

Semoga README ini membantu Anda memulai proyek backend baru Anda!
