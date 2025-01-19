package com.effigoproject.internmanagementsystem.repo;

import com.effigoproject.internmanagementsystem.entity.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternRepository extends JpaRepository<Intern, Long> {

}
