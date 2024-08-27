package implementsDao;

import domain.Usuario;
import java.util.List;

public interface DaoUser {

    public String create(String ruta, Usuario obj, boolean modo);
    public List<Usuario> getAll(String ruta);
    public String update(String ruta, Usuario obj);
    public Usuario getOne(String ruta, String user);
    public String delete(String ruta, String user);
    
}
