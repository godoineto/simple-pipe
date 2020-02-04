package com.godoineto.simplepipe.api;

import com.godoineto.simplepipe.api.dto.UserDTO;
import com.godoineto.simplepipe.api.util.ResponseUtil;
import com.godoineto.simplepipe.config.GeneralAPI;
import com.godoineto.simplepipe.service.UserService;
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
@RequestMapping("/v1/user")
@Api(tags = "User Resource", description = "Set of endpoints to management user resource")
public class UserAPI implements GeneralAPI {

    private final UserService service;

    public UserAPI(UserService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("List of all user")
    public List<UserDTO> listAll() {
        return service.listAll();
    }

    @PostMapping
    @ApiOperation("Create an user")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO userDTO) throws URISyntaxException {
        UserDTO created = service.create(userDTO);
        return ResponseEntity.created(new URI("/api/user/" + created.getId()))
                .body(created);
    }

    @GetMapping("{id}")
    @ApiOperation("Get a specific user")
    public ResponseEntity<UserDTO> get(@PathVariable String id) {
        Optional<UserDTO> maybeUserDTO = service.get(id);
        return ResponseUtil.wrapOrNotFound(maybeUserDTO);
    }

    @PutMapping("{id}")
    @ApiOperation("Update a specific user")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody @Valid UserDTO userDTO) {
        Optional<UserDTO> maybeUserDTO = service.update(id, userDTO);
        return ResponseUtil.wrapOrNotFound(maybeUserDTO);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete a specific user")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
