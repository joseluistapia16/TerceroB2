package archivos;

import domain.Usuario;
import implementsDao.DaoUser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Archivo implements DaoUser {

    String mensaje = null;

    public String createUpdate(String ruta, String msg, boolean modo) {
        try {
            FileWriter escribir = null;
            File archivo = new File(ruta);
            escribir = new FileWriter(ruta, modo);
            escribir.write(msg);
            escribir.close();
            mensaje = "El registro se creo con exito";
        } catch (IOException ex) {
            mensaje = "El registro no se creo con exito";
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

    @Override
    public String create(String ruta, Usuario obj, boolean modo) {
        FileWriter escritura = null;
        var msg = "";
        try {
            File archivo = new File(ruta);
            escritura = new FileWriter(archivo, modo);
            String cadena = obj.getUsername() + ";" + obj.getPassword() + ";" + obj.getNombres() + ";"
                    + obj.getApellidos() + ";" + obj.getEmail() + ";\n";
            escritura.write(cadena);
            escritura.close();
            msg = "el registro se ha creado con exito!!!";
        } catch (IOException ex) {
            msg = "ocurrio un error al momento de crear el registro :(";
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escritura.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return msg;

    }

    @Override
    public List<Usuario> getAll(String ruta) {
        BufferedReader contenido = null;
        List<Usuario> lista = new ArrayList<>();
        try {
            contenido = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = contenido.readLine()) != null) {
                String[] fila = linea.split(";");
                Usuario obj = new Usuario(fila[0], fila[1], fila[2], fila[3], fila[4]);
                lista.add(obj);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                contenido.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public String update(String ruta, Usuario obj) {
        List<Usuario> lista = getAll(ruta);
        String datos = "";
        String mensaje = "";
        int pos = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (obj.getUsername().equals(lista.get(i).getUsername())) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            lista.set(pos, obj);
            for (int i = 0; i < lista.size(); i++) {
                datos = datos + lista.get(i).getUsername() + ";" + lista.get(i).getPassword()
                        + ";" + lista.get(i).getNombres() + ";" + lista.get(i).getApellidos()
                        + ";" + lista.get(i).getEmail() + ";\n";
            }
//            System.out.println(datos);
            createUpdate(ruta, datos, false);//copien esto ve
            mensaje = "sus datos han sido actualizados con exito";
        } else {
            mensaje = "los datos no se han actualizado con exito";
        }
        return mensaje;
    }

    @Override
    public Usuario getOne(String ruta, String user) {
        List<Usuario> lista = getAll(ruta);
        Usuario obj = null;
        for (int i = 0; i < lista.size(); i++) {
            if (user.equals(lista.get(i).getUsername())) {
                obj = lista.get(i);
                break;
            }
        }
        return obj;
    }

    @Override
    public String delete(String ruta, String user) {
        List<Usuario> lista = getAll(ruta);
        String datos = "";
        String mensaje = "";
        int pos = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (user.equals(lista.get(i).getUsername())) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            lista.remove(pos);
            for (int i = 0; i < lista.size(); i++) {
                datos = datos + lista.get(i).getUsername() + ";" + lista.get(i).getPassword()
                        + ";" + lista.get(i).getNombres() + ";" + lista.get(i).getApellidos()
                        + ";" + lista.get(i).getEmail() + ";\n";
            }
//            System.out.println(datos);
            createUpdate(ruta, datos, false);//copien esto ve
            mensaje = "sus datos han sido actualizados con exito";
        } else {
            mensaje = "los datos no se han actualizado con exito";
        }
        return mensaje;
    }
    
    public Usuario getLogin(String ruta, String user, String password){
        List<Usuario> lista = getAll(ruta);
        Usuario obj = null;
        for (int i = 0; i < lista.size(); i++) {
            if (user.equals(lista.get(i).getUsername()) && password.equals(lista.get(i).getPassword())) {
                obj = lista.get(i);
                break;
            }
        }
        return obj;
    }
}
