package dao;

import connection.ConnectionX;
import dto.CountryDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDAO implements DAOPattern<CountryDTO> {

	
	private static final String SQL_INSERT = "INSERT INTO country (country, last_update) VALUES (?, ?)";
	private static final String SQL_DELETE = "DELETE FROM country WHERE country_id = ?";
	private static final String SQL_UPDATE = "UPDATE country SET country = ?, last_update = ? WHERE country_id = ?";
	private static final String SQL_SELECT = "SELECT * FROM country WHERE country_id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM country";
	
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();
	
	@Override
	public boolean insert(CountryDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setString(1, t.getCountry());
			ps.setTimestamp(2, t.getLast_update());

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
	public boolean update(CountryDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getCountry());
			ps.setTimestamp(2, t.getLast_update());
			ps.setInt(3, t.getCountry_id());

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
	public CountryDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT);
			ps.setInt(1, (int) pk);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				CountryDTO country = new CountryDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3));
				return country;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public ArrayList<CountryDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);

			ResultSet rs = ps.executeQuery();
			ArrayList<CountryDTO> countries = new ArrayList<CountryDTO>();

			while (rs.next()) {
				CountryDTO country = new CountryDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3));
				countries.add(country);
			}
			return countries;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
