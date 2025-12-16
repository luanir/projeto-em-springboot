package com.produtoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtoapi.model.Produto;
import com.produtoapi.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<Produto> listarTodos(){
        return produtoRepository.findAll(); //Retorna todos os produtos da base de dados
    }

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto); //Salva os produto na base de dados
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id); //Deleta o produto na base de dados
    }

    public Produto atualizar(Long id, Produto produto){ //Atualiza o produto
        if(produtoRepository.existsById(id)){
            produto.setId(id);
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado"); /*  Se você quiser lançar uma exceção propositalmente em Java, use a palavra-chave throw. 
                                                                    Isso é útil para indicar que ocorreu um erro em uma condição específica durante a execução do programa.  */
        }
    }

    public Optional<Produto> findById(Long id){
        return produtoRepository.findById(id);
    }

    public List<Produto> salvarLista(List<Produto> produtos){
        return produtoRepository.saveAll(produtos);
    } //Usado para adicionar em lotes varios

    // ==============================================================================
    // =================== BUSCAS DETALHADAS POR NOME DE PRODUTO ====================
    // ==============================================================================
    
    // Métodos de busca por nome

    public List<Produto> findByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public List<Produto> findByNomeContaining(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    public List<Produto> findByNomeAndStatus(String nome, String status) {
        return produtoRepository.findByNomeAndStatus(nome, status);
    }

    public List<Produto> findByNomeStartingWith(String prefix) {
        return produtoRepository.findByNomeStartingWith(prefix);
    }

    public List<Produto> findByNomeEndingWith(String suffix) {
        return produtoRepository.findByNomeEndingWith(suffix);
    }
    
	 // ==============================================================================
	 // =================== BUSCAS DETALHADAS POR PREÇO ==============================
	 // ==============================================================================
    
    public List<Produto> findByPreco(Double preco) {
    	return produtoRepository.findByPreco(preco);
    }
   
    public List<Produto> findByPrecoGreaterThan(Double preco) {
    	return produtoRepository.findByPrecoGreaterThan(preco);
    }
    
    public List<Produto> findByPrecoLessThan(Double preco) {
    	return produtoRepository.findByPrecoLessThan(preco);
    }
    
    public Double findTotalPreco() {
    	return produtoRepository.findTotalPreco();
    }
    
	 // ==============================================================================
	 // ================ BUSCAS DETALHADAS POR QUANTIDADE DE PRODUTO =================
	 // ==============================================================================
    
    public List<Produto> findByQuantidade(Integer quantidade) {
	 return produtoRepository.findByQuantidade(quantidade);
    }
 	
    public List<Produto> findByQuantidadeLessThan(Integer quantidade) {
 		return produtoRepository.findByQuantidadeLessThan(quantidade);
    }
 	
    public List<Produto> findByQuantidadeGreaterThan(Integer quantidade) {
 		return produtoRepository.findByQuantidadeGreaterThan(quantidade);
    }
    
    //===========================================================================
    //============= BUSCAS DETALHADAS POR STATUS E PREÇO DE PRODUTO =============
    //===========================================================================
    
    public List<Produto> findByStatus(String status) {
    	return produtoRepository.findByStatus(status);
    }
    
    public List<Produto> findByStatusIsNull() {
    	return produtoRepository.findByStatusIsNull();
    }
    
    public List<Produto> findByPrecoAndStatus(Double preco, String status) {
    	return produtoRepository.findByPrecoAndStatus(preco, status);
    }
    
    // Método para trazer o número total de produtos
    
    public Long count() {
    	return produtoRepository.count();
    }
}