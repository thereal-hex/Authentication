package com.google.demo.Controller;

import com.google.demo.Entity.RoleEntity;
import com.google.demo.Service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/api/role"})
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody RoleEntity roleEntity) {
        return ResponseEntity.ok(roleService.create(roleEntity));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Long id, RoleEntity roleEntity) {
        return ResponseEntity.ok(roleService.update(id, roleEntity));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody RoleEntity roleEntity) throws Exception {
        roleService.delete(roleEntity);
        return ResponseEntity.ok("successfully deleted");
    }

    @PostMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }


}
