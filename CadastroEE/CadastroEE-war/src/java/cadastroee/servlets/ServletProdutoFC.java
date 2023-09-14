
package cadastroee.servlets;

import cadastroee.model.Produto;
import cadastroee.controller.ProdutoFacadeLocal;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        String destino = "ProdutoLista.jsp"; // Página padrão de destino
        
        if (acao != null) {
            if (acao.equals("listar")) {
                List<Produto> produtos = facade.findAll(); // Recuperando todos os produtos
                request.setAttribute("produtos", produtos);
            } else if (acao.equals("formAlterar")) {
                String idProdutoStr = request.getParameter("idProduto");
                if (idProdutoStr != null) {
                    Integer idProduto = Integer.parseInt(idProdutoStr);
                    Produto produto = facade.find(idProduto); // Recuperando um produto pelo ID
                    request.setAttribute("produto", produto);
                    destino = "ProdutoDados.jsp";
                }
            } else if (acao.equals("formIncluir")) {
                // Ação para exibir o formulário de inclusão
                destino = "ProdutoDados.jsp";
            } else if (acao.equals("excluir")) {
                String idProdutoStr = request.getParameter("idProduto");
                if (idProdutoStr != null) {
                    Integer idProduto = Integer.parseInt(idProdutoStr);
                    Produto produto = facade.find(idProduto); // Recuperando um produto pelo ID
                    if (produto != null) {
                        facade.remove(produto);
                    }
                    // Recarregando a lista após a exclusão
                    List<Produto> produtos = facade.findAll(); // Recuperando todos os produtos
                    request.setAttribute("produtos", produtos);
                }
            } else if (acao.equals("alterar") || acao.equals("incluir")) {
                // Recuperando os parâmetros do formulário
                String idProdutoStr = request.getParameter("idProduto");
                String nome = request.getParameter("nome");
                String quantidadeStr = request.getParameter("quantidade");
                String precoVendaStr = request.getParameter("precoVenda");
                
                // Valores padrão
                Integer idProduto = null;
                Integer quantidade = null;
                Float precoVenda = null;
                
                // Convertendo valores se estiverem disponíveis
                if (idProdutoStr != null && !idProdutoStr.isEmpty()) {
                    idProduto = Integer.parseInt(idProdutoStr);
                }
                if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                    quantidade = Integer.parseInt(quantidadeStr);
                }
                if (precoVendaStr != null && !precoVendaStr.isEmpty()) {
                    precoVenda = Float.parseFloat(precoVendaStr);
                }
                
                // Criando ou atualizando o produto
                Produto produto;
                if (acao.equals("alterar")) {
                    produto = facade.find(idProduto); // Recuperando um produto pelo ID
                    produto.setNome(nome);
                    produto.setQuantidade(quantidade);
                    produto.setPrecoVenda(precoVenda);
                } else {
                    produto = new Produto(idProduto, nome, quantidade, precoVenda);
                }
                facade.edit(produto); // Inserindo ou atualizando um produto
                
                // Recarregando a lista após a inclusão ou atualização
                List<Produto> produtos = facade.findAll(); // Recuperando todos os produtos
                request.setAttribute("produtos", produtos);
            }
        }
        
        // Redirecionamento para o destino apropriado
        request.getRequestDispatcher(destino).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ServletProdutoFC";
    }
}