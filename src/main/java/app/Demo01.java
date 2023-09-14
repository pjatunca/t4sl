package app;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

public class Demo01 {
	// registro de un nuevo Usuario usando valores fijos
	public static void main(String[] args) {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		
		// objeto a grabar
		Usuario u = new Usuario();
		u.setCod_usua(162);
		u.setNom_usua("Maria");
		u.setApe_usua("Lopez");
		u.setUsr_usua("mlopez");
		u.setCla_usua("5656565656");
		u.setFna_usua("2005/05/07");
		u.setIdtipo(1);
		u.setEst_usua(1);
		// si queremos registrar, actualizar o eliminar -> transa..
		manager.getTransaction().begin();
		//INSERT INTO TB_XX VALUES (?,?, ....
		manager.persist(u); 
		manager.getTransaction().commit();
		System.out.println("Registro Ok");
		manager.close();
		
		
	}
}
