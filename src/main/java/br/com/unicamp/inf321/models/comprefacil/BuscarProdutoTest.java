package br.com.unicamp.inf321.models.comprefacil;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.unicamp.inf321.BuscarProdutoSHARED;
import br.com.unicamp.inf321.utils.WebDriveUtils;

@GraphWalker(value = "random(edge_coverage(100))", start = "e_Init")
public class BuscarProdutoTest extends ExecutionContext implements
		BuscarProdutoSHARED {

	@Override
	public void v_PesquisarProduto() {

		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina02.html");
	
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina03.html");
		
		System.out.println("v_PesquisarProduto");

	}

	@Override
	public void e_ExecutarPesquisaProduto() {

		System.out.println("e_ExecutarPesquisaProduto");

	}


	@Override
	public void e_ProdutoNaoEncontrado() {
		System.out.println("e_ProdutoNaoEncontrado");

	}

	@Override
	public void v_Erro() {
		System.out.println("v_Erro");

	}

	@Override
	public void e_ResultadoNOK() {
		System.out.println("e_ResultadoNOK");

	}

	@Override
	public void v_ExecutarPesquisaProduto() {
		System.out.println("v_ExecutarPesquisaProduto");

	}

	@Override
	public void v_ExibicaoResultadoBusca() {
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina04.html");
		System.out.println("v_MostrarDetalhes");

	}

	@Override
	public void e_ResultadoOK() {
		System.out.println("e_ResultadoOK");

	}

	@Override
	public void v_Sucesso() {
		System.out.println("v_Sucesso");

	}

	@Override
	public void e_Init() {
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina01.html");
		System.out.println("e_Init");

	}

	@Override
	public void e_MostrarDetalhes() {
		System.out.println("e_MostrarDetalhes");

	}

	@Override
	public void e_retornarPesquisa() {
		System.out.println("e_retornarPesquisa");
	}

}
