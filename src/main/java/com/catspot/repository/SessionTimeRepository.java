package com.catspot.repository;

import com.catspot.entity.SessionTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionTimeRepository extends JpaRepository<SessionTime, Long> {
}
