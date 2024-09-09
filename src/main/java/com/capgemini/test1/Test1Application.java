package com.capgemini.test1;

import com.capgemini.test1.entities.*;
import com.capgemini.test1.repositories.UserRepository;
import com.capgemini.test1.service.collaborateurservice.ICollaborateurServiceImpl;
import com.capgemini.test1.service.gradeservice.IGradeServiceImpl;
import com.capgemini.test1.service.projetservice.IProjetServiceImpl;
import com.capgemini.test1.service.siteservice.ISiteServiceImpl;
import com.capgemini.test1.service.statusservice.IStatusServiceImpl;
import com.capgemini.test1.service.technologieservice.ITechnologieServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;


@SpringBootApplication
public class Test1Application {
    public static void main(String[] args) {
        SpringApplication.run(Test1Application.class, args);
    }

    @Bean
    CommandLineRunner start(
            IProjetServiceImpl iProjetServiceImpl,
            ITechnologieServiceImpl iTechnologieServiceImpl,
            ICollaborateurServiceImpl iCollaborateurServiceImpl,
            IStatusServiceImpl iStatusServiceImpl ,
            IGradeServiceImpl iGradeServiceImpl ,
            ISiteServiceImpl iSiteServiceImpl ) {
        return args -> {
            // Create and save Status
            Status status1 = new Status();
            status1.setName("Started");
            iStatusServiceImpl.saveStatus(status1);

            Status status2 = new Status();
            status2.setName("IN PROGRESS");
            iStatusServiceImpl.saveStatus(status2);

            // Create and save Technologie
            Technologie technologie1 = new Technologie();
            technologie1.setName("JAVA");
            iTechnologieServiceImpl.saveTechnologie(technologie1);

            Technologie technologie2 = new Technologie();
            technologie2.setName("Python");
            iTechnologieServiceImpl.saveTechnologie(technologie2);

            Technologie technologie3 = new Technologie();
            technologie3.setName("JEE");
            iTechnologieServiceImpl.saveTechnologie(technologie3);

            // Create and save Grade
            Grade grade1 = new Grade();
            grade1.setGradeName("A2");
            // Assuming a GradeService exists
             iGradeServiceImpl.saveGrade(grade1);

            Grade grade2 = new Grade();
            grade2.setGradeName("C");
            //grade2.setSousGrade("NULL");
            iGradeServiceImpl.saveGrade(grade2);

            // Create and save Site
            Site site1 = new Site();
            site1.setCity("Casa");
            // Assuming a SiteService exists
             iSiteServiceImpl.saveSite(site1);

            Site site2 = new Site();
            site2.setCity("Rabat");
            iSiteServiceImpl.saveSite(site2);

            // Create and save Collaborateurs
            Collaborateur collaborateur1 = new Collaborateur();
            collaborateur1.setNom("Jalal ELARACHE");
            collaborateur1.setEmail("ELARACHE@gmail.com");
            collaborateur1.setGrade(grade1);
            collaborateur1.setTechnologies(new ArrayList<>());
            collaborateur1.getTechnologies().add(technologie1);
            collaborateur1.setSite(site1);
            iCollaborateurServiceImpl.SaveCollaborateur(collaborateur1);


            Collaborateur collaborateur2 = new Collaborateur();
            collaborateur2.setNom("Anas BENHAROUN");
            collaborateur2.setEmail("BENHAROUN@gmail.com");
            collaborateur2.setGrade(grade1);
            collaborateur2.setTechnologies(new ArrayList<>());
            collaborateur2.getTechnologies().add(technologie1);
            collaborateur2.setSite(site2);
            iCollaborateurServiceImpl.SaveCollaborateur(collaborateur2);

            Collaborateur collaborateur3 = new Collaborateur();
            collaborateur3.setNom("Louay MIKOU");
            collaborateur3.setEmail("LMIKOU@gmail.com");
            collaborateur3.setGrade(grade2);
            collaborateur3.setTechnologies(new ArrayList<>());
            collaborateur3.getTechnologies().add(technologie2);
            collaborateur3.setSite(site2);
            iCollaborateurServiceImpl.SaveCollaborateur(collaborateur3);

            technologie1.setCollaborateurs(new ArrayList<>());
            technologie1.getCollaborateurs().add(collaborateur1);
            technologie1.setCollaborateurs(new ArrayList<>());
            technologie1.getCollaborateurs().add(collaborateur2);
            technologie2.setCollaborateurs(new ArrayList<>());
            technologie2.getCollaborateurs().add(collaborateur3);

            // Create and save Projet
            Projet projet1 = new Projet();
            projet1.setDated(new Date());
            projet1.setDescription("Hello, my name is Jalal, I'm a developer...");
            projet1.setTitle("DigiCamp");
            projet1.setCollaborateurs(new ArrayList<>());
            projet1.getCollaborateurs().add(collaborateur1);
            projet1.getCollaborateurs().add(collaborateur2);
            projet1.setStatus(status1);
            iProjetServiceImpl.saveProjet(projet1);

            // Create and save Projet
            Projet projet2 = new Projet();
            projet2.setDated(new Date());
            projet2.setDescription("Hello, my TITLE is DIGICAMP, I'm a PROJECT...");
            projet2.setTitle("DigiCamp2");
            projet2.setCollaborateurs(new ArrayList<>());
            projet2.getCollaborateurs().add(collaborateur1);
            projet2.getCollaborateurs().add(collaborateur2);
            projet2.getCollaborateurs().add(collaborateur3);
            projet2.setStatus(status2);
            iProjetServiceImpl.saveProjet(projet2);

            // Print out to verify
            System.out.println("Status created: " + status1);
            System.out.println("Technologie created: " + technologie1);
            System.out.println("Collaborateurs created: " + collaborateur1 + ", " + collaborateur2);
            System.out.println("Projet created:1 " + projet1 + " 2 " + projet2);
        };
    }
}
