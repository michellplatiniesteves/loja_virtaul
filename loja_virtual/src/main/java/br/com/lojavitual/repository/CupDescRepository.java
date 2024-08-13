package br.com.lojavitual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lojavitual.model.CupDesc;

@Repository
public interface CupDescRepository extends JpaRepository<CupDesc, Long> {

}
