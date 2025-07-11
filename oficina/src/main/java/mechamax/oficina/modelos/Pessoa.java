package mechamax.oficina.modelos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa {
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;
    @Column(name = "cep", length = 10, nullable = false)
    private String cep;
    @Column(name = "rua", length = 70, nullable = false)
    private String rua;
    @Column(name = "bairro", length = 70, nullable = false)
    private String bairro;
    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;
    @Column(name = "numeroCasa", nullable = false)
    private int numeroCasa;
    @Column(name = "enderecObservacao", length = 255, nullable = true)
    private String enderecObservacao;

    public Pessoa(){
    }

    public Pessoa(String nome, String cpf, String email, Date dataNascimento, String cep, String rua, String bairro,
            String cidade, int numeroCasa, String enderecObservacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numeroCasa = numeroCasa;
        this.enderecObservacao = enderecObservacao;
    }
}
