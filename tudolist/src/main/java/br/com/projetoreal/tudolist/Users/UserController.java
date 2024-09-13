package br.com.projetoreal.tudolist.Users;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/UserController")
public class UserController {

    // esta falando que oq vc esta recebendo ja esta dentro do body, ja na estrutura

    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){

        System.out.println(userModel.getName());
        System.out.println(userModel.getUserName());
        System.out.println(userModel.getPassword());

    }
}
