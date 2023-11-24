package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario extends DaoBase{
    public Usuario obtenerUsuario(int idUsuario){
        Usuario usuario = new Usuario();
        DaoRol dRol = new DaoRol();
        String sql = "select * from jugadores where idusuario = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idUsuario);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt("idusuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(dRol.obtenerRol(rs.getInt("idrol")));
                    usuario.setFechaRegistro(rs.getDate("usuario"));
                    //usuario.setContrasena(rs.getString("contrasena"));
                    //usuario.setListaNegra(rs.getBoolean("ban"));
                }
                else {
                    usuario = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    public Usuario obtenerUsuarioCorreo(String correo){
        DaoRol daoRol = new DaoRol();
        Usuario usuario = new Usuario();
        String sql = "select * from usuario where correo = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,correo);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt("idusuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(daoRol.obtenerRol(rs.getInt("idrol")));
                    usuario.setFechaUltimo(rs.getDate("ultimo_ingreso"));
                    usuario.setCantIngresos(rs.getInt("cantidad_ingresos"));
                    usuario.setFechaRegistro(rs.getDate("fecha_registro"));
                    usuario.setFechaEdicion(rs.getDate("fecha_edicion"));
                }
                else {
                    usuario = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    public void actualizarIngreso(int idUsuario){
        String sql = "update usuario set ultimo_ingreso = now(), cantidad_ingresos = cantidad_ingresos+1 where idusuario = ?";
        try (Connection conn = this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean login(String usuario, String contrasena){

        boolean valido = false;
        contrasena = Sha256.cipherPassword(contrasena);

        String sql = "SELECT usuario, contrasena FROM jugadores WHERE usuario = ? AND contrasena = ? ";


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);

            try(ResultSet rs = pstmt.executeQuery()){

                while(rs.next()){
                    String usuarioDb = rs.getString(1);
                    String contrasenaDb = rs.getString(2);

                    if (usuarioDb == null || contrasenaDb == null){
                        valido = false;
                    } else if (usuarioDb.equals(usuario) && contrasenaDb.equals(contrasena)){
                        valido = true;
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return valido;
    }

    public Usuario obtenerAdministrador(int idAdministrador){
        Usuario administrador  = new Usuario();
        String sql = " ";
        String nameRol = obtenerUsuario(idAdministrador).getRol().getNombre();
        if(nameRol.equalsIgnoreCase("administrador")){
            administrador = obtenerUsuario(idAdministrador);
        }
        return  administrador;
    }


}
