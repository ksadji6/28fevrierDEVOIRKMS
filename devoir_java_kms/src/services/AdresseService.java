package services;

import java.util.List;

import entities.Adresse;
import repositories.AdresseRepository;
import repositories.ClientRepository;

public class AdresseService {
    AdresseRepository adresseRepository=new AdresseRepository();
    ClientRepository clientRepository=new ClientRepository();
    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }
    public AdresseService(AdresseRepository adresseRepository, ClientRepository clientRepository) {
        this.adresseRepository = adresseRepository;
        this.clientRepository = clientRepository;
    }
    public void ajouterAdresse(Adresse adresse){
        adresseRepository.insert(adresse);
    }
    public List<Adresse> listerAdresse(){
        return adresseRepository.selectAll();
    }
}
