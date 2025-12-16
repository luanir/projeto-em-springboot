package com.produtoapi.controller;

import com.produtoapi.model.Produto;
import com.produtoapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*") //Permite requisições de qualquer Origem, pode se colocar uma URL especifica
@RestController 
//Indica que esta classe é um Controller REST (retorna JSON, não páginas HTML)

@RequestMapping("/produtos") 
//Define a rota base da API
//Exemplo: http://localhost:8080/produtos
public class ProdutoController {

 @Autowired
 // Injeção de dependência: o Spring cria e gerencia o ProdutoService
 private ProdutoService produtoService; 
 // Permite acessar: controller -> service -> repository -> model

 @GetMapping
 // Mapeia requisições HTTP GET para "/produtos"
 public List<Produto> listarTodos() { 
     // Método que retorna todos os produtos cadastrados

     return produtoService.listarTodos();
     // Chama o método listarTodos() da camada Service
     // Retorna uma lista de produtos em formato JSON
 }

 @PostMapping
 // Mapeia requisições HTTP POST para "/produtos"
 public Produto salvar(@RequestBody Produto produto){ 
     // @RequestBody converte o JSON recebido no corpo da requisição
     // em um objeto Produto

     return produtoService.salvar(produto);
     // Envia o produto para o Service salvar no banco
     // Retorna o produto salvo (já com ID gerado)
 }

 @PutMapping("/{id}")
 // Mapeia requisições HTTP PUT para "/produtos/{id}"
 public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto){
     // @PathVariable captura o ID da URL
     // @RequestBody recebe os novos dados do produto via JSON

     return produtoService.atualizar(id, produto);
     // Atualiza o produto com base no ID informado
 }
 
 @PostMapping("/salvarLista")
 public List<Produto> salvarLista(@RequestBody List<Produto> produtos){
	 return produtoService.salvarLista(produtos);
 } //Salva todos os produtos colocados no POSTMAN

 @DeleteMapping("/{id}")
 // Mapeia requisições HTTP DELETE para "/produtos/{id}"
 public void deletar(@PathVariable Long id){
     // Recebe o ID do produto que será removido

     produtoService.deletar(id);
     // Chama o Service para excluir o produto do banco
 }

 @GetMapping("/{id}")
 // Mapeia requisições HTTP GET para "/produtos/{id}"
 public Optional<Produto> findById(@PathVariable Long id){
     // Busca um produto específico pelo ID

     return produtoService.findById(id);
     // Retorna um Optional:
     // - Contém o produto se existir
     // - Vazio se não existir
 }
    // ==============================================================================
    // =================== BUSCAS DETALHADAS POR NOME DE PRODUTO ====================
    // ==============================================================================
    
 	// Endpoints de busca por nome
 
 	@GetMapping("/buscarPorNome")
 public List<Produto> buscarPorNome(@RequestParam String valor) {
    return produtoService.findByNome(valor);
    }
    
 	@GetMapping("/buscarPorNomeContendo")
 public List<Produto> buscarPorNomeContendo(@RequestParam String valor) {
    return produtoService.findByNomeContaining(valor);
    }
    
 	@GetMapping("/buscarPorNomeEStatus")
 public List<Produto> buscarPorNomeEStatus(@RequestParam String nome, @RequestParam String
    status) {
    return produtoService.findByNomeAndStatus(nome, status);
    }
   
 	@GetMapping("/buscarPorNomeComecandoCom")
 public List<Produto> buscarPorNomeComecandoCom(@RequestParam String valor) {
    return produtoService.findByNomeStartingWith(valor);
    }
   
 	@GetMapping("/buscarPorNomeTerminandoCom")
 public List<Produto> buscarPorNomeTerminandoCom(@RequestParam String valor) {
    return produtoService.findByNomeEndingWith(valor);
    }
    
    // ==============================================================================
    // =================== BUSCAS DETALHADAS POR PREÇO ==============================
    // ==============================================================================
    
    @GetMapping("/buscarPorPreco")
  public List<Produto> buscarPorPreco(@RequestParam Double valor) {
    return produtoService.findByPreco(valor);
    }
    
    @GetMapping("/buscarPorPrecoMaiorQue")
 public List<Produto> buscarPorPrecoMaiorQue(@RequestParam Double valor) {
	 return produtoService.findByPrecoGreaterThan(valor);
	 }
	
    @GetMapping("/buscarPorPrecoMenorQue")
 public List<Produto> buscarPorPrecoMenorQue(@RequestParam Double valor) {
	 return produtoService.findByPrecoLessThan(valor);
	 }
	
    @GetMapping("/buscarTotalPreco")
 public Double buscarTotalPreco() {
	 return produtoService.findTotalPreco();
	 }
    
	 // ==============================================================================
	 // ================ BUSCAS DETALHADAS POR QUANTIDADE DE PRODUTO =================
	 // ==============================================================================
	    
    @GetMapping("/buscarPorQuantidade")
  public List<Produto> buscarPorQuantidade(@RequestParam Integer valor) {
     return produtoService.findByQuantidade(valor);
    }
    @GetMapping("/buscarPorQuantidadeMenorQue")
 public List<Produto> buscarPorQuantidadeMenorQue(@RequestParam Integer valor) {
	return produtoService.findByQuantidadeLessThan(valor);
    }
 	@GetMapping("/buscarPorQuantidadeMaiorQue")
 public List<Produto> buscarPorQuantidadeMaiorQue(@RequestParam Integer valor) {
	 return produtoService.findByQuantidadeGreaterThan(valor);
 	}
 	
 	//==============================================================================
 	// =================== BUSCAS DETALHADAS POR STATUS DE PRODUTO =================
 	//==============================================================================
 	
 	@GetMapping("/buscarPorStatus")
 public List<Produto> buscarPorStatus(@RequestParam(required = false) String
 	valor) {
 	 return produtoService.findByStatus(valor);
 	}
 
 	// Retorna exclusivamente produtos com status null
 	@GetMapping("/buscarPorStatusNulos")
 public List<Produto> buscarPorStatusNulos() {
 	 return produtoService.findByStatusIsNull();
 	}
 
 	// Busca baseada em dois campos (preço e status)
 	@GetMapping("/buscarPorPrecoEStatus")
 public List<Produto> buscarPorPrecoEStatus(@RequestParam Double preco,
 	@RequestParam String status) {
 	 return produtoService.findByPrecoAndStatus(preco, status);
 	}
 
 	// Endpoint para trazer o total de produtos
 	@GetMapping("/contarTotalDeProdutos")
 public Long contarTotalDeProdutos() {
 	 return produtoService.count();
 	}
 	// Se não passar nada, volta todos produtos com status padrão, neste caso será "Disponível".
 	
 	@GetMapping("/buscarPorStatusPadrao")
 	public List<Produto> buscarPorStatusPadrao(@RequestParam(defaultValue = "Disponível") String valor) {
 	 return produtoService.findByStatus(valor);
 	} 
}