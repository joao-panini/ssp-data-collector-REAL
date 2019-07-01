package com.repository;

import com.entity.WVIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WVIRepository extends JpaRepository<WVIEntity,Long> {

}
