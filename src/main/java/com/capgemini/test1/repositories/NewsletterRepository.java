package com.capgemini.test1.repositories;

import com.capgemini.test1.entities.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<Newsletter,Long> {
}
