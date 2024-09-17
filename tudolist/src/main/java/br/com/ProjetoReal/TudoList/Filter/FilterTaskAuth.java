package br.com.ProjetoReal.TudoList.Filter;


import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class FilterTaskAuth implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // executar alguma açao
        // ele verifica se o usuario existe no banco de dados
        //todas as verificaçoes necessarias para que o usuario consiga fazer um cadastro

        System.out.println("chegou no filtro");
        //vai seguir normalmente
        filterChain .doFilter(servletRequest,servletResponse);

    }
}
