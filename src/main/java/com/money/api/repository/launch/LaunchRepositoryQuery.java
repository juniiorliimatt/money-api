package com.money.api.repository.launch;

import com.money.api.model.Launch;
import com.money.api.repository.filter.LaunchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchRepositoryQuery {

  public Page<Launch> filter(LaunchFilter filter, Pageable page);

}
