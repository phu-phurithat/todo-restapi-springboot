package com.phu.todoapi.repos;

import com.phu.todoapi.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Tasks, Long> {

    List<Tasks> findAllByUserId(Long userId);
}
