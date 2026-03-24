import java.io.PrintWriter;
import java.util.Random;

public class GenerateInfoFiles {
    // Lista de nombres posibles para los vendedores
    private static final String[] NOMBRES = {
        "Carlos", "Ana", "Luis", "Maria", "Juan",
        "Sofia", "Pedro", "Laura", "Andres", "Valentina",
        "Miguel", "Camila", "Jorge", "Isabella", "David"
    };
    // Lista de apellidos posibles
    private static final String[] APELLIDOS = {
        "Gomez", "Perez", "Rodriguez", "Martinez",
        "Lopez", "Garcia", "Hernandez", "Torres",
        "Ramirez", "Flores", "Vargas", "Castro"
    };
    // Tipos de documento posibles
    private static final String[] TIPOS_DOC = {"CC", "CE", "TI"};
    // Nombres de productos
    private static final String[] PRODUCTOS_NOMBRES = {
        "Laptop", "Mouse", "Teclado", "Monitor",
        "Auriculares", "Webcam", "Disco SSD", "Memoria RAM",
        "Impresora", "Tablet", "Cable HDMI", "Hub USB"
    };
    // Precios de los productos (en el mismo orden que los nombres)
    private static final double[] PRODUCTOS_PRECIOS = {
        2500000.50, 80000.00, 150000.99, 950000.00,
        200000.00, 180000.50, 320000.00, 250000.75,
        450000.00, 1200000.00, 35000.00, 95000.99
    };

    // MEJORA TÉCNICA: Instancia única de Random para mejor rendimiento y verdadera aleatoriedad
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        try {
            System.out.println("=========================================");
            System.out.println("📦 Iniciando generación de archivos...");
            System.out.println("=========================================\n");
            
            // Genera el archivo de productos
            createProductsFile(PRODUCTOS_NOMBRES.length);
            // Genera el archivo de vendedores (con 3 vendedores)
            createSalesManInfoFile(3);
            
            System.out.println("\n=========================================");
            System.out.println("✅ ¡Archivos generados exitosamente!");
            System.out.println("=========================================");
        } catch (Exception e) { 
            System.err.println("❌ ERROR: Ocurrió un problema durante la generación - " + e.getMessage()); 
        }
    }

    // Crea el archivo productos.csv con la lista de productos y precios
    public static void createProductsFile(int productsCount) throws Exception {
        System.out.print("➤ Generando catálogo de productos (productos.csv)... ");
        try (PrintWriter writer = new PrintWriter("productos.csv", "UTF-8")) {
            for (int i = 0; i < productsCount; i++) {
                // Cada línea: ID;NombreProducto;Precio
                writer.println((i + 1) + ";" + PRODUCTOS_NOMBRES[i] + ";" + PRODUCTOS_PRECIOS[i]);
            }
            System.out.println("[OK]");
        }
    }

    // Crea el archivo vendedores.csv con información de varios vendedores
    public static void createSalesManInfoFile(int salesmanCount) throws Exception {
        System.out.println("➤ Generando información de vendedores y sus ventas...");
        try (PrintWriter writer = new PrintWriter("vendedores.csv", "UTF-8")) {
            for (int i = 0; i < salesmanCount; i++) {
                // Genera un número de documento aleatorio de 9 dígitos
                long id = 100000000 + RANDOM.nextInt(900000000);
                // Selecciona un nombre y apellido aleatorio
                String nombre = NOMBRES[RANDOM.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[RANDOM.nextInt(APELLIDOS.length)];
                
                // Escribe en vendedores.csv: TipoDoc;ID;Nombre;Apellido
                writer.println(TIPOS_DOC[RANDOM.nextInt(TIPOS_DOC.length)] + ";" + id + ";" + nombre + ";" + apellido);
                
                // Genera archivo individual para cada vendedor con sus ventas
                createSalesMenFile(RANDOM.nextInt(4) + 2, nombre, id);
            }
            System.out.println("   ↳ Archivo general 'vendedores.csv' creado [OK]");
        }
    }

    // Crea un archivo vendedor_ID.csv con las ventas de un vendedor específico
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws Exception {
        String fileName = "vendedor_" + id + ".csv";
        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            // Primera línea: TipoDoc;ID
            writer.println(TIPOS_DOC[RANDOM.nextInt(TIPOS_DOC.length)] + ";" + id);
            // Genera varias ventas aleatorias
            for (int i = 0; i < randomSalesCount; i++) {
                // Cada venta: IDProducto;CantidadVendida;
                writer.println((RANDOM.nextInt(PRODUCTOS_NOMBRES.length) + 1) + ";" + (RANDOM.nextInt(10) + 1) + ";");
            }
            // MEJORA VISUAL: Muestra en consola qué archivo se acaba de crear
            System.out.println("   ↳ Archivo de ventas creado: " + fileName + " (" + randomSalesCount + " ventas)");
        }
    }
}