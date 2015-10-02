package br.com.unicamp.inf321.models.comprefacil;

import org.graphwalker.core.machine.ExecutionContext;

import br.com.unicamp.inf321.AdicionarProdutoCarrinhoSHARED;
import br.com.unicamp.inf321.utils.WebDriveUtils;

public class AdicionarProdutoCarrinhoTest extends ExecutionContext implements
		AdicionarProdutoCarrinhoSHARED {
	
	@Override
	public void v_adicaoNovoProduto() {
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina04.html");
		
		System.out.println("Running: v_adicaoNovoProduto");
	}

	@Override
	public void v_validacaoQtdAlteradaEstoque() {
		System.out.println("Running: v_validacaoQtdAlteradaEstoque");
	}

	@Override
	public void v_exibicaoAvisoProdutoIndisponivel() {
		
		
		System.out.println("Running: v_exibicaoAvisoProdutoIndisponivel");
	}

	@Override
	public void e_validarNovaQtdDisponivelEstoque() {
		System.out.println("Running: e_validarNovaQtdDisponivelEstoque");
	}

	@Override
	public void e_alterarParaQtdMaior() {
		System.out.println("Running: e_alterarParaQtdMaior");
	}

	@Override
	public void e_alterarParaQtdMenor() {
		System.out.println("Running: e_alterarParaQtdMenor");
	}

	@Override
	public void e_exibirCarrinho() {
		System.out.println("Running: e_exibirCarrinho");
	}

	@Override
	public void v_ExibicaoResultadoBusca() {
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina03.html");
		
		System.out.println("Running: v_exibicaoResultadoCarrinho");
	}

//	@Override
//	public void e_init() {
//		System.out.println("Running: e_init");
//	}

	@Override
	public void e_validarNovaQtdDisponivel() {
		System.out.println("Running: e_validarNovaQtdDisponivel");
	}

	@Override
	public void e_adicionarNovoProduto() {
		System.out.println("Running: e_adicionarNovoProduto");
	}

	@Override
	public void v_alteracaoQtdProduto() {
		System.out.println("Running: v_alteracaoQtdProduto");
	}

	@Override
	public void v_exibicaoEstadoCarrinho() {
		WebDriveUtils.openURL("http://www.students.ic.unicamp.br/~espsoft75/cfacil/Pagina05.html");
		
		System.out.println("Running: v_exibicaoEstadoCarrinho");
	}

	@Override
	public void e_selecionarProduto() {
		System.out.println("Running: e_selecionarProduto");
	}

	@Override
	public void e_procurarNovoProduto() {
		System.out.println("Running: e_procurarNovoProduto");
	}

}
