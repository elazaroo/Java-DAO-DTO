package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.CategoryDTO;

public class CategoryDAO implements DAOPattern<CategoryDTO> {
	
	private static final String SQL_INSERT = "INSERT INTO category (name, last_update) VALUES (?, ?)";
	private static final String SQL_DELETE = "DELETE FROM category WHERE category_id = ?";
	private static final String SQL_UPDATE = "UPDATE category SET name = ?, last_update = ? WHERE category_id = ?";
	private static final String SQL_SELECT = "SELECT * FROM category WHERE category_id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM category";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();


	@Override
	public boolean insert(CategoryDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setString(1, t.getName());
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
	public boolean update(CategoryDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getName());
			ps.setTimestamp(2, t.getLast_update());
			ps.setInt(3, t.getId());

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
	public CategoryDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT);
			ps.setInt(1, (int) pk);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				CategoryDTO category = new CategoryDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3));
				return category;
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
	public ArrayList<CategoryDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<CategoryDTO> categories = new ArrayList<CategoryDTO>();
            
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3));
                categories.add(category);
            }
            
            return categories;
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
