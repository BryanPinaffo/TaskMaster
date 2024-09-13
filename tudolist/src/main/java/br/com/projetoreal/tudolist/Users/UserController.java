package br.com.projetoreal.tudolist.Users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/UserController")
public class UserController {

    // esta falando que oq vc esta recebendo ja esta dentro do body, ja na estrutura

    @PostMapping("/")
    public UserModel create(@RequestBody UserModel userModel){

      UserModel  userCreated = this.userRepository.save(userModel);

      return userCreated;

    }
    @Autowired
    private InterUserRepository userRepository;
}
