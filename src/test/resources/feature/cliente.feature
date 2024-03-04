# language: pt

Funcionalidade: Cliente

    Cenario: Cadastrar cliente
        Quando cadastro um cliente
        Então o cliente é registrado com sucesso
        E deve ser apresentado

    Cenario: Buscar clientes
        Dado que já possui clientes cadastrado
        Quando efetuar a busca
        Então os clientes são retornados

    Cenario: Buscar um cliente por cpf
        Dado que já possui o cliente cadastrado
        Quando efetuar a busca
        Então o cliente é retornado

    Cenario: Buscar um cliente por e-mail
        Dado que já possui o cliente cadastrado
        Quando efetuar a busca
        Então o cliente é retornado

    Cenario: Remover cliente
        Dado que já possui o cliente cadastrado
        Quando requisitar a remoção do cliente
        Então o cliente é removido com sucesso