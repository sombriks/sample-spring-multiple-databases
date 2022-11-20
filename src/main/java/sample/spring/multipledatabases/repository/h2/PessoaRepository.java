package sample.spring.multipledatabases.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.spring.multipledatabases.model.h2.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
