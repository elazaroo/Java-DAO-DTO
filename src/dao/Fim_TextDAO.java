package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.Film_TextDTO;

public class Fim_TextDAO implements DAOPattern<Film_TextDTO>{
	
	private final String SQL_INSERT = "INSERT INTO film_text (film_id, title, description) VALUES (?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM film_text WHERE film_id = ?";
	private final String SQL_UPDATE = "UPDATE film_text SET film_id = ?, title = ?, description = ? WHERE film_id = ?";
	private final String SQL_SELECT = "SELECT * FROM film_text WHERE film_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM film_text";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(Film_TextDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getFilm_id());
            ps.setString(2, t.getTitle());
            ps.setString(3, t.getDescription());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (ps != null)
                    ps.close(); // Close the PreparedStatement
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }    
		return false;
	}

	@Override
	public boolean delete(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_DELETE);
            ps.setInt(1, ((Film_TextDTO) pk).getFilm_id());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		finally {
            try {
                if (ps != null)
                    ps.close(); // Close the PreparedStatement
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		return false;
	}

	@Override
	public boolean update(Film_TextDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getFilm_id());
            ps.setString(2, t.getTitle());
            ps.setString(3, t.getDescription());
            ps.setInt(4, t.getFilm_id());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		finally {
			try {
				if (ps != null)
					ps.close(); // Close the PreparedStatement
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Film_TextDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((Film_TextDTO) pk).getFilm_id());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Film_TextDTO film_text = new Film_TextDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
                
                return film_text;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		finally {
			try {
                if (ps != null)
                    ps.close(); // Close the PreparedStatement
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		return null;
	}

	@Override
	public ArrayList<Film_TextDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);

			ResultSet rs = ps.executeQuery();

			ArrayList<Film_TextDTO> film_texts = new ArrayList<Film_TextDTO>();

			while (rs.next()) {
				Film_TextDTO film_text = new Film_TextDTO(rs.getInt(1), rs.getString(2), rs.getString(3));

				film_texts.add(film_text);
			}

			return film_texts;
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (ps != null)
					ps.close(); // Close the PreparedStatement
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	

}
