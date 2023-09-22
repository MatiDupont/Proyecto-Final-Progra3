import java.util.Random;
public abstract class Personaje {
    private String raza;
    private String nombre;
    private String apodo;
    private String edad;
    private int salud;
    private int velocidad;
    private int destreza;
    private int fuerza;
    private int nivel;
    private int armadura;
    private double efectividadDisparo;

    Random random = new Random();
    public Personaje(String raza, String nombre, String apodo, String edad, int salud, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        this.raza = raza;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edad = edad;
        this.salud = salud;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.nivel = nivel;
        this.armadura = armadura;
        this.efectividadDisparo = (random.nextInt(100) + 1 ) / 100.0;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public void setEfectividadDisparo(double efectividadDisparo) {
        this.efectividadDisparo = efectividadDisparo;
    }

    public String getRaza() {
        return raza;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public String getEdad() {
        return edad;
    }

    public int getSalud() {
        return salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public double getEfectividadDisparo() {
        return efectividadDisparo;
    }

    public abstract int poderDisparo();
    public abstract double valorAtaque();
    public abstract double poderDefensa();
}
