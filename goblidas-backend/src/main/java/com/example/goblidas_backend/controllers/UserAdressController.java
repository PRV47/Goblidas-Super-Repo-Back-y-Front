package com.example.goblidas_backend.controllers;

import com.example.goblidas_backend.entities.User;
import com.example.goblidas_backend.entities.UserAdress;
import com.example.goblidas_backend.services.UserAdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/useradress")
public class UserAdressController extends BaseController<UserAdress> {

    @Autowired
    private UserAdressService userAdressService;

    public UserAdressController(UserAdressService userAdressService){
        super(userAdressService);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserAdress>> getUserAdressByUserId(@RequestParam Long userId){
        List<UserAdress> adresses = userAdressService.getUserAdressesByUserId(userId);
        return ResponseEntity.ok(adresses);
    }
}
