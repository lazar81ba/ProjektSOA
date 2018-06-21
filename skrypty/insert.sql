INSERT into public.USERS values('555555', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT into public.USERS values('223322', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb');
INSERT into public.USERS values('223323', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb');
INSERT into public.USERS values('223324', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb');
INSERT into public.USER_ROLES values('555555', 'admin');
INSERT into public.USER_ROLES values('223322', 'user');

INSERT into public.Category(login_user,category_label,parameter_label,parameter_value)  values('223323','jaskinia','powierzchnia',100);
INSERT into public.Category(login_user,category_label,parameter_label,parameter_value) values('223322','wieża','wysokość',40);
INSERT into public.Category(login_user,category_label,parameter_label,parameter_value) values('223324','las','liczba drzew',1500);

INSERT into public.Element(id_category,element_label,parameter1_label,parameter1_value,parameter2_label,parameter2_value,parameter3_label,parameter3_value,parameter4_label,parameter4_value)  values(1,'smok','imię','Smaug','złoto',150,'kolor',2,'moc',300);
INSERT into public.Element(id_category,element_label,parameter1_label,parameter1_value,parameter2_label,parameter2_value,parameter3_label,parameter3_value,parameter4_label,parameter4_value)  values(2,'smok','imię','Bagienny smok','złoto',400,'kolor',2,'moc',300);
INSERT into public.Element(id_category,element_label,parameter1_label,parameter1_value,parameter2_label,parameter2_value,parameter3_label,parameter3_value,parameter4_label,parameter4_value)   values(2,'mag','imię','Gandalf','mana',100,'żywioł',1,'moc',150);
INSERT into public.Element(id_category,element_label,parameter1_label,parameter1_value,parameter2_label,parameter2_value,parameter3_label,parameter3_value,parameter4_label,parameter4_value)   values(3,'mag','imię','Saruman','mana',150,'żywioł',1,'moc',150);
INSERT into public.Element(id_category,element_label,parameter1_label,parameter1_value,parameter2_label,parameter2_value,parameter3_label,parameter3_value,parameter4_label,parameter4_value)   values(3,'elf','imię','Erlond','liczba strzał',50,'rodziaj łuku',5,'moc',50);
INSERT into public.Element(id_category,element_label,parameter1_label,parameter1_value,parameter2_label,parameter2_value,parameter3_label,parameter3_value,parameter4_label,parameter4_value)   values(1,'elf','imię','Legolas','liczba strzał',150,'rodziaj łuku',5,'moc',50);
