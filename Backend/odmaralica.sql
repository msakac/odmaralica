PGDMP     6                    {        
   odmaralica    15.3    15.1 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16454 
   odmaralica    DATABASE     �   CREATE DATABASE odmaralica WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Croatian_Croatia.1250';
    DROP DATABASE odmaralica;
                postgres    false            �            1259    16487    accommodation_unit    TABLE     �   CREATE TABLE public.accommodation_unit (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    residence_id bigint NOT NULL
);
 &   DROP TABLE public.accommodation_unit;
       public         heap    postgres    false            �            1259    16486    accommodation_unit_id_seq    SEQUENCE     �   CREATE SEQUENCE public.accommodation_unit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.accommodation_unit_id_seq;
       public          postgres    false    221            �           0    0    accommodation_unit_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.accommodation_unit_id_seq OWNED BY public.accommodation_unit.id;
          public          postgres    false    220            �            1259    16496    accommodation_unit_image    TABLE     �   CREATE TABLE public.accommodation_unit_image (
    id bigint NOT NULL,
    accommodation_unit_id bigint NOT NULL,
    image_id bigint NOT NULL
);
 ,   DROP TABLE public.accommodation_unit_image;
       public         heap    postgres    false            �            1259    16495    accommodation_unit_image_id_seq    SEQUENCE     �   CREATE SEQUENCE public.accommodation_unit_image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.accommodation_unit_image_id_seq;
       public          postgres    false    223            �           0    0    accommodation_unit_image_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.accommodation_unit_image_id_seq OWNED BY public.accommodation_unit_image.id;
          public          postgres    false    222            �            1259    16503    activity_type    TABLE     h   CREATE TABLE public.activity_type (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);
 !   DROP TABLE public.activity_type;
       public         heap    postgres    false            �            1259    16502    activity_type_id_seq    SEQUENCE     }   CREATE SEQUENCE public.activity_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.activity_type_id_seq;
       public          postgres    false    225            �           0    0    activity_type_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.activity_type_id_seq OWNED BY public.activity_type.id;
          public          postgres    false    224            �            1259    16509    address    TABLE     �   CREATE TABLE public.address (
    id character varying(255) NOT NULL,
    additional character varying(255),
    street character varying(255),
    city_id bigint NOT NULL,
    residence_id bigint NOT NULL
);
    DROP TABLE public.address;
       public         heap    postgres    false            �            1259    16517    amenity    TABLE     �   CREATE TABLE public.amenity (
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
    DROP TABLE public.amenity;
       public         heap    postgres    false            �            1259    16516    amenity_id_seq    SEQUENCE     w   CREATE SEQUENCE public.amenity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.amenity_id_seq;
       public          postgres    false    228            �           0    0    amenity_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.amenity_id_seq OWNED BY public.amenity.id;
          public          postgres    false    227            �            1259    16524    amount    TABLE     �   CREATE TABLE public.amount (
    id bigint NOT NULL,
    amount numeric(19,2) NOT NULL,
    currency character varying(3) NOT NULL
);
    DROP TABLE public.amount;
       public         heap    postgres    false            �            1259    16523    amount_id_seq    SEQUENCE     v   CREATE SEQUENCE public.amount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.amount_id_seq;
       public          postgres    false    230            �           0    0    amount_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.amount_id_seq OWNED BY public.amount.id;
          public          postgres    false    229            �            1259    16531    city    TABLE     �   CREATE TABLE public.city (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    zip character varying(255) NOT NULL,
    region_id bigint NOT NULL
);
    DROP TABLE public.city;
       public         heap    postgres    false            �            1259    16530    city_id_seq    SEQUENCE     t   CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.city_id_seq;
       public          postgres    false    232            �           0    0    city_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;
          public          postgres    false    231            �            1259    16456    country    TABLE     �   CREATE TABLE public.country (
    id bigint NOT NULL,
    country_code character varying(2) NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.country;
       public         heap    postgres    false            �            1259    16455    country_id_seq    SEQUENCE     w   CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.country_id_seq;
       public          postgres    false    215            �           0    0    country_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;
          public          postgres    false    214            �            1259    16540    image    TABLE     N   CREATE TABLE public.image (
    id bigint NOT NULL,
    image oid NOT NULL
);
    DROP TABLE public.image;
       public         heap    postgres    false            �            1259    16539    image_id_seq    SEQUENCE     u   CREATE SEQUENCE public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.image_id_seq;
       public          postgres    false    234            �           0    0    image_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;
          public          postgres    false    233            �            1259    16547    log    TABLE     �   CREATE TABLE public.log (
    id bigint NOT NULL,
    log_message character varying(255) NOT NULL,
    activity_type_id bigint NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.log;
       public         heap    postgres    false            �            1259    16546 
   log_id_seq    SEQUENCE     s   CREATE SEQUENCE public.log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.log_id_seq;
       public          postgres    false    236            �           0    0 
   log_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.log_id_seq OWNED BY public.log.id;
          public          postgres    false    235            �            1259    16554    price_period    TABLE     �   CREATE TABLE public.price_period (
    id bigint NOT NULL,
    end_at date NOT NULL,
    start_at date NOT NULL,
    accommodation_unit_id bigint NOT NULL,
    amount_id bigint NOT NULL
);
     DROP TABLE public.price_period;
       public         heap    postgres    false            �            1259    16553    price_period_id_seq    SEQUENCE     |   CREATE SEQUENCE public.price_period_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.price_period_id_seq;
       public          postgres    false    238            �           0    0    price_period_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.price_period_id_seq OWNED BY public.price_period.id;
          public          postgres    false    237            �            1259    16561    region    TABLE     �   CREATE TABLE public.region (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    country_id bigint NOT NULL
);
    DROP TABLE public.region;
       public         heap    postgres    false            �            1259    16560    region_id_seq    SEQUENCE     v   CREATE SEQUENCE public.region_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.region_id_seq;
       public          postgres    false    240            �           0    0    region_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.region_id_seq OWNED BY public.region.id;
          public          postgres    false    239            �            1259    16568    reservation    TABLE     0  CREATE TABLE public.reservation (
    id bigint NOT NULL,
    cancelled boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    end_at date NOT NULL,
    note character varying(255),
    start_at date NOT NULL,
    accommodation_unit_id bigint NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16567    reservation_id_seq    SEQUENCE     {   CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.reservation_id_seq;
       public          postgres    false    242            �           0    0    reservation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;
          public          postgres    false    241            �            1259    16575 	   residence    TABLE     �   CREATE TABLE public.residence (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    owner_id bigint NOT NULL
);
    DROP TABLE public.residence;
       public         heap    postgres    false            �            1259    16584    residence_attribute    TABLE     x  CREATE TABLE public.residence_attribute (
    id bigint NOT NULL,
    distance_from_bank character varying(255),
    distance_from_beach character varying(255),
    distance_from_center character varying(255),
    distance_from_restoran character varying(255),
    distance_from_sea integer,
    distance_from_store character varying(255),
    residence_id bigint NOT NULL
);
 '   DROP TABLE public.residence_attribute;
       public         heap    postgres    false            �            1259    16583    residence_attribute_id_seq    SEQUENCE     �   CREATE SEQUENCE public.residence_attribute_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.residence_attribute_id_seq;
       public          postgres    false    246            �           0    0    residence_attribute_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.residence_attribute_id_seq OWNED BY public.residence_attribute.id;
          public          postgres    false    245            �            1259    16574    residence_id_seq    SEQUENCE     y   CREATE SEQUENCE public.residence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.residence_id_seq;
       public          postgres    false    244            �           0    0    residence_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.residence_id_seq OWNED BY public.residence.id;
          public          postgres    false    243            �            1259    16593    residence_image    TABLE     �   CREATE TABLE public.residence_image (
    id bigint NOT NULL,
    image_id bigint NOT NULL,
    residence_id bigint NOT NULL
);
 #   DROP TABLE public.residence_image;
       public         heap    postgres    false            �            1259    16592    residence_image_id_seq    SEQUENCE        CREATE SEQUENCE public.residence_image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.residence_image_id_seq;
       public          postgres    false    248            �           0    0    residence_image_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.residence_image_id_seq OWNED BY public.residence_image.id;
          public          postgres    false    247            �            1259    16463    role    TABLE     _   CREATE TABLE public.role (
    id bigint NOT NULL,
    role character varying(255) NOT NULL
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    16462    role_id_seq    SEQUENCE     t   CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public          postgres    false    217            �           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
          public          postgres    false    216            �            1259    16470    user    TABLE     �  CREATE TABLE public."user" (
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
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16469    user_id_seq    SEQUENCE     t   CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false    219            �           0    0    user_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;
          public          postgres    false    218            �           2604    16490    accommodation_unit id    DEFAULT     ~   ALTER TABLE ONLY public.accommodation_unit ALTER COLUMN id SET DEFAULT nextval('public.accommodation_unit_id_seq'::regclass);
 D   ALTER TABLE public.accommodation_unit ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    16499    accommodation_unit_image id    DEFAULT     �   ALTER TABLE ONLY public.accommodation_unit_image ALTER COLUMN id SET DEFAULT nextval('public.accommodation_unit_image_id_seq'::regclass);
 J   ALTER TABLE public.accommodation_unit_image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    223    223            �           2604    16506    activity_type id    DEFAULT     t   ALTER TABLE ONLY public.activity_type ALTER COLUMN id SET DEFAULT nextval('public.activity_type_id_seq'::regclass);
 ?   ALTER TABLE public.activity_type ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224    225            �           2604    16520 
   amenity id    DEFAULT     h   ALTER TABLE ONLY public.amenity ALTER COLUMN id SET DEFAULT nextval('public.amenity_id_seq'::regclass);
 9   ALTER TABLE public.amenity ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227    228            �           2604    16527 	   amount id    DEFAULT     f   ALTER TABLE ONLY public.amount ALTER COLUMN id SET DEFAULT nextval('public.amount_id_seq'::regclass);
 8   ALTER TABLE public.amount ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    230    230            �           2604    16534    city id    DEFAULT     b   ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);
 6   ALTER TABLE public.city ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    232    232            �           2604    16459 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            �           2604    16543    image id    DEFAULT     d   ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);
 7   ALTER TABLE public.image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    234    233    234            �           2604    16550    log id    DEFAULT     `   ALTER TABLE ONLY public.log ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);
 5   ALTER TABLE public.log ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    236    235    236            �           2604    16557    price_period id    DEFAULT     r   ALTER TABLE ONLY public.price_period ALTER COLUMN id SET DEFAULT nextval('public.price_period_id_seq'::regclass);
 >   ALTER TABLE public.price_period ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    238    237    238            �           2604    16564 	   region id    DEFAULT     f   ALTER TABLE ONLY public.region ALTER COLUMN id SET DEFAULT nextval('public.region_id_seq'::regclass);
 8   ALTER TABLE public.region ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    239    240    240            �           2604    16571    reservation id    DEFAULT     p   ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);
 =   ALTER TABLE public.reservation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    241    242    242            �           2604    16578    residence id    DEFAULT     l   ALTER TABLE ONLY public.residence ALTER COLUMN id SET DEFAULT nextval('public.residence_id_seq'::regclass);
 ;   ALTER TABLE public.residence ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    244    243    244            �           2604    16587    residence_attribute id    DEFAULT     �   ALTER TABLE ONLY public.residence_attribute ALTER COLUMN id SET DEFAULT nextval('public.residence_attribute_id_seq'::regclass);
 E   ALTER TABLE public.residence_attribute ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    245    246    246            �           2604    16596    residence_image id    DEFAULT     x   ALTER TABLE ONLY public.residence_image ALTER COLUMN id SET DEFAULT nextval('public.residence_image_id_seq'::regclass);
 A   ALTER TABLE public.residence_image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    248    247    248            �           2604    16466    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    16473    user id    DEFAULT     d   ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);
 8   ALTER TABLE public."user" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �          0    16487    accommodation_unit 
   TABLE DATA           Q   COPY public.accommodation_unit (id, description, name, residence_id) FROM stdin;
    public          postgres    false    221   �       �          0    16496    accommodation_unit_image 
   TABLE DATA           W   COPY public.accommodation_unit_image (id, accommodation_unit_id, image_id) FROM stdin;
    public          postgres    false    223   7�       �          0    16503    activity_type 
   TABLE DATA           1   COPY public.activity_type (id, name) FROM stdin;
    public          postgres    false    225   T�       �          0    16509    address 
   TABLE DATA           P   COPY public.address (id, additional, street, city_id, residence_id) FROM stdin;
    public          postgres    false    226   q�       �          0    16517    amenity 
   TABLE DATA           y   COPY public.amenity (id, bathroom, garage, kitchen, parking, sea_view, terrace, wifi, accommodation_unit_id) FROM stdin;
    public          postgres    false    228   ��       �          0    16524    amount 
   TABLE DATA           6   COPY public.amount (id, amount, currency) FROM stdin;
    public          postgres    false    230   ��       �          0    16531    city 
   TABLE DATA           8   COPY public.city (id, name, zip, region_id) FROM stdin;
    public          postgres    false    232   Ȳ       �          0    16456    country 
   TABLE DATA           9   COPY public.country (id, country_code, name) FROM stdin;
    public          postgres    false    215   �       �          0    16540    image 
   TABLE DATA           *   COPY public.image (id, image) FROM stdin;
    public          postgres    false    234   (�       �          0    16547    log 
   TABLE DATA           I   COPY public.log (id, log_message, activity_type_id, user_id) FROM stdin;
    public          postgres    false    236   E�       �          0    16554    price_period 
   TABLE DATA           ^   COPY public.price_period (id, end_at, start_at, accommodation_unit_id, amount_id) FROM stdin;
    public          postgres    false    238   b�       �          0    16561    region 
   TABLE DATA           6   COPY public.region (id, name, country_id) FROM stdin;
    public          postgres    false    240   �       �          0    16568    reservation 
   TABLE DATA           x   COPY public.reservation (id, cancelled, created_at, end_at, note, start_at, accommodation_unit_id, user_id) FROM stdin;
    public          postgres    false    242   ��       �          0    16575 	   residence 
   TABLE DATA           J   COPY public.residence (id, description, name, type, owner_id) FROM stdin;
    public          postgres    false    244   
�       �          0    16584    residence_attribute 
   TABLE DATA           �   COPY public.residence_attribute (id, distance_from_bank, distance_from_beach, distance_from_center, distance_from_restoran, distance_from_sea, distance_from_store, residence_id) FROM stdin;
    public          postgres    false    246   '�       �          0    16593    residence_image 
   TABLE DATA           E   COPY public.residence_image (id, image_id, residence_id) FROM stdin;
    public          postgres    false    248   D�       �          0    16463    role 
   TABLE DATA           (   COPY public.role (id, role) FROM stdin;
    public          postgres    false    217   a�       �          0    16470    user 
   TABLE DATA           }   COPY public."user" (id, activated, description, email, name, password, phone_number, surname, image_id, role_id) FROM stdin;
    public          postgres    false    219   ~�       �           0    0    accommodation_unit_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.accommodation_unit_id_seq', 1, false);
          public          postgres    false    220            �           0    0    accommodation_unit_image_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.accommodation_unit_image_id_seq', 1, false);
          public          postgres    false    222            �           0    0    activity_type_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.activity_type_id_seq', 1, false);
          public          postgres    false    224            �           0    0    amenity_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.amenity_id_seq', 1, false);
          public          postgres    false    227            �           0    0    amount_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.amount_id_seq', 1, false);
          public          postgres    false    229            �           0    0    city_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.city_id_seq', 1, false);
          public          postgres    false    231            �           0    0    country_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.country_id_seq', 5, true);
          public          postgres    false    214            �           0    0    image_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.image_id_seq', 1, false);
          public          postgres    false    233            �           0    0 
   log_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.log_id_seq', 1, false);
          public          postgres    false    235            �           0    0    price_period_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.price_period_id_seq', 1, false);
          public          postgres    false    237            �           0    0    region_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.region_id_seq', 14, true);
          public          postgres    false    239            �           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 1, false);
          public          postgres    false    241            �           0    0    residence_attribute_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.residence_attribute_id_seq', 1, false);
          public          postgres    false    245            �           0    0    residence_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.residence_id_seq', 1, false);
          public          postgres    false    243            �           0    0    residence_image_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.residence_image_id_seq', 1, false);
          public          postgres    false    247            �           0    0    role_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.role_id_seq', 1, false);
          public          postgres    false    216            �           0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 1, false);
          public          postgres    false    218            �           2606    16501 6   accommodation_unit_image accommodation_unit_image_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.accommodation_unit_image
    ADD CONSTRAINT accommodation_unit_image_pkey PRIMARY KEY (id);
 `   ALTER TABLE ONLY public.accommodation_unit_image DROP CONSTRAINT accommodation_unit_image_pkey;
       public            postgres    false    223            �           2606    16494 *   accommodation_unit accommodation_unit_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.accommodation_unit
    ADD CONSTRAINT accommodation_unit_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.accommodation_unit DROP CONSTRAINT accommodation_unit_pkey;
       public            postgres    false    221            �           2606    16508     activity_type activity_type_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.activity_type
    ADD CONSTRAINT activity_type_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.activity_type DROP CONSTRAINT activity_type_pkey;
       public            postgres    false    225            �           2606    16515    address address_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.address DROP CONSTRAINT address_pkey;
       public            postgres    false    226            �           2606    16522    amenity amenity_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.amenity
    ADD CONSTRAINT amenity_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.amenity DROP CONSTRAINT amenity_pkey;
       public            postgres    false    228            �           2606    16529    amount amount_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.amount
    ADD CONSTRAINT amount_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.amount DROP CONSTRAINT amount_pkey;
       public            postgres    false    230            �           2606    16538    city city_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.city DROP CONSTRAINT city_pkey;
       public            postgres    false    232            �           2606    16461    country country_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.country DROP CONSTRAINT country_pkey;
       public            postgres    false    215            �           2606    16545    image image_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.image DROP CONSTRAINT image_pkey;
       public            postgres    false    234            �           2606    16552    log log_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.log DROP CONSTRAINT log_pkey;
       public            postgres    false    236            �           2606    16559    price_period price_period_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.price_period
    ADD CONSTRAINT price_period_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.price_period DROP CONSTRAINT price_period_pkey;
       public            postgres    false    238            �           2606    16566    region region_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.region
    ADD CONSTRAINT region_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.region DROP CONSTRAINT region_pkey;
       public            postgres    false    240            �           2606    16573    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    242            �           2606    16591 ,   residence_attribute residence_attribute_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.residence_attribute
    ADD CONSTRAINT residence_attribute_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.residence_attribute DROP CONSTRAINT residence_attribute_pkey;
       public            postgres    false    246            �           2606    16598 $   residence_image residence_image_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.residence_image
    ADD CONSTRAINT residence_image_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.residence_image DROP CONSTRAINT residence_image_pkey;
       public            postgres    false    248            �           2606    16582    residence residence_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.residence
    ADD CONSTRAINT residence_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.residence DROP CONSTRAINT residence_pkey;
       public            postgres    false    244            �           2606    16468    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    217            �           2606    16483 !   role uk_bjxn5ii7v7ygwx39et0wawu0q 
   CONSTRAINT     \   ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_bjxn5ii7v7ygwx39et0wawu0q UNIQUE (role);
 K   ALTER TABLE ONLY public.role DROP CONSTRAINT uk_bjxn5ii7v7ygwx39et0wawu0q;
       public            postgres    false    217            �           2606    16600 !   city uk_hry8a4gw6jil5eu84p8munrjq 
   CONSTRAINT     [   ALTER TABLE ONLY public.city
    ADD CONSTRAINT uk_hry8a4gw6jil5eu84p8munrjq UNIQUE (zip);
 K   ALTER TABLE ONLY public.city DROP CONSTRAINT uk_hry8a4gw6jil5eu84p8munrjq;
       public            postgres    false    232            �           2606    16602 #   region uk_ixr2itih2n9q41fv3qx6mbkrp 
   CONSTRAINT     ^   ALTER TABLE ONLY public.region
    ADD CONSTRAINT uk_ixr2itih2n9q41fv3qx6mbkrp UNIQUE (name);
 M   ALTER TABLE ONLY public.region DROP CONSTRAINT uk_ixr2itih2n9q41fv3qx6mbkrp;
       public            postgres    false    240            �           2606    16481 $   country uk_llidyp77h6xkeokpbmoy710d4 
   CONSTRAINT     _   ALTER TABLE ONLY public.country
    ADD CONSTRAINT uk_llidyp77h6xkeokpbmoy710d4 UNIQUE (name);
 N   ALTER TABLE ONLY public.country DROP CONSTRAINT uk_llidyp77h6xkeokpbmoy710d4;
       public            postgres    false    215            �           2606    16485 !   user uk_ob8kqyqqgmefl0aco34akdtpe 
   CONSTRAINT     _   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email);
 M   ALTER TABLE ONLY public."user" DROP CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe;
       public            postgres    false    219            �           2606    16479 $   country uk_oqixmig4k8qxc8oba3fl4gqkr 
   CONSTRAINT     g   ALTER TABLE ONLY public.country
    ADD CONSTRAINT uk_oqixmig4k8qxc8oba3fl4gqkr UNIQUE (country_code);
 N   ALTER TABLE ONLY public.country DROP CONSTRAINT uk_oqixmig4k8qxc8oba3fl4gqkr;
       public            postgres    false    215            �           2606    16477    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    219                       2606    16653    log fk3wxdofviqe2smmvh1w1yf98o1    FK CONSTRAINT        ALTER TABLE ONLY public.log
    ADD CONSTRAINT fk3wxdofviqe2smmvh1w1yf98o1 FOREIGN KEY (user_id) REFERENCES public."user"(id);
 I   ALTER TABLE ONLY public.log DROP CONSTRAINT fk3wxdofviqe2smmvh1w1yf98o1;
       public          postgres    false    3287    219    236                       2606    16698 +   residence_image fk5xbkwdnvayaobash8oym0ya3r    FK CONSTRAINT     �   ALTER TABLE ONLY public.residence_image
    ADD CONSTRAINT fk5xbkwdnvayaobash8oym0ya3r FOREIGN KEY (residence_id) REFERENCES public.residence(id);
 U   ALTER TABLE ONLY public.residence_image DROP CONSTRAINT fk5xbkwdnvayaobash8oym0ya3r;
       public          postgres    false    248    3317    244            �           2606    16623 4   accommodation_unit_image fk7rj72y8hqa29sjllesku09u87    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_unit_image
    ADD CONSTRAINT fk7rj72y8hqa29sjllesku09u87 FOREIGN KEY (image_id) REFERENCES public.image(id);
 ^   ALTER TABLE ONLY public.accommodation_unit_image DROP CONSTRAINT fk7rj72y8hqa29sjllesku09u87;
       public          postgres    false    3305    234    223                       2606    16668 "   region fk7vb2cqcnkr9391hfn72louxkq    FK CONSTRAINT     �   ALTER TABLE ONLY public.region
    ADD CONSTRAINT fk7vb2cqcnkr9391hfn72louxkq FOREIGN KEY (country_id) REFERENCES public.country(id);
 L   ALTER TABLE ONLY public.region DROP CONSTRAINT fk7vb2cqcnkr9391hfn72louxkq;
       public          postgres    false    215    3275    240            �           2606    16603    user fk9hpx11qlu8cqhrkjn0yor93h    FK CONSTRAINT     �   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk9hpx11qlu8cqhrkjn0yor93h FOREIGN KEY (image_id) REFERENCES public.image(id);
 K   ALTER TABLE ONLY public."user" DROP CONSTRAINT fk9hpx11qlu8cqhrkjn0yor93h;
       public          postgres    false    3305    219    234                       2606    16693 +   residence_image fkas88a1k1s92d5qtu2mpufwc54    FK CONSTRAINT     �   ALTER TABLE ONLY public.residence_image
    ADD CONSTRAINT fkas88a1k1s92d5qtu2mpufwc54 FOREIGN KEY (image_id) REFERENCES public.image(id);
 U   ALTER TABLE ONLY public.residence_image DROP CONSTRAINT fkas88a1k1s92d5qtu2mpufwc54;
       public          postgres    false    3305    234    248                       2606    16648    log fkf4audw8sj4btiebhq61hu6wda    FK CONSTRAINT     �   ALTER TABLE ONLY public.log
    ADD CONSTRAINT fkf4audw8sj4btiebhq61hu6wda FOREIGN KEY (activity_type_id) REFERENCES public.activity_type(id);
 I   ALTER TABLE ONLY public.log DROP CONSTRAINT fkf4audw8sj4btiebhq61hu6wda;
       public          postgres    false    3293    225    236                       2606    16663 (   price_period fkj88h8vg4v5rsva37g4px2997j    FK CONSTRAINT     �   ALTER TABLE ONLY public.price_period
    ADD CONSTRAINT fkj88h8vg4v5rsva37g4px2997j FOREIGN KEY (amount_id) REFERENCES public.amount(id);
 R   ALTER TABLE ONLY public.price_period DROP CONSTRAINT fkj88h8vg4v5rsva37g4px2997j;
       public          postgres    false    3299    230    238            
           2606    16683 %   residence fkkxikg9ms9bba3opaolmnf1hu3    FK CONSTRAINT     �   ALTER TABLE ONLY public.residence
    ADD CONSTRAINT fkkxikg9ms9bba3opaolmnf1hu3 FOREIGN KEY (owner_id) REFERENCES public."user"(id);
 O   ALTER TABLE ONLY public.residence DROP CONSTRAINT fkkxikg9ms9bba3opaolmnf1hu3;
       public          postgres    false    244    3287    219                       2606    16658 (   price_period fkly1ghoqslo8ewh6e1uqpkebcs    FK CONSTRAINT     �   ALTER TABLE ONLY public.price_period
    ADD CONSTRAINT fkly1ghoqslo8ewh6e1uqpkebcs FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);
 R   ALTER TABLE ONLY public.price_period DROP CONSTRAINT fkly1ghoqslo8ewh6e1uqpkebcs;
       public          postgres    false    3289    221    238                       2606    16678 '   reservation fkm4oimk0l1757o9pwavorj6ljg    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkm4oimk0l1757o9pwavorj6ljg FOREIGN KEY (user_id) REFERENCES public."user"(id);
 Q   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fkm4oimk0l1757o9pwavorj6ljg;
       public          postgres    false    219    242    3287            �           2606    16618 2   accommodation_unit_image fkn2d5pbm8m1mit5ibspcjv9g    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_unit_image
    ADD CONSTRAINT fkn2d5pbm8m1mit5ibspcjv9g FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);
 \   ALTER TABLE ONLY public.accommodation_unit_image DROP CONSTRAINT fkn2d5pbm8m1mit5ibspcjv9g;
       public          postgres    false    221    223    3289            �           2606    16608     user fkn82ha3ccdebhokx3a8fgdqeyy    FK CONSTRAINT     �   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fkn82ha3ccdebhokx3a8fgdqeyy FOREIGN KEY (role_id) REFERENCES public.role(id);
 L   ALTER TABLE ONLY public."user" DROP CONSTRAINT fkn82ha3ccdebhokx3a8fgdqeyy;
       public          postgres    false    217    3281    219                       2606    16638 #   amenity fkovihcjeh1nmnckb3wihxq5566    FK CONSTRAINT     �   ALTER TABLE ONLY public.amenity
    ADD CONSTRAINT fkovihcjeh1nmnckb3wihxq5566 FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);
 M   ALTER TABLE ONLY public.amenity DROP CONSTRAINT fkovihcjeh1nmnckb3wihxq5566;
       public          postgres    false    3289    221    228            �           2606    16628 #   address fkpo044ng5x4gynb291cv24vtea    FK CONSTRAINT     �   ALTER TABLE ONLY public.address
    ADD CONSTRAINT fkpo044ng5x4gynb291cv24vtea FOREIGN KEY (city_id) REFERENCES public.city(id);
 M   ALTER TABLE ONLY public.address DROP CONSTRAINT fkpo044ng5x4gynb291cv24vtea;
       public          postgres    false    226    3301    232                       2606    16688 /   residence_attribute fksbjijn4dmkyqfhv7bpqf08t8r    FK CONSTRAINT     �   ALTER TABLE ONLY public.residence_attribute
    ADD CONSTRAINT fksbjijn4dmkyqfhv7bpqf08t8r FOREIGN KEY (residence_id) REFERENCES public.residence(id);
 Y   ALTER TABLE ONLY public.residence_attribute DROP CONSTRAINT fksbjijn4dmkyqfhv7bpqf08t8r;
       public          postgres    false    3317    244    246                       2606    16643     city fksi0dkm9kk6dyuedmc0j18t770    FK CONSTRAINT     �   ALTER TABLE ONLY public.city
    ADD CONSTRAINT fksi0dkm9kk6dyuedmc0j18t770 FOREIGN KEY (region_id) REFERENCES public.region(id);
 J   ALTER TABLE ONLY public.city DROP CONSTRAINT fksi0dkm9kk6dyuedmc0j18t770;
       public          postgres    false    240    232    3311            	           2606    16673 '   reservation fkt9oltduhthtnw1m3xhqq41mmt    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkt9oltduhthtnw1m3xhqq41mmt FOREIGN KEY (accommodation_unit_id) REFERENCES public.accommodation_unit(id);
 Q   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fkt9oltduhthtnw1m3xhqq41mmt;
       public          postgres    false    242    3289    221                        2606    16633 #   address fktgrvifpvh3nx9fnfq37x7idhh    FK CONSTRAINT     �   ALTER TABLE ONLY public.address
    ADD CONSTRAINT fktgrvifpvh3nx9fnfq37x7idhh FOREIGN KEY (residence_id) REFERENCES public.residence(id);
 M   ALTER TABLE ONLY public.address DROP CONSTRAINT fktgrvifpvh3nx9fnfq37x7idhh;
       public          postgres    false    226    244    3317            �           2606    16613 -   accommodation_unit fkv2v0uubyg5sb9wvhdiohqrrn    FK CONSTRAINT     �   ALTER TABLE ONLY public.accommodation_unit
    ADD CONSTRAINT fkv2v0uubyg5sb9wvhdiohqrrn FOREIGN KEY (residence_id) REFERENCES public.residence(id);
 W   ALTER TABLE ONLY public.accommodation_unit DROP CONSTRAINT fkv2v0uubyg5sb9wvhdiohqrrn;
       public          postgres    false    3317    244    221            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   3   x�3�tq�tO-�M̫�2�t�t+J�KN�2���t.�O,�L����� ��5      �      x������ � �      �      x������ � �      �      x������ � �      �   ^   x�3�K,J�J��+�NL�4�2GH�4�@���H�T �� Y ,d�\��YR��������X6$c��R��dsq:��@�=... �*      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     