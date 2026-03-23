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

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando generación de archivos...");
            // Genera el archivo de productos
            createProductsFile(PRODUCTOS_NOMBRES.length);
            // Genera el archivo de vendedores (con 3 vendedores)
            createSalesManInfoFile(3);
            System.out.println("¡Archivos generados exitosamente!");
        } catch (Exception e) { 
            System.err.println("ERROR: " + e.getMessage()); 
        }
    }

    // Crea el archivo productos.csv con la lista de productos y precios
    public static void createProductsFile(int productsCount) throws Exception {
        try (PrintWriter writer = new PrintWriter("productos.csv", "UTF-8")) {
            for (int i = 0; i < productsCount; i++) {
                // Cada línea: ID;NombreProducto;Precio
                writer.println((i + 1) + ";" + PRODUCTOS_NOMBRES[i] + ";" + PRODUCTOS_PRECIOS[i]);
            }
        }
    }

    // Crea el archivo vendedores.csv con información de varios vendedores
    public static void createSalesManInfoFile(int salesmanCount) throws Exception {
        Random rand = new Random();
        try (PrintWriter writer = new PrintWriter("vendedores.csv", "UTF-8")) {
            for (int i = 0; i < salesmanCount; i++) {
                // Genera un número de documento aleatorio de 9 dígitos
                long id = 100000000 + rand.nextInt(900000000);
                // Selecciona un nombre y apellido aleatorio
                String nombre = NOMBRES[rand.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[rand.nextInt(APELLIDOS.length)];
                // Escribe en vendedores.csv: TipoDoc;ID;Nombre;Apellido
                writer.println(TIPOS_DOC[rand.nextInt(TIPOS_DOC.length)] + ";" + id + ";" + nombre + ";" + apellido);
                // Genera archivo individual para cada vendedor con sus ventas
                createSalesMenFile(rand.nextInt(4) + 2, nombre, id);
            }
        }
    }

    // Crea un archivo vendedor_ID.csv con las ventas de un vendedor específico
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws Exception {
        Random rand = new Random();
        String fileName = "vendedor_" + id + ".csv";
        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            // Primera línea: TipoDoc;ID
            writer.println(TIPOS_DOC[rand.nextInt(TIPOS_DOC.length)] + ";" + id);
            // Genera varias ventas aleatorias
            for (int i = 0; i < randomSalesCount; i++) {
                // Cada venta: IDProducto;CantidadVendida;
                writer.println((rand.nextInt(PRODUCTOS_NOMBRES.length) + 1) + ";" + (rand.nextInt(10) + 1) + ";");
            }
        }
    }
}
