package com.produtoapi.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.produtoapi.model.Produto;


public class CRUDJavaClient {
	private static final String BASE_URL = "http://localhost:8080/produtos";
	private RestTemplate restTemplate;
	
	public CRUDJavaClient() {
		this.restTemplate = new RestTemplate();
	}
	
	//Metodo para listar todos os produtos
	public void listarTodos() {
		ResponseEntity<Produto[]> response = restTemplate.getForEntity(BASE_URL, Produto[].class);
		List<Produto> produtos = Arrays.asList(response.getBody());
		produtos.forEach(produto -> {
			System.out.println("ID: " + produto.getId());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Preço: " + produto.getPreco());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Status: " + produto.getStatus());
			System.out.println("------------------------");
		});
	}

	//Metodo para salvar um novo produto
	public Produto salvar(Produto produto){
		HttpEntity<Produto> request = new HttpEntity<>(produto);
		return restTemplate.postForObject(BASE_URL, request, Produto.class);
	}

	//Metodo para deletar o produto
	public void deletar(long id){
		restTemplate.delete(BASE_URL + "/" + id);
	}

	//Metodo para atualizar o produto
	public Produto atualizar(long id, Produto produto){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Produto> request = new HttpEntity<>(produto, headers);
		ResponseEntity<Produto> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, request, Produto.class);
		return response.getBody();
	}
	
	//Metodo para buscar o produto pelo ID
	public Produto findById(int id){
		ResponseEntity<Produto> response = restTemplate.getForEntity(BASE_URL + "/" + id, Produto.class);
		return response.getBody();
	}


	public static void main (String [] args){
		
		CRUDJavaClient client = new CRUDJavaClient();

		//=========== Criar um novo produto ============
		/* 
		Produto novoProduto = new Produto();
		novoProduto.setNome("Skate Profissional");
		novoProduto.setPreco(300);
		novoProduto.setQuantidade(20);
		novoProduto.setStatus("disponivel");

		System.out.println("Criar um novo produto");
		client.salvar(novoProduto);
		client.listarTodos(); // Listar todos os produtos */

		
		//========== Atualizar o produto ===========
		/*
		Produto atualizarProduto = client.findById(352);
		atualizarProduto.setNome("Skate Infantil");
		atualizarProduto.setPreco(100.66);

		System.out.print("Atualizar o produto");
		client.atualizar(atualizarProduto.getId(), atualizarProduto);

		//Listar todos os produtos novamente
		client.listarTodos(); */

		//============ Deletar o produto ============
		/*
		System.out.println("Deletar o produto");
		client.deletar(302);

		//Listar todos os produtos novamente
		client.listarTodos(); */
		

	}
}
