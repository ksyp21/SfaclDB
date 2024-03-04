package com.sfacl.magnus.repository;


import com.sfacl.magnus.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRecordRepository extends JpaRepository<UserRecord, Integer> {
}
