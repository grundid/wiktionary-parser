package de.grundid.twiki.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiEntryRepository extends JpaRepository<WikiEntry, Integer> {

}
