package br.com.unicuritiba.CadastroJogos.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tabela_jogo")
public class Jogo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "genero", nullable = false)
    private String genero;
    
    @Column(name = "preco", nullable = false)
    private double preco;
    
    @Column(name = "desenvolvedora", nullable = false)
    private String desenvolvedora;
    
    @Column(name = "plataforma", nullable = false)
    private String plataforma;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
