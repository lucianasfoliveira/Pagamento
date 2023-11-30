package ada.tech.tenthirty.tvpackages.repository;

import ada.tech.tenthirty.tvpackages.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findAllByUsuarioId(String usuarioId);
}


