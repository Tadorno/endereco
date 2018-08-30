-- Database: seia_endereco

-- DROP DATABASE seia_endereco;

CREATE DATABASE seia_endereco
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE seia_endereco
  IS 'Database de endereços';


-- Table: public.endereco

-- DROP TABLE public.endereco;

CREATE TABLE public.endereco
(
  id integer NOT NULL DEFAULT nextval('sq_endereco'::regclass),
  cep character varying(8), -- Código de Endereçamento Postal cadastrado nos Correios
  logradouro character varying, -- Logradouro associado ao CEP cadastrado nos Correios
  bairro character varying, -- Nome do bairro associado ao CEP cadastrado nos Correios
  municipio character varying, -- Município do Endereço
  uf character varying(2), -- UF do Endereço
  excluido boolean NOT NULL DEFAULT false, -- Flag para exclusão lógica
  dtc_excluido timestamp without time zone, -- Data da exclusão lógica
  fonte smallint, -- Fonte do dado do enredeço. A fonte determina o grau de confiabilidade do dado. quanto menor o valor, maior a confiabilidade....
  dtc_cadastro timestamp without time zone NOT NULL DEFAULT now(), -- Data de cadastro do registro
  CONSTRAINT pk_endereco PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.endereco
  OWNER TO postgres;
COMMENT ON TABLE public.endereco
  IS 'Tabela que armazena as informações comuns de endereços';
COMMENT ON COLUMN public.endereco.cep IS 'Código de Endereçamento Postal cadastrado nos Correios';
COMMENT ON COLUMN public.endereco.logradouro IS 'Logradouro associado ao CEP cadastrado nos Correios';
COMMENT ON COLUMN public.endereco.bairro IS 'Nome do bairro associado ao CEP cadastrado nos Correios';
COMMENT ON COLUMN public.endereco.municipio IS 'Município do Endereço';
COMMENT ON COLUMN public.endereco.uf IS 'UF do Endereço';
COMMENT ON COLUMN public.endereco.excluido IS 'Flag para exclusão lógica';
COMMENT ON COLUMN public.endereco.dtc_excluido IS 'Data da exclusão lógica';
COMMENT ON COLUMN public.endereco.fonte IS 'Fonte do dado do enredeço. A fonte determina o grau de confiabilidade do dado. quanto menor o valor, maior a confiabilidade.

1 = Correios
2 = Local
99 = Outros ';
COMMENT ON COLUMN public.endereco.dtc_cadastro IS 'Data de cadastro do registro';
