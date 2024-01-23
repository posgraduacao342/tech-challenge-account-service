# language: pt
Funcionalidade: Cliente

  Cenario: Cadastrar cliente
    Quando cadastro um cliente
    Entao o cliente é registrado com sucesso
    E deve ser apresentado

  Cenario: Buscar um cliente por cpf
    Dado que já possui cliente cadastrado
    Quando efetuar a busca por cpf
    Entao cliente é retornado

  Cenario: Remover cliente
    Dado que já possui cliente cadastrado
    Quando requisitar a remoção do cliente
    Entao o cliente é removido com sucesso