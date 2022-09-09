package modelo;

public class Aeropuerto{
   private String codigoAeropuerto;
   private String ciudad;
   private String nombre;

    public Aeropuerto(String codigoAeropuerto, String ciudad, String nombre) {
        this.codigoAeropuerto = codigoAeropuerto;
        this.ciudad = ciudad;
        this.nombre = nombre;
    }

    public Aeropuerto() {

    }

    public String getCodigoAeropuerto() {
        return codigoAeropuerto;
    }

    public void setCodigoAeropuerto(String codigoAeropuerto) {
        this.codigoAeropuerto = codigoAeropuerto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Aeropuerto{" +
                "codigoAeropuerto='" + codigoAeropuerto + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aeropuerto that = (Aeropuerto) o;

        if (codigoAeropuerto != null ? !codigoAeropuerto.equals(that.codigoAeropuerto) : that.codigoAeropuerto != null)
            return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        return nombre != null ? nombre.equals(that.nombre) : that.nombre == null;
    }

    @Override
    public int hashCode() {
        int result = codigoAeropuerto != null ? codigoAeropuerto.hashCode() : 0;
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
