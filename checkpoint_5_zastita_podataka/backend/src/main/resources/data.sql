insert into wxs.user values(1, 'admin1@gmail.com', 'admin1', 'admin1', 'admin1@Password', 0, 2, 'admin1');
insert into wxs.user values(2, 'user1@gmail.com', 'user1', 'user1', 'user1@Password', 0, 0, 'user1');
insert into wxs.user values(3, 'user2@gmail.com', 'petar', 'petrovic', 'user2@Password', 0, 0, 'user2');
insert into wxs.user values(4, 'user3@gmail.com', 'milica', 'milic', 'user3@Password', 1, 0, 'user3');

insert into wxs.agent values(1, 123456789, 'agent1', 'agent1', 'agent1@Password', 'agent1');
insert into wxs.agent values(2, 987654321, 'agent2', 'agent2', 'agent2@Password', 'agent2');

insert into wxs.accomodation_category values (5, 3);
insert into wxs.accomodation_category values (6, 4);
insert into wxs.accomodation_category values (7, 5);
insert into wxs.accomodation_category values (61, 2);
insert into wxs.accomodation_category values (62, 1);

insert into wxs.accomodation_type values (2, 'hotel');
insert into wxs.accomodation_type values (3, 'bed and breakfast');
insert into wxs.accomodation_type values (4, 'apartman');
insert into wxs.accomodation_type values (60, 'motel');

insert into wxs.adress values (9, 'Novi Sad', 'Srbija', 'Novosadskog Sajma', 35);
insert into wxs.adress values (14, 'Bec', 'Austrija', 'Ringstrasse', 47);
insert into wxs.adress values (19, 'Beograd', 'Srbija', 'Vitezova Karadjordjeve zvezde', 76);
insert into wxs.adress values (23, 'Pariz', 'Francuska', 'Boulevard de Grenelle', 132);
insert into wxs.adress values (25, 'Pariz', 'Francuska', 'Boulevard de Grenelle', 132);
insert into wxs.adress values (29, 'Rejkjavik', 'Island', 'Hringbraut', 92);
insert into wxs.adress values (34, 'Uzice', 'Srbija', 'Rujanska', 18);
insert into wxs.adress values (39, 'Budva', 'Crna Gora', 'Njegoseva', 34);
insert into wxs.adress values (63, 'grad', 'drzava', 'ulica', 1);

insert into wxs.price_plan values (10, 2020, 4500, 2017);
insert into wxs.price_plan values (15, 2019, 5500, 2018);
insert into wxs.price_plan values (20, 2021, 2300, 2015);
insert into wxs.price_plan values (24, 2019, 6500, 2018);
insert into wxs.price_plan values (26, 2019, 6900, 2018);
insert into wxs.price_plan values (30, 2023, 2700, 2013);
insert into wxs.price_plan values (35, 2020, 2350, 2017);
insert into wxs.price_plan values (40, 2019, 3980, 2017);
insert into wxs.price_plan values (64, 2019, 1234, 2018);

insert into wxs.images values (11, 'C:\\Users\\Bojana\\Desktop\\hotelPark.jpg');
insert into wxs.images values (16, 'C:\\Users\\Bojana\\Desktop\\hotelViennaPlaza.jpg');
insert into wxs.images values (21, 'C:\\Users\\Bojana\\Desktop\\apartmaniLaguna.jpg');
insert into wxs.images values (27, 'C:\\Users\\Bojana\\Desktop\\CitadinesTourEiffelParis.jpg');
insert into wxs.images values (31, 'C:\\Users\\Bojana\\Desktop\\BedandBreakfastKeflavikCentre.jpg');
insert into wxs.images values (36, 'C:\\Users\\Bojana\\Desktop\\apartmaniVilaMarina.jpg');
insert into wxs.images values (41, 'C:\\Users\\Bojana\\Desktop\\sunHostelBudva.jpg');
insert into wxs.images values (65, 'C:\\Users\\Bojana\\Desktop\\hotelPark.jpg');

insert into wxs.accomodation_images values (12, 11);
insert into wxs.accomodation_images values (17, 16);
insert into wxs.accomodation_images values (22, 21);
insert into wxs.accomodation_images values (28, 27);
insert into wxs.accomodation_images values (32, 31);
insert into wxs.accomodation_images values (37, 36);
insert into wxs.accomodation_images values (42, 41);
insert into wxs.accomodation_images values (66, 65);

insert into wxs.message values (44, 'O Hotelu Park smo culi sve najlepse - nadamo se da cete ispuniti nasa ocekivanja.', 1, 44, 2, 1);
insert into wxs.message values (49, 'Ovo je neka poruka :D', 1, 49, 47, 1);
insert into wxs.message values (55, 'Sun Hotel Budva se u prvih nekoliko dana koliko boravimo ovde pokazao kao odlican izbor. Usluga je fantasticna.', 1, 55, 47, 2);
insert into wxs.message values (69, 'porukicaaaaaaa', 1, 69, 2, 2);

insert into wxs.reply values (51, 49, 'Ovo je odgovor na neku porukuuu :D', 0, 1);
insert into wxs.reply values (71, 69, 'odg', 0, 2);

insert into wxs.reservation values (13, 0, '2018-06-30 00:00:00', 4, 18000, 0, '2018-06-27 00:00:00', 0, 12, 1);
insert into wxs.reservation values (18, 0, '2018-07-10 00:00:00', 3, 55000, 0, '2018-07-01 00:00:00', 0, 17, 1);
insert into wxs.reservation values (33, 0, '2018-06-30 00:00:00', 5, 16100, 0, '2018-06-24 00:00:00', 0, 22, 1);
insert into wxs.reservation values (38, 0, '2018-06-20 00:00:00', 4, 25850, 0, '2018-06-10 00:00:00', 0, 37, 2);
insert into wxs.reservation values (43, 0, '2018-07-20 02:00:00', 2, 27000, 0, '2018-07-15 02:00:00', 2, 12, 1);
insert into wxs.reservation values (45, 0, '2018-08-30 02:00:00', 3, 25850, 0, '2018-08-20 02:00:00', 2, 37, 2);
insert into wxs.reservation values (46, 0, '2018-09-24 02:00:00', 4, 34500, 0, '2018-09-10 02:00:00', 2, 22, 1);
insert into wxs.reservation values (48, 0, '2018-06-30 02:00:00', 2, 41400, 0, '2018-06-25 02:00:00', 47, 28, 1);
insert into wxs.reservation values (50, 0, '2018-06-09 02:00:00', 5, 16100, 0, '2018-06-03 02:00:00', 47, 22, 1);
insert into wxs.reservation values (52, 0, '2017-05-17 02:00:00', 3, 18800, 0, '2017-05-10 02:00:00', 47, 37, 2);
insert into wxs.reservation values (54, 0, '2018-06-30 02:00:00', 2, 43780, 0, '2018-06-20 02:00:00', 47, 42, 2);
insert into wxs.reservation values (58, 0, '2018-01-05 01:00:00', 2, 27500, 0, '2018-01-01 01:00:00', 2, 17, 1);
insert into wxs.reservation values (67, 0, '2018-12-31 00:00:00', 4, 38254, 0, '2018-12-01 00:00:00', 0, 66, 2);
insert into wxs.reservation values (68, 0, '2019-01-10 01:00:00', 3, 12340, 0, '2019-01-01 01:00:00', 2, 66, 2);

insert into wxs.review values (56, 1, 'Vila Marina su apartmani koji su ispunili skoro sva nasa ocekivanja. Poseticemo ih opet.', 4, 0, 47, 37);
insert into wxs.review values (57, 1, 'Apartmani Vila Marina spadaju u prosecan smestaj.', 3, 0, 47, 37);
insert into wxs.review values (59, 1, 'Usluge u hotela u Becu su na izuzetno visokom nivou!', 5, 0, 2, 17);
insert into wxs.review values (70, 1, 'jsdfgbnh', 3, 0, 2, 17);
insert into wxs.review values (72, 1, 'review123', 2, 0, 2, 17);

insert into wxs.accomodation values (12, 0, 'Hotel Park se nalazi u Novom Sadu, na ivici velikog parka u mirnom okruzenju i u neposrednoj blizini centra grada. Hotel je u potpunosti klimatizovan i nudi elegantno uredjen smestaj sa besplatnim internetom i kablovskom televizijom.', 1, 0, 0, 'Hotel Park', 4, 1, 0, 0, 1, 1, 6, 2, 9, 1, 10);
insert into wxs.accomodation values (17, 1, 'Hotel je smesten na prestiznoj lokaciji - u ulici Ringstrasse. Inspirisan je umetnoscu iz 1920-ih.', 0, 1, 0, 'Hilton Vienna Plaza', 3, 1, 1, 0, 1, 1, 7, 2, 14, 1, 15);
insert into wxs.accomodation values (22, 0, 'Apartmani Laguna su, po preporuci nasih dosadasnjih gostiju, najbolji izbor ukoliko kratkotrajno boravite u Beogradu.', 0, 0, 1, 'Apartmani Laguna', 5, 0, 0, 0, 1, 1, 5, 4, 19, 1, 20);
insert into wxs.accomodation values (28, 0, 'Prelep pogled, odlicna lokacija, povoljne cene, kvalitetna usluga i jos mnogo toga - posetite nas!', 1, 0, 0, 'Citadines Tour Eiffel Paris', 2, 0, 1, 0, 1, 1, 7, 2, 25, 1, 26);
insert into wxs.accomodation values (32, 0, 'Ovaj pansion sa uslugom doru?ka je u porodi?nom vlasništvu i nalazi se u centralnom Keblaviku (Keflavík), 3 km od Medjunarodnog aerodroma Keblavik. U ponudi ima uslugu organizovanog aerodromskog prevoza.', 0, 0, 1, 'Bed and Breakfast Keflavik Centre', 3, 1, 0, 0, 1, 1, 5, 3, 29, 1, 30);
insert into wxs.accomodation values (37, 0, 'Apartmani Vila Marina nalaze se nedaleko odZlatibora. Okolina je pogodna za razne aktivnosti, kao što su skijanje i jahanje. Gostima je na raspolaganju besplatan bezicni internet. Parking u okviru objekta moze besplatno da se koristi.', 0, 0, 1, 'Apartmani Vila Marina', 4, 1, 0, 0, 1, 1, 6, 4, 34, 2, 35);
insert into wxs.accomodation values (42, 0, 'Na svega 500 metara od budvanske plaze, ovaj hotel nudi klimatizovane sobe sa balkonom. Bezicni internet je besplatan u zajednickim prostorijama. Nalazi se na 15 minuta hoda od Starog grada.', 0, 1, 0, 'Sun Hotel Budva', 5, 1, 0, 0, 1, 1, 5, 2, 39, 2, 40);
insert into wxs.accomodation values (66, 0, 'opis', 0, 0, 0, 'smestaj1', 4, 1, 0, 0, 0, 1, 7, 60, 63, 2, 64);