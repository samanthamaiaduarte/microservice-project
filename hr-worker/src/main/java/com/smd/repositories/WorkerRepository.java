package com.smd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smd.entities.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
