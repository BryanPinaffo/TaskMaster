package br.com.ProjetoReal.TudoList.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public void copyNonNullProperty(Object source, Object target){

        BeanUtils.copyProperties(source, target,getNullPropertyNames(source));
        // copyProperties é um metodo do Spring Framework que copia os valores dos atributos de um objeto source para um objeto target
        // vai atribuir todos os nulos para o beansUtils

    }




    
    public String [] getNullPropertyNames(Object source) {
        
        final BeanWrapper src = new BeanWrapperImpl(source);
        // BeanWrapper é uma interface fornecida pelo Spring Framework que permite acessar e manipular
        // as propriedades de um JavaBean (um objeto com propriedades acessiveis por metodos getters e setters)
        // com ele vc pode ler, modificar ou definir valores de propriedades objetos java de forma dinamica

        PropertyDescriptor[] puds = src.getPropertyDescriptors();
        // devolve um array de propertyDescriptor, onde cada propertyDescriptor descreve uma propriedade do objeto source

        Set<String> empyNames = new HashSet<>();
        // SET, vem do java ultil, ele nao permite elementos duplicados, util para quando vc quer armazenar elementos unicos
        // <String>, só pode conter objetos do tipo string
        // new HashSet<>(); é uma implementaçao completa da interface set, assim, criando uma instancia que sera usada para armazenar
        // elementos do tipo String
        //ele é muito usado pq tem as caracteristicas de : nao permitir elementos duplicados, nao mantem ordem, desepenho eficiente

        for (PropertyDescriptor pd : puds) {

            Object srcValue = src.getPropertyValue(pd.getName());

            if(srcValue == null){

                empyNames.add(pd.getName());

            }
            //O loop percorre cada PropertyDescriptor do array puds.
           // src.getPropertyValue(pd.getName()) é usado para obter o valor da propriedade correspondente do objeto source.
           // Se o valor da propriedade for null, o nome da propriedade é adicionado ao Set empyNames.

        }
        // pega tudo oq tem de propriedade nula


        String [] result = new String[empyNames.size()];

        return empyNames.toArray(result);
        
    }
}
