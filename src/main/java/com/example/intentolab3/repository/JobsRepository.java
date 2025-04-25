package com.example.intentolab3.repository;

import com.example.intentolab3.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs, String> {
}
