package com.example.restservice;

import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RestserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestserviceApplication.class, args);
    }
}

@RestController
class CidadeRest{
    private CidadeDAO lista = new CidadeDAO(); 
    
    @PostMapping("/cidade")
    public Cidade create(@RequestBody Cidade cidade){
        return lista.insert(cidade);
    }
    
    @GetMapping("/cidade")
    public ArrayList<Cidade> get(){
        return lista.getAll();
    }
    
    @PutMapping("/cidade")
    public Cidade update(@RequestBody Cidade cidade){
        return lista.update(cidade);
    }
    
    @DeleteMapping("/cidade")
    public int delete(@RequestBody Long id){
        return lista.delete(id);
    }
}

class Cidade {

    private Long id;
    private String nome;
    private Estado estado;
    
    public Cidade(Long id,String nome,Estado estado){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
    
    public Long getId(){
        return id;
    }
    
}

class Estado {

    private Long id;
    private String nome;
    
    public Estado(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }
}

class CidadeDAO {

    private ArrayList<Cidade> listaCidade;

    public CidadeDAO() {
        listaCidade = new ArrayList();
    }

    public Cidade insert(Cidade cidade) {
        listaCidade.add(cidade);
        return cidade;
    }

    public ArrayList<Cidade> getAll() {
        return listaCidade;
    }

    public Cidade get(Long id) {
        for (Cidade c : listaCidade) {
            if (id.equals(c.getId())) {
                return c;
            }
        }
        return null;
    }
    
    public Cidade update(Cidade cidade){
        int i = 0;
        for (Cidade c : listaCidade) {
            if (cidade.getId().equals(c.getId())) {
                i = listaCidade.indexOf(c);
            }
        }
        
        listaCidade.set(i,cidade);
        return cidade;
    }
    
    public int delete(Long id){
        for (Cidade c : listaCidade) {
            if (id.equals(c.getId())) {
                listaCidade.remove(c);
                return 200;
            }
        }
        return 404;
    }
}
