package com.dcapi.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dcapi.entity.DcCasesEntity;

public interface DcCasesRepository extends JpaRepository<DcCasesEntity, Serializable>{

}
