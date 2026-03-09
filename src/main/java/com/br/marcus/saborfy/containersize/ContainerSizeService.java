package com.br.marcus.saborfy.containersize;

import com.br.marcus.saborfy.containersize.dto.CreateContainerSizeRequest;
import com.br.marcus.saborfy.containersize.dto.DeleteContainerSizeRequest;
import com.br.marcus.saborfy.containersize.dto.UpdateContainerSizeRequest;
import com.br.marcus.saborfy.containersize.entity.ContainerSize;
import com.br.marcus.saborfy.exception.exceptions.ConflictException;
import com.br.marcus.saborfy.exception.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerSizeService {
    private final ContainerSizeRepository containerSizeRepository;

    public ContainerSizeService(ContainerSizeRepository containerSizeRepository) {
        this.containerSizeRepository = containerSizeRepository;
    }

    public ContainerSize createContainerSize(CreateContainerSizeRequest request) {
        if (containerSizeRepository.existsByNome(request.getNome())) {
            throw new ConflictException("Já existe um container com este nome!");
        }

        ContainerSize containerSize = new ContainerSize();

        containerSize.setCapacidade(request.getCapacidade());
        containerSize.setNome(request.getNome());
        containerSize.setPreco(request.getPreco());

        containerSizeRepository.save(containerSize);

        return containerSize;
    }

    public List<ContainerSize> listContainerSizes() {
        return containerSizeRepository.findAll();
    }

    public void deleteContainerSiz(DeleteContainerSizeRequest request) {
        containerSizeRepository.deleteById(request.getId());
    }

    public ContainerSize updateContainerSize(Long id, UpdateContainerSizeRequest request) {
        ContainerSize containerSize = containerSizeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item nao encontrado!"));

        containerSize.setPreco(request.getPreco());
        containerSize.setNome(request.getNome());
        containerSize.setCapacidade(request.getCapacidade());

        containerSizeRepository.save(containerSize);
        return containerSize;
    }
}
