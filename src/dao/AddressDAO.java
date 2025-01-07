package dao;

import connection.ConnectionX;
import dto.AddressDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddressDAO implements DAOPattern<AddressDTO> {
	
	private static final String SQL_INSERT = "INSERT INTO address (address, address2, district, city_id, postal_code, phone, last_update) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM address WHERE address_id = ?";
	private static final String SQL_UPDATE = "UPDATE address SET address = ?, address2 = ?, district = ?, city_id = ?, postal_code = ?, phone = ?, last_update = ? WHERE address_id = ?";
	private static final String SQL_SELECT = "SELECT * FROM address WHERE address_id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM address";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(AddressDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, t.getAddress());
            ps.setString(2, t.getAddress2());
            ps.setString(3, t.getDistrict());
            ps.setInt(4, t.getCity_id());
            ps.setString(5, t.getPostal_code());
            ps.setString(6, t.getPhone());
            ps.setTimestamp(7, t.getLast_update());
            
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
	public boolean update(AddressDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getAddress());
			ps.setString(2, t.getAddress2());
			ps.setString(3, t.getDistrict());
			ps.setInt(4, t.getCity_id());
			ps.setString(5, t.getPostal_code());
			ps.setString(6, t.getPhone());
			ps.setTimestamp(7, t.getLast_update());
			ps.setInt(8, t.getAddress_id());

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
	public AddressDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT);
			ps.setInt(1, (int) pk);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				AddressDTO address = new AddressDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8));
				return address;
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
	public ArrayList<AddressDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<AddressDTO> addresses = new ArrayList<AddressDTO>();
            
            while(rs.next()) {
                AddressDTO address = new AddressDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8));
                addresses.add(address);
            }
            return addresses;
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
