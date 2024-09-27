package br.com.ProjetoReal.TudoList.Filter;


import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.ProjetoReal.TudoList.Users.InterUserRepository;
import br.com.ProjetoReal.TudoList.Users.UserModel;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired // indica a spring que a dependencia deve ser injetada automaticamente
    private InterUserRepository interUserRepository;

    // principal  beneficio do OnceRequestFilter é que ele garante que o filtro seja executado apenas uma vez por requisição

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //request tudo esta vindo da nossa requisiçao
        //response oq estamos enviando para o usuario
        var servletPath = request.getServletPath();

        System.out.println( " olha isso ai "+servletPath);
        if (servletPath.startsWith("/tasks/")) {
            // colocado o if para que seja feita em uma rota especifica,
            //pq se nao colocar ele, sera aplicado em todas as rotas, fazendo com q todas as requisiçoes recebidas pela aplicaçao
            // sejam executadas a autenticaçao


            // Pegar a autenticaçao ( usuario e senha)
            var authorization = request.getHeader("Authorization"); // nesse caso no lugar no var seria uma String

            var authorizationRetirada = authorization.substring("Basic".length()).trim();
            //substring é utilizado para extrair uma parte de um texto de uma String
            // o length vai percorrer o basic e contar quantos caracteres ele tem e retirar exatamentes esses caracteres
            // o trim retira qualquer espaço


            byte[] authDecode = Base64.getDecoder().decode(authorizationRetirada);
            // ele decodifica o token Base64 que esta contido no authorizationRetirada para obter o usuário e senha)

            String authDecodeString = new String(authDecode); // os bytes decodificados são convertidos em uma String
            // o new String cria uma nova instância da classe String com os bytes decodificados como seu conteúdo.

            String[] credentials = authDecodeString.split(":"); // o split vai separar o usuario e senha
            String username = credentials[0];
            String password = credentials[1];
            // o usuario e senha estão separados pelo :
            // o username recebe o primeiro elemento do array e o password recebe o segundo elemento do array


            // validar usuario
            UserModel user = this.interUserRepository.findByUserName(username);

            if (user == null) {

                response.sendError(401, "Usuário não encontrado");

            } else {
                //validar senha

                BCrypt.Result passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                    // request.setAttribute é usado para armazenar dados de uma requisiçao
                    // o IdUser é o nome do atributo que está sendo armazenado
                    // o user.getId() é o valor que está sendo armazenado no atributo
                    filterChain.doFilter(request, response);
                    // segue viagem
                } else {
                    response.sendError(401, "Senha inválida");
                }

            }

        } else {
            //segue viagem
            filterChain.doFilter(request, response);
        }
    }


    //ele também já cuida da criação de objetos como HttpServletRequest e HttpServletResponse,
    // o que torna o código mais focado na lógica principal do filtro.
}
