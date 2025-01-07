package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.StoreDTO;

public class StoreDAO implements DAOPattern<StoreDTO>{
	private final String SQL_INSERT = "INSERT INTO store (store_id, manager_staff_id, address_id, last_update) VALUES (?, ?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM store WHERE store_id = ?";
	private final String SQL_UPDATE = "UPDATE store SET store_id = ?, manager_staff_id = ?, address_id = ?, last_update = ? WHERE store_id = ?";
	private final String SQL_SELECT = "SELECT * FROM store WHERE store_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM store";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(StoreDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getStore_id());
            ps.setInt(2, t.getManager_staff_id());
            ps.setInt(3, t.getAddress_id());
            ps.setTimestamp(4, t.getLast_update());

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
            ps.setInt(1, ((StoreDTO) pk).getStore_id());
            
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
	public boolean update(StoreDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getStore_id());
            ps.setInt(2, t.getManager_staff_id());
            ps.setInt(3, t.getAddress_id());
            ps.setTimestamp(4, t.getLast_update());
            ps.setInt(5, t.getStore_id());

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
	public StoreDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((StoreDTO) pk).getStore_id());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                StoreDTO store = new StoreDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4));
                
                return store;
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
	public ArrayList<StoreDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);

			ResultSet rs = ps.executeQuery();
			ArrayList<StoreDTO> stores = new ArrayList<StoreDTO>();

			while (rs.next()) {
				StoreDTO store = new StoreDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4));
				stores.add(store);
			}

			return stores;
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
