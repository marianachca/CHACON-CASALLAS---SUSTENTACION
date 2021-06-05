package ParoNacional;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Victima {
    /**
     * Atributo que representa el nombre de la victima
     */
    private String nombres;

    /**
     * Atributo que representa el apellido de la victima
     */
    private String apellidos;

    /**
     * Atributo que representa el id de la victima
     */
    private String id;

    /**
     * Atributo que representa la ciudad de origen de la victima
     */
    private String ciudadOrigen;

    /**
     * Atributo que representa los hechos de la victima
     */
    private String hechos;

    private String fecha;

    /**
     * Este metodo es el constructor de la victima
     * @param pNombres Nombre de la victima
     * @param pApellidos Apellido de la victima
     * @param pId ID de la victima
     * @param pCiudadOrigen Ciudad de origen de la victima
     * @param pHechos Hechos de la situacion que protagonizo la victima
     */
    public Victima(String pNombres, String pApellidos, String pId, String pCiudadOrigen, String pHechos, String fechaN)
    {
        nombres = pNombres;
        apellidos = pApellidos;
        id = pId;
        ciudadOrigen = pCiudadOrigen;
        hechos = pHechos;
        fecha = fechaN;
    }

    /**
     * Este metodo entrega el nombre de la victima
     * @return Nombre de la victima
     */
    public String darNombres()
    {
        return nombres;
    }

    /**
     * Este metodo entrega el apellido de la victima
     * @return Apellido de la victima
     */
    public String darApellidos()
    {
        return apellidos;
    }

    /**
     * Este metodo entrega el ID de la victima
     * @return ID de la victima
     */
    public String darId()
    {
        return id;
    }

    /**
     * Este metodo entrega la ciudad de origen de la victima
     * @return Ciudad de origen de la victima
     */
    public String darCiudadOrigen()
    {
        return ciudadOrigen;
    }

    /**
     * Este metodo entrega el hecho que protagonizo la victima
     * @return Hecho de la victima
     */
    public String darHechos()
    {
        return hechos;
    }

    public String darEdad(){ return fecha;}

    /**
     * Este metodo edita el nombre de la vitima
     * @param pNuevoNombre Nombre nuevo
     */
    public void editarNombres(String pNuevoNombre)
    {
        nombres = pNuevoNombre;
    }

    /**
     * Este metodo edita el apellido de la victima
     * @param pNuevoApellido Apellido nuevo
     */
    public void editarApellidos(String pNuevoApellido)
    {
        apellidos = pNuevoApellido;
    }

    /**
     * Este metodo edita la ciudad de origen de la victima
     * @param pNuevaCiudadOrigen Ciudad de origen nueva
     */
    public void editarCiudadOrigen(String pNuevaCiudadOrigen)
    {
        ciudadOrigen = pNuevaCiudadOrigen;
    }

    /**
     * Este metodo edita los hechos que protagonizo la victima
     * @param pHechos Hecho nuevo
     */
    public void editarHecho(String pHechos)
    {
        hechos = pHechos;
    }

    public void darEdad(String fechaN){}



    public String fechaNacimiento() {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse("15/08/1993", fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);



        return String.valueOf(periodo.getYears());
    }
}
