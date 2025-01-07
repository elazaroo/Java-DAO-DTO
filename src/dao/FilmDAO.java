package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.FilmDTO;

public class FilmDAO implements DAOPattern<FilmDTO>{
	
	private final String SQL_INSERT = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, last_update) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM film WHERE film_id = ?";
	private final String SQL_UPDATE = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ?, last_update = ? WHERE film_id = ?";
	private final String SQL_SELECT = "SELECT * FROM film WHERE film_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM film";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(FilmDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, t.getTitle());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getRelease_year());
            ps.setInt(4, t.getLanguage_id());
            ps.setInt(5, t.getRental_duration());
            ps.setDouble(6, t.getRental_rate());
            ps.setInt(7, t.getLength());
            ps.setDouble(8, t.getReplacement_cost());
            ps.setString(9, t.getRating());
            ps.setString(10, t.getSpecial_features());
            ps.setTimestamp(11, t.getLast_update());

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
            ps.setInt(1, ((FilmDTO) pk).getFilm_id());
            
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
	public boolean update(FilmDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getTitle());
			ps.setString(2, t.getDescription());
			ps.setInt(3, t.getRelease_year());
			ps.setInt(4, t.getLanguage_id());
			ps.setInt(5, t.getRental_duration());
			ps.setDouble(6, t.getRental_rate());
			ps.setInt(7, t.getLength());
			ps.setDouble(8, t.getReplacement_cost());
			ps.setString(9, t.getRating());
			ps.setString(10, t.getSpecial_features());
			ps.setTimestamp(11, t.getLast_update());
			ps.setInt(12, t.getFilm_id());

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
	public FilmDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT);
			ps.setInt(1, ((FilmDTO) pk).getFilm_id());

			if (ps.executeQuery() != null) {
				return (FilmDTO) pk;
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
	public ArrayList<FilmDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<FilmDTO> films = new ArrayList<FilmDTO>();
            
            while(rs.next()) {
            	films.add(new FilmDTO(
        		    rs.getInt("film_id"), 
        		    rs.getString("title"), 
        		    rs.getString("description"), 
        		    rs.getInt("release_year"), 
        		    rs.getInt("language_id"), 
        		    rs.getInt("original_language_id"), 
        		    rs.getInt("rental_duration"), 
        		    rs.getDouble("rental_rate"), 
        		    rs.getInt("length"), 
        		    rs.getDouble("replacement_cost"), 
        		    rs.getString("rating"), 
        		    rs.getString("special_features"), 
        		    rs.getTimestamp("last_update")
        		));
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
	
	

}
