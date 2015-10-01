package br.com.unicamp.inf321.models.comprefacil;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.unicamp.inf321.FinalizarCompraSHARED;


public class FinalizarCompraTest extends ExecutionContext implements
		FinalizarCompraSHARED {
	
	static WebDriver driver;

	static {
		driver = new FirefoxDriver();
	}

	@Override
	public void e_retornaCarrinhoVazio() {
		System.out.println("Running: e_retornaCarrinhoVazio");
	}

	@Override
	public void e_submetDadosInvalidos() {
		System.out.println("Running: e_submetDadosInvalidos");
	}

	@Override
	public void e_retornaDadosPagamento() {
		System.out.println("Running: e_retornaDadosPagamento");
	}

	@Override
	public void v_carrinho() {
		driver.get("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina06.html");
		System.out.println("Running: v_carrinho");
	}

	@Override
	public void v_Erro() {
		System.out.println("Running: v_Erro");
	}

	@Override
	public void e_finalizarCompra() {
		System.out.println("Running: e_finalizarCompra");
	}
	
	@Override
	public void v_DadosInvalidos() {
		System.out.println("Running: v_DadosInvalidos");
	}

	@Override
	public void e_finalizaVazio() {
		System.out.println("Running: e_finalizaVazio");
	}

	@Override
	public void e_submeteDadosOK() {
		System.out.println("Running: e_submeteDadosOK");
	}

	@Override
	public void v_dadosPagamento() {
		driver.get("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina09.html");
		System.out.println("Running: v_dadosPagamento");
	}

	@Override
	public void v_CompraFinalizada() {
		driver.get("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina10.html");
		System.out.println("Running: v_CompraFinalizada");
	}

	@Override
	public void e_retomaCarrinho() {
		driver.get("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina05.html");
		System.out.println("Running: e_retomaCarrinho");
	}

}
