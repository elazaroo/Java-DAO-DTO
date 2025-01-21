package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.StaffDTO;

public class StaffDAO implements DAOPattern<StaffDTO>{
	
	private final String SQL_INSERT = "INSERT INTO staff (staff_id, first_name, last_name, address_id, email, store_id, active, username, password, last_update) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM staff WHERE staff_id = ?";
	private final String SQL_UPDATE = "UPDATE staff SET staff_id = ?, first_name = ?, last_name = ?, address_id = ?, email = ?, store_id = ?, active = ?, username = ?, password = ?, last_update = ? WHERE staff_id = ?";
	private final String SQL_SELECT = "SELECT * FROM staff WHERE staff_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM staff";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(StaffDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getStaff_id());
            ps.setString(2, t.getFirst_name());
            ps.setString(3, t.getLast_name());
            ps.setInt(4, t.getAddress_id());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getStore_id());
            ps.setInt(7, t.getActive());
            ps.setString(8, t.getUsername());
            ps.setString(9, t.getPassword());
            ps.setTimestamp(10, t.getLast_update());

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
            ps.setInt(1, ((StaffDTO) pk).getStaff_id());
            
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
	public boolean update(StaffDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getStaff_id());
            ps.setString(2, t.getFirst_name());
            ps.setString(3, t.getLast_name());
            ps.setInt(4, t.getAddress_id());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getStore_id());
            ps.setInt(7, t.getActive());
            ps.setString(8, t.getUsername());
            ps.setString(9, t.getPassword());
            ps.setTimestamp(10, t.getLast_update());
            ps.setInt(11, t.getStaff_id());
            
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
	public StaffDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((StaffDTO) pk).getStaff_id());
            
            if (ps.executeQuery() != null) {
                return (StaffDTO) ps.executeQuery();
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
	public ArrayList<StaffDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);

            ResultSet rs = ps.executeQuery();
            
            ArrayList<StaffDTO> staffs = new ArrayList<>();
            
			while (rs.next()) {
				staffs.add(new StaffDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getBlob(5), rs.getString(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getTimestamp(11)));
			}
			return staffs;
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
