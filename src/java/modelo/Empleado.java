/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo
 */
public class Empleado extends Persona{
 
    private conexion cn;
    private int id, idpuesto;
    private String fecha_inicio_labores, fecha_ingreso;
    
    public Empleado() {
    }

    public Empleado(int id, int idpuesto, String fecha_inicio_labores, String fecha_ingreso, String nombres, String apellidos, String direccion, String telefono, String dpi, String fecha_nacimiento, int genero) {
        super(nombres, apellidos, direccion, telefono, dpi, fecha_nacimiento, genero);
        this.id = id;
        this.idpuesto = idpuesto;
        this.fecha_inicio_labores = fecha_inicio_labores;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(int idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

   
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new conexion();
            cn.abrir_cn();
            String query="";
            query = "SELECT idempleados as id,nombres,apellidos,direccion,telefono,dpi,genero,fecha_nacimiento,puestos.puestos as puesto,fecha_inicio_labores,fecha_ingreso, empleados.idpuesto FROM empleados inner join puestos on empleados.idpuesto= puestos.idpuesto;";
            ResultSet consulta = cn.cnbd.createStatement().executeQuery(query);
            String encabezado[] = {"id","Nombres","Apellidos","Direccion","Telefono","DPI","Genero","Fecha Nacimiento","Id puesto","Fecha Inicio","Fecha Ingreso", "idpuesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String[12];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("dpi");
                datos[6] = consulta.getString("genero");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getString("puesto");
                datos[9] = consulta.getString("fecha_inicio_labores");
                datos[10] = consulta.getString("fecha_ingreso");
                datos[11] = consulta.getString("idpuesto");
                tabla.addRow(datos);
            }     
            cn.cerrar_cn();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return tabla;
    }
    
    @Override
    public int agregar(){
        int retorno = 0;
    try{
        cn = new conexion();
        PreparedStatement parametro;
        String query="INSERT INTO empleados(nombres,apellidos, direccion,telefono,dpi, genero, fecha_nacimiento, idpuesto, fecha_inicio_labores, fecha_ingreso) VALUES (?,?,?,?,?,?,?,?,?,?);";
        cn.abrir_cn();
        parametro =  (PreparedStatement)cn.cnbd.prepareStatement(query);
        parametro.setString(1, getNombres());
        parametro.setString(2, getApellidos());
        parametro.setString(3, getDireccion());
        parametro.setString(4, getTelefono());
        parametro.setString(5, getDpi());
        parametro.setInt(6, getGenero());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setInt(8, this.getIdpuesto());
        parametro.setString(9, getFecha_inicio_labores());
        parametro.setString(10, getFecha_ingreso());
        
        retorno = parametro.executeUpdate();
        cn.cerrar_cn();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
    
    @Override
    public int modificar(){
        int retorno = 0;
    try{
        cn = new conexion();
        PreparedStatement parametro;
        String query="update empleados set nombres=?,apellidos=?,direccion=?,telefono=?,dpi=?, genero=?, fecha_nacimiento=?,idpuesto=?, fecha_inicio_labores=?, fecha_ingreso=? where idempleados=?;";
        cn.abrir_cn();
        parametro =  (PreparedStatement)cn.cnbd.prepareStatement(query);
        parametro.setString(1, getNombres());
        parametro.setString(2, getApellidos());
        parametro.setString(3, getDireccion());
        parametro.setString(4, getTelefono());
        parametro.setString(5, getDpi());
        parametro.setInt(6, getGenero());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setInt(8, this.getIdpuesto());
        parametro.setString(9, getFecha_inicio_labores());
        parametro.setString(10, getFecha_ingreso());
        parametro.setInt(11, this.getId());
        retorno = parametro.executeUpdate();
        cn.cerrar_cn();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
    
    
    @Override
    public int eliminar(){
        int retorno = 0;
    try{
        cn = new conexion();
        PreparedStatement parametro;
        String query="delete from empleados where idempleados=?;";
        cn.abrir_cn();
        parametro =  (PreparedStatement)cn.cnbd.prepareStatement(query);
        parametro.setInt(1, this.getId());
        retorno = parametro.executeUpdate();
        cn.cerrar_cn();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
    
    
    public static void main(String [] args){
        System.out.println("Hola Loca");
        Empleado cl = new Empleado();
        
        //cl.setId(2);
        cl.setNombres("Nicolas");
        cl.setApellidos("mendez");
        cl.setDireccion("Guate");
        cl.setTelefono("464654");
        cl.setDpi("654656");
        cl.setGenero(1);
        cl.setFecha_nacimiento("2000-01-01");
        int resultado=cl.agregar();
       DefaultTableModel tabla = new DefaultTableModel();
       tabla=cl.leer();
        System.out.println("El resultado es"+resultado);
        //System.out.println("El resultado es"+tabla);
    }
}
