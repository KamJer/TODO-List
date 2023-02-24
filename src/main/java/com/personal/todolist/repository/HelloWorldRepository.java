package com.personal.todolist.repository;

import com.personal.todolist.entity.HelloWorldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloWorldRepository extends JpaRepository<HelloWorldEntity, Long> {

}
