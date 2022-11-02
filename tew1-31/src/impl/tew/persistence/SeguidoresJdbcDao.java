package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tew.model.Seguidores;
import com.tew.persistence.SeguidoresDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class SeguidoresJdbcDao implements SeguidoresDao {

	@Override
	public List<Seguidores> getSeguidores() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Seguidores> seguidores = new ArrayList<Seguidores>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.SEGUIDORES");
			rs = ps.executeQuery();

			while (rs.next()) {

				Seguidores seguidor = new Seguidores();

				seguidor.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				seguidor.setEmail_seguidor(rs.getString("EMAIL_SEGUIDOR"));
				seguidor.setAceptada(rs.getBoolean("ACEPTADA"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) { try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return seguidores;
	}

	@Override
	public List<Seguidores> getSeguidores(String email_usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Seguidores> seguidores = new ArrayList<Seguidores>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.SEGUIDORES where EMAIL_SEGUIDOR = ? and ACEPTADA = '0' ");
			ps.setString(1, email_usuario);

			rs = ps.executeQuery();

			while (rs.next()) {

				Seguidores seguidor = new Seguidores();

				seguidor.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				seguidor.setEmail_seguidor(rs.getString("EMAIL_SEGUIDOR"));
				seguidor.setAceptada(rs.getBoolean("ACEPTADA"));

				seguidores.add(seguidor);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return seguidores;
	}

	
	@Override
	public List<Seguidores> getSeguidores12(String email_usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Seguidores> seguidores = new ArrayList<Seguidores>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.SEGUIDORES where EMAIL_SEGUIDOR = ? and ACEPTADA = '0' ");
			ps.setString(1, email_usuario);

			rs = ps.executeQuery();

			while (rs.next()) {

				Seguidores seguidor = new Seguidores();

				seguidor.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				seguidor.setEmail_seguidor(rs.getString("EMAIL_SEGUIDOR"));
				seguidor.setAceptada(rs.getBoolean("ACEPTADA"));

				seguidores.add(seguidor);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return seguidores;
	}
	
	@Override
	public void save(Seguidores a) throws AlreadyPersistedException {

		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into PUBLIC.SEGUIDORES (EMAIL_USUARIO, EMAIL_SEGUIDOR, ACEPTADA) " + "values (?, ?, ?)");

			ps.setString(2, a.getEmail_usuario());
			ps.setString(3, a.getEmail_seguidor());
			ps.setBoolean(3, a.isAceptada());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Seguidor " + a + " already persisted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}
	
	@Override
	public void save12(String s, String l) throws AlreadyPersistedException {

		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into PUBLIC.SEGUIDORES (EMAIL_USUARIO, EMAIL_SEGUIDOR, ACEPTADA) " + "values (?, ?, ?)");

			ps.setString(2, s);
			ps.setString(3, l);
			ps.setBoolean(3, true);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Seguidor " + s + " already persisted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public Seguidores findByEmail(String email_usuario) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Seguidores seguidor = null;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.SEGUIDORES where EMAIL_USUARIO = ?");
			ps.setString(1, email_usuario);

			rs = ps.executeQuery();
			if (rs.next()) {
				seguidor = new Seguidores();

				seguidor.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				seguidor.setEmail_seguidor(rs.getString("EMAIL_SEGUIDOR"));
				seguidor.setAceptada(rs.getBoolean("ACEPTADA"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return seguidor;
	}

	@Override
	public void aceptar(String email_usuario, String email_seguidor) throws NotPersistedException {

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		Connection con = null;
		int rows = 0;

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"UPDATE PUBLIC.SEGUIDORES SET ACEPTADA='1' WHERE EMAIL_USUARIO=? AND EMAIL_SEGUIDOR = ? AND ACEPTADA='0'");

			ps.setString(1, email_usuario);
			ps.setString(2, email_seguidor);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuario " + email_usuario + " not found");
			}

			ps2 = con.prepareStatement("insert into PUBLIC.SEGUIDORES (EMAIL_USUARIO, EMAIL_SEGUIDOR, ACEPTADA) values ( ?, ?, '1')");

			ps2.setString(1, email_seguidor);
			ps2.setString(2, email_usuario);

			rows = ps2.executeUpdate();

			if (rows != 1) {
				throw new NotPersistedException("Usuario " + email_usuario + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public List<Seguidores> getCandidatos(String email_seguidor) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Seguidores> seguidores = new ArrayList<Seguidores>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.SEGUIDORES where EMAIL_USUARIO = ? and ACEPTADA = '1' ");
			ps.setString(1, email_seguidor);

			rs = ps.executeQuery();

			while (rs.next()) {

				Seguidores seguidor = new Seguidores();

				seguidor.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				seguidor.setEmail_seguidor(rs.getString("EMAIL_SEGUIDOR"));
				seguidor.setAceptada(rs.getBoolean("ACEPTADA"));

				seguidores.add(seguidor);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return seguidores;
	}

	@Override
	public List<Seguidores> getCandidatos1(String email_seguidor) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Seguidores> seguidores = new ArrayList<Seguidores>();

		try {

			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			Class.forName(SQL_DRV);

			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLIC.SEGUIDORES where (EMAIL_USUARIO = ? and ACEPTADA = '1') ");
			ps.setString(1, email_seguidor);

			rs = ps.executeQuery();

			while (rs.next()) {

				Seguidores seguidor = new Seguidores();

				seguidor.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				seguidor.setEmail_seguidor(rs.getString("EMAIL_SEGUIDOR"));
				seguidor.setAceptada(rs.getBoolean("ACEPTADA"));

				seguidores.add(seguidor);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return seguidores;
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
            ps = con.prepareStatement("delete from PUBLIC.SEGUIDORES where EMAIL_USUARIO = ? or EMAIL_SEGUIDOR=?");

            ps.setString(1, email);
            ps.setString(2, email);

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
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                }
            }
            ;
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }
    }


    public void delete1(String emailUsuario,String emailSeguidor) throws NotPersistedException {

        PreparedStatement ps = null;
        Connection con = null;
        int rows = 0;

        try {

            String SQL_DRV = "org.hsqldb.jdbcDriver";
            String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

            Class.forName(SQL_DRV);
            con = DriverManager.getConnection(SQL_URL, "sa", "");
            ps = con.prepareStatement("delete from PUBLIC.SEGUIDORES where (EMAIL_USUARIO = ? AND EMAIL_SEGUIDOR=? AND ACEPTADA='1')");

            ps.setString(1, emailUsuario);
            ps.setString(2, emailSeguidor);

            rows = ps.executeUpdate();

            if (rows < 0) {
                throw new NotPersistedException("Usuario not found");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException("Invalid SQL or database schema", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                }
            }
            ;
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }
    }

		
}