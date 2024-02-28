package repositories;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import entities.Adresse;
import entities.Client;

public class AdresseRepository extends Database {
    private final  String SQL_SELECT_ALL="SELECT * FROM `adresse` a, client cl WHERE a.client_id=cl.id_client;" ;
   private final  String SQL_SELECT_BY_TEL="Select * from client where tel_client like ? " ;
   private final  String SQL_INSERT="INSERT INTO `adresse` ( `id_ad`, `ville`, `quartier`, `numVilla`,client_id)  VALUES (?,?,?,?,?)";

   public void insert(Adresse adresse){
    try {
       openConnexion();
       initPreparedStatement(SQL_INSERT);
        statement.setInt(1, adresse.getId());
        statement.setString(2,adresse.getVille());
        statement.setString(3,adresse.getQuartier());
        statement.setString(4,adresse.getNumVilla());
        statement.setInt(5,adresse.getClient().getId());
        int nbreLigne=statement.executeUpdate();
        statement.close();
        closeConnexion();
 }
catch (SQLException e) {
   System.out.println("Erreur de Connexion a la BD");
}

 }

 public List<Adresse> selectAll(){
    List<Adresse> adresses=new ArrayList<>();
     try {
       openConnexion();
       initPreparedStatement(SQL_SELECT_ALL);
       ResultSet rs= executeSelect();
       while (rs.next()) {

           Client client=new Client();
           client.setId(rs.getInt("id_client"));
           client.setNomComplet(rs.getString("nom_client"));
           client.setTelephone(rs.getString("tel_client"));
           client.setEmail(rs.getString("email_client"));
           Adresse ad=new Adresse();
           ad.setId(rs.getInt("id_ad"));
           ad.setVille(rs.getString("ville"));
           ad.setQuartier(rs.getString("quartier"));
           ad.setNumVilla(rs.getString("numVilla"));
           ad.setClient(client);
           adresses.add(ad);
       }
       statement.close();
       rs.close();
       conn.close();
  } 
  catch (SQLException e) {
    System.out.println("Erreur de Connexion a la BD");
  }
  return  adresses;
 }

}
