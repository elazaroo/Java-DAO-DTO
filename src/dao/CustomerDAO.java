package dao;

import connection.ConnectionX;
import dto.CustomerDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class CustomerDAO implements DAOPattern<CustomerDTO> {

	private static final String SQL_INSERT = "INSERT INTO customer (store_id, first_name, last_name, email, address_id, active, create_date, last_update) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM customer WHERE customer_id = ?";
	private static final String SQL_UPDATE = "UPDATE customer SET store_id = ?, first_name = ?, last_name = ?, email = ?, address_id = ?, active = ?, create_date = ?, last_update = ? WHERE customer_id = ?";
	private static final String SQL_SELECT = "SELECT * FROM customer WHERE customer_id = ?";
	private static final String SQL_SELECT_BY_COUNTRY = "SELECT * FROM customer WHERE address_id IN (SELECT address_id FROM address WHERE city_id IN (SELECT city_id FROM city WHERE country_id IN (SELECT country_id FROM country WHERE country = ?)))";
	private static final String SQL_SELECT_BY_STAFF = "SELECT * FROM customer WHERE store_id IN (SELECT store_id FROM store WHERE manager_staff_id IN (SELECT staff_id FROM staff WHERE username = ?))";
	private static final String SQL_SELECT_ALL = "SELECT * FROM customer";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(CustomerDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getStore_id());
			ps.setString(2, t.getFirst_name());
			ps.setString(3, t.getLast_name());
			ps.setString(4, t.getEmail());
			ps.setInt(5, t.getAddress_id());
			ps.setInt(6, t.getActive());
			ps.setTimestamp(7, t.getCreate_date());
			ps.setTimestamp(8, t.getLast_update());

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
	public boolean update(CustomerDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getStore_id());
            ps.setString(2, t.getFirst_name());
            ps.setString(3, t.getLast_name());
            ps.setString(4, t.getEmail());
            ps.setInt(5, t.getAddress_id());
            ps.setInt(6, t.getActive());
            ps.setTimestamp(7, t.getCreate_date());
            ps.setTimestamp(8, t.getLast_update());
            ps.setInt(9, t.getCustomer_id());
            
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
	public CustomerDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) pk);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                CustomerDTO customer = new CustomerDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getTimestamp(9));                
                return customer;
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
	
	public ArrayList<CustomerDTO> findByCountry(String country) {
		PreparedStatement ps = null;
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        
        try {
        	ps = conn.getConnection().prepareStatement(SQL_SELECT_BY_COUNTRY);
            ps.setString(1, country);
            
        	ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CustomerDTO customer = new CustomerDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getTimestamp(9));                
                customers.add(customer);
            }
            
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
	
	public ArrayList<CustomerDTO> findByStaff(String staff) {
		PreparedStatement ps = null;
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        
        try {
        	ps = conn.getConnection().prepareStatement(SQL_SELECT_BY_STAFF);
            ps.setString(1, staff);
            
        	ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CustomerDTO customer = new CustomerDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getTimestamp(9));                
                customers.add(customer);
            }
            
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

	@Override
	public ArrayList<CustomerDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<CustomerDTO> customers = new ArrayList<CustomerDTO>();
            
            while (rs.next()) {
                CustomerDTO customer = new CustomerDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8), rs.getTimestamp(9));
                customers.add(customer);
            }
            return customers;
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
