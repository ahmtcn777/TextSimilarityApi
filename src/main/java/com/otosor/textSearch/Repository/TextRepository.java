package com.otosor.textSearch.Repository;

import com.otosor.textSearch.Entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findAll();
}
