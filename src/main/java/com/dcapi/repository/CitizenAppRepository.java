package com.dcapi.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dcapi.entity.CitizenApiEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenApiEntity, Serializable>{

}
