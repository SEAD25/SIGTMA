package sn.uasz.SIGTMA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.SIGTMA.model.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long>{
}
