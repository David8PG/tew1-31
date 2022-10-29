package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.tew.model.Foto;
import com.tew.persistence.FotoDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class FotoJdbcDao implements FotoDao{

	@Override
	public List<Foto> getFotos() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Foto> fotos = new ArrayList<Foto>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM PUBLIC.FOTOS");
			rs = ps.executeQuery();

			while(rs.next()) {

				Foto foto = new Foto();
				foto.setID(rs.getLong("ID"));
				foto.setEmail(rs.getString("EMAIL"));
				foto.setTitulo(rs.getString("TITULO"));
				foto.setPath(rs.getString("FILEPATH"));
				foto.setFecha(rs.getLong("FECHA"));

				fotos.add(foto);

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

		return fotos;
	}



	int cont =100;

	@Override
	public void save(Foto f) throws AlreadyPersistedException {

		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");		
			Statement statement = con.createStatement();
	        ResultSet res = statement.executeQuery("SELECT MAX(ID) FROM PUBLIC.FOTOS");
	        res.next();
	        String sum = res.getString(1);
	        Long a = Long.parseLong(sum);
	        a++;
			ps = con.prepareStatement("insert into PUBLIC.FOTOS (ID, FILEPATH,EMAIL,TITULO,FECHA) values ( ? , ?, ?, ?, ?)");
			
			ps.setLong(1,a);
			ps.setString(2, f.getPath());
			ps.setString(3, f.getEmail());
			ps.setString(4, f.getTitulo());
			ps.setLong(5, f.getFecha());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Foto " + f + " already persisted");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema ", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}





	@Override
	public Foto findById(Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Foto foto = null;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";


			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.FOTOS where ID = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				foto = new Foto();

				foto.setID(rs.getLong("ID"));
				foto.setEmail(rs.getString("EMAIL"));
				foto.setTitulo(rs.getString("TITULO"));
				foto.setPath(rs.getString("FILEPATH"));
				foto.setFecha(rs.getLong("FECHA"));

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

		return foto;
	}



	@Override
	public List<Foto> getFotos(String email_usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Foto> fotos = new ArrayList<Foto>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.FOTOS where EMAIL = ?");
			ps.setString(1, email_usuario);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				Date fecha_ok = new Date(rs.getLong("FECHA"));
				SimpleDateFormat fecha_format = new SimpleDateFormat("dd/MM/yyyy");
								
				String Fecha=fecha_format.format(fecha_ok);

				Foto foto = new Foto();
				foto.setID(rs.getLong("ID"));
				foto.setEmail(rs.getString("EMAIL"));
				foto.setTitulo(rs.getString("TITULO"));
				foto.setPath(rs.getString("FILEPATH"));
				foto.setFecha_legible(Fecha);
				
				fotos.add(foto);

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

		return fotos;
	}
	
	
	@Override
	public List<Foto> getFotosSeguidores(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Foto> fotos = new ArrayList<Foto>();

		try {

			String SQL_DRV  = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM PUBLIC.FOTOS WHERE EMAIL IN (SELECT EMAIL_SEGUIDOR FROM PUBLIC.SEGUIDORES WHERE EMAIL_USUARIO= ? AND ACEPTADA='1') OR EMAIL= ?");
			ps.setString(1, email);
			ps.setString(2, email);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				Date fecha_ok = new Date(rs.getLong("FECHA"));
				SimpleDateFormat fecha_format = new SimpleDateFormat("dd/MM/yyyy");
								
				String Fecha=fecha_format.format(fecha_ok);
	

				Foto foto = new Foto();
				foto.setID(rs.getLong("ID"));
				foto.setEmail(rs.getString("EMAIL"));
				foto.setTitulo(rs.getString("TITULO"));
				foto.setPath(rs.getString("FILEPATH"));
				foto.setFecha_legible(Fecha);
				
				fotos.add(foto);

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

		return fotos;
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
			ps = con.prepareStatement("delete from PUBLIC.FOTOS where EMAIL = ?");

			ps.setString(1, email);

			rows = ps.executeUpdate();
			if (rows < 0) {
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
}

