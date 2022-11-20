package sample.spring.multipledatabases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sample.spring.multipledatabases.model.h2.Pessoa;
import sample.spring.multipledatabases.model.hsqldb.Imovel;
import sample.spring.multipledatabases.repository.h2.PessoaRepository;
import sample.spring.multipledatabases.repository.hsqldb.ImovelRepository;

@Transactional
@SpringBootTest
class SampleSpringMultipleDatabasesApplicationTests {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    ImovelRepository imovelRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void deveriaSalvarPessoa() {
        Pessoa p = new Pessoa("João");
        pessoaRepository.save(p);
        Assertions.assertNotNull(p.getId());
    }

    @Test
    void deveriaSalvarImovel() {
        Imovel i = new Imovel("Rua 1");
        imovelRepository.save(i);
        Assertions.assertNotNull(i.getId());
    }

}
