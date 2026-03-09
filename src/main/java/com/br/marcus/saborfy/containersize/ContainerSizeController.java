package com.br.marcus.saborfy.containersize;

import com.br.marcus.saborfy.containersize.dto.CreateContainerSizeRequest;
import com.br.marcus.saborfy.containersize.dto.DeleteContainerSizeRequest;
import com.br.marcus.saborfy.containersize.dto.DeleteContainerSizeResponse;
import com.br.marcus.saborfy.containersize.dto.UpdateContainerSizeRequest;
import com.br.marcus.saborfy.containersize.entity.ContainerSize;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/containersize")
public class ContainerSizeController {
    private final ContainerSizeService containerSizeService;

    public ContainerSizeController(ContainerSizeService containerSizeService) {
        this.containerSizeService = containerSizeService;
    }

    @PostMapping
    public ResponseEntity<ContainerSize> create(@RequestBody @Valid CreateContainerSizeRequest request) {
        return ResponseEntity.ok(containerSizeService.createContainerSize(request));
    }

    @GetMapping
    public ResponseEntity<List<ContainerSize>> select() {
        return ResponseEntity.ok(containerSizeService.listContainerSizes());
    }

    @DeleteMapping
    public ResponseEntity<DeleteContainerSizeResponse> delete(@RequestBody @Valid DeleteContainerSizeRequest request) {
        containerSizeService.deleteContainerSiz(request);
        return ResponseEntity.ok(new DeleteContainerSizeResponse("Item removido com sucesso!"));
    }

    @PutMapping
    public ResponseEntity<ContainerSize> update(@RequestParam Long id, @RequestBody @Valid UpdateContainerSizeRequest request) {
        return ResponseEntity.ok(containerSizeService.updateContainerSize(id, request));
    }
}
