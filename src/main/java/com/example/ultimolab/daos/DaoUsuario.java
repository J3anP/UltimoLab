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
}
