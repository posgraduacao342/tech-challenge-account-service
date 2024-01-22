CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE clientes (
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	nome varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	cpf varchar(255) NOT NULL,
	data_criacao timestamptz NOT NULL DEFAULT now(),
    data_atualizacao timestamptz NOT NULL DEFAULT now(),
    data_delecao timestamptz,
	CONSTRAINT cliente_pkey PRIMARY KEY (id)
);