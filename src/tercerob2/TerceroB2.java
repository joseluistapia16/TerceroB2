package tercerob2;

import archivos.Archivo;
import domain.Usuario;

public class TerceroB2 {

    public static void main(String[] args){
        String ruta = "C:\\Users\\user\\Documents\\NetBeansProjects\\TerceroB2\\usuario3b.csv";
        Archivo obj = new Archivo();
        var obj1 = new Usuario("gokussj", "123456", "paco", "ramirez", "paco123@hotmail.com");
//        var msj = obj.update(ruta, obj1);
        var res = obj.delete(ruta, "gokussj");
        System.out.println(res);
    }
    
}
