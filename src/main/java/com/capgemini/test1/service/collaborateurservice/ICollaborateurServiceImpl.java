package com.capgemini.test1.service.collaborateurservice;

import com.capgemini.test1.entities.*;
import com.capgemini.test1.repositories.*;
import com.capgemini.test1.repositories.CollaborateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ICollaborateurServiceImpl  {

    private CollaborateurRepository collaborateurRepository;
    private SiteRepository siteRepository;
    private GradeRepository gradeRepository;

    public ICollaborateurServiceImpl(CollaborateurRepository collaborateurRepository,SiteRepository siteRepository,GradeRepository gradeRepository) {
        this.collaborateurRepository = collaborateurRepository;
        this.siteRepository = siteRepository;
        this.gradeRepository = gradeRepository;
    }
    public Collaborateur createCollaborateur(String nom, String email,byte[] image,Long gradeId, Long siteId, Collection<Technologie> technologies) {
        Site site = siteRepository.findById(siteId).get();
        Grade grade = gradeRepository.findById(gradeId).get();
        Collaborateur collaborateur = new Collaborateur();
        collaborateur.setNom(nom);
        collaborateur.setEmail(email);
        collaborateur.setImage(image);
        collaborateur.setGrade(grade);
        collaborateur.setSite(site);
        collaborateur.setTechnologies(technologies);
        return collaborateurRepository.save(collaborateur);
    }
    public Collaborateur SaveCollaborateur(Collaborateur collaborateur) {
        return collaborateurRepository.save(collaborateur);
    }
    public Collection<Collaborateur> GetCollaborateurs() {
        List<Collaborateur> collaborateurs = collaborateurRepository.findAll();
        for (Collaborateur collaborateur : collaborateurs) {
            collaborateur.setProjectCount(collaborateur.getProjets().size());
        }
        return collaborateurs;
    }
    public void AssigneCount() {
        List<Collaborateur> collaborateurs = collaborateurRepository.findAll();
        for (Collaborateur collaborateur : collaborateurs) {
            collaborateur.setProjectCount(collaborateur.getProjets().size());
        }
    }

    public Collaborateur GetCollaborateur(Long id) {
        Collaborateur collaborateur = collaborateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Collaborateur not found"));
        collaborateur.setProjectCount(collaborateur.getProjets().size());
        return collaborateur;
    }

    public void DeleteCollaborateur(Long id) {
        collaborateurRepository.deleteById(id);
    }

    public Optional<Collaborateur> updateCollaborateur(Long id, Collaborateur collaborateurDetails) {
        return collaborateurRepository.findById(id).map(collaborateur -> {
            collaborateur.setNom(collaborateurDetails.getNom());
            collaborateur.setEmail(collaborateurDetails.getEmail());
            collaborateur.setImage(collaborateurDetails.getImage());
            collaborateur.setTechnologies(collaborateurDetails.getTechnologies());
            collaborateur.setSite(collaborateurDetails.getSite());
            collaborateur.setProjets(collaborateurDetails.getProjets());
            collaborateur.setGrade(collaborateurDetails.getGrade());
            return collaborateurRepository.save(collaborateur);
        });
    }

    public Optional<Collaborateur> partialUpdateCollaborateur(Long id, Collaborateur collaborateurDetails) {
        return collaborateurRepository.findById(id).map(collaborateur -> {
            if (collaborateurDetails.getNom() != null) {
                collaborateur.setNom(collaborateurDetails.getNom());
            }
            if (collaborateurDetails.getEmail() != null) {
                collaborateur.setEmail(collaborateurDetails.getEmail());
            }
            if (collaborateurDetails.getImage() != null) {
                collaborateur.setImage(collaborateurDetails.getImage());
            }
            if (collaborateurDetails.getTechnologies() != null) {
                collaborateur.setTechnologies(collaborateurDetails.getTechnologies());
            }
            if (collaborateurDetails.getSite() != null) {
                collaborateur.setSite(collaborateurDetails.getSite());
            }
            if (collaborateurDetails.getProjets() != null) {
                collaborateur.setProjets(collaborateurDetails.getProjets());
            }
            if (collaborateurDetails.getGrade() != null) {
                collaborateur.setGrade(collaborateurDetails.getGrade());
            }
            return collaborateurRepository.save(collaborateur);
        });
    }

    public List<Collaborateur> searchCollaborateurs(String nom, String email, String siteName, String gradeName, String technologieName) {
        return collaborateurRepository.findByCriteria(nom, email, siteName, gradeName, technologieName);
    }
    public Collaborateur findCollaborateurById(Long id) {
        Optional<Collaborateur> optionalCollaborateur = collaborateurRepository.findById(id);
        if (optionalCollaborateur.isPresent()) {
            return optionalCollaborateur.get();
        } else {
            throw new RuntimeException("Collaborateur not found for id: " + id);
        }
    }
}
