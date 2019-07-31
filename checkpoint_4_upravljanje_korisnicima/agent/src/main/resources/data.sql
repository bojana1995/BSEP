insert into agent.agent values (1, '123456789', 'agent1', 'agent1', 'agent1@Password', 'agent1');

insert into agent.accomodation_category values (4, 3);
insert into agent.accomodation_category values (5, 4);
insert into agent.accomodation_category values (6, 5);
insert into agent.accomodation_category values (83, 2);
insert into agent.accomodation_category values (84, 1);

insert into agent.accomodation_type values (1, 'hotel');
insert into agent.accomodation_type values (37, 'hotel');
insert into agent.accomodation_type values (3, 'bed and breakfast');
insert into agent.accomodation_type values (25, 'apartman');
insert into agent.accomodation_type values (38, 'apartman');
insert into agent.accomodation_type values (49, 'hotel');
insert into agent.accomodation_type values (50, 'apartman');
insert into agent.accomodation_type values (58, 'hotel');
insert into agent.accomodation_type values (59, 'apartman');
insert into agent.accomodation_type values (69, 'hotel');
insert into agent.accomodation_type values (70, 'apartman');
insert into agent.accomodation_type values (80, 'hotel');
insert into agent.accomodation_type values (81, 'apartman');
insert into agent.accomodation_type values (82, 'motel');
insert into agent.accomodation_type values (96, 'hotel');
insert into agent.accomodation_type values (97, 'apartman');
insert into agent.accomodation_type values (98, 'motel');

insert into agent.adress values (7, 'Novi Sad', 'Srbija', 'Novosadskog Sajma', 35);
insert into agent.adress values (12, 'Bec', 'Austrija', 'Ringstrasse', 47);
insert into agent.adress values (17, 'Beograd', 'Srbija', 'Vitezova Karadjordjeve zvezde', 76);
insert into agent.adress values (28, 'Pariz', 'Francuska', 'Boulevard de Grenelle', 132);
insert into agent.adress values (32, 'Rejkjavik', 'Island', 'Hringbraut', 92);

insert into agent.price_plan values (8, 2020, 4500, 2017);
insert into agent.price_plan values (13, 2019, 5500, 2018);
insert into agent.price_plan values (18, 2021, 2300, 2015);
insert into agent.price_plan values (29, 2019, 6900, 2018);
insert into agent.price_plan values (33, 2023, 2700, 2013);

insert into agent.images values (10, 'C:\\Users\\Bojana\\Desktop\\hotelPark.jpg');
insert into agent.images values (15, 'C:\\Users\\Bojana\\Desktop\\hotelViennaPlaza.jpg');
insert into agent.images values (20, 'C:\\Users\\Bojana\\Desktop\\apartmaniLaguna.jpg');
insert into agent.images values (24, 'C:\\Users\\Bojana\\Desktop\\CitadinesTourEiffelParis.jpg');
insert into agent.images values (31, 'C:\\Users\\Bojana\\Desktop\\CitadinesTourEiffelParis.jpg');
insert into agent.images values (35, 'C:\\Users\\Bojana\\Desktop\\BedandBreakfastKeflavikCentre.jpg');

insert into agent.accomodation_images values (9, 10);
insert into agent.accomodation_images values (14, 15);
insert into agent.accomodation_images values (19, 20);
insert into agent.accomodation_images values (23, 24);
insert into agent.accomodation_images values (30, 31);
insert into agent.accomodation_images values (34, 35);

insert into agent.message values (39, 'O Hotelu Park smo culi sve najlepse - nadamo se da cete ispuniti nasa ocekivanja.', 0, 44, 2, 1);
insert into agent.message values (40, 'Ovo je neka poruka :D', 1, 49, 47, 1);

insert into agent.reply values (48, 49, 'Ovo je odgovor na neku porukuuu :D', 0, 1);

insert into agent.reservation values (106, 0, '2018-01-05 00:00:00', 2, 27500, 58, '2018-01-01 00:00:00', 2, 14, 1);
insert into agent.reservation values (105, 0, '2018-06-09 00:00:00', 5, 16100, 50, '2018-06-03 00:00:00', 47, 19, 1);
insert into agent.reservation values (104, 0, '2018-06-30 00:00:00', 2, 41400, 48, '2018-06-25 00:00:00', 47, 30, 1);
insert into agent.reservation values (103, 0, '2018-09-24 00:00:00', 4, 34500, 46, '2018-09-10 00:00:00', 2, 19, 1);
insert into agent.reservation values (102, 0, '2018-07-20 00:00:00', 2, 27000, 43, '2018-07-15 00:00:00', 2, 9, 1);
insert into agent.reservation values (101, 0, '2018-06-30 00:00:00', 5, 16100, 33, '2018-06-24 00:00:00', 0, 19, 1);
insert into agent.reservation values (100, 0, '2018-07-10 00:00:00', 3, 55000, 18, '2018-07-01 00:00:00', 0, 14, 1);
insert into agent.reservation values (99, 0, '2018-06-30 00:00:00', 4, 18000, 13, '2018-06-27 00:00:00', 0, 9, 1);

insert into agent.review values (108, 1, 'jsdfgbnh', 3, 70, 2, 14);
insert into agent.review values (107, 1, 'Usluge u hotela u Becu su na izuzetno visokom nivou!', 5, 59, 2, 14);
insert into agent.review values (109, 1, 'review123', 2, 72, 2, 14);

insert into agent.accomodation values (9, 0, 'Hotel Park se nalazi u Novom Sadu, na ivici velikog parka u mirnom okruzenju i u neposrednoj blizini centra grada. Hotel je u potpunosti klimatizovan i nudi elegantno uredjen smestaj sa besplatnim internetom i kablovskom televizijom.', 1, 0, 0, 'Hotel Park', 4, 1, 0, 0, 1, 1, 5, 1, 7, 1, 8);
insert into agent.accomodation values (14, 1, 'Hotel je smesten na prestiznoj lokaciji - u ulici Ringstrasse. Inspirisan je umetnoscu iz 1920-ih.', 0, 1, 0, 'Hotel Vienna Plaza', 3, 1, 1, 0, 1, 1, 6, 1, 12, 1, 13);
insert into agent.accomodation values (19, 0, 'Apartmani Laguna su, po preporuci nasih dosadasnjih gostiju, najbolji izbor ukoliko kratkotrajno boravite u Beogradu.', 0, 0, 1, 'Apartmani Laguna', 5, 0, 0, 0, 1, 1, 4, 3, 17, 1, 18);
insert into agent.accomodation values (30, 0, 'Prelep pogled, odlicna lokacija, povoljne cene, kvalitetna usluga i jos mnogo toga - posetite nas!', 1, 0, 0, 'Citadines Tour Eiffel Paris', 2, 0, 1, 0, 1, 1, 6, 1, 28, 1, 29);
insert into agent.accomodation values (34, 0, 'Ovaj pansion sa uslugom doru?ka je u porodi?nom vlasništvu i nalazi se u centralnom Keblaviku (Keflavík), 3 km od Medjunarodnog aerodroma Keblavik. U ponudi ima uslugu organizovanog aerodromskog prevoza.', 0, 0, 1, 'Bed and Breakfast Keflavik Centre', 3, 1, 0, 0, 1, 1, 4, 3, 32, 1, 33);





-- agent2
insert into agent2.agent values (1, 987654321, 'agent2', 'agent2', 'agent2@Password', 'agent2');

insert into agent2.accomodation_category values (4, 3);
insert into agent2.accomodation_category values (5, 4);
insert into agent2.accomodation_category values (6, 5);
insert into agent2.accomodation_category values (42, 2);
insert into agent2.accomodation_category values (43, 1);

insert into agent2.accomodation_type values (1, 'hotel');
insert into agent2.accomodation_type values (2, 'hotel');
insert into agent2.accomodation_type values (3, 'bed and breakfast');
insert into agent2.accomodation_type values (16, 'apartman');
insert into agent2.accomodation_type values (19, 'apartman');
insert into agent2.accomodation_type values (23, 'apartman');
insert into agent2.accomodation_type values (27, 'apartman');
insert into agent2.accomodation_type values (33, 'apartman');
insert into agent2.accomodation_type values (40, 'apartman');
insert into agent2.accomodation_type values (41, 'motel');
insert into agent2.accomodation_type values (55, 'apartman');
insert into agent2.accomodation_type values (56, 'motel');

insert into agent2.adress values (7, 'Uzice', 'Srbija', 'Rujanska', 18);
insert into agent2.adress values (12, 'Budva', 'Crna Gora', 'Njegoseva', 34);
insert into agent2.adress values (50, 'grad', 'drzava', 'ulica', 1);

insert into agent2.price_plan values (8, 2020, 2350, 2017);
insert into agent2.price_plan values (13, 2019, 3980, 2017);
insert into agent2.price_plan values (51, 2019, 1234, 2018);

insert into agent2.images values (10, 'C:\\Users\\Bojana\\Desktop\\apartmaniVilaMarina.jpg');
insert into agent2.images values (15, 'C:\\Users\\Bojana\\Desktop\\sunHostelBudva.jpg');
insert into agent2.images values (53, 'C:\\Users\\Bojana\\Desktop\\hotelPark.jpg');

insert into agent2.accomodation_images values (9, 10);
insert into agent2.accomodation_images values (14, 15);
insert into agent2.accomodation_images values (52, 53);

insert into agent2.message values (28, 'Sun Hotel Budva se u prvih nekoliko dana koliko boravimo ovde pokazao kao odlican izbor. Usluga je fantasticna.', 0, 55, 47, 1);
insert into agent2.message values (57, 'porukicaaaaaaa', 1, 69, 2, 1);

insert into agent2.reply values (66, 69, 'odg', 0, 1);

insert into agent2.reservation values (62, 0, '2018-12-31 00:00:00', 4, 38254, 67, '2018-12-01 00:00:00', 0, 52, 1);
insert into agent2.reservation values (61, 0, '2018-06-30 00:00:00', 2, 43780, 54, '2018-06-20 00:00:00', 47, 14, 1);
insert into agent2.reservation values (60, 0, '2017-05-17 00:00:00', 3, 18800, 52, '2017-05-10 00:00:00', 47, 9, 1);
insert into agent2.reservation values (59, 0, '2018-08-30 00:00:00', 3, 25850, 45, '2018-08-20 00:00:00', 2, 9, 1);
insert into agent2.reservation values (58, 0, '2018-06-20 00:00:00', 4, 25850, 38, '2018-06-10 00:00:00', 0, 9, 1);
insert into agent2.reservation values (63, 0, '2019-01-10 00:00:00', 3, 12340, 68, '2019-01-01 00:00:00', 2, 52, 1);

insert into agent2.review values (64, 0, 'Vila Marina su apartmani koji su ispunili skoro sva nasa ocekivanja. Poseticemo ih opet.', 4, 56, 47, 9);
insert into agent2.review values (65, 1, 'Apartmani Vila Marina spadaju u prosecan smestaj.', 3, 57, 47, 9);

insert into agent2.accomodation values (9, 0, 'Apartmani Vila Marina nalaze se nedaleko odZlatibora. Okolina je pogodna za razne aktivnosti, kao što su skijanje i jahanje. Gostima je na raspolaganju besplatan bezicni internet. Parking u okviru objekta moze besplatno da se koristi.', 0, 0, 1, 'Apartmani Vila Marina', 4, 1, 0, 0, 1, 1, 5, 3, 7, 1, 8);
insert into agent2.accomodation values (14, 0, 'Na svega 500 metara od budvanske plaze, ovaj hotel nudi klimatizovane sobe sa balkonom. Bezicni internet je besplatan u zajednickim prostorijama. Nalazi se na 15 minuta hoda od Starog grada.', 0, 1, 0, 'Sun Hotel Budva', 5, 1, 0, 0, 1, 1, 4, 1, 12, 1, 13);
insert into agent2.accomodation values (52, 0, 'opis', 0, 0, 0, 'smestaj1', 4, 1, 0, 0, 0, 1, 6, 41, 50, 1, 51);