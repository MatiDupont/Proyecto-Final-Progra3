public class Orco extends Personaje{

    public Orco(String raza, String nombre, String apodo, String edad, int salud, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        super(raza, nombre, apodo, edad, salud, velocidad, destreza, fuerza, nivel, armadura);
    }

    @Override
    public int poderDisparo() {
        return (this.getDestreza() * this.getFuerza() * this.getNivel());
    }

    @Override
    public double valorAtaque() {
        return (poderDisparo() * this.getEfectividadDisparo());
    }

    @Override
    public double poderDefensa() {
        return (this.getArmadura() * this.getVelocidad());
    }
}
