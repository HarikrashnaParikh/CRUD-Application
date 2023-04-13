package com.harikrashna.contactdirectory.repository;

import com.harikrashna.contactdirectory.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile,Integer> {
}


