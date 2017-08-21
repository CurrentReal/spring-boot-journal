package com.apress.spring.repository;

import com.apress.spring.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hyunjin on 2017-08-21.
 */
public interface JournalRepository extends JpaRepository<Journal, Long> { }
