--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.1

-- Started on 2023-08-12 15:21:24

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
-- TOC entry 221 (class 1259 OID 16487)
-- Name: accommodation_unit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accommodation_unit (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    residence_id bigint NOT NULL
);


ALTER TABLE public.accommodation_unit OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16486)
-- Name: accommodation_unit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.accommodation_unit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.accommodation_unit_id_seq OWNER TO postgres;

--
-- TOC entry 3524 (class 0 OID 0)
-- Dependencies: 220
-- Name: accommodation_unit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.accommodation_unit_id_seq OWNED BY public.accommodation_unit.id;


--
-- TOC entry 223 (class 1259 OID 16496)
-- Name: accommodation_unit_image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accommodation_unit_image (
    id bigint NOT NULL,
    accommodation_unit_id bigint NOT NULL,
    image_id bigint NOT NULL
);


ALTER TABLE public.accommodation_unit_image OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16495)
-- Name: accommodation_unit_image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.accommodation_unit_image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.accommodation_unit_image_id_seq OWNER TO postgres;

--
-- TOC entry 3525 (class 0 OID 0)
-- Dependencies: 222
-- Name: accommodation_unit_image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.accommodation_unit_image_id_seq OWNED BY public.accommodation_unit_image.id;


--
-- TOC entry 225 (class 1259 OID 16503)
-- Name: activity_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.activity_type (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.activity_type OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16502)
-- Name: activity_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.activity_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.activity_type_id_seq OWNER TO postgres;

--
-- TOC entry 3526 (class 0 OID 0)
-- Dependencies: 224
-- Name: activity_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.activity_type_id_seq OWNED BY public.activity_type.id;


--
-- TOC entry 226 (class 1259 OID 16509)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id character varying(255) NOT NULL,
    additional character varying(255),
    street character varying(255),
    city_id bigint NOT NULL,
    residence_id bigint NOT NULL
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16517)
-- Name: amenity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.amenity (
    id bigint NOT NULL,
    bathroom smallint,
    garage smallint,
    kitchen smallint,
    parking smallint,
    sea_view smallint,
    terrace smallint,
    wifi smallint,
    accommodation_unit_id bigint NOT NULL
);


ALTER TABLE public.amenity OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16516)
-- Name: amenity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.amenity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.amenity_id_seq OWNER TO postgres;

--
-- TOC entry 3527 (class 0 OID 0)
-- Dependencies: 227
-- Name: amenity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.amenity_id_seq OWNED BY public.amenity.id;


--
-- TOC entry 230 (class 1259 OID 16524)
-- Name: amount; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.amount (
    id bigint NOT NULL,
    amount numeric(19,2) NOT NULL,
    currency character varying(3) NOT NULL
);


ALTER TABLE public.amount OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16523)
-- Name: amount_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.amount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.amount_id_seq OWNER TO postgres;

--
-- TOC entry 3528 (class 0 OID 0)
-- Dependencies: 229
-- Name: amount_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.amount_id_seq OWNED BY public.amount.id;


--
-- TOC entry 232 (class 1259 OID 16531)
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.city (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    zip character varying(255) NOT NULL,
    region_id bigint NOT NULL
);


ALTER TABLE public.city OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16530)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_id_seq OWNER TO postgres;

--
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 231
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- TOC entry 215 (class 1259 OID 16456)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id bigint NOT NULL,
    country_code character varying(2) NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.country OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16455)
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO postgres;

--
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 214
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;


--
-- TOC entry 234 (class 1259 OID 16540)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id bigint NOT NULL,
    image oid NOT NULL
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16539)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.image_id_seq OWNER TO postgres;

--
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 233
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;


--
-- TOC entry 236 (class 1259 OID 16547)
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.log (
    id bigint NOT NULL,
    log_message character varying(255) NOT NULL,
    activity_type_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.log OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16546)
-- Name: log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.log_id_seq OWNER TO postgres;

--
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 235
-- Name: log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.log_id_seq OWNED BY public.log.id;


--
-- TOC entry 238 (class 1259 OID 16554)
-- Name: price_period; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.price_period (
    id bigint NOT NULL,
    end_at date NOT NULL,
    start_at date NOT NULL,
    accommodation_unit_id bigint NOT NULL,
    amount_id bigint NOT NULL
);


ALTER TABLE public.price_period OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16553)
-- Name: price_period_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.price_period_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.price_period_id_seq OWNER TO postgres;

--
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 237
-- Name: price_period_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.price_period_id_seq OWNED BY public.price_period.id;


--
-- TOC entry 240 (class 1259 OID 16561)
-- Name: region; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.region (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    country_id bigint NOT NULL
);


ALTER TABLE public.region OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16560)
-- Name: region_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.region_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.region_id_seq OWNER TO postgres;

--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 239
-- Name: region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.region_id_seq OWNED BY public.region.id;


--
-- TOC entry 242 (class 1259 OID 16568)
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id bigint NOT NULL,
    cancelled boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    end_at date NOT NULL,
    note character varying(255),
    start_at date NOT NULL,
    accommodation_unit_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16567)
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_seq OWNER TO postgres;

--
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 241
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
-- TOC entry 244 (class 1259 OID 16575)
-- Name: residence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.residence (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    owner_id bigint NOT NULL
);


ALTER TABLE public.residence OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 16584)
-- Name: residence_attribute; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.residence_attribute (
    id bigint NOT NULL,
    distance_from_bank character varying(255),
    distance_from_beach character varying(255),
    distance_from_center character varying(255),
    distance_from_restoran character varying(255),
    distance_from_sea integer,
    distance_from_store character varying(255),
    residence_id bigint NOT NULL
);


ALTER TABLE public.residence_attribute OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 16583)
-- Name: residence_attribute_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.residence_attribute_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.residence_attribute_id_seq OWNER TO postgres;

--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 245
-- Name: residence_attribute_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.residence_attribute_id_seq OWNED BY public.residence_attribute.id;


--
-- TOC entry 243 (class 1259 OID 16574)
-- Name: residence_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.residence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.residence_id_seq OWNER TO postgres;

--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 243
-- Name: residence_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.residence_id_seq OWNED BY public.residence.id;


--
-- TOC entry 248 (class 1259 OID 16593)
-- Name: residence_image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.residence_image (
    id bigint NOT NULL,
    image_id bigint NOT NULL,
    residence_id bigint NOT NULL
);


ALTER TABLE public.residence_image OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 16592)
-- Name: residence_image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.residence_image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.residence_image_id_seq OWNER TO postgres;

--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 247
-- Name: residence_image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.residence_image_id_seq OWNED BY public.residence_image.id;


--
-- TOC entry 217 (class 1259 OID 16463)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    role character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16462)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 216
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- TOC entry 219 (class 1259 OID 16470)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    activated boolean NOT NULL,
    description character varying(255),
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    phone_number character varying(255),
    surname character varying(255) NOT NULL,
    image_id bigint,
    role_id bigint
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16469)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 218
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- TOC entry 3260 (class 2604 OID 16490)
-- Name: accommodation_unit id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accommodation_unit ALTER COLUMN id SET DEFAULT nextval('public.accommodation_unit_id_seq'::regclass);


--
-- TOC entry 3261 (class 2604 OID 16499)
-- Name: accommodation_unit_image id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accommodation_unit_image ALTER COLUMN id SET DEFAULT nextval('public.accommodation_unit_image_id_seq'::regclass);


--
-- TOC entry 3262 (class 2604 OID 16506)
-- Name: activity_type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_type ALTER COLUMN id SET DEFAULT nextval('public.activity_type_id_seq'::regclass);


--
-- TOC entry 3263 (class 2604 OID 16520)
-- Name: amenity id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amenity ALTER COLUMN id SET DEFAULT nextval('public.amenity_id_seq'::regclass);


--
-- TOC entry 3264 (class 2604 OID 16527)
-- Name: amount id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amount ALTER COLUMN id SET DEFAULT nextval('public.amount_id_seq'::regclass);


--
-- TOC entry 3265 (class 2604 OID 16534)
-- Name: city id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- TOC entry 3257 (class 2604 OID 16459)
-- Name: country id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


--
-- TOC entry 3266 (class 2604 OID 16543)
-- Name: image id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);


--
-- TOC entry 3267 (class 2604 OID 16550)
-- Name: log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);


--
-- TOC entry 3268 (class 2604 OID 16557)
-- Name: price_period id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.price_period ALTER COLUMN id SET DEFAULT nextval('public.price_period_id_seq'::regclass);


--
-- TOC entry 3269 (class 2604 OID 16564)
-- Name: region id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.region ALTER COLUMN id SET DEFAULT nextval('public.region_id_seq'::regclass);


--
-- TOC entry 3270 (class 2604 OID 16571)
-- Name: reservation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- TOC entry 3271 (class 2604 OID 16578)
-- Name: residence id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence ALTER COLUMN id SET DEFAULT nextval('public.residence_id_seq'::regclass);


--
-- TOC entry 3272 (class 2604 OID 16587)
-- Name: residence_attribute id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence_attribute ALTER COLUMN id SET DEFAULT nextval('public.residence_attribute_id_seq'::regclass);


--
-- TOC entry 3273 (class 2604 OID 16596)
-- Name: residence_image id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence_image ALTER COLUMN id SET DEFAULT nextval('public.residence_image_id_seq'::regclass);


--
-- TOC entry 3258 (class 2604 OID 16466)
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 3259 (class 2604 OID 16473)
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- TOC entry 3491 (class 0 OID 16487)
-- Dependencies: 221
-- Data for Name: accommodation_unit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accommodation_unit (id, description, name, residence_id) FROM stdin;
\.


--
-- TOC entry 3493 (class 0 OID 16496)
-- Dependencies: 223
-- Data for Name: accommodation_unit_image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accommodation_unit_image (id, accommodation_unit_id, image_id) FROM stdin;
\.


--
-- TOC entry 3495 (class 0 OID 16503)
-- Dependencies: 225
-- Data for Name: activity_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.activity_type (id, name) FROM stdin;
\.


--
-- TOC entry 3496 (class 0 OID 16509)
-- Dependencies: 226
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (id, additional, street, city_id, residence_id) FROM stdin;
\.


--
-- TOC entry 3498 (class 0 OID 16517)
-- Dependencies: 228
-- Data for Name: amenity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.amenity (id, bathroom, garage, kitchen, parking, sea_view, terrace, wifi, accommodation_unit_id) FROM stdin;
\.


--
-- TOC entry 3500 (class 0 OID 16524)
-- Dependencies: 230
-- Data for Name: amount; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.amount (id, amount, currency) FROM stdin;
\.


--
-- TOC entry 3502 (class 0 OID 16531)
-- Dependencies: 232
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.city (id, name, zip, region_id) FROM stdin;
\.


--
-- TOC entry 3485 (class 0 OID 16456)
-- Dependencies: 215
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.country (id, country_code, name) FROM stdin;
3	DE	Germany
4	FR	France
1	HR	Croatia
\.


--
-- TOC entry 3504 (class 0 OID 16540)
-- Dependencies: 234
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image (id, image) FROM stdin;
\.


--
-- TOC entry 3506 (class 0 OID 16547)
-- Dependencies: 236
-- Data for Name: log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.log (id, log_message, activity_type_id, user_id) FROM stdin;
\.


--
-- TOC entry 3508 (class 0 OID 16554)
-- Dependencies: 238
-- Data for Name: price_period; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.price_period (id, end_at, start_at, accommodation_unit_id, amount_id) FROM stdin;
\.


--
-- TOC entry 3510 (class 0 OID 16561)
-- Dependencies: 240
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.region (id, name, country_id) FROM stdin;
4	Varazdinskae	3
7	Varazdinskd	1
8	Varazdinskf	1
9	Varazdinske	1
10	Varazdinskede	1
11	Splitsko-dalmatinska	1
14	Drecka	3
2	Croatia	3
\.


--
-- TOC entry 3512 (class 0 OID 16568)
-- Dependencies: 242
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, cancelled, created_at, end_at, note, start_at, accommodation_unit_id, user_id) FROM stdin;
\.


--
-- TOC entry 3514 (class 0 OID 16575)
-- Dependencies: 244
-- Data for Name: residence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.residence (id, description, name, type, owner_id) FROM stdin;
\.


--
-- TOC entry 3516 (class 0 OID 16584)
-- Dependencies: 246
-- Data for Name: residence_attribute; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.residence_attribute (id, distance_from_bank, distance_from_beach, distance_from_center, distance_from_restoran, distance_from_sea, distance_from_store, residence_id) FROM stdin;
\.


--
-- TOC entry 3518 (class 0 OID 16593)
-- Dependencies: 248
-- Data for Name: residence_image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.residence_image (id, image_id, residence_id) FROM stdin;
\.


--
-- TOC entry 3487 (class 0 OID 16463)
-- Dependencies: 217
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, role) FROM stdin;
\.


--
-- TOC entry 3489 (class 0 OID 16470)
-- Dependencies: 219
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, activated, description, email, name, password, phone_number, surname, image_id, role_id) FROM stdin;
\.


--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 220
-- Name: accommodation_unit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.accommodation_unit_id_seq', 1, false);


--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 222
-- Name: accommodation_unit_image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.accommodation_unit_image_id_seq', 1, false);


--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 224
-- Name: activity_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.activity_type_id_seq', 1, false);


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 227
-- Name: amenity_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.amenity_id_seq', 1, false);


--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 229
-- Name: amount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.amount_id_seq', 1, false);


--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 231
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.city_id_seq', 1, false);


--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 214
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_id_seq', 5, true);


--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 233
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 1, false);


--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 235
-- Name: log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.log_id_seq', 1, false);


--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 237
-- Name: price_period_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.price_period_id_seq', 1, false);


--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 239
-- Name: region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.region_id_seq', 14, true);


--
-- TOC entry 3552 (class 0 OID 0)
-- Dependencies: 241
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 1, false);


--
-- TOC entry 3553 (class 0 OID 0)
-- Dependencies: 245
-- Name: residence_attribute_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.residence_attribute_id_seq', 1, false);


--
-- TOC entry 3554 (class 0 OID 0)
-- Dependencies: 243
-- Name: residence_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.residence_id_seq', 1, false);


--
-- TOC entry 3555 (class 0 OID 0)
-- Dependencies: 247
-- Name: residence_image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.residence_image_id_seq', 1, false);


--
-- TOC entry 3556 (class 0 OID 0)
-- Dependencies: 216
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 1, false);


--
-- TOC entry 3557 (class 0 OID 0)
-- Dependencies: 218
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


--
-- TOC entry 3291 (class 2606 OID 16501)
-- Name: accommodation_unit_image accommodation_unit_image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accommodation_unit_image
    ADD CONSTRAINT accommodation_unit_image_pkey PRIMARY KEY (id);


--
-- TOC entry 3289 (class 2606 OID 16494)
-- Name: accommodation_unit accommodation_unit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accommodation_unit
    ADD CONSTRAINT accommodation_unit_pkey PRIMARY KEY (id);


--
-- TOC entry 3293 (class 2606 OID 16508)
-- Name: activity_type activity_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activity_type
    ADD CONSTRAINT activity_type_pkey PRIMARY KEY (id);


--
-- TOC entry 3295 (class 2606 OID 16515)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 3297 (class 2606 OID 16522)
-- Name: amenity amenity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amenity
    ADD CONSTRAINT amenity_pkey PRIMARY KEY (id);


--
-- TOC entry 3299 (class 2606 OID 16529)
-- Name: amount amount_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amount
    ADD CONSTRAINT amount_pkey PRIMARY KEY (id);


--
-- TOC entry 3301 (class 2606 OID 16538)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- TOC entry 3275 (class 2606 OID 16461)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- TOC entry 3305 (class 2606 OID 16545)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 3307 (class 2606 OID 16552)
-- Name: log log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);


--
-- TOC entry 3309 (class 2606 OID 16559)
-- Name: price_period price_period_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.price_period
    ADD CONSTRAINT price_period_pkey PRIMARY KEY (id);


--
-- TOC entry 3311 (class 2606 OID 16566)
-- Name: region region_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.region
    ADD CONSTRAINT region_pkey PRIMARY KEY (id);


--
-- TOC entry 3315 (class 2606 OID 16573)
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- TOC entry 3319 (class 2606 OID 16591)
-- Name: residence_attribute residence_attribute_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence_attribute
    ADD CONSTRAINT residence_attribute_pkey PRIMARY KEY (id);


--
-- TOC entry 3321 (class 2606 OID 16598)
-- Name: residence_image residence_image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence_image
    ADD CONSTRAINT residence_image_pkey PRIMARY KEY (id);


--
-- TOC entry 3317 (class 2606 OID 16582)
-- Name: residence residence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence
    ADD CONSTRAINT residence_pkey PRIMARY KEY (id);


--
-- TOC entry 3281 (class 2606 OID 16468)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3283 (class 2606 OID 16483)
-- Name: role uk_bjxn5ii7v7ygwx39et0wawu0q; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_bjxn5ii7v7ygwx39et0wawu0q UNIQUE (role);


--
-- TOC entry 3303 (class 2606 OID 16600)
-- Name: city uk_hry8a4gw6jil5eu84p8munrjq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT uk_hry8a4gw6jil5eu84p8munrjq UNIQUE (zip);


--
-- TOC entry 3313 (class 2606 OID 16602)
-- Name: region uk_ixr2itih2n9q41fv3qx6mbkrp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.region
    ADD CONSTRAINT uk_ixr2itih2n9q41fv3qx6mbkrp UNIQUE (name);


--
-- TOC entry 3277 (class 2606 OID 16481)
-- Name: country uk_llidyp77h6xkeokpbmoy710d4; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT uk_llidyp77h6xkeokpbmoy710d4 UNIQUE (name);


--
-- TOC entry 3285 (class 2606 OID 16485)
-- Name: user uk_ob8kqyqqgmefl0aco34akdtpe; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email);


--
-- TOC entry 3279 (class 2606 OID 16479)
-- Name: country uk_oqixmig4k8qxc8oba3fl4gqkr; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT uk_oqixmig4k8qxc8oba3fl4gqkr UNIQUE (country_code);


--
-- TOC entry 3287 (class 2606 OID 16477)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 3331 (class 2606 OID 16653)
-- Name: log fk3wxdofviqe2smmvh1w1yf98o1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT fk3wxdofviqe2smmvh1w1yf98o1 FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 3340 (class 2606 OID 16698)
-- Name: residence_image fk5xbkwdnvayaobash8oym0ya3r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence_image
    ADD CONSTRAINT fk5xbkwdnvayaobash8oym0ya3r FOREIGN KEY (residence_id) REFERENCES public.residence(id);


--
-- TOC entry 3325 (class 2606 OID 16623)
-- Name: accommodation_unit_image fk7rj72y8hqa29sjllesku09u87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accommodation_unit_image
    ADD CONSTRAINT fk7rj72y8hqa29sjllesku09u87 FOREIGN KEY (image_id) REFERENCES public.image(id);


--
-- TOC entry 3335 (class 2606 OID 16668)
-- Name: region fk7vb2cqcnkr9391hfn72louxkq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.region
    ADD CONSTRAINT fk7vb2cqcnkr9391hfn72louxkq FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- TOC entry 3322 (class 2606 OID 16603)
-- Name: user fk9hpx11qlu8cqhrkjn0yor93h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk9hpx11qlu8cqhrkjn0yor93h FOREIGN KEY (image_id) REFERENCES public.image(id);


--
-- TOC entry 3341 (class 2606 OID 16693)
-- Name: residence_image fkas88a1k1s92d5qtu2mpufwc54; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence_image
    ADD CONSTRAINT fkas88a1k1s92d5qtu2mpufwc54 FOREIGN KEY (image_id) REFERENCES public.image(id);


--
-- TOC entry 3332 (class 2606 OID 16648)
-- Name: log fkf4audw8sj4btiebhq61hu6wda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT fkf4audw8sj4btiebhq61hu6wda FOREIGN KEY (activity_type_id) REFERENCES public.activity_type(id);


--
-- TOC entry 3333 (class 2606 OID 16663)
-- Name: price_period fkj88h8vg4v5rsva37g4px2997j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.price_period
    ADD CONSTRAINT fkj88h8vg4v5rsva37g4px2997j FOREIGN KEY (amount_id) REFERENCES public.amount(id);


--
-- TOC entry 3338 (class 2606 OID 16683)
-- Name: residence fkkxikg9ms9bba3opaolmnf1hu3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence
    ADD CONSTRAINT fkkxikg9ms9bba3opaolmnf1hu3 FOREIGN KEY (owner_id) REFERENCES public."user"(id);


--
-- TOC entry 3334 (class 2606 OID 16658)
-- Name: price_period fkly1ghoqslo8ewh6e1uqpkebcs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.price_period
    ADD CONSTRAINT fkly1ghoqslo8ewh6e1uqpkebcs FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);


--
-- TOC entry 3336 (class 2606 OID 16678)
-- Name: reservation fkm4oimk0l1757o9pwavorj6ljg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkm4oimk0l1757o9pwavorj6ljg FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 3326 (class 2606 OID 16618)
-- Name: accommodation_unit_image fkn2d5pbm8m1mit5ibspcjv9g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accommodation_unit_image
    ADD CONSTRAINT fkn2d5pbm8m1mit5ibspcjv9g FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);


--
-- TOC entry 3323 (class 2606 OID 16608)
-- Name: user fkn82ha3ccdebhokx3a8fgdqeyy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fkn82ha3ccdebhokx3a8fgdqeyy FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 3329 (class 2606 OID 16638)
-- Name: amenity fkovihcjeh1nmnckb3wihxq5566; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amenity
    ADD CONSTRAINT fkovihcjeh1nmnckb3wihxq5566 FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);


--
-- TOC entry 3327 (class 2606 OID 16628)
-- Name: address fkpo044ng5x4gynb291cv24vtea; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fkpo044ng5x4gynb291cv24vtea FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- TOC entry 3339 (class 2606 OID 16688)
-- Name: residence_attribute fksbjijn4dmkyqfhv7bpqf08t8r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.residence_attribute
    ADD CONSTRAINT fksbjijn4dmkyqfhv7bpqf08t8r FOREIGN KEY (residence_id) REFERENCES public.residence(id);


--
-- TOC entry 3330 (class 2606 OID 16643)
-- Name: city fksi0dkm9kk6dyuedmc0j18t770; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT fksi0dkm9kk6dyuedmc0j18t770 FOREIGN KEY (region_id) REFERENCES public.region(id);


--
-- TOC entry 3337 (class 2606 OID 16673)
-- Name: reservation fkt9oltduhthtnw1m3xhqq41mmt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkt9oltduhthtnw1m3xhqq41mmt FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);


--
-- TOC entry 3328 (class 2606 OID 16633)
-- Name: address fktgrvifpvh3nx9fnfq37x7idhh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fktgrvifpvh3nx9fnfq37x7idhh FOREIGN KEY (residence_id) REFERENCES public.residence(id);


--
-- TOC entry 3324 (class 2606 OID 16613)
-- Name: accommodation_unit fkv2v0uubyg5sb9wvhdiohqrrn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accommodation_unit
    ADD CONSTRAINT fkv2v0uubyg5sb9wvhdiohqrrn FOREIGN KEY (residence_id) REFERENCES public.residence(id);


-- Completed on 2023-08-12 15:21:25

--
-- PostgreSQL database dump complete
--
