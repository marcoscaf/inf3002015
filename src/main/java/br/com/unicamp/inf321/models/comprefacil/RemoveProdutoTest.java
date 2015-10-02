package br.com.unicamp.inf321.models.comprefacil;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.unicamp.inf321.BuscarProdutoSHARED;
import br.com.unicamp.inf321.RemoverProdutoSHARED;

@GraphWalker(value = "random(edge_coverage(100))", start = "e_Init")
public class RemoveProdutoTest extends ExecutionContext implements
		RemoverProdutoSHARED {

	@Override
	public void v_Erro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void e_incrementarEstoque() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void v_remocaoProdutos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void e_visualizarCarrinho() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void e_retornarCarrinho() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void v_exibicaoEstadoCarrinho() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void e_removerProdutos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void e_init() {
		// TODO Auto-generated method stub
		
	}

}
