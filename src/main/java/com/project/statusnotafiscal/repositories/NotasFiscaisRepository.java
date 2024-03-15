package com.project.statusnotafiscal.repositories;

import com.project.statusnotafiscal.models.NotaFiscal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotasFiscaisRepository extends MongoRepository<NotaFiscal,String> {
    Optional<List<NotaFiscal>> findNotaFiscalByAutorizador(String estado);

    @Query("{ 'ultimaVerificacao' :{$regex: ?0 }}")
    Optional<List<NotaFiscal>> findNotaFiscalByUltimaVerificacaoRegex(String data);


}
