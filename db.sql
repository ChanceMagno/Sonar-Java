--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: events; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE events (
    id integer NOT NULL,
    event_name character varying,
    game_name character varying,
    platform_id integer,
    date date,
    "time" time without time zone,
    max_players integer,
    event_creator_id integer
);


ALTER TABLE events OWNER TO "Guest";

--
-- Name: events_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE events_id_seq OWNER TO "Guest";

--
-- Name: events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE events_id_seq OWNED BY events.id;


--
-- Name: gamers; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE gamers (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE gamers OWNER TO "Guest";

--
-- Name: gamers_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE gamers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE gamers_id_seq OWNER TO "Guest";

--
-- Name: gamers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE gamers_id_seq OWNED BY gamers.id;


--
-- Name: platforms; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE platforms (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE platforms OWNER TO "Guest";

--
-- Name: platforms_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE platforms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE platforms_id_seq OWNER TO "Guest";

--
-- Name: platforms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE platforms_id_seq OWNED BY platforms.id;


--
-- Name: signups; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE signups (
    id integer NOT NULL,
    gamer_id integer,
    event_id integer
);


ALTER TABLE signups OWNER TO "Guest";

--
-- Name: signups_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE signups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE signups_id_seq OWNER TO "Guest";

--
-- Name: signups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE signups_id_seq OWNED BY signups.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY events ALTER COLUMN id SET DEFAULT nextval('events_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY gamers ALTER COLUMN id SET DEFAULT nextval('gamers_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY platforms ALTER COLUMN id SET DEFAULT nextval('platforms_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY signups ALTER COLUMN id SET DEFAULT nextval('signups_id_seq'::regclass);


--
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY events (id, event_name, game_name, platform_id, date, "time", max_players, event_creator_id) FROM stdin;
\.


--
-- Name: events_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('events_id_seq', 1, false);


--
-- Data for Name: gamers; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY gamers (id, name) FROM stdin;
\.


--
-- Name: gamers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('gamers_id_seq', 1, false);


--
-- Data for Name: platforms; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY platforms (id, name) FROM stdin;
1	PC
2	Xbox One
3	Xbox 360
4	Xbox
5	Playstation 4
6	Playstation 3
7	Switch
8	Wii
9	3DS
\.


--
-- Name: platforms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('platforms_id_seq', 1, false);


--
-- Data for Name: signups; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY signups (id, gamer_id, event_id) FROM stdin;
\.


--
-- Name: signups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('signups_id_seq', 1, false);


--
-- Name: events_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- Name: gamers_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY gamers
    ADD CONSTRAINT gamers_pkey PRIMARY KEY (id);


--
-- Name: platforms_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY platforms
    ADD CONSTRAINT platforms_pkey PRIMARY KEY (id);


--
-- Name: signups_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY signups
    ADD CONSTRAINT signups_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

