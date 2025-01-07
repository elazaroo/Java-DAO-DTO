package dao;

import connection.ConnectionX;
import dto.ActorDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ActorDAO implements DAOPattern<ActorDTO> {
	
	private static final String SQL_INSERT = "INSERT INTO actor (first_name, last_name, last_update) VALUES (?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM actor WHERE actor_id = ?";
	private static final String SQL_UPDATE = "UPDATE actor SET first_name = ?, last_name = ?, last_update = ? WHERE actor_id = ?";
	private static final String SQL_SELECT = "SELECT * FROM actor WHERE actor_id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM actor";
	
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();


	@Override
	public boolean insert(ActorDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setString(1, t.getFirst_name());
			ps.setString(2, t.getLast_name());
			ps.setTimestamp(3, t.getLast_update());
			
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch(SQLException e) {
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
			ps.setInt(1, (int) pk);

			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(ps != null) ps.close(); // Close the PreparedStatement
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}


	@Override
	public boolean update(ActorDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getFirst_name());
            ps.setString(2, t.getLast_name());
            ps.setTimestamp(3, t.getLast_update());
            ps.setInt(4, t.getActor_id());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		finally {
			try {
				if(ps != null) ps.close(); // Close the PreparedStatement
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}


	@Override
	public ActorDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) pk);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
            	ActorDTO actor = new ActorDTO(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getTimestamp("last_update"));
                return actor;
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
            try {
                if(ps != null) ps.close(); // Close the PreparedStatement
            }catch(Exception e2) {
                e2.printStackTrace();
            }
        }
		return null;
	}


	@Override
	public ArrayList<ActorDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<ActorDTO> actors = new ArrayList<ActorDTO>();
            
            while(rs.next()) {
                ActorDTO actor = new ActorDTO(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getTimestamp("last_update"));
                actors.add(actor);
            }
            return actors;
        } catch (SQLException e) {
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
