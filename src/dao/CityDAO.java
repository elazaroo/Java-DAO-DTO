package dao;

import connection.ConnectionX;
import dto.CityDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDAO implements DAOPattern<CityDTO> {

	private static final String SQL_INSERT = "INSERT INTO city (city, country_id, last_update) VALUES (?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM city WHERE city_id = ?";
	private static final String SQL_UPDATE = "UPDATE city SET city = ?, country_id = ?, last_update = ? WHERE city_id = ?";
	private static final String SQL_SELECT = "SELECT * FROM city WHERE city_id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM city";
	
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();
	
	
	@Override
	public boolean insert(CityDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setString(1, t.getCity());
			ps.setInt(2, t.getCountry_id());
			ps.setTimestamp(3, t.getLast_update());

			if (ps.executeUpdate() > 0) {
				return true;
			}
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
				if (ps != null)
					ps.close(); // Close the PreparedStatement
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	@Override
	public boolean update(CityDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getCity());
            ps.setInt(2, t.getCountry_id());
            ps.setTimestamp(3, t.getLast_update());
            ps.setInt(4, t.getCity_id());
            
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
	public CityDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT);
			ps.setInt(1, (int) pk);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				CityDTO city = new CityDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4));
				return city;
			}
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
	@Override
	public ArrayList<CityDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<CityDTO> cities = new ArrayList<CityDTO>();
            
            while(rs.next()) {
                CityDTO city = new CityDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4));
                cities.add(city);
            }
            
            return cities;
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
		return null;
	}
}
