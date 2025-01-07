package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.RentalDTO;

public class RentalDAO implements DAOPattern<RentalDTO>{
	
	private final String SQL_INSERT = "INSERT INTO rental (rental_id, rental_date, inventory_id, customer_id, return_date, staff_id, last_update) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM rental WHERE rental_id = ?";
	private final String SQL_UPDATE = "UPDATE rental SET rental_date = ?, inventory_id = ?, customer_id = ?, return_date = ?, staff_id = ?, last_update = ? WHERE rental_id = ?";
	private final String SQL_SELECT = "SELECT * FROM rental WHERE rental_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM rental";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(RentalDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getRental_id());
            ps.setTimestamp(2, t.getRental_date());
            ps.setInt(3, t.getInventory_id());
            ps.setInt(4, t.getCustomer_id());
            ps.setTimestamp(5, t.getReturn_date());
            ps.setInt(6, t.getStaff_id());
            ps.setTimestamp(7, t.getLast_update());

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
            ps.setInt(1, ((RentalDTO) pk).getRental_id());
            
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
	public boolean update(RentalDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setTimestamp(1, t.getRental_date());
            ps.setInt(2, t.getInventory_id());
            ps.setInt(3, t.getCustomer_id());
            ps.setTimestamp(4, t.getReturn_date());
            ps.setInt(5, t.getStaff_id());
            ps.setTimestamp(6, t.getLast_update());
            ps.setInt(7, t.getRental_id());
            
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
	public RentalDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((RentalDTO) pk).getRental_id());
            
            if (ps.executeQuery() != null) {
                return (RentalDTO) ps.executeQuery();
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
	public ArrayList<RentalDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<RentalDTO> rentals = new ArrayList<RentalDTO>();
            
            while (rs.next()) {
                rentals.add(new RentalDTO(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5), rs.getInt(6), rs.getTimestamp(7)));
            }
            return rentals;
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
