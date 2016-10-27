package com.vklindukhov.dao;

import com.vklindukhov.entity.Actor;
import com.vklindukhov.entity.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByName(String name);
}
