package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

public class Demo03 {
	// encontrar y mostrar los datos de un Usuario, según su código.
	public static void main(String[] args) {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		
		// objeto a modificar
		Usuario u = new Usuario();
		
		// si queremos registrar, actualizar o eliminar -> transa..
		try {
			// SELECT * FROM TB_XXX WHERE .....
			u = manager.find(Usuario.class, 1); 
			
			if (u == null) {
				System.out.println("Usuario no existe");
			} else {
				System.out.println(u);
			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getCause().getMessage());
		}
		manager.close();
		
		
	}
}
