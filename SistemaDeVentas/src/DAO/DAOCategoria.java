package DAO;

/**
 *
 * @author Brian Miro Moron
 */
import TransferObject.Categoria;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import java.util.*;
import java.sql.ResultSet;

public class DAOCategoria {

    public boolean Registrar(Categoria c) {

        Connection con = null;
        CallableStatement cstm = null;
        boolean confirmacion = true;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{CALL pr_registrar_categoria(?,?)}");//Buscamos el nombre del procedimiento almacenada en DB
            cstm.setString(1, c.getNombre());
            cstm.setString(2, c.getDescripcion());
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

    public boolean Modificar(Categoria c) {

        Connection con = null;
        CallableStatement cstm = null;
        boolean confirmacion = true;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{CALL pr_modificar_categoria(?,?,?)}"); //Buscamos el nombre del procedimiento almacenada en DB
            cstm.setInt(1, c.getIdcategoria());//Tenemos en cuenta los parametros del procedimiento y su tipo de dato
            cstm.setString(2, c.getNombre());
            cstm.setString(3, c.getDescripcion());
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

    public boolean Eliminar(Categoria c) {

        Connection con = null;
        CallableStatement cstm = null;
        boolean confirmacion = true;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);
            cstm = con.prepareCall("{CALL pr_eliminar_categoria(?)}");//Buscamos el nombre del procedimiento almacenada en DB
            cstm.setInt(1, c.getIdcategoria());
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

    public List<Categoria> Listar() {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        List<Categoria> lista = null;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall("{CALL pr_listar_categorias()}");//Buscamos el nombre del procedimiento almacenada en DB
            rs = cstm.executeQuery();//no tiene parametros
            lista = new ArrayList<>();
            Categoria cat = null;
            while (rs.next()) {
                cat = new Categoria();
                cat.setIdcategoria(rs.getInt("idCategoria"));
                cat.setNombre(rs.getString("Nombre"));
                cat.setDescripcion(rs.getString("Descripcion"));
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

    public List<Categoria> Buscar(String nombre) {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        List<Categoria> lista = null;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall("{CALL pr_buscar_categoria(?)}");
            cstm.setString(1, nombre);
            rs = cstm.executeQuery();
            lista = new ArrayList<>();
            Categoria cat = null;
            while (rs.next()) {
                cat = new Categoria();
                cat.setIdcategoria(rs.getInt("idCategoria"));
                cat.setNombre(rs.getString("Nombre"));
                cat.setDescripcion(rs.getString("Descripcion"));
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
