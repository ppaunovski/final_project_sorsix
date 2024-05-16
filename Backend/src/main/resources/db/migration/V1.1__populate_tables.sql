insert into user_account (u_first_name, u_last_name, u_email, u_password, u_joined_date, u_date_host_started)
values ('Pavel', 'Paunovski', 'pavel@pavel.com', 'pp', '2024-01-16', '2024-01-16'),
       ('Nikola', 'Nacevski', 'nikola@nikola.com', 'nn', '2024-01-16', '2024-01-16'),
       ('Guest', 'Guestovski', 'guest@guest.com', 'gg', '2024-01-16', null);

insert into city (c_id, c_name)
values (1, 'Skopje'),
       (2, 'Kumanovo'),
       (3, 'Ohrid'),
       (4, 'Bitola'),
       (5, 'Prilep'),
       (6, 'Strumica');

insert into property_type (pt_id, pt_type_name)
VALUES (1, 'Room'),
       (2, 'Entire House'),
       (3, 'Apartment'),
       (4, 'Type 4');

insert into attribute (a_name, a_description, a_icon)
VALUES ('Wi-Fi', 'Connection to internet in the property.', 'wifi'),
       ('Parking', 'Connection to internet in the property.', 'local_parking'),
       ('Private Bathroom', 'Connection to internet in the property.', 'bathtub'),
       ('Kitchen', 'Connection to internet in the property.', 'kitchen'),
       ('Pet Friendly', 'Connection to internet in the property.', 'pets'),
       ('Breakfast', 'Connection to internet in the property.', 'egg_alt'),
       ('AC', 'Connection to internet in the property.', 'mode_fan'),
       ('Iron', 'Connection to internet in the property.', 'iron'),
       ('Bay View', 'Connection to internet in the property.', 'beach_access'),
       ('Balcony', 'Connection to internet in the property.', 'balcony'),
       ('Washer', 'Connection to internet in the property.', 'laundry'),
       ('Garden', 'Connection to internet in the property.', 'outdoor_garden'),
       ('Smoke alarm', 'Connection to internet in the property.', 'smoke-free'),
       ('TV', 'Connection to internet in the property.', 'tv'),
       ('Elevator', 'Connection to internet in the property.', 'elevator'),
       ('Heater', 'Connection to internet in the property.', 'sunny');

insert into review_component (rc_component_name, rc_icon)
VALUES ('Cleanliness', 'cleaning_services'),
       ('Accuracy', 'verified'),
       ('Check-in', 'key'),
       ('Communication', 'contact_support'),
       ('Location', 'map'),
       ('Value', 'paid');
insert into property (p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms,
                      p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, u_id, c_id, pt_id)
VALUES (3500.0, 'Biljana Luxury Apartment', 2, 1, 1, 1, true,
        'Odlicen apartment za mladi lugje, se naogja vo stariot grad na Ohrid i ima pogled koj ezeroto i Samoilovata tvrdina. Na samo 5 minuti peshki od Sv. Kaneo i 10 minuti od Plaoshnik.',
        'Samoilova 12', 20.78353, 41.12107, 1, 3, 3),
       (1500.0, 'Klime Fisherman`s house', 5, 3, 2, 2, true,
        'Odlicen apartment za avanturisti, se naogja vo na 25 minuti od centarot na gradot Ohrid i ima pogled kon ezeroto. Na samo 5 minuti peshki od plazhata i 10 minuti od prviot market.',
        'Plazhovska 5', 20.78353, 41.12107, 2, 3, 2),
       (2500.0, 'Alexander The Great Lux', 2, 1, 1, 1, false,
        'Apartman vo centarot na gradot Skopje. Na samo 5 minuti peshki od ploshtadot na gradot, 15 minuti od gradskiot park. Apartmanot raspolaga so zatvorena terasa na koja moze da se pushat cigari i ima  ogled kon Vodno.',
        'Partizanska 32', 21.4228, 41.9997, 1, 1, 3),
       (200.0, 'Helper Room', 1, 1, 1, 1, true,
        'Soba vo mojot stan, koja ja izdavam na mlad student koj studira na UKIM. Lokacijata e odlicna i opshtestveniot kampus e na samo 10 minuti peshki odalechenost. Vo blizina e i univerzitetskata biblioteka i centarot na gradot. Vo stanot prestojuvam samo jas.',
        'Goce Delchev 3', 21.4228, 41.9997, 1, 1, 1);

insert into property_attribute (p_id, a_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (2, 7),
       (3, 1),
       (3, 3),
       (3, 6),
       (3, 2),
       (3, 13),
       (3, 11),
       (3, 10),
       (3, 8),
       (4, 2),
       (4, 4),
       (4, 5),
       (4, 6),
       (4, 7),
       (4, 8),
       (4, 9),
       (4, 10),
       (4, 11),
       (4, 12),
       (4, 13),
       (4, 14),
       (4, 15);

insert into favorite (p_id, u_id)
VALUES (1, 3);
insert into booking_status (bs_status_name)
VALUES ( 'APPROVED'),
       ( 'CANCELLED'),
       ( 'WAITING_FOR_APPROVAL'),
       ( 'REJECTED');
insert into guest_type (gt_type_name, gt_description)
VALUES ( 'ADULT', 'Age 13+'),
       ( 'CHILD', 'Under 12'),
       ( 'PET', '');
insert into booking (b_booking_date, b_checkin_date, b_checkout_date, b_nightly_price, b_service_fee, b_cleaning_fee,
                     p_id, u_id, bs_id)
VALUES
    ('2024-05-02', '2024-05-10', '2024-05-15', 3500.0, 500.0, 500.0, 1, 3, 1),
    ('2024-05-02', '2024-05-10', '2024-05-15', 3500.0, 500.0, 500.0, 2, 3, 1),
    ('2024-05-02', '2024-05-10', '2024-05-15', 3500.0, 500.0, 500.0, 3, 3, 1),
    ('2024-05-02', '2024-05-10', '2024-05-15', 3500.0, 500.0, 500.0, 4, 3, 1);

insert into booking_guests (bg_num_guests, b_id, gt_id)
VALUES (2, 1, 1);

insert into property_availabilities (pav_start_date, pav_end_date, p_id)
values ('2024-01-01', '2024-12-31', 1),
       ('2024-01-01', '2024-12-31', 2),
       ('2024-01-01', '2024-12-31', 3),
       ('2024-01-01', '2024-12-31', 4);

insert into user_review ( ur_comment, ur_review_date, u_id, p_id, b_id)
values (
           'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.',
           '2024-04-20', 3, 1, 1),
       (
           'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.',
           '2024-04-28', 3, 2, 2),
       (
           'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.',
           '2024-02-11', 3, 3, 3),
       (
           'Apartmanot e odlicen, hostot bese prijaten. Se sto bese navedeno vo opisot go imase, a mozebi i poveke. Topla preporaka za sekogo.',
           '2024-03-23', 3, 4, 4);

insert into component_rating (cr_rating, ur_id, rc_id)
VALUES (5, 1, 1),
       (5, 1, 2),
       (5, 1, 3),
       (5, 1, 4),
       (5, 1, 5),
       (5, 1, 6),

       (5, 2, 1),
       (4, 2, 2),
       (3, 2, 3),
       ( 4, 2, 4),
       ( 2, 2, 5),
       ( 4, 2, 6),

       (3, 3, 1),
       (5, 3, 2),
       (5, 3, 3),
       (5, 3, 4),
       (2, 3, 5),
       (4, 3, 6),

       (3, 4, 1),
       (5, 4, 2),
       (3, 4, 3),
       (5, 4, 4),
       (2, 4, 5),
       (5, 4, 6);
