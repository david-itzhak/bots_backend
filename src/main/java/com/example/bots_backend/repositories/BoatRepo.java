package com.example.bots_backend.repositories;

import com.example.bots_backend.entities.BoatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Dmitry Itskov
 */
public interface BoatRepo extends JpaRepository<BoatEntity, String> {
}
