CREATE TABLE IF NOT EXISTS public.USERS(
login VARCHAR(64) PRIMARY KEY,
passwd VARCHAR(64) not null
);
CREATE TABLE IF NOT EXISTS public.USER_ROLES(
login VARCHAR(64) PRIMARY KEY, 
role VARCHAR(32)
);


CREATE TABLE IF NOT EXISTS public.Category(
id serial not null
    constraint category_pkey
    primary key,
login_user varchar(64) not null,
category_label varchar(64) unique not null,
parameter_label varchar(64) not null,
parameter_value integer not null
);

CREATE TABLE IF NOT EXISTS public.Element(
id serial not null
    constraint element_pkey
    primary key,
id_category integer not null,
element_label varchar(64) not null,
parameter1_label varchar(64) not null,
parameter1_value varchar(64) not null,
parameter2_label varchar(64) not null,
parameter2_value integer not null,
parameter3_label varchar(64) not null,
parameter3_value integer not null,
parameter4_label varchar(64) not null,
parameter4_value integer not null
);

ALTER TABLE public.Element ADD CONSTRAINT element_category_fkey FOREIGN KEY (id_category) REFERENCES public.Category(id) ;
ALTER TABLE public.Category ADD CONSTRAINT category_user_fkey FOREIGN KEY (login_user) REFERENCES public.USERS(login) ;
