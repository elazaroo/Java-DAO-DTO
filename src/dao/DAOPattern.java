package dao;

import java.util.ArrayList;

public interface DAOPattern <TipoGen>{
	    public boolean insert(TipoGen t);
	    public boolean delete(Object pk);
	    public boolean update(TipoGen t);
	    public TipoGen find(Object pk);
	    public ArrayList<TipoGen> selectAll();
}
