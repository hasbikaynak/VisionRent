package com.visionrent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visionrent.domain.ImageFile;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, String> {
	
	@EntityGraph(attributePaths = "id")
	List<ImageFile> findAll();
	
	@EntityGraph(attributePaths = "id")
	Optional<ImageFile> findImageById(String id);

}
