import java.util.Scanner;
// ================= CLASE EMPLEADO =================
class Empleado {

    // Atributos privado
    private String nombre;
    private double salarioHora;
    private int horasTrabajadas;
    private double bonificacion;

    // Constructor
    public Empleado() {
        nombre = "Sin asignar";
        salarioHora = 10000;
        horasTrabajadas = 40;
        bonificacion = 0;
    }

    // Constructor con parametros
    public Empleado(String nombre, double salarioHora, int horasTrabajadas, double bonificacion) {
        this.nombre = nombre;
        this.salarioHora = salarioHora;
        this.horasTrabajadas = horasTrabajadas;
        this.bonificacion = bonificacion;
    }

    // Metodo 1: calcular el salario
    public double calcularSalario() {
        double salario;

        if (horasTrabajadas <= 40) {
            // Solo horas ordinarias
            salario = horasTrabajadas * salarioHora;
        } else {
            // 40 horas normales + las demas al 150%
            int horasExtra = horasTrabajadas - 40;
            salario = (40 * salarioHora) + (horasExtra * salarioHora * 1.5);
        }

        //suma la bonificacion
        salario = salario + bonificacion;
        return salario;
    }

    // Metodo 2: registrar las horas de varios dias
    public void registrarHoras(int dias) {
        Scanner entrada = new Scanner(System.in);
        int total = 0;

        for (int i = 1; i <= dias; i++) {
            System.out.print("Horas trabajadas el dia " + i + ": ");
            int horas = entrada.nextInt();

            // Validar que las horas sean correctas
            while (horas < 0 || horas > 24) {
                System.out.println("Horas invalidas (deben ser de 0 a 24)");
                System.out.print("Horas trabajadas el dia " + i + ": ");
                horas = entrada.nextInt();
            }

            total = total + horas;
        }

        horasTrabajadas = total;
        System.out.println("Total de horas registradas: " + horasTrabajadas);
    }

    // Mostrar la información del empleado
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Salario por hora: " + salarioHora);
        System.out.println("Horas trabajadas: " + horasTrabajadas);
        System.out.println("Bonificacion: " + bonificacion);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 0) {
            this.nombre = nombre;
        } else {
            System.out.println("Nombre invalido");
        }
    }

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        if (salarioHora > 0) {
            this.salarioHora = salarioHora;
        } else {
            System.out.println("Salario por hora invalido");
        }
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        if (horasTrabajadas >= 0) {
            this.horasTrabajadas = horasTrabajadas;
        } else {
            System.out.println("Horas invalidas");
        }
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(double bonificacion) {
        if (bonificacion >= 0) {
            this.bonificacion = bonificacion;
        } else {
            System.out.println("Bonificacion invalida");
        }
    }
}

// ================= CLASE ADMINISTRADOR =================
public class ManejoEmpleados {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Arreglo para guardar los empleados
        Empleado[] empleados = new Empleado[10];

        // Cuantos empleados hay creados
        int contador = 0;
        int opcion;
        int posicion;

        do {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Crear empleado por defecto");
            System.out.println("2. Crear empleado con parametros");
            System.out.println("3. Modificar atributos del empleado");
            System.out.println("4. Visualizar empleados registrados");
            System.out.println("5. Calcular salario");
            System.out.println("6. Registrar horas trabajadas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    if (contador < 10) {
                        empleados[contador] = new Empleado();
                        System.out.println("Empleado creado correctamente");
                        contador++;
                    } else {
                        System.out.println("No hay mas espacio para empleados");
                    }
                    break;

                case 2:
                    if (contador < 10) {
                        entrada.nextLine(); // limpiar el enter
                        System.out.print("Ingrese nombre: ");
                        String nombre = entrada.nextLine();
                        System.out.print("Ingrese salario por hora: ");
                        double salarioHora = entrada.nextDouble();
                        System.out.print("Ingrese horas trabajadas: ");
                        int horas = entrada.nextInt();
                        System.out.print("Ingrese bonificacion: ");
                        double bonificacion = entrada.nextDouble();

                        if (salarioHora > 0 && horas >= 0 && bonificacion >= 0) {
                            empleados[contador] = new Empleado(nombre, salarioHora, horas, bonificacion);
                            System.out.println("Empleado creado con parametros");
                            contador++;
                        } else {
                            System.out.println("Datos invalidos, no se creo el empleado");
                        }
                    } else {
                        System.out.println("No hay mas espacio para empleados");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese posicion del empleado: ");
                    posicion = entrada.nextInt();

                    if (posicion >= 0 && posicion < contador) {
                        System.out.println("1. Nombre");
                        System.out.println("2. Salario por hora");
                        System.out.println("3. Horas trabajadas");
                        System.out.println("4. Bonificacion");
                        System.out.print("Que desea modificar: ");
                        int atributo = entrada.nextInt();

                        if (atributo == 1) {
                            entrada.nextLine(); // limpiar el enter
                            System.out.print("Nuevo nombre: ");
                            String nuevoNombre = entrada.nextLine();
                            empleados[posicion].setNombre(nuevoNombre);
                        } else if (atributo == 2) {
                            System.out.print("Nuevo salario por hora: ");
                            double nuevoSalario = entrada.nextDouble();
                            empleados[posicion].setSalarioHora(nuevoSalario);
                        } else if (atributo == 3) {
                            System.out.print("Nuevas horas trabajadas: ");
                            int nuevasHoras = entrada.nextInt();
                            empleados[posicion].setHorasTrabajadas(nuevasHoras);
                        } else if (atributo == 4) {
                            System.out.print("Nueva bonificacion: ");
                            double nuevaBonificacion = entrada.nextDouble();
                            empleados[posicion].setBonificacion(nuevaBonificacion);
                        } else {
                            System.out.println("Opcion invalida");
                        }
                    } else {
                        System.out.println("Posicion invalida");
                    }
                    break;

                case 4:
                    if (contador == 0) {
                        System.out.println("No hay empleados registrados");
                    } else {
                        for (int i = 0; i < contador; i++) {
                            System.out.println("\nEmpleado " + i);
                            empleados[i].mostrarInformacion();
                        }
                    }
                    break;

                case 5:
                    System.out.print("Ingrese posicion del empleado: ");
                    posicion = entrada.nextInt();

                    if (posicion >= 0 && posicion < contador) {
                        double salario = empleados[posicion].calcularSalario();
                        System.out.println("El salario de " + empleados[posicion].getNombre() + " es: " + salario);
                    } else {
                        System.out.println("Posicion invalida");
                    }
                    break;

                case 6:
                    System.out.print("Ingrese posicion del empleado: ");
                    posicion = entrada.nextInt();

                    if (posicion >= 0 && posicion < contador) {
                        System.out.print("Cuantos dias desea registrar: ");
                        int dias = entrada.nextInt();

                        if (dias > 0) {
                            empleados[posicion].registrarHoras(dias);
                        } else {
                            System.out.println("Cantidad de dias invalida");
                        }
                    } else {
                        System.out.println("Posicion invalida");
                    }
                    break;

                case 7:
                    System.out.println("Programa finalizado");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 7);

        entrada.close();
    }
}