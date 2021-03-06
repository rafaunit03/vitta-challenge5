-- SQL Manager Lite for PostgreSQL 5.2.0.3
-- ---------------------------------------
-- Host      : localhost
-- Database  : vitame
-- Version   : PostgreSQL 9.6.2, compiled by Visual C++ build 1800, 64-bit



SET check_function_bodies = false;
--
-- Structure for table tab_quadrado (OID = 16464) : 
--
SET search_path = public, pg_catalog;
CREATE TABLE public.tab_quadrado (
    cod_quadrado integer NOT NULL,
    nome varchar(128) NOT NULL,
    x_inicio integer NOT NULL,
    x_fim integer NOT NULL,
    y_inicio integer NOT NULL,
    y_fim integer
)
WITH (oids = false);
--
-- Definition for sequence seq_tab_quadrado (OID = 16469) : 
--
CREATE SEQUENCE public.seq_tab_quadrado
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table tab_quadrado_pintado (OID = 16473) : 
--
CREATE TABLE public.tab_quadrado_pintado (
    cod_quadrado_pintado integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    cod_quadrado integer NOT NULL
)
WITH (oids = false);
--
-- Definition for sequence seq_tab_quadrado_pintado (OID = 16483) : 
--
CREATE SEQUENCE public.seq_tab_quadrado_pintado
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for index tab_quadrado_pkey (OID = 16467) : 
--
ALTER TABLE ONLY tab_quadrado
    ADD CONSTRAINT tab_quadrado_pkey
    PRIMARY KEY (cod_quadrado);
--
-- Definition for index tab_quadrado_pintado_pkey (OID = 16476) : 
--
ALTER TABLE ONLY tab_quadrado_pintado
    ADD CONSTRAINT tab_quadrado_pintado_pkey
    PRIMARY KEY (cod_quadrado_pintado);
--
-- Definition for index tab_quadrado_pintado_fk (OID = 16478) : 
--
ALTER TABLE ONLY tab_quadrado_pintado
    ADD CONSTRAINT tab_quadrado_pintado_fk
    FOREIGN KEY (cod_quadrado) REFERENCES tab_quadrado(cod_quadrado);
--
-- Data for sequence public.seq_tab_quadrado (OID = 16469)
--
SELECT pg_catalog.setval('seq_tab_quadrado', 8, true);
--
-- Data for sequence public.seq_tab_quadrado_pintado (OID = 16483)
--
SELECT pg_catalog.setval('seq_tab_quadrado_pintado', 5, true);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
