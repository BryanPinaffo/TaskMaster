package br.com.projetoreal.tudolist.ControllerTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// controller é um componente
// utilizamos ela para ser a camada entre a requisiçao (oq o usuario manda)
// e as demais camandas, como a de negocio e banco de dados

// desiginada para ser a primeira camada de acesso para receber requisçoes do usuario

@Controller  // criar estruturas onde tenha paginas,
@RestController // quer retornar uma api
// sempre quando se coloca o Rest, ele cria uma rota (http://Localhost:8080/----)
@RequestMapping("/primeiraRota")
// cria uma rota

// @ é chamado de annotation pois sao metadados que fornecem informaçoes sobre o codigo para o compilador
// elas servem para fornecer instruçoes especiais ao compilador, gerar codigo e afins

//no caso desses dois @, ele esta definindo a classe que vai ser o controller
public class MinhaPrimeiraController {

    // como é usadado na arquitetura REST

    /*

    METODO DE ACESSO DO HTTP -

    GET- busca informaçao
    POST- adiciona um dado/ informaçao
    PUT- alterar um dado/info
    DELETE- remover um dado
    PATCH- alterar somente uma parte da informaçao

     */
    @GetMapping("/PrimeiroMetodo")
        public String primeiraMensagem(){


            return "eu sou um lindo";
        }
}
