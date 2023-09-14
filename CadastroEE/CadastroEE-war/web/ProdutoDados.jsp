
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Produto</title>
    <!-- Inclua o link para o Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <!-- Inclua o link para o Bootstrap JavaScript (copiado do Bootstrap CDN) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="container"> <!-- Adicione a classe 'container' ao body -->
    <h1>Cadastro de Produto</h1>
    
    <form action="ServletProdutoFC" method="post" class="form"> <!-- Adicione a classe 'form' ao formulário -->
        <input type="hidden" name="acao" value="${empty produto ? 'incluir' : 'alterar'}">
        <c:if test="${not empty produto}">
            <input type="hidden" name="idProduto" value="${produto.idProduto}">
        </c:if>
        
        <div class="mb-3"> <!-- Encapsule cada par label / input em div com classe 'mb-3' -->
            <label for="nome" class="form-label">Nome:</label> <!-- Adicione a classe 'form-label' em cada label -->
            <input type="text" id="nome" name="nome" value="${empty produto ? '' : produto.nome}" required class="form-control"> <!-- Adicione a classe 'form-control' em cada input -->
        </div>
        
        <div class="mb-3"> <!-- Encapsule cada par label / input em div com classe 'mb-3' -->
            <label for="quantidade" class="form-label">Quantidade:</label> <!-- Adicione a classe 'form-label' em cada label -->
            <input type="number" id="quantidade" name="quantidade" value="${empty produto ? '' : produto.quantidade}" required class="form-control"> <!-- Adicione a classe 'form-control' em cada input -->
        </div>
        
        <div class="mb-3"> <!-- Encapsule cada par label / input em div com classe 'mb-3' -->
            <label for="precoVenda" class="form-label">Preço de Venda:</label> <!-- Adicione a classe 'form-label' em cada label -->
            <input type="number" id="precoVenda" name="precoVenda" value="${empty produto ? '' : produto.precoVenda}" required class="form-control"> <!-- Adicione a classe 'form-control' em cada input -->
        </div>
        
        <input type="submit" value="${empty produto ? 'Incluir' : 'Alterar'} Produto" class="btn btn-primary"> <!-- Adicione as classes 'btn' e 'btn-primary' ao botão -->
    </form>
</body>
</html>
