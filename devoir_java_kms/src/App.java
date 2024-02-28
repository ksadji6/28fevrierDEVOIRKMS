import java.util.List;
import java.util.Scanner;

import entities.Adresse;
import entities.Client;
import repositories.AdresseRepository;
import repositories.ClientRepository;
import services.AdresseService;
import services.ClientService;

public class App {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        ClientRepository clientRepository=new ClientRepository();
        AdresseRepository adresseRepository=new AdresseRepository();
        ClientService clientService=new ClientService(clientRepository);
        AdresseService adresseService=new AdresseService(adresseRepository);
        do {
            System.out.println("1-Créer un client");
            System.out.println("2-Lister les clients"); 
            System.out.println("3-Ajouter une adresse"); 
            System.out.println("4-Lister les adresses");
            System.out.println("5-Quitter"); 
             choix=sc.nextInt();
             sc.nextLine();
             switch (choix) {
                case 1:
                System.out.println("Entrer le Telephone");
                     String telephone=sc.nextLine();
                     int id;
                     String nomComplet, email;
                     Client client=  clientService.rechercherClientParTel(telephone);
                     if (client!=null) {
                          System.out.println("Ce numero de Telephone existe deja");
                     } else {
                        System.out.println("Entrez un id de client");
                        id=sc.nextInt(); 
                        sc.nextLine();
                        System.out.println("Entrer le nom complet du client");
                        nomComplet=sc.nextLine();  
                        System.out.println("Entrer l'email du client");
                        email=sc.nextLine();  
                        client=new Client();
                        client.setId(id);
                        client.setNomComplet(nomComplet);
                        client.setTelephone(telephone);
                        client.setEmail(email);
                        clientService.ajouterClient(client);

                        
                     }
                    break;
                case 2:
                    List<Client>  clients= clientService.listerClient();
                   for (Client cl: clients) {
                     System.out.println("ID "+ cl.getId());
                     System.out.println("Nom Complet "+ cl.getNomComplet());
                     System.out.println("Telephone "+cl.getTelephone() );
                     System.out.println("Email "+cl.getEmail() );
                     System.out.println("=================================");
                   }
                   break;
                   case 3:
                   System.out.println("Entrer le Telephone du client");
                      telephone=sc.nextLine(); 
                   client = clientService.rechercherClientParTel(telephone);
                         if (client==null) {
                              System.out.println("Entrer un ID");
                               id=sc.nextInt(); 
                               sc.nextLine();
                              System.out.println("Entrer un Nom complet");
                              nomComplet=sc.nextLine();   
                              System.out.println("Entrer un téléphone");
                              telephone=sc.nextLine();   
                              System.out.println("Entrer un email");
                              email=sc.nextLine();   
                              client=new Client();
                              client.setId(id);
                              client.setNomComplet(nomComplet);
                              client.setTelephone(telephone);
                              clientService.ajouterClient(client);
                         }
                       
                         System.out.println("Entrer l'ID de l'adresse");
                               id=sc.nextInt(); 
                               sc.nextLine();
                              System.out.println("Entrer la ville de l'adresse");
                              String ville = sc.nextLine();   
                              System.out.println("Entrer le quartier");
                              String quartier=sc.nextLine();   
                              System.out.println("Entrer le numéro de la villa");
                              String numVilla=sc.nextLine();   
                              Adresse adresse=new Adresse();
                              adresse.setId(id);
                              adresse.setVille(ville);
                              adresse.setQuartier(quartier);
                              adresse.setNumVilla(numVilla);
                              adresseService.ajouterAdresse(adresse);
                         



                   break;
                   case 4:
                   List<Adresse> adresses= adresseService.listerAdresse();
                     for (Adresse ad: adresses) {
                          System.out.println("ID  :"+ ad.getId());
                          System.out.println("Ville :"+ ad.getVille());
                          System.out.println("Quartier :"+ ad.getQuartier());
                          System.out.println("Numéro Villa :"+ ad.getNumVilla());
                          System.out.println("Client :"+ ad.getClient().getId());
                          System.out.println("====================================================================");
                     }
                   break;

                
                   
    

            default:
                break;  }
        } while (choix!=5);
    }
    

}
