--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

-- Started on 2022-03-25 15:50:24

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 41587)
-- Name: account_bank; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_bank (
    id integer NOT NULL,
    address character varying(255),
    city character varying(255),
    iban character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    statut_active boolean,
    zip integer,
    user_id integer
);


ALTER TABLE public.account_bank OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 41586)
-- Name: account_bank_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_bank_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_bank_id_seq OWNER TO postgres;

--
-- TOC entry 3339 (class 0 OID 0)
-- Dependencies: 209
-- Name: account_bank_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.account_bank_id_seq OWNED BY public.account_bank.id;


--
-- TOC entry 215 (class 1259 OID 41830)
-- Name: connections; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.connections (
    user_id integer NOT NULL,
    friend_id integer NOT NULL
);


ALTER TABLE public.connections OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 41596)
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    id integer NOT NULL,
    balance real,
    date_transaction timestamp without time zone,
    id_transaction character varying(255),
    is_account_bank boolean,
    libelle_perso character varying(255),
    statut_transaction boolean,
    account_bank_id integer,
    emitter_id integer,
    receiver_id integer,
    taxe double precision
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 41595)
-- Name: transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_id_seq OWNER TO postgres;

--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 211
-- Name: transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaction_id_seq OWNED BY public.transaction.id;


--
-- TOC entry 214 (class 1259 OID 41795)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    password character varying(255),
    balance real,
    date_creat timestamp without time zone,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    statut_active boolean
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 41794)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 213
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3178 (class 2604 OID 41590)
-- Name: account_bank id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_bank ALTER COLUMN id SET DEFAULT nextval('public.account_bank_id_seq'::regclass);


--
-- TOC entry 3179 (class 2604 OID 41599)
-- Name: transaction id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction ALTER COLUMN id SET DEFAULT nextval('public.transaction_id_seq'::regclass);


--
-- TOC entry 3180 (class 2604 OID 41798)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3182 (class 2606 OID 41594)
-- Name: account_bank account_bank_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_bank
    ADD CONSTRAINT account_bank_pkey PRIMARY KEY (id);


--
-- TOC entry 3184 (class 2606 OID 41603)
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


--
-- TOC entry 3186 (class 2606 OID 41804)
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 3188 (class 2606 OID 41802)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 41833)
-- Name: connections fk20sa372nawtxpqrh6k2su61k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.connections
    ADD CONSTRAINT fk20sa372nawtxpqrh6k2su61k FOREIGN KEY (friend_id) REFERENCES public.users(id);


--
-- TOC entry 3189 (class 2606 OID 41805)
-- Name: account_bank fk7xkis8xvyqcq2so0uc9vikxs7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_bank
    ADD CONSTRAINT fk7xkis8xvyqcq2so0uc9vikxs7 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3190 (class 2606 OID 41820)
-- Name: transaction fkbyt1k9mwrvggqeqvr63wg1bvh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fkbyt1k9mwrvggqeqvr63wg1bvh FOREIGN KEY (emitter_id) REFERENCES public.users(id);


--
-- TOC entry 3192 (class 2606 OID 41627)
-- Name: transaction fkiicimtpl2sykis5ui0k3xycpe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fkiicimtpl2sykis5ui0k3xycpe FOREIGN KEY (account_bank_id) REFERENCES public.account_bank(id);


--
-- TOC entry 3194 (class 2606 OID 41838)
-- Name: connections fkltpo1ymtaafd67hx5tls1db6u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.connections
    ADD CONSTRAINT fkltpo1ymtaafd67hx5tls1db6u FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3191 (class 2606 OID 41825)
-- Name: transaction fksv2y5gvlk351bf2b8kao6o7n9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT fksv2y5gvlk351bf2b8kao6o7n9 FOREIGN KEY (receiver_id) REFERENCES public.users(id);


-- Completed on 2022-03-25 15:50:24

--
-- PostgreSQL database dump complete
--

