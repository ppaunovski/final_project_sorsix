insert into user_account (u_id, u_first_name, u_last_name, u_email, u_password, u_joined_date, u_date_host_started)
values
    (1, 'Pavel', 'Paunovski', 'pavel@pavel.com', 'pp', '2024-01-16', '2024-01-16'),
    (2, 'Nikola', 'Nacevski', 'nikola@nikola.com', 'nn', '2024-01-16', '2024-01-16'),
    (3, 'Guest', 'Guestovski', 'guest@guest.com', 'gg', '2024-01-16', null);

insert into city (c_id, c_name) values
                                    (1, 'Skopje'),
                                    (2, 'Kumanovo'),
                                    (3, 'Ohrid'),
                                    (4, 'Bitola'),
                                    (5, 'Prilep'),
                                    (6, 'Strumica');

insert into property_type (pt_id, pt_type_name) VALUES
                                                    (1, 'Room'),
                                                    (2, 'Entire House'),
                                                    (3, 'Apartment'),
                                                    (4, 'Type 4');

insert into attribute (a_id, a_name, a_description) VALUES
                                                        (1, 'Wi-Fi', 'Connection to internet in the property.'),
                                                        (2, 'Parking', 'Connection to internet in the property.'),
                                                        (3, 'Private Bathroom', 'Connection to internet in the property.'),
                                                        (4, 'Kitchen', 'Connection to internet in the property.'),
                                                        (5, 'Pet Friendly', 'Connection to internet in the property.'),
                                                        (6, 'Breakfast', 'Connection to internet in the property.'),
                                                        (7, 'AC', 'Connection to internet in the property.'),
                                                        (8, 'Iron', 'Connection to internet in the property.'),
                                                        (9, 'Bay View', 'Connection to internet in the property.'),
                                                        (10, 'Balcony', 'Connection to internet in the property.'),
                                                        (11, 'Washer', 'Connection to internet in the property.'),
                                                        (12, 'Garden', 'Connection to internet in the property.'),
                                                        (13, 'Smoke alarm', 'Connection to internet in the property.'),
                                                        (14, 'TV', 'Connection to internet in the property.'),
                                                        (15, 'Heater', 'Connection to internet in the property.');

insert into review_component (rc_id, rc_component_name) VALUES
                                                            (1, 'Cleanliness'),
                                                            (2, 'Accuracy'),
                                                            (3, 'Check-in'),
                                                            (4, 'Communication'),
                                                            (5, 'Location'),
                                                            (6, 'Value');
insert into property (p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, u_id, c_id, pt_id)
VALUES
    (1, 3500.0, 'Biljana Luxury Apartment', 2, 1, 1, 1, true, 'Odlicen apartment za mladi lugje, se naogja vo stariot grad na Ohrid i ima pogled koj ezeroto i Samoilovata tvrdina. Na samo 5 minuti peshki od Sv. Kaneo i 10 minuti od Plaoshnik.', 'Samoilova 12', 40.0923, 42.1234, 1, 3, 3),
    (2, 1500.0, 'Klime Fisherman`s house', 5, 3, 2, 2, true, 'Odlicen apartment za avanturisti, se naogja vo na 25 minuti od centarot na gradot Ohrid i ima pogled kon ezeroto. Na samo 5 minuti peshki od plazhata i 10 minuti od prviot market.', 'Plazhovska 5', 40.1234, 42.2234, 2, 3, 2),
    (3, 2500.0, 'Alexander The Great Lux', 2, 1, 1, 1, false, 'Apartman vo centarot na gradot Skopje. Na samo 5 minuti peshki od ploshtadot na gradot, 15 minuti od gradskiot park. Apartmanot raspolaga so zatvorena terasa na koja moze da se pushat cigari i ima  ogled kon Vodno.', 'Partizanska 32', 38.0923, 42.1234, 1, 1, 3),
    (4, 200.0, 'Helper Room', 1, 1, 1, 1, true, 'Soba vo mojot stan, koja ja izdavam na mlad student koj studira na UKIM. Lokacijata e odlicna i opshtestveniot kampus e na samo 10 minuti peshki odalechenost. Vo blizina e i univerzitetskata biblioteka i centarot na gradot. Vo stanot prestojuvam samo jas.', 'Goce Delchev 3', 40.0923, 42.1234, 1, 1, 1);

insert into property_attribute (pa_id, p_id, a_id) VALUES
                                                (1, 1, 1),
                                                (2, 1, 2),
                                                (3, 1, 3),
                                                (4, 1, 4),
                                                (5, 1, 5),
                                                (6, 1, 6),
                                                (7, 1, 7),
                                                (8, 2, 1),
                                                (9, 2, 2),
                                                (10, 2, 3),
                                                (11, 2, 4),
                                                (12, 2, 5),
                                                (13, 2, 6),
                                                (14, 2, 7),
                                                (15, 3, 1),
                                                (16, 3, 3),
                                                (17, 3, 6),
                                                (18, 3, 2),
                                                (19, 3, 13),
                                                (20, 3, 11),
                                                (21, 3, 10),
                                                (22, 3, 8),
                                                (23, 4, 2),
                                                (24, 4, 4),
                                                (25, 4, 5),
                                                (26, 4, 6),
                                                (27, 4, 7),
                                                (28, 4, 8),
                                                (29, 4, 9),
                                                (30, 4, 10),
                                                (31, 4, 11),
                                                (32, 4, 12),
                                                (33, 4, 13),
                                                (34, 4, 14),
                                                (35, 4, 15);
insert into user_review (ur_id, ur_comment, ur_review_date, u_id, p_id)
values
    (1, 'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.', '2024-04-20', 3, 1),
    (2, 'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.', '2024-04-28', 3, 2),
    (3, 'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.', '2024-02-11', 3, 3),
    (4, 'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.', '2024-03-23', 3, 4);

insert into component_rating (cr_id, cr_rating, ur_id, rc_id) VALUES
                                                                  (1, 5, 1, 1),
                                                                  (2, 5, 1, 2),
                                                                  (3, 5, 1, 3),
                                                                  (4, 5, 1, 4),
                                                                  (5, 5, 1, 5),
                                                                  (6, 5, 1, 6),

                                                                  (7, 5, 2, 1),
                                                                  (8, 4, 2, 2),
                                                                  (9, 3, 2, 3),
                                                                  (10, 4, 2, 4),
                                                                  (11, 2, 2, 5),
                                                                  (12, 4, 2, 6),

                                                                  (13, 3, 3, 1),
                                                                  (14, 5, 3, 2),
                                                                  (15, 5, 3, 3),
                                                                  (16, 5, 3, 4),
                                                                  (17, 2, 3, 5),
                                                                  (18, 4, 3, 6),

                                                                  (19, 3, 4, 1),
                                                                  (20, 5, 4, 2),
                                                                  (21, 3, 4, 3),
                                                                  (22, 5, 4, 4),
                                                                  (23, 2, 4, 5),
                                                                  (24, 5, 4, 6);
insert into favorite (f_id, p_id, u_id) VALUES
                                            (1, 1, 3);
insert into booking_status (bs_id, bs_status_name) VALUES
                                                       (1, 'APPROVED'),
                                                       (2, 'CANCELLED'),
                                                       (3, 'WAITING_FOR_APPROVAL'),
                                                       (4, 'REJECTED');
insert into guest_type (gt_id, gt_type_name, gt_description) VALUES
                                                 (1, 'ADULT','Age 13+'),
                                                 (2, 'CHILD','Under 12'),
                                                 (3, 'PET','');
insert into booking (b_booking_date, b_checkin_date, b_checkout_date, b_nightly_price, b_service_fee, b_cleaning_fee, p_id, u_id, bs_id)
VALUES
    ('2024-05-02', '2024-05-10', '2024-05-15', 3500.0, 500.0, 500.0, 1, 3, 1);

insert into booking_guests (bg_id, bg_num_guests, b_id, gt_id) VALUES
                                                                   (1, 2, 1, 1);

insert into property_availabilities (pav_start_date, pav_end_date, p_id)
values
    ('2024-01-01', '2024-12-31', 1),
    ('2024-01-01', '2024-12-31', 2),
    ('2024-01-01', '2024-12-31', 3),
    ('2024-01-01', '2024-12-31', 4);