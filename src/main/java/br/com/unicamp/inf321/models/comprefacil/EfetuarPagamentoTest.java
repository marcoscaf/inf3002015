package br.com.unicamp.inf321.models.comprefacil;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;

import br.com.unicamp.inf321.EfetuarPagamentoSHARED;
import br.com.unicamp.inf321.utils.WebDriveUtils;

@GraphWalker(value = "random(edge_coverage(100))", start = "e_Init")
public class EfetuarPagamentoTest extends ExecutionContext implements
		EfetuarPagamentoSHARED {

	@Override
	public void e_efetuarPagamento() {
		System.out.println("e_efetuarPagamento");
		
	}

	@Override
	public void e_autenticar() {
		System.out.println("e_autenticar");
		
	}

	@Override
	public void v_autenticacao() {
		System.out.println("v_autenticacao");
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina01.html");
		
	}

	@Override
	public void e_reprovarPagamento() {
		System.out.println("e_reprovarPagamento");
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina09.html");
	}

	@Override
	public void e_retornarPedido() {
		System.out.println("e_retornarPedido");
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina09.html");
		
	}

	@Override
	public void v_exibicaoPedido() {
		System.out.println("v_exibicaoPedido");
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina09.html");
		
	}

	@Override
	public void e_aprovarPagamento() {
		System.out.println("e_aprovarPagamento");
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina10.html");
		
	}

	@Override
	public void v_validacaoPagamento() {
		System.out.println("v_validacaoPagamento");
		
		
	}
}
