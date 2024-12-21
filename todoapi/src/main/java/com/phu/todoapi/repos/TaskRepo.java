package com.phu.todoapi.repos;

import com.phu.todoapi.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Tasks, Long> {

}
