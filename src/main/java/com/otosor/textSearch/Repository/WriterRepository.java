package com.otosor.textSearch.Repository;

import com.otosor.textSearch.Entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WriterRepository extends JpaRepository<Writer, Long> {
    List<Writer> findAll();

    Writer getWriterById(Long id);
}
