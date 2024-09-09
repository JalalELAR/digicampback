package com.capgemini.test1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Collaborateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    @Lob
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private byte[] image;
    @ManyToMany
    @JoinTable(
            name = "collaborateur_technologie",
            joinColumns = @JoinColumn(name = "collaborateur_id"),
            inverseJoinColumns = @JoinColumn(name = "technologie_id")
    )
    private Collection<Technologie> technologies;
    @ManyToOne
    private Site site;
    @ManyToOne(fetch = FetchType.EAGER)
    private Grade grade;
    @ToString.Exclude
    @ManyToMany(mappedBy = "collaborateurs")
    @JsonIgnore
    private Collection<Projet> projets;
    @Transient
    private int projectCount;

}
