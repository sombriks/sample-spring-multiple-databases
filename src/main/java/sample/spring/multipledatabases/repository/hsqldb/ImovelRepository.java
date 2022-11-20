package sample.spring.multipledatabases.repository.hsqldb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.spring.multipledatabases.model.hsqldb.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel,Long> {
}
