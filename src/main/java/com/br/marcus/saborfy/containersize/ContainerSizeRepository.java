package com.br.marcus.saborfy.containersize;

import com.br.marcus.saborfy.containersize.entity.ContainerSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerSizeRepository extends JpaRepository<ContainerSize, Long> {
    boolean existsByNome(String nome);
}
