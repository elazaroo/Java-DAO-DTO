package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectionX;
import dto.LanguageDTO;

public class LanguageDAO implements DAOPattern<LanguageDTO>{
	
	private final String SQL_INSERT = "INSERT INTO language (language_id, name, last_update) VALUES (?, ?, ?)";
	private final String SQL_DELETE = "DELETE FROM language WHERE language_id = ?";
	private final String SQL_UPDATE = "UPDATE language SET language_id = ?, name = ?, last_update = ? WHERE language_id = ?";
	private final String SQL_SELECT = "SELECT * FROM language WHERE language_id = ?";
	private final String SQL_SELECT_ALL = "SELECT * FROM language";
	
	// Connection object creation
	private ConnectionX conn = ConnectionX.getInstance();

	@Override
	public boolean insert(LanguageDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getLanguage_id());
            ps.setString(2, t.getName());
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
            ps.setInt(1, ((LanguageDTO) pk).getLanguage_id());
            
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
	public boolean update(LanguageDTO t) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getLanguage_id());
            ps.setString(2, t.getName());
            ps.setTimestamp(3, t.getLast_update());
            ps.setInt(4, t.getLanguage_id());

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
	public LanguageDTO find(Object pk) {
		PreparedStatement ps = null;
		
		try {
            ps = conn.getConnection().prepareStatement(SQL_SELECT);
            ps.setInt(1, ((LanguageDTO) pk).getLanguage_id());
            
            if (ps.executeQuery() != null) {
                return (LanguageDTO) ps.executeQuery();
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
	public ArrayList<LanguageDTO> selectAll() {
		PreparedStatement ps = null;
		
		try {
			ps = conn.getConnection().prepareStatement(SQL_SELECT_ALL);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<LanguageDTO> languages = new ArrayList<LanguageDTO>();

			while(rs.next()) {
				languages.add(new LanguageDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3)));
			}
			return languages;
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
