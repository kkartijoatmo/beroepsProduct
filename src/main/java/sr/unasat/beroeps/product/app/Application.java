package sr.unasat.beroeps.product.app;

import sr.unasat.beroeps.product.entities.Bestelling;
import sr.unasat.beroeps.product.entities.Klant;
import sr.unasat.beroeps.product.entities.Product;
import sr.unasat.beroeps.product.entities.Winkel;
import sr.unasat.beroeps.product.repositories.BestellingRepository;
import sr.unasat.beroeps.product.repositories.KlantRepository;
import sr.unasat.beroeps.product.repositories.ProductRepository;
import sr.unasat.beroeps.product.repositories.WinkelRepository;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        WinkelRepository winkelRepo = new WinkelRepository();
        ProductRepository productRepo = new ProductRepository();
        KlantRepository klantRepo = new KlantRepository();
        BestellingRepository bestellingRepo = new BestellingRepository();


        Winkel winkel1 = new Winkel("Tulip", "Tourtonnelaan", "234567");
        Winkel winkel2 = new Winkel("Kirpalani", "Domineestraat", "234657");
        winkelRepo.insertWinkel(winkel1);
        winkelRepo.insertWinkel(winkel2);


        Winkel foundWinkel = winkelRepo.findWinkel("Tulip");
        Product product1 = new Product("Levensmiddelen", "Aardappelen", 1.0, foundWinkel);
        foundWinkel = winkelRepo.findWinkel("Kirpalani");
        Product product2 = new Product("Levensmiddelen", "Tomaten", 1.0, foundWinkel);
        foundWinkel = winkelRepo.findWinkel("Kirpalani");
        Product product3 = new Product("Levensmiddelen", "Suiker", 1.0, foundWinkel);
        productRepo.insertProduct(product1);
        productRepo.insertProduct(product2);
        productRepo.insertProduct(product3);


        Klant klant1 = new Klant("Kajal", "Ghisaidoobe", "234567", "Leysweg",
                "k.Ghisaidoobe@unasat.sr");
        Klant klant2 = new Klant("Kevin", "Kartijoatmo", "234567", "Leysweg",
                "k.Kartijoatmo@unasat.sr");
        Klant klant3 = new Klant("Unasat", "HBO", "234567", "Leysweg",
                "info@unasat.sr");
        klantRepo.insertKlant(klant1);
        klantRepo.insertKlant(klant2);
        klantRepo.insertKlant(klant3);


        Klant foundKlant = klantRepo.findKlant("Kajal", "Ghisaidoobe");
        Product foundProduct = productRepo.findProduct("Aardappelen");
        Bestelling bestelling1 = new Bestelling("Lachmonstraat", 55, foundKlant, foundProduct);
        bestellingRepo.addBestelling(bestelling1);

        foundKlant = klantRepo.findKlant("Kevin", "Kartijoatmo");
        foundProduct = productRepo.findProduct("Tomaten");
        Bestelling bestelling2 = new Bestelling("Kasabaholoweg", 55, foundKlant, foundProduct);
        bestellingRepo.addBestelling(bestelling2);


        Bestelling foundBestelling = bestellingRepo.findBestelling("Lachmonstraat");
        foundKlant = klantRepo.findKlant("Unasat", "hbo");
        foundBestelling.setKlant(foundKlant);
        foundProduct = productRepo.findProduct("suiker");
        foundBestelling.setProduct(foundProduct);
        bestellingRepo.updateBestelling(foundBestelling);

        System.out.println();

        List<Bestelling> bestellingList = bestellingRepo.getAllBestellingen();
        for (Bestelling bestelling : bestellingList) {
            System.out.println(bestelling);
        }


        System.out.println();


        foundBestelling = bestellingRepo.findBestelling("Kasabaholoweg");
        bestellingRepo.deleteBestelling(foundBestelling);

    }
}
