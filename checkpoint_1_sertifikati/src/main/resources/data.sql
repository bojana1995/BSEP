insert into bsep.korisnik values(1, 'Kralja Aleksandra 89', 1, '585696', 'admin1@gmail.com', 'Jovan', 'admin1@Password', 'Milica', 'Jovanovic', 0, 'admin');
insert into bsep.korisnik values(2, 'Bulevar Evrope 34', 1, '325905', 'agent1@gmail.com', 'Nikola', 'agent1@Password', 'Popovic', 'Nikolic', 0, 'agent');
insert into bsep.korisnik values(3, 'Masarikova 5', 1, '123456', 'user1@gmail.com', 'Petar', 'user1@Password', 'Zoki', 'Petrovic', 0, 'obican');

insert into bsep.key_store values (1, 'keystore1', 1);
insert into bsep.key_store values (2, 'keystore2', 2);

--keystore1
insert into bsep.certificate_model values (1, 'SHA256WITHRSA', 'alias 1', '2019-06-27 11:21:25', 1, 'CN=samopotpisani1,O=ime org 1,OU=org jed 1,C=drzava 1,SERIALNUMBER=1,E=samopotpisani1@gmail.com,Pseudonym=alias 1', 'keystore1', 1, 0, 'CN=samopotpisani1,O=ime org 1,OU=org jed 1,C=drzava 1,SERIALNUMBER=1,E=samopotpisani1@gmail.com,Pseudonym=alias 1', 'X.509', 3);
insert into bsep.certificate_model values (2, 'SHA256WITHRSA', 'alias2', '2019-06-28 11:40:26', 1, 'CN=sertifikat2,O=ime org2,OU=org jed2,C=drzava2,SERIALNUMBER=2,E=sertifikat2@gmail.com,Pseudonym=alias2', 'keystore1', 2, 0, 'CN=sertifikat2,O=ime org2,OU=org jed2,C=drzava2,SERIALNUMBER=2,E=sertifikat2@gmail.com,Pseudonym=alias2', 'X.509', 3);
--keystore2
insert into bsep.certificate_model values (3, 'SHA256WITHRSA', 'alias3', '2019-06-28 11:46:07', 1, 'CN=sertifikat3,O=ime org3,OU=org jed3,C=drzava3,SERIALNUMBER=3,E=sertifikat3@gmail.com,Pseudonym=alias3', 'keystore2', 3, 0, 'CN=sertifikat3,O=ime org3,OU=org jed3,C=drzava3,SERIALNUMBER=3,E=sertifikat3@gmail.com,Pseudonym=alias3', 'X.509', 3);