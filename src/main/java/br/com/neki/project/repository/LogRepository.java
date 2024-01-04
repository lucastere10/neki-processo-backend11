package br.com.neki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.project.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}
