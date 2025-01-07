package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.InventoryDTO;

public class InventoryDAO implements DAOPattern<InventoryDTO>{
	
	private final String SQL_INSERT = "INSERT INTO inventory (film_id, store_id, last_update) VALUES (?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM inventory WHERE film_id = ? AND store_id = ?";
	private final String SQL_UPDATE = "UPDATE inventory SET film_id = ?, store_id = ?, last_update = ? WHERE film_id = ? AND store_id = ?";
	private final String SQL_SELECT = "SELECT * FROM inventory WHERE film_id = ? AND store_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM inventory";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(InventoryDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getFilm_id());
            ps.setInt(2, t.getStore_id());
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
            ps.setInt(1, ((InventoryDTO) pk).getFilm_id());
            ps.setInt(2, ((InventoryDTO) pk).getStore_id());
            
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
	public boolean update(InventoryDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getFilm_id());
            ps.setInt(2, t.getStore_id());
            ps.setTimestamp(3, t.getLast_update());
            ps.setInt(4, t.getFilm_id());
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
	public InventoryDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((InventoryDTO) pk).getInventory_id());
            ps.setInt(2, ((InventoryDTO) pk).getFilm_id());
            ps.setInt(3, ((InventoryDTO) pk).getStore_id());
            ps.setTimestamp(4, ((InventoryDTO) pk).getLast_update());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                InventoryDTO inventory = new InventoryDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(3));
                
                return inventory;
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
	public ArrayList<InventoryDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);

			ResultSet rs = ps.executeQuery();

			ArrayList<InventoryDTO> inventories = new ArrayList<InventoryDTO>();

			while (rs.next()) {
				InventoryDTO inventory = new InventoryDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4));

				inventories.add(inventory);
			}

			return inventories;
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
