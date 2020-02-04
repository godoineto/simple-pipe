package com.godoineto.simplepipe.api;

import com.godoineto.simplepipe.api.dto.LeadDTO;
import com.godoineto.simplepipe.api.util.ResponseUtil;
import com.godoineto.simplepipe.config.GeneralAPI;
import com.godoineto.simplepipe.domain.LeadStatus;
import com.godoineto.simplepipe.service.LeadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/lead")
@Api(tags = "Lead Resource", description = "Set of endpoints to management lead resource")
public class LeadAPI implements GeneralAPI {

    private final LeadService service;

    public LeadAPI(LeadService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("List of all leads")
    public List<LeadDTO> listAll() {
        return service.listAll();
    }

    @PostMapping
    @ApiOperation("Create a lead")
    public ResponseEntity<LeadDTO> create(@RequestBody @Valid LeadDTO leadDTO) throws URISyntaxException {
        LeadDTO created = service.create(leadDTO);
        return ResponseEntity.created(new URI("/api/lead/" + created.getId()))
                .body(created);
    }

    @GetMapping("{id}")
    @ApiOperation("Get a specific lead")
    public ResponseEntity<LeadDTO> get(@PathVariable String id) {
        Optional<LeadDTO> maybeLeadDTO = service.get(id);
        return ResponseUtil.wrapOrNotFound(maybeLeadDTO);
    }

    @PutMapping("{id}")
    @ApiOperation("Update a specific lead")
    public ResponseEntity<LeadDTO> update(@PathVariable String id, @RequestBody @Valid LeadDTO leadDTO) {
        Optional<LeadDTO> maybeLeadDTO = service.update(id, leadDTO);
        return ResponseUtil.wrapOrNotFound(maybeLeadDTO);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete a specific lead")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PatchMapping("{id}/{status}")
    @ApiOperation("Finish a specific lead with WON or LOST")
    public ResponseEntity<LeadDTO> finish(@PathVariable String id, @PathVariable LeadStatus status) {
        Optional<LeadDTO> maybeLeadDTO = service.finish(id, status);
        return ResponseUtil.wrapOrNotFound(maybeLeadDTO);
    }
}
