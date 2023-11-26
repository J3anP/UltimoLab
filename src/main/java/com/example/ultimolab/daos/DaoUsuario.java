package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.beans.CursoHasDocente;
import com.example.ultimolab.beans.Usuario;
import com.example.ultimolab.beans.Facultad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUsuario extends DaoBase{
    public Usuario obtenerUsuario(int idUsuario){
        Usuario usuario = new Usuario();
        DaoRol dRol = new DaoRol();
        String sql = "select * from usuario where idusuario = ?";

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
    public boolean login(String correo, String password){

        boolean valido = false;
        password = Sha256.cipherPassword(password);

        String sql = "SELECT correo, password FROM usuario WHERE correo = ? AND password = ? ";


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, correo);
            pstmt.setString(2, password);

            try(ResultSet rs = pstmt.executeQuery()){

                while(rs.next()){
                    String correoDb = rs.getString(1);
                    String passwordDb = rs.getString(2);

                    if (correoDb == null || passwordDb == null){
                        valido = false;
                    } else if (correoDb.equals(correo) && passwordDb.equals(password)){
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

    //Metodos del decano:

    public void decanoRegistraDocente(String nombre, String correo, String contrasena){
        contrasena = Sha256.cipherPassword(contrasena);

        String sql = "insert into usuario(nombre,correo,password,idrol,cantidad_ingresos,fecha_registro,fecha_edicion) values(?,?,?,?,0,now(),now())";
        try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setString(2,correo);
            pstmt.setString(3,contrasena);
            pstmt.setInt(4,4);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void decanoEditaDocente(String nombre, int idDocente){
        String sql = "update usuario set nombre = ? where idusuario = ?";
        try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setInt(2,idDocente);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void decanoEliminaDocente(int idDocente){ //solo se elimina si  el profesor no tiene curso
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        if(daoCursoHasDocente.obtenerCursosPorDocente(idDocente).isEmpty()){
            String sql = "delete from usuario where idusuario = ?";
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1,idDocente);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Usuario> listaDocenteDeDecano(int idDecano){

        ArrayList<Usuario> listaDocentes = new ArrayList<>();
        String sql = "select * from usuario u left join rol r on u.idrol = r.idrol left join curso_has_docente chd on u.idusuario = chd.iddocente left join curso c on chd.idcurso = c.idcurso left join facultad f on c.idfacultad = f.idfacultad left join facultad_has_decano fhd on f.idfacultad = fhd.idfacultad where (r.nombre='docente' and u.idusuario not in (select iddocente from curso_has_docente)) or fhd.iddecano=?";
        try(Connection conn=this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,idDecano);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Usuario docente = new Usuario();
                    docente.setIdUsuario(rs.getInt(1));
                    docente.setNombre(rs.getString(2));
                    docente.setCorreo(rs.getString(3));
                    docente.setFechaUltimo(rs.getDate(6));
                    docente.setCantIngresos(rs.getInt(7));
                    docente.setFechaRegistro(rs.getDate(8));
                    docente.setFechaEdicion(rs.getDate(9));
                    listaDocentes.add(docente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaDocentes;

    }

    public ArrayList<Usuario> docentesSinCurso(){
        ArrayList<Usuario> docentesSinCurso = new ArrayList<>();
        String sql = "select * from usuario u inner join rol r on r.idrol = u.idrol where r.nombre='docente' and u.idusuario not in (select iddocente from curso_has_docente)";
        try(Connection conn = getConnection();
            ResultSet rs = conn.prepareStatement(sql).executeQuery()){
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                docentesSinCurso.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return docentesSinCurso;
    }


}
