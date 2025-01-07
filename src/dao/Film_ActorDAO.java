package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.Film_ActorDTO;

public class Film_ActorDAO implements DAOPattern<Film_ActorDTO> {
	
	private final String SQL_INSERT = "INSERT INTO film_actor (actor_id, film_id, last_update) VALUES (?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM film_actor WHERE actor_id = ? AND film_id = ?";
	private final String SQL_UPDATE = "UPDATE film_actor SET actor_id = ?, film_id = ?, last_update = ? WHERE actor_id = ? AND film_id = ?";
	private final String SQL_SELECT = "SELECT * FROM film_actor WHERE actor_id = ? AND film_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM film_actor";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(Film_ActorDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getActor_id());
			ps.setInt(2, t.getFilm_id());
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
            ps.setInt(1, ((Film_ActorDTO) pk).getActor_id());
            ps.setInt(2, ((Film_ActorDTO) pk).getFilm_id());
            
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
	public boolean update(Film_ActorDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getActor_id());
            ps.setInt(2, t.getFilm_id());
            ps.setTimestamp(3, t.getLast_update());
            ps.setInt(4, t.getActor_id());
            ps.setInt(5, t.getFilm_id());
            
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
	public Film_ActorDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT);
			ps.setInt(1, ((Film_ActorDTO) pk).getActor_id());
			ps.setInt(2, ((Film_ActorDTO) pk).getFilm_id());

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Film_ActorDTO(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3));
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
	public ArrayList<Film_ActorDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Film_ActorDTO> film_actors = new ArrayList<Film_ActorDTO>();
            
            while (rs.next()) {
                film_actors.add(new Film_ActorDTO(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3)));
            }
            return film_actors;
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
