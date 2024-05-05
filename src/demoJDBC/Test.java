package demoJDBC;

import java.util.List;

import JDBCProjet.beans.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.service.ClientService;

public class Test {
	public static void main(String args[]) {
		Connexion.getConnection();

		ClientService c1 = new ClientService();
		//c1.create(new Client("syrine", "boussetta"));

		List<Client> l = c1.findAll();
		for (Client lc : l) {
			System.out.println(lc.toString());
		}
		Client c=c1.findById(2);
		System.out.println(c.toString());
		
		c1.delete(new Client(3,"syrine","boussetta"));
		
		c1.update(new Client(2,"xxx","boussetta"));
	
		
		
		
	}
}
