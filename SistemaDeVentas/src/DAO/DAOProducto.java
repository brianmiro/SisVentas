//Metodos que nos pemitiran realizar el CRUD con la base de datos
//Son los mismos de la clase DAOProducto adaptados para esta clase 
package DAO;

import TransferObject.Categoria;
import TransferObject.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brian Mirò Moron
 * Programa Ventas
 * AllFitness
 */
public class DAOProducto {
    public boolean Registrar(Producto c) {

        Connection con = null;
        CallableStatement cstm = null;
        boolean confirmacion = true;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{CALL pr_registrar_producto(?,?,?,?,?)}");//Buscamos el nombre del procedimiento almacenada en DB
            cstm.setString(1, c.getNombre());
            cstm.setDouble(2, c.getPrecio());
            cstm.setInt(3,c.getStock());
            cstm.setString(4,c.getDescripcion());
            cstm.setInt(5,c.getCategoria().getIdcategoria());
            confirmacion = cstm.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (con != null) {
                    con.rollback();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return confirmacion;
    }

    public boolean Modificar(Producto c) {

        Connection con = null;
        CallableStatement cstm = null;
        boolean confirmacion = true;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{CALL pr_modificar_producto(?,?,?,?,?,?)}"); //Buscamos el nombre del procedimiento almacenada en DB
            cstm.setInt(1,c.getIdproducto());
            cstm.setString(2, c.getNombre());
            cstm.setDouble(3, c.getPrecio());
            cstm.setInt(4,c.getStock());
            cstm.setString(5,c.getDescripcion());
            cstm.setInt(6,c.getCategoria().getIdcategoria());
            confirmacion = cstm.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (con != null) {
                    con.rollback();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return confirmacion;
    }

    public boolean Eliminar(Producto c) {

        Connection con = null;
        CallableStatement cstm = null;
        boolean confirmacion = true;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{CALL pr_eliminar_producto(?)}");//Buscamos el nombre del procedimiento almacenada en DB
            cstm.setInt(1,c.getIdproducto());
            confirmacion = cstm.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (con != null) {
                    con.rollback();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return confirmacion;
    }

    public List<Producto> Listar() {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        List<Producto> lista = null;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall("{CALL pr_listar_producto()}");//Buscamos el nombre del procedimiento almacenada en DB
            rs = cstm.executeQuery();//no tiene parametros
            lista = new ArrayList<>();
            Producto cat = null;
            while (rs.next()) {
                cat = new Producto();
                cat.setIdproducto(rs.getInt("idProducto"));
                cat.setNombre(rs.getString("Nombre"));
                cat.setPrecio(rs.getDouble("Precio"));
                cat.setStock(rs.getInt("Stock"));
                cat.setDescripcion(rs.getString("Descripcion"));
                cat.setCategoria(new Categoria());
                cat.getCategoria().setIdcategoria(rs.getInt("idCategoria"));
                cat.getCategoria().setNombre(rs.getString("Categoria"));
                lista.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

        }
        return lista;
    }

    public List<Producto> Buscar(String nombre) {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        List<Producto> lista = null;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall("{CALL pr_buscar_producto(?)}");
            cstm.setString(1, nombre);
            rs = cstm.executeQuery();
            lista = new ArrayList<>();
            Producto cat = null;
            while (rs.next()) {
                cat = new Producto();
                cat.setIdproducto(rs.getInt("idProducto"));
                cat.setNombre(rs.getString("Nombre"));
                cat.setPrecio(rs.getDouble("Precio"));
                cat.setStock(rs.getInt("Stock"));
                cat.setDescripcion(rs.getString("Descripcion"));
                cat.setCategoria(new Categoria());
                cat.getCategoria().setIdcategoria(rs.getInt("idCategoria"));
                cat.getCategoria().setNombre(rs.getString("Categoria"));
                lista.add(cat);
                lista.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

        }
        return lista;
    }
}
