-- Database: requestsystem

-- DROP DATABASE requestsystem;

CREATE DATABASE requestsystem
    WITH 
    OWNER = "Zufar"
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1252'
    LC_CTYPE = 'Russian_Russia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
	-- SCHEMA: public

-- DROP SCHEMA public ;

CREATE SCHEMA public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;


-- SEQUENCE: public.order_seq

-- DROP SEQUENCE public.order_seq;

CREATE SEQUENCE public.order_seq
    INCREMENT 50
    START 101
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.order_seq
    OWNER TO "Zufar";
	
	-- SEQUENCE: public.role_seq

-- DROP SEQUENCE public.role_seq;

CREATE SEQUENCE public.role_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.role_seq
    OWNER TO "Zufar";
	
	-- SEQUENCE: public.user_seq

-- DROP SEQUENCE public.user_seq;

CREATE SEQUENCE public.user_seq
    INCREMENT 50
    START 101
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.user_seq
    OWNER TO "Zufar";
	
	-- Table: public.orders

-- DROP TABLE public.orders;

CREATE TABLE public.orders
(
    id bigint NOT NULL,
    title character varying(256) COLLATE pg_catalog."default" NOT NULL,
    user_id bigint,
    description character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.orders
    OWNER to "Zufar";
	
	-- Table: public.roles

-- DROP TABLE public.roles;

CREATE TABLE public.roles
(
    id bigint NOT NULL,
    name character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT uk_ofx66keruapi6vyqpv6f2or37 UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.roles
    OWNER to "Zufar";
	
	-- Table: public.user_roles

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT uk_5q4rc4fh1on6567qk69uesvyf UNIQUE (role_id),
    CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.user_roles
    OWNER to "Zufar";
	
	
	-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id bigint NOT NULL,
    firstname character varying(256) COLLATE pg_catalog."default" NOT NULL,
    lastname character varying(256) COLLATE pg_catalog."default" NOT NULL,
    login character varying(256) COLLATE pg_catalog."default" NOT NULL,
    nickname character varying(256) COLLATE pg_catalog."default" NOT NULL,
    password character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_2ty1xmrrgtn89xt7kyxx6ta7h UNIQUE (nickname),
    CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to "Zufar";