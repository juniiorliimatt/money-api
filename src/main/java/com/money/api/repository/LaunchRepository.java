package com.money.api.repository;

import com.money.api.model.Launch;
import com.money.api.repository.launch.LaunchRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Long>, LaunchRepositoryQuery {
}
