package impl.tew.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.tew.model.Seguidores;
import com.tew.model.Usuarios;
import com.tew.persistence.UsuariosDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class UsuariosJdbcDao implements UsuariosDao{

	@Override
	public List<Usuarios> getUsuarios() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Usuarios> usuarios = new ArrayList<Usuarios>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.USUARIOS");
			rs = ps.executeQuery();

			while(rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));

				usuarios.add(usuario);
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

		return usuarios;
	}
	
	
	@Override
	public List<Usuarios> getUsuarios(String filtro, String email) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Usuarios> usuarios = new ArrayList<Usuarios>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.USUARIOS where email not in (select email_seguidor from PUBLIC.SEGUIDORES where email_usuario = ?) and email <> ? and ((email like ? or nombre like ?) and (email != 'admin@email.com') )");
			ps.setString(1, email);
			ps.setString(2, email);
			ps.setString(3, "%"+filtro+"%");
			ps.setString(4, "%"+filtro+"%");
			
			rs = ps.executeQuery();

			while(rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));

				usuarios.add(usuario);
			}
			
			System.out.println(usuarios);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		return usuarios;	
	}

	@Override
	public void save(Usuarios u) throws AlreadyPersistedException {

		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(	"insert into PUBLIC.USUARIOS (EMAIL, PASSWD, ROL, NOMBRE) " +	"values (?, ?, ?, ?)");

			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPasswd());
			ps.setString(3, u.getRol());
			ps.setString(4, u.getNombre());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Usuario " + u + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
	
	

	@Override
	public void guardarSeguidor(Seguidores nuevoSeguidor) throws AlreadyPersistedException {
		
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(	"insert into PUBLIC.SEGUIDORES (EMAIL_USUARIO, EMAIL_SEGUIDOR, ACEPTADA) " +	"values ( ?, ?, ?)");

			ps.setString(1, nuevoSeguidor.getEmail_usuario());
			ps.setString(2, nuevoSeguidor.getEmail_seguidor());
			ps.setBoolean(3, nuevoSeguidor.isAceptada());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Usuario " + nuevoSeguidor + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}		
	}

	
	@Override
    public void delete(String email) throws NotPersistedException {

        PreparedStatement ps = null;
        Connection con = null;
        int rows = 0;

        try {

            String SQL_DRV = "org.hsqldb.jdbcDriver";
            String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

            Class.forName(SQL_DRV);
            con = DriverManager.getConnection(SQL_URL, "sa", "");
            ps = con.prepareStatement("delete from PUBLIC.USUARIOS where EMAIL = ?");

            ps.setString(1, email);

            rows = ps.executeUpdate();
            if (rows != 1) {
                throw new NotPersistedException("Usuario " + email + " not found");
            } 

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("Invalid SQL or database schema", e);
        }
        finally  {
            if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
            if (con != null) {try{ con.close(); } catch (Exception ex){}};
        }


    }
	
	@Override
	public void reset() {

		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.clear();

		ejecutaSQL("DELETE FROM SEGUIDORES");
		ejecutaSQL("DELETE FROM FOTOS");
		ejecutaSQL("DELETE FROM USUARIOS");
		

		ejecutaSQL("INSERT INTO USUARIOS VALUES('admin@email.com','admin','administrador','admin1')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user1@email.com','user1','usuario','user1')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user2@email.com','user2','usuario','user2')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user3@email.com','user3','usuario','user3')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user4@email.com','user4','usuario','user4')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user5@email.com','user5','usuario','user5')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user6@email.com','user6','usuario','user6')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user7@email.com','user7','usuario','user7')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user8@email.com','user8','usuario','user8')");
		ejecutaSQL("INSERT INTO USUARIOS VALUES('user9@email.com','user9','usuario','user9')");
		
		ejecutaSQL("INSERT INTO FOTOS VALUES(0,'data/alps-7426887__340.jpg','user1@email.com','Hola',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(1,'data/beach-7411683_640.jpg','user1@email.com','Hola1',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(2,'data/bridge-7504605__340.jpg','user1@email.com','Hola2',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(3,'data/cloud-of-bunch-of-7372799__340.jpg','user2@email.com','Hola',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(4,'data/coast-6067736__340.jpg','user2@email.com','Hola1',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(5,'data/crane-houses-7518536_640.jpg','user2@email.com','Hola2',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(6,'data/hyacinth-macaw-7501470__340.jpg','user3@email.com','Hola',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(7,'data/sea-7484743__340.jpg','user3@email.com','Hola1',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(8,'data/whale-7446905_640.jpg','user3@email.com','Hola2',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(9,'data/elephant-6841179_640.jpg','user4@email.com','Hola',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(10,'data/cat-7523894__340.jpg','user4@email.com','Hola1',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(11,'data/man-7526514__340.jpg','user4@email.com','Hola2',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(12,'data/flamingos-7525793__340.jpg','user5@email.com','Hola',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(13,'data/sea-7519353__340.jpg','user5@email.com','Hola1',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(14,'data/sea-7375467_640.jpg','user5@email.com','Hola2',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(15,'data/bulldog-7476727__340.jpg','user6@email.com','Hola',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(16,'data/muslim-7520627__340.jpg','user6@email.com','Hola1',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(17,'data/buttercup-7422987__340.jpg','user6@email.com','Hola2',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(18,'data/houses-7495861_640.jpg','user7@email.com','Hola',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(19,'data/dough-7521444__340.jpg','user7@email.com','Hola1',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(20,'data/duck-7406987__340.jpg','user7@email.com','Hola2',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(21,'data/piano-7460435__340.jpg','user8@email.com','Hola',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(22,'data/tea-7331591__340.jpg','user8@email.com','Hola1',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(23,'data/hedgehog-7274569__340.jpg','user8@email.com','Hola2',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(24,'data/koyasan-temple-7387445__340.jpg','user9@email.com','Hola',177776777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(25,'data/torii-7256271__340.jpg','user9@email.com','Hola1',177777777777)");
		ejecutaSQL("INSERT INTO FOTOS VALUES(26,'data/lighthouse-7486290__340.jpg','user9@email.com','Hola2',177776777777)");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user1@email.com','user2@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user1@email.com','user3@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user1@email.com','user4@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user1@email.com','user8@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user2@email.com','user1@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user2@email.com','user5@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user2@email.com','user6@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user3@email.com','user1@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user3@email.com','user7@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user3@email.com','user8@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user4@email.com','user1@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user4@email.com','user5@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user4@email.com','user9@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user5@email.com','user2@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user5@email.com','user4@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user5@email.com','user9@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user6@email.com','user2@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user6@email.com','user7@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user6@email.com','user9@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user7@email.com','user3@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user7@email.com','user6@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user7@email.com','user8@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user8@email.com','user1@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user8@email.com','user3@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user8@email.com','user7@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user9@email.com','user4@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user9@email.com','user5@email.com','1')");
		ejecutaSQL("INSERT INTO SEGUIDORES VALUES('user9@email.com','user6@email.com','1')");
				
		System.out.println("llego fin jdbc");
	}
	

	private void ejecutaSQL(String linea) {

		PreparedStatement ps = null;
		Connection con = null;
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(linea);

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}	
	}

	@Override
	public Usuarios findByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuarios usuario = null;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";


			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.USUARIOS where EMAIL = ?");
			ps.setString(1, email);

			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuarios();

				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

		return usuario;
	}
}
