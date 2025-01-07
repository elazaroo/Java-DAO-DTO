package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.Film_CategoryDTO;

public class Film_CategoryDAO implements DAOPattern<Film_CategoryDTO>{
	
	private final String SQL_INSERT = "INSERT INTO film_category (film_id, category_id, last_update) VALUES (?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM film_category WHERE film_id = ? AND category_id = ?";
	private final String SQL_UPDATE = "UPDATE film_category SET film_id = ?, category_id = ?, last_update = ? WHERE film_id = ? AND category_id = ?";
	private final String SQL_SELECT = "SELECT * FROM film_category WHERE film_id = ? AND category_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM film_category";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(Film_CategoryDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getFilm_id());
			ps.setInt(2, t.getCategory_id());
			ps.setTimestamp(3, t.getLast_update());

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
            ps.setInt(1, ((Film_CategoryDTO) pk).getFilm_id());
            ps.setInt(2, ((Film_CategoryDTO) pk).getCategory_id());
            
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
	public boolean update(Film_CategoryDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_UPDATE);
			ps.setInt(1, t.getFilm_id());
			ps.setInt(2, t.getCategory_id());
			ps.setTimestamp(3, t.getLast_update());
			ps.setInt(4, t.getFilm_id());
			ps.setInt(5, t.getCategory_id());

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
	public Film_CategoryDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((Film_CategoryDTO) pk).getFilm_id());
            ps.setInt(2, ((Film_CategoryDTO) pk).getCategory_id());
            ps.setTimestamp(3, ((Film_CategoryDTO) pk).getLast_update());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Film_CategoryDTO(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3));
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
	public ArrayList<Film_CategoryDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = ps.executeQuery();

			ArrayList<Film_CategoryDTO> film_category = new ArrayList<Film_CategoryDTO>();

			while (rs.next()) {
				film_category.add(new Film_CategoryDTO(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3)));
			}
			return film_category;
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
