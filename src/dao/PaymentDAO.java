package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.PaymentDTO;

public class PaymentDAO implements DAOPattern<PaymentDTO>{
	
	private final String SQL_INSERT = "INSERT INTO payment (payment_id, customer_id, staff_id, rental_id, amount, payment_date, last_update) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM payment WHERE payment_id = ?";
	private final String SQL_UPDATE = "UPDATE payment SET payment_id = ?, customer_id = ?, staff_id = ?, rental_id = ?, amount = ?, payment_date = ?, last_update = ? WHERE payment_id = ?";
	private final String SQL_SELECT = "SELECT * FROM payment WHERE payment_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM payment";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(PaymentDTO t) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getPayment_id());
			ps.setInt(2, t.getCustomer_id());
			ps.setInt(3, t.getStaff_id());
			ps.setInt(4, t.getRental_id());
			ps.setDouble(5, t.getAmount());
			ps.setTimestamp(6, t.getPayment_date());
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
            ps.setInt(1, ((PaymentDTO) pk).getPayment_id());
            
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
	public boolean update(PaymentDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getPayment_id());
            ps.setInt(2, t.getCustomer_id());
            ps.setInt(3, t.getStaff_id());
            ps.setInt(4, t.getRental_id());
            ps.setDouble(5, t.getAmount());
            ps.setTimestamp(6, t.getPayment_date());
            ps.setTimestamp(7, t.getLast_update());
            ps.setInt(8, t.getPayment_id());
            
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
	public PaymentDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((PaymentDTO) pk).getPayment_id());
            
            if (ps.executeQuery() != null) {
                return (PaymentDTO) ps.executeQuery();
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
	public ArrayList<PaymentDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<PaymentDTO> payments = new ArrayList<PaymentDTO>();
			
			while (rs.next()) {
				payments.add(new PaymentDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5),
						rs.getTimestamp(6), rs.getTimestamp(7)));
			}
			
			return payments;
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
