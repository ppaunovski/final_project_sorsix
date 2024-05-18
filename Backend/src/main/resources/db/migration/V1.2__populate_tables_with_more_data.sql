INSERT INTO user_account (u_first_name, u_last_name, u_email, u_password, u_joined_date, u_date_host_started)
VALUES
    ('Pavel', 'Paunovski', 'pavel@pavel.com', 'pp', '2024-01-16', '2024-01-16'),
    ('Nikola', 'Nacevski', 'nikola@nikola.com', 'nn', '2024-01-16', '2024-01-16'),
    ('Guest', 'Guestovski', 'guest@guest.com', 'gg', '2024-01-16', NULL),
    ('Ana', 'Ivanova', 'ana@ivanova.com', 'ai', '2024-01-16', '2024-01-17'),
    ('Marko', 'Markovski', 'marko@marko.com', 'mm', '2024-01-17', '2024-01-18'),
    ('Sara', 'Sarovska', 'sara@sara.com', 'ss', '2024-01-17', NULL),
    ('David', 'Davidski', 'david@david.com', 'dd', '2024-01-17', '2024-01-18'),
    ('Eva', 'Evanska', 'eva@eva.com', 'ee', '2024-01-18', NULL),
    ('Ivan', 'Ivanovski', 'ivan@ivan.com', 'ii', '2024-01-18', '2024-01-19'),
    ('Mila', 'Milovska', 'mila@mila.com', 'mm', '2024-01-19', '2024-01-20'),
    ('Luka', 'Lukovski', 'luka@luka.com', 'll', '2024-01-19', NULL),
    ('Sofia', 'Sofovska', 'sofia@sofia.com', 'ss', '2024-01-20', '2024-01-21'),
    ('Petar', 'Petrovski', 'petar@petar.com', 'pp', '2024-01-20', '2024-01-22'),
    ('Elena', 'Elenovska', 'elena@elena.com', 'ee', '2024-01-20', NULL),
    ('Dimitar', 'Dimitrovski', 'dimitar@dimitar.com', 'dd', '2024-01-21', '2024-01-22'),
    ('Katerina', 'Katerinska', 'katerina@kat.com', 'kk', '2024-01-21', NULL),
    ('Boris', 'Borisovski', 'boris@boris.com', 'bb', '2024-01-22', '2024-01-23'),
    ('Jana', 'Janovska', 'jana@jana.com', 'jj', '2024-01-22', NULL),
    ('Aleksandar', 'Aleksandrov', 'aleks@aleks.com', 'aa', '2024-01-22', '2024-01-24'),
    ('Tina', 'Tinovska', 'tina@tina.com', 'tt', '2024-01-23', '2024-01-25'),
    ('Filip', 'Filipovski', 'filip@filip.com', 'ff', '2024-01-23', NULL),
    ('Goran', 'Goranski', 'goran@goran.com', 'gg', '2024-01-24', '2024-01-25'),
    ('Marija', 'Marijevska', 'marija@marija.com', 'mm', '2024-01-24', NULL),
    ('Stefan', 'Stefanovski', 'stefan@stefan.com', 'ss', '2024-01-24', '2024-01-26'),
    ('Ivana', 'Ivanovska', 'ivana@ivana.com', 'ii', '2024-01-25', '2024-01-27'),
    ('Viktor', 'Viktorovski', 'viktor@viktor.com', 'vv', '2024-01-25', NULL),
    ('Dragana', 'Draganovska', 'dragana@dragana.com', 'dd', '2024-01-26', '2024-01-28'),
    ('Kristijan', 'Kristijanovski', 'kristijan@kristijan.com', 'kk', '2024-01-26', NULL),
    ('Marta', 'Martovska', 'marta@marta.com', 'mm', '2024-01-27', '2024-01-29'),
    ('Darko', 'Darkovski', 'darko@darko.com', 'dd', '2024-01-27', '2024-01-30');


INSERT INTO city (c_id, c_name)
VALUES
    (1, 'Skopje'),
    (2, 'Kumanovo'),
    (3, 'Ohrid'),
    (4, 'Bitola'),
    (5, 'Prilep'),
    (6, 'Strumica'),
    (7, 'Tetovo'),
    (8, 'Veles'),
    (9, 'Shtip'),
    (10, 'Kavadarci'),
    (11, 'Gostivar'),
    (12, 'Kochani'),
    (13, 'Kichevo'),
    (14, 'Struga'),
    (15, 'Radovish'),
    (16, 'Gevgelija'),
    (17, 'Debar'),
    (18, 'Kriva Palanka'),
    (19, 'Negotino'),
    (20, 'Sveti Nikole'),
    (21, 'Resen'),
    (22, 'Berovo'),
    (23, 'Vinica'),
    (24, 'Delchevo'),
    (25, 'Pehchevo'),
    (26, 'Makedonski Brod'),
    (27, 'Kratovo'),
    (28, 'Bogdanci'),
    (29, 'Demir Hisar'),
    (30, 'Demir Kapija'),
    (31, 'Krushevo'),
    (32, 'Valandovo'),
    (33, 'Probishtip'),
    (34, 'Zrnovci'),
    (35, 'Konche'),
    (36, 'Plasnica'),
    (37, 'Mogila'),
    (38, 'Vasilevo'),
    (39, 'Vevchani'),
    (40, 'Novaci'),
    (41, 'Dolneni'),
    (42, 'Rosoman'),
    (43, 'Gradsko'),
    (44, 'Lipkovo'),
    (45, 'Zelenikovo'),
    (46, 'Ilinden'),
    (47, 'Studenichani'),
    (48, 'Sopishte'),
    (49, 'Aracinovo'),
    (50, 'Petrovec');

INSERT INTO property_type (pt_id, pt_type_name)
VALUES
    (1, 'Apartment'),
    (2, 'House'),
    (3, 'Villa'),
    (4, 'Cottage'),
    (5, 'Studio'),
    (6, 'Loft'),
    (7, 'Townhouse'),
    (8, 'Bungalow'),
    (9, 'Condominium'),
    (10, 'Cabin'),
    (11, 'Chalet'),
    (12, 'Guesthouse'),
    (13, 'Farmhouse'),
    (14, 'Penthouse'),
    (15, 'Duplex'),
    (16, 'Mansion'),
    (17, 'Hostel'),
    (18, 'Hotel Room'),
    (19, 'Resort'),
    (20, 'Houseboat'),
    (21, 'Castle'),
    (22, 'Tiny House'),
    (23, 'Barn'),
    (24, 'Camper/RV'),
    (25, 'Treehouse'),
    (26, 'Yurt'),
    (27, 'Tent'),
    (28, 'Serviced Apartment'),
    (29, 'Ryokan'),
    (30, 'Villa Suite');

INSERT INTO attribute (a_name, a_description, a_icon)
VALUES
    ('Wi-Fi', 'Connection to internet in the property.', 'wifi'),
    ('Parking', 'Parking space available at the property.', 'local_parking'),
    ('Private Bathroom', 'Exclusive bathroom for the room.', 'bathtub'),
    ('Kitchen', 'Kitchen facilities available.', 'kitchen'),
    ('Pet Friendly', 'Pets allowed in the property.', 'pets'),
    ('Breakfast', 'Breakfast included in the stay.', 'egg_alt'),
    ('AC', 'Air conditioning available.', 'mode_fan'),
    ('Iron', 'Iron and ironing board available.', 'iron'),
    ('Bay View', 'View of the bay from the property.', 'beach_access'),
    ('Balcony', 'Private balcony available.', 'balcony'),
    ('Washer', 'Washing machine available.', 'local_laundry_service'),
    ('Garden', 'Garden available for guests.', 'yard'),
    ('Smoke alarm', 'Smoke alarm installed.', 'vape_free'),
    ('TV', 'Television available.', 'tv'),
    ('Elevator', 'Elevator available for use.', 'elevator'),
    ('Heater', 'Heating system available.', 'sunny'),
    ('Swimming Pool', 'Swimming pool available.', 'pool'),
    ('Gym', 'Fitness center available.', 'fitness_center'),
    ('Fireplace', 'Fireplace available for use.', 'fireplace'),
    ('BBQ Grill', 'Barbecue grill available.', 'outdoor_grill'),
    ('Hot Tub', 'Hot tub available for use.', 'hot_tub'),
    ('Game Room', 'Game room available.', 'sports_esports'),
    ('Library', 'Library with books available.', 'menu_book'),
    ('Workspace', 'Dedicated workspace available.', 'computer'),
    ('Bicycle Rental', 'Bicycles available for rent.', 'directions_bike'),
    ('Spa', 'Spa services available.', 'spa'),
    ('Sauna', 'Sauna available for use.', 'sauna'),
    ('Rooftop Access', 'Access to the rooftop area.', 'roofing'),
    ('Security', 'Security services available.', 'security'),
    ('Soundproofing', 'Soundproof rooms.', 'hearing'),
    ('Childcare', 'Childcare services available.', 'child_care');

insert into review_component (rc_component_name, rc_icon)
VALUES ('Cleanliness', 'cleaning_services'),
       ('Accuracy', 'verified'),
       ('Check-in', 'key'),
       ('Communication', 'contact_support'),
       ('Location', 'map'),
       ('Value', 'paid');

INSERT INTO property (p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, u_id, c_id, pt_id)
VALUES
    (3500.0, 'Biljana Luxury Apartment', 2, 1, 1, 1, true,
     'Biljana Luxury Apartment is an exquisite one-bedroom apartment located in the heart of the old town of Ohrid. This charming apartment offers a stunning view of the lake and the historic Samuil Fortress, making it an ideal spot for couples or solo travelers looking for a romantic getaway. The apartment is tastefully decorated with modern furnishings and comes with a fully equipped kitchen, a cozy living area, and a comfortable bedroom. Guests can enjoy their morning coffee on the balcony while taking in the breathtaking views. The location is perfect, just a 5-minute walk from the famous Church of St. John at Kaneo and a 10-minute walk from Plaoshnik, providing easy access to some of Ohrid`s most iconic landmarks. Whether you are here for sightseeing or simply to relax by the lake, Biljana Luxury Apartment offers the perfect blend of comfort and convenience.',
'Samoilova 12', 20.78353, 41.12107, 1, 3, 3),

(1500.0, 'Klime Fisherman`s House', 5, 3, 2, 2, true,
     'Klime Fisherman`s House is a charming and rustic home located just 25 minutes from the center of Ohrid. This spacious house is perfect for adventurous travelers looking for a unique stay near the lake. With three bedrooms and two bathrooms, it comfortably accommodates up to five guests. The house is designed with a fisherman`s touch, featuring wooden interiors and nautical decor. Guests can enjoy a short 5-minute walk to the beach, making it ideal for those who love water activities or simply relaxing by the shore. The house also boasts a beautiful garden where guests can unwind and enjoy the peaceful surroundings. Whether you are looking to explore the local markets or take a boat trip on the lake, Klime Fisherman`s House provides a cozy and welcoming retreat.',
'Plazhovska 5', 20.78353, 41.12107, 2, 3, 2),

(2500.0, 'Alexander The Great Lux', 2, 1, 1, 1, false,
'Alexander The Great Lux is a premium one-bedroom apartment located in the vibrant city center of Skopje. This modern apartment is perfect for business travelers or couples seeking a stylish and comfortable place to stay. It features a spacious living room, a fully equipped kitchen, and a bedroom with a comfortable queen-sized bed. The apartment also has a closed terrace where guests can smoke and enjoy views of Mount Vodno. Located just a 5-minute walk from Skopje`s main square and a 15-minute walk from the city park, guests will have easy access to restaurants, shops, and cultural attractions. Whether you are in Skopje for business or leisure, Alexander The Great Lux offers a luxurious and convenient base for your stay.',
     'Partizanska 32', 21.4228, 41.9997, 1, 1, 3),

    (200.0, 'Helper Room', 1, 1, 1, 1, true,
     'Helper Room is a cozy and affordable accommodation option located in the heart of Skopje. This private room in a shared apartment is ideal for young students or solo travelers studying at the nearby Ss. Cyril and Methodius University. The location is excellent, with the university campus just a 10-minute walk away and the city center within easy reach. The room is simple yet comfortable, providing all the essentials for a short-term stay. Guests will have access to a shared kitchen, living area, and bathroom. The host, who resides in the apartment, is friendly and welcoming, offering local tips and assistance as needed. Helper Room is a great choice for those looking for a budget-friendly and convenient place to stay in Skopje.',
     'Goce Delchev 3', 21.4228, 41.9997, 1, 1, 1),

    (3000.0, 'Skopje City Apartment', 3, 2, 2, 1, true,
     'Skopje City Apartment is a modern and spacious two-bedroom apartment located in the heart of Skopje. This apartment is perfect for families or small groups looking for a comfortable and stylish place to stay. The apartment features a fully equipped kitchen, a large living area with a flat-screen TV, and two cozy bedrooms with comfortable beds. Guests can enjoy stunning city views from the balcony. The apartment is situated close to Macedonia Square and the Old Bazaar, making it an ideal base for exploring the city`s attractions. With its convenient location and modern amenities, Skopje City Apartment provides a luxurious home away from home.',
'Makedonska 10', 21.4346, 41.9981, 1, 1, 3),

(1800.0, 'Tetovo Cozy Home', 4, 2, 1, 1, false,
'Tetovo Cozy Home is a charming and comfortable house located in the center of Tetovo. This home is perfect for families or groups of friends looking for a relaxing getaway. The house features two bedrooms, a fully equipped kitchen, a spacious living room, and a lovely garden where guests can unwind and enjoy the fresh air. The location is excellent, with easy access to local shops, restaurants, and cultural attractions. Tetovo Cozy Home offers a peaceful and welcoming retreat in the heart of the city.',
'Tetovsko 22', 20.9707, 42.0094, 4, 7, 2),

(2200.0, 'Bitola Historic House', 6, 3, 3, 2, true,
'Bitola Historic House is a beautifully preserved historic home located in the heart of Bitola. This spacious house features three bedrooms, three bathrooms, and can comfortably accommodate up to six guests. The interior is elegantly decorated with antique furniture and classic decor, providing a sense of stepping back in time. The house is situated near Sirok Sokak, Bitola`s main street, known for its vibrant cafes, shops, and cultural landmarks. Guests can enjoy a leisurely stroll through the historic town or visit nearby attractions such as the Heraclea Lyncestis archaeological site. Bitola Historic House offers a unique and charming accommodation experience for those looking to explore the rich history of Bitola.',
     'Sirok Sokak 15', 21.3339, 41.0286, 5, 4, 2),

    (4000.0, 'Prilep Mountain Villa', 8, 4, 4, 3, true,
     'Prilep Mountain Villa is a luxurious four-bedroom villa located in the picturesque mountains of Prilep. This stunning villa offers breathtaking views of the surrounding mountains and the city below. The villa features a spacious living area, a fully equipped kitchen, and four beautifully decorated bedrooms, each with its own en-suite bathroom. Guests can enjoy the outdoor terrace, perfect for dining al fresco, and the private garden. The villa is ideal for families or groups of friends looking for a high-end retreat. Whether you are hiking in the mountains or exploring the local attractions, Prilep Mountain Villa provides a serene and luxurious escape.',
     'Planinski Pat 7', 21.5494, 41.3464, 10, 5, 4),

    (2500.0, 'Strumica Garden House', 5, 2, 2, 2, true,
     'Strumica Garden House is a delightful two-bedroom house with a beautiful garden located in Strumica. This charming house is perfect for families or couples looking for a peaceful retreat. The house features a fully equipped kitchen, a cozy living area, and two comfortable bedrooms. Guests can relax in the garden, which is perfect for enjoying the fresh air and outdoor dining. The house is situated close to local attractions, shops, and restaurants, making it a convenient base for exploring Strumica. Strumica Garden House offers a tranquil and comfortable stay in a beautiful setting.',
     'Gradinarska 5', 22.6413, 41.4401, 7, 6, 2),

    (1000.0, 'Veles Riverside Apartment', 2, 1, 1, 1, false,
     'Veles Riverside Apartment is a cozy one-bedroom apartment located by the river in the center of Veles. This apartment is perfect for couples or solo travelers looking for a comfortable and convenient place to stay. The apartment features a fully equipped kitchen, a comfortable living area, and a bedroom with a queen-sized bed. Guests can enjoy views of the river from the balcony. The location is ideal, with easy access to local shops, restaurants, and cultural attractions. Veles Riverside Apartment offers a peaceful and welcoming stay in the heart of Veles.',
     'Riverska 2', 21.7753, 41.7157, 4, 8, 3),
    (3500.0, 'Lakeview Retreat Villa', 8, 4, 3, 3, true,
     'Escape to the tranquility of Lakeview Retreat Villa, a luxurious getaway nestled on the shores of Lake Ohrid. This stunning villa offers panoramic views of the sparkling waters and the surrounding mountains, providing a serene setting for relaxation and rejuvenation. With four spacious bedrooms, a private infinity pool, and a lush garden, the villa provides the perfect retreat for families or groups of friends. Guests can enjoy al fresco dining on the terrace, go for a swim in the lake, or simply unwind in the outdoor hot tub while admiring the breathtaking sunset. Lakeview Retreat Villa promises an unforgettable escape in the heart of Macedonia.',
     'Kej Marshal Tito', 20.7994, 41.1125, 2, 3, 1),

    (1200.0, 'Macedonian Countryside Cottage', 4, 2, 2, 1, false,
     'Experience the charm of rural Macedonia at Macedonian Countryside Cottage, a quaint retreat nestled amidst rolling hills and picturesque vineyards. This cozy cottage offers a peaceful escape from the hustle and bustle of city life, providing guests with a taste of authentic Macedonian hospitality. With two comfortable bedrooms, a fully equipped kitchen, and a cozy living area with a fireplace, the cottage provides all the comforts of home. Guests can explore nearby hiking trails, visit local wineries, or simply relax on the terrace and enjoy the stunning views of the countryside. Macedonian Countryside Cottage is the perfect destination for those seeking tranquility and natural beauty.',
     'Village of Ljubojno', 20.7974, 41.0918, 30, 3, 2),

    (2800.0, 'Ohrid Lakeside Villa', 10, 5, 4, 3, true,
     'Embrace luxury at Ohrid Lakeside Villa, a lavish retreat located directly on the shores of Lake Ohrid. This exquisite villa boasts panoramic views of the crystal-clear waters and the surrounding mountains, creating a spectacular backdrop for relaxation and indulgence. With five elegant bedrooms, a private pool, and a spacious terrace, the villa offers the ultimate in comfort and style for discerning guests. Enjoy leisurely swims in the lake, dine al fresco under the stars, or simply unwind in the sauna after a day of exploration. Ohrid Lakeside Villa promises a truly unforgettable experience in one of Macedonia`s most enchanting destinations.',
     'Bulevar Turistichka', 20.8050, 41.1128, 4, 3, 1),

    (200.0, 'Cozy Studio in Skopje Center', 2, 1, 1, 1, false,
     'Discover comfort and convenience at Cozy Studio in Skopje Center, a charming retreat located in the heart of Macedonia`s capital city. This stylish studio apartment is ideal for solo travelers or couples looking for a central base from which to explore Skopje`s vibrant attractions. The apartment features a well-appointed kitchenette, a comfortable queen-sized bed, and a modern bathroom. Guests can enjoy easy access to the city`s landmarks, restaurants, and nightlife, as well as tranquil walks along the Vardar River promenade. Whether you`re here for business or leisure, Cozy Studio offers a warm and welcoming retreat in Skopje.',
     'Bulevar Partizanski Odredi', 21.4234, 41.9981, 5, 1, 1),

    (3000.0, 'Luxury Penthouse in Bitola', 4, 2, 2, 2, true,
     'Indulge in opulence at Luxury Penthouse in Bitola, a sophisticated retreat located in the historic heart of the city. This exquisite penthouse apartment boasts panoramic views of Bitola`s charming skyline and offers luxurious amenities for the discerning traveler. With two elegant bedrooms, a spacious living area, and a private rooftop terrace, the penthouse provides the perfect setting for relaxation and entertainment. Guests can explore nearby cultural landmarks, dine in gourmet restaurants, or simply unwind with a glass of wine while admiring the sunset over Pelister National Park. Luxury Penthouse in Bitola promises an unforgettable stay in one of Macedonia`s most iconic destinations.',
     'Marshal Tito', 21.3341, 41.0286, 7, 4, 3),
    (2200.0, 'Luxury Lakeside Villa in Struga', 8, 4, 4, 3, true,
     'Experience unparalleled luxury at this Lakeside Villa in Struga. This magnificent property offers stunning views of Lake Ohrid and the surrounding mountains, providing an idyllic setting for a memorable vacation. With four spacious bedrooms, a private pool, and a range of upscale amenities, including a sauna and jacuzzi, this villa caters to every comfort and desire. Guests can enjoy leisurely walks along the lake shore, explore nearby cultural attractions, or simply relax and soak in the breathtaking scenery from the comfort of the villa. Whether you`re seeking adventure or relaxation, this Lakeside Villa promises an unforgettable stay in the heart of Macedonia.',
     'Nikola Karev 15', 20.6749, 41.1785, 7, 3, 1),

    (1500.0, 'Cozy Countryside Cabin in Kriva Palanka', 4, 2, 1, 1, false,
     'Escape the hustle and bustle of city life with a stay at this charming Countryside Cabin in Kriva Palanka. Nestled amidst the tranquil beauty of rural Macedonia, this cozy cabin offers the perfect retreat for nature lovers and outdoor enthusiasts. With one comfortable bedroom, a well-equipped kitchen, and a cozy living area with a fireplace, the cabin provides all the comforts of home in a picturesque setting. Guests can explore nearby hiking trails, go fishing in the river, or simply relax and enjoy the peace and quiet of the countryside. Whether you`re seeking adventure or relaxation, this Countryside Cabin offers an authentic Macedonian experience.',
     'Village of Gari', 22.3324, 42.2101, 9, 18, 2),

    (2500.0, 'Historic Townhouse in Ohrid Old Town', 6, 3, 3, 2, true,
     'Step back in time with a stay at this Historic Townhouse in Ohrid Old Town. Situated in the heart of the city`s UNESCO-listed old town, this beautifully restored townhouse offers a unique glimpse into Macedonia`s rich history and heritage. With three elegant bedrooms, a spacious living area, and a charming courtyard garden, the townhouse combines period charm with modern comforts for a truly memorable stay. Guests can explore the narrow cobbled streets, visit ancient churches and monasteries, or simply relax and soak in the atmosphere of this enchanting historic district. Whether you`re a history buff or simply seeking a unique travel experience, this Historic Townhouse is the perfect choice for your stay in Ohrid.',
     'Kuzman Kapidan 10', 20.7984, 41.1097, 9, 3, 2),

    (2000.0, 'Modern Apartment in Skopje City Center', 4, 2, 2, 1, false,
     'Experience the vibrant energy of Macedonia`s capital with a stay at this Modern Apartment in Skopje City Center. Located just steps away from Macedonia Square and the city`s main attractions, this stylish apartment offers the perfect blend of comfort and convenience for your stay. With two spacious bedrooms, a sleek open-plan living area, and a fully equipped kitchen, the apartment provides all the comforts of home in a contemporary setting. Guests can explore the city`s cultural landmarks, dine in gourmet restaurants, or simply soak up the atmosphere of Skopje`s bustling streets. Whether you`re here for business or leisure, this Modern Apartment offers a chic and comfortable base for your adventures in Skopje.',
     'Bulevar 11 Oktomvri', 21.4318, 41.9949, 10, 1, 3),

    (1800.0, 'Ski Chalet in Mavrovo National Park', 6, 3, 2, 2, true,
     'Escape to the pristine beauty of Mavrovo National Park with a stay at this cozy Ski Chalet. Nestled amidst snow-capped mountains and towering pine forests, this charming chalet offers the perfect retreat for winter sports enthusiasts and nature lovers alike. With three comfortable bedrooms, a spacious living area with a fireplace, and a fully equipped kitchen, the chalet provides all the comforts of home after a day on the slopes. Guests can enjoy skiing, snowboarding, or snowshoeing in the nearby ski resort, or simply relax and take in the breathtaking mountain views from the comfort of the chalet. Whether you`re seeking adventure or relaxation, this Ski Chalet promises an unforgettable stay in the heart of Macedonia`s winter wonderland.',
     'Mavrovo', 20.6864, 41.6958, 12, 5, 2),
    (2200.0, 'Modern Loft in Skopje Old Bazaar', 4, 2, 1, 1, true,
     'Immerse yourself in the vibrant atmosphere of Skopje`s Old Bazaar with a stay at this Modern Loft. Located in a historic building dating back centuries, this stylish loft apartment offers a unique blend of tradition and modernity. With one spacious bedroom, a sleek open-plan living area, and a fully equipped kitchen, the loft provides all the comforts of home in a contemporary setting. Guests can explore the bustling streets of the Old Bazaar, visit local artisan shops, or dine in traditional Macedonian restaurants just steps away from the apartment. Whether you`re a history buff or simply seeking a unique travel experience, this Modern Loft offers the perfect base for your adventures in Skopje.',
     'Old Bazaar Street', 21.4387, 42.0037, 12, 1, 3),

    (1800.0, 'Riverside Cabin in Strumica', 4, 2, 1, 1, false,
     'Escape to nature with a stay at this Riverside Cabin in Strumica. Nestled on the banks of the Strumica River, this charming cabin offers a peaceful retreat surrounded by lush greenery and pristine wilderness. With one cozy bedroom, a well-equipped kitchenette, and a comfortable living area, the cabin provides all the comforts of home in a tranquil setting. Guests can relax on the riverside terrace, go fishing or swimming in the river, or explore the nearby hiking trails and natural attractions. Whether you`re seeking adventure or relaxation, this Riverside Cabin promises an unforgettable getaway in the heart of Macedonia`s countryside.',
     'Riverside Lane', 22.6417, 41.4366, 13, 6, 2),

    (2500.0, 'Luxury Villa with Mountain Views in Prilep', 8, 4, 3, 3, true,
     'Experience the height of luxury with a stay at this stunning Villa in Prilep. Perched on a hillside overlooking the majestic mountains of Prilep, this luxurious villa offers breathtaking views and unparalleled comfort. With four elegant bedrooms, a private infinity pool, and a range of upscale amenities, including a sauna and jacuzzi, the villa provides the perfect setting for relaxation and indulgence. Guests can explore the nearby hiking trails, visit local wineries, or simply relax and soak in the stunning mountain views from the comfort of the villa. Whether you`re seeking adventure or relaxation, this Luxury Villa promises an unforgettable stay in one of Macedonia`s most picturesque destinations.',
     'Mountainview Street', 21.5489, 41.3441, 13, 10, 1),

    (2000.0, 'Seaside Apartment in Ohrid', 4, 2, 1, 1, false,
     'Enjoy a beachside getaway with a stay at this Seaside Apartment in Ohrid. Located just steps away from the shores of Lake Ohrid, this cozy apartment offers the perfect retreat for sun-seekers and water enthusiasts. With one comfortable bedroom, a well-equipped kitchen, and a bright living area, the apartment provides all the comforts of home with stunning lake views. Guests can relax on the nearby beach, go swimming or kayaking in the crystal-clear waters, or explore the charming streets of Ohrid`s old town. Whether you`re seeking relaxation or adventure, this Seaside Apartment offers the perfect base for your stay in Macedonia`s most enchanting lakeside destination.',
     'Lakeside Avenue', 20.7969, 41.1132, 15, 3, 3),

    (3500.0, 'Mountain Retreat Chalet in Krusevo', 6, 3, 2, 2, true,
     'Escape to the mountains with a stay at this cozy Chalet in Krusevo. Surrounded by pristine nature and breathtaking mountain views, this charming chalet offers the perfect retreat for outdoor enthusiasts and nature lovers alike. With three comfortable bedrooms, a spacious living area with a fireplace, and a fully equipped kitchen, the chalet provides all the comforts of home in a rustic setting. Guests can explore nearby hiking trails, go paragliding over the valley, or simply relax and enjoy the peace and tranquility of the mountains. Whether you`re seeking adventure or relaxation, this Mountain Retreat Chalet promises an unforgettable stay in one of Macedonia`s most scenic destinations.',
     'Mountainview Road', 21.2480, 41.3683, 15, 31, 2),
    (1800.0, 'Cozy Cottage Retreat in Ohrid', 4, 2, 1, 1, true,
     'Escape to the countryside with a stay at this Cozy Cottage Retreat in Ohrid. Tucked away amidst lush greenery and rolling hills, this charming cottage offers a peaceful retreat for nature lovers and outdoor enthusiasts. With one comfortable bedroom, a well-equipped kitchen, and a cozy living area with a fireplace, the cottage provides all the comforts of home in a rustic setting. Guests can explore nearby hiking trails, go fishing in the nearby lake, or simply relax and enjoy the tranquility of the countryside. Whether you`re seeking adventure or relaxation, this Cozy Cottage Retreat promises an unforgettable stay in the heart of Macedonia`s natural beauty.',
     'Makedonska 25', 20.7879, 41.1140, 17, 3, 4),

    (2500.0, 'Luxury Penthouse in Skopje City Center', 6, 3, 2, 2, false,
     'Indulge in luxury with a stay at this stunning Penthouse in Skopje City Center. Perched atop a prestigious high-rise building, this luxurious penthouse offers breathtaking views of the city skyline and surrounding mountains. With three elegant bedrooms, a spacious living area, and a private rooftop terrace with a jacuzzi, the penthouse provides the ultimate in comfort and sophistication. Guests can explore the city`s cultural landmarks, dine in gourmet restaurants, or simply relax and take in the panoramic views from the comfort of the penthouse. Whether you`re here for business or leisure, this Luxury Penthouse promises an unforgettable stay in the heart of Skopje.',
     'City Tower 20th floor', 21.4339, 41.9943, 17, 1, 14),

    (2000.0, 'Secluded Treehouse Retreat in Galicnik', 2, 1, 1, 1, true,
     'Experience a unique stay in the treetops with this Secluded Treehouse Retreat in Galicnik. Nestled amidst a lush forest in the remote village of Galicnik, this charming treehouse offers a truly magical escape from the hustle and bustle of everyday life. With one cozy bedroom, a romantic canopy bed, and a private balcony overlooking the forest canopy, the treehouse provides a one-of-a-kind accommodation experience. Guests can explore the surrounding wilderness, go hiking or birdwatching in the nearby national park, or simply relax and enjoy the peace and tranquility of nature. Whether you`re seeking adventure or romance, this Secluded Treehouse Retreat promises an unforgettable stay in Macedonia`s pristine wilderness.',
     'Forest Trail', 20.8986, 41.8014, 19, 22, 25),

    (3000.0, 'Riverside Yurt Experience in Kriva Palanka', 4, 1, 1, 1, false,
     'Embark on a unique glamping adventure with a stay at this Riverside Yurt Experience in Kriva Palanka. Set beside a tranquil river in the picturesque countryside, this cozy yurt offers a one-of-a-kind accommodation experience for nature lovers and outdoor enthusiasts. With one comfortable bed, a wood-burning stove, and rustic furnishings, the yurt provides a cozy retreat in a stunning natural setting. Guests can explore the surrounding wilderness, go fishing or kayaking in the river, or simply relax and enjoy the peace and quiet of the countryside. Whether you`re seeking adventure or relaxation, this Riverside Yurt Experience promises an unforgettable stay in the heart of Macedonia`s pristine wilderness.',
     'Riverside Glade', 22.1985, 42.2025, 20, 18, 26),

    (3500.0, 'Historic Castle Stay in Pelister National Park', 10, 5, 4, 3, true,
     'Step back in time with a stay at this Historic Castle in Pelister National Park. Dating back centuries, this magnificent castle offers a unique glimpse into Macedonia`s rich history and heritage. With five elegant bedrooms, a grand dining hall, and sprawling gardens, the castle provides a truly regal accommodation experience. Guests can explore the castle`s historic halls and chambers, stroll through the manicured gardens, or venture into the surrounding national park to explore ancient ruins and breathtaking vistas. Whether you`re a history buff or simply seeking a unique travel experience, this Historic Castle promises an unforgettable stay in the heart of Macedonia.',
     'Castle Road', 21.2224, 41.0187, 29, 18, 21);

-- Biljana Luxury Apartment
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (1, 1), (1, 2), (1, 3), (1, 4), (1, 7), (1, 13), (1, 15), (1, 16), (1, 18), (1, 29);

-- Klime Fisherman's House
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (2, 2), (2, 3), (2, 5), (2, 12), (2, 20), (2, 21), (2, 22), (2, 23), (2, 24), (2, 26);

-- Alexander The Great Lux
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (3, 1), (3, 4), (3, 6), (3, 7), (3, 13), (3, 15), (3, 16), (3, 18), (3, 19), (3, 28);

-- Helper Room
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (4, 1), (4, 2), (4, 4), (4, 7), (4, 13), (4, 20), (4, 26), (4, 28), (4, 29), (4, 30);

-- Skopje City Apartment
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (5, 1), (5, 4), (5, 7), (5, 13), (5, 15), (5, 16), (5, 18), (5, 28), (5, 29), (5, 30);

-- Tetovo Cozy Home
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (6, 2), (6, 3), (6, 4), (6, 12), (6, 13), (6, 16), (6, 18), (6, 28), (6, 29), (6, 30);

-- Bitola Historic House
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (7, 1), (7, 2), (7, 3), (7, 4), (7, 12), (7, 13), (7, 21), (7, 22), (7, 24), (7, 30);

-- Prilep Mountain Villa
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (8, 2), (8, 3), (8, 4), (8, 7), (8, 13), (8, 15), (8, 16), (8, 18), (8, 19), (8, 28);

-- Strumica Garden House
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (9, 1), (9, 3), (9, 4), (9, 12), (9, 13), (9, 16), (9, 18), (9, 24), (9, 29), (9, 30);

-- Veles Riverside Apartment
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (10, 1), (10, 2), (10, 4), (10, 7), (10, 13), (10, 15), (10, 18), (10, 28), (10, 29), (10, 30);

-- Lakeview Retreat Villa
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (11, 3), (11, 4), (11, 6), (11, 13), (11, 15), (11, 16), (11, 18), (11, 22), (11, 23), (11, 29);

-- Macedonian Countryside Cottage
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (12, 2), (12, 3), (12, 4), (12, 12), (12, 13), (12, 16), (12, 18), (12, 24), (12, 26), (12, 30);

-- Ohrid Lakeside Villa
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (13, 3), (13, 4), (13, 6), (13, 13), (13, 15), (13, 16), (13, 18), (13, 22), (13, 23), (13, 29);

-- Cozy Studio in Skopje Center
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (14, 1), (14, 4), (14, 7), (14, 13), (14, 15), (14, 16), (14, 18), (14, 28), (14, 29), (14, 30);

-- Luxury Penthouse in Bitola
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (15, 2), (15, 3), (15, 4), (15, 7), (15, 12), (15, 13), (15, 16), (15, 18), (15, 24), (15, 26);


-- Modern Loft in Skopje Old Bazaar
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (17, 1), (17, 4), (17, 7), (17, 12), (17, 13), (17, 16), (17, 18), (17, 28), (17, 29), (17, 30);

-- Riverside Cabin in Strumica
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (18, 2), (18, 3), (18, 5), (18, 12), (18, 13), (18, 15), (18, 18), (18, 22), (18, 24), (18, 26);

-- Luxury Villa with Mountain Views in Prilep
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (19, 2), (19, 3), (19, 5), (19, 7), (19, 12), (19, 13), (19, 15), (19, 18), (19, 24), (19, 26);

-- Seaside Apartment in Ohrid
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (20, 2), (20, 4), (20, 5), (20, 12), (20, 13), (20, 15), (20, 18), (20, 22), (20, 24), (20, 26);

-- Mountain Retreat Chalet in Krusevo
INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (21, 2), (21, 3), (21, 5), (21, 7), (21, 12), (21, 13), (21, 16), (21, 18), (21, 22), (21, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (22, 2), (22, 3), (22, 5), (22, 7), (22, 12), (22, 13), (22, 16), (22, 18), (22, 22), (22, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (23, 2), (23, 3), (23, 5), (23, 7), (23, 12), (23, 13), (23, 16), (23, 18), (23, 22), (23, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (24, 2), (24, 3), (24, 5), (24, 7), (24, 12), (24, 13), (24, 16), (24, 18), (24, 22), (24, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (25, 2), (25, 3), (25, 5), (25, 7), (25, 12), (25, 13), (25, 16), (25, 18), (25, 22), (25, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (26, 2), (26, 3), (26, 5), (26, 7), (26, 12), (26, 13), (26, 16), (26, 18), (26, 22), (26, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (27, 2), (27, 3), (27, 5), (27, 7), (27, 12), (27, 13), (27, 16), (27, 18), (27, 22), (27, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (28, 2), (28, 3), (28, 5), (28, 7), (28, 12), (28, 13), (28, 16), (28, 18), (28, 22), (28, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (29, 2), (29, 3), (29, 5), (29, 7), (29, 12), (29, 13), (29, 16), (29, 18), (29, 22), (29, 24);

INSERT INTO property_attribute (p_id, a_id) VALUES
                                                (30, 2), (30, 3), (30, 5), (30, 7), (30, 12), (30, 13), (30, 16), (30, 18), (30, 22), (30, 24);


insert into booking_status (bs_status_name)
VALUES ( 'APPROVED'),
       ( 'CANCELLED'),
       ( 'WAITING_FOR_APPROVAL'),
       ( 'REJECTED');

insert into guest_type (gt_type_name, gt_description)
VALUES ( 'ADULT', 'Age 13+'),
       ( 'CHILD', 'Under 12'),
       ( 'PET', '');

-- INSERT INTO booking (b_booking_date, b_checkin_date, b_checkout_date, b_nightly_price, b_service_fee, b_cleaning_fee, p_id, u_id, bs_id)
-- VALUES
--     ('2024-05-20', '2024-05-25', '2024-05-30', 3500.0, 500.0, 500.0, 1, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 1) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-05-31', '2024-06-05', '2024-06-10', 3500.0, 500.0, 500.0, 1, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 1) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-11', '2024-06-16', '2024-06-21', 3500.0, 500.0, 500.0, 1, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 1) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-22', '2024-06-27', '2024-07-02', 3500.0, 500.0, 500.0, 1, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 1) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-07-03', '2024-07-08', '2024-07-13', 3500.0, 500.0, 500.0, 1, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 1) ORDER BY RANDOM() LIMIT 1), 1);
--
--
-- INSERT INTO booking (b_booking_date, b_checkin_date, b_checkout_date, b_nightly_price, b_service_fee, b_cleaning_fee, p_id, u_id, bs_id)
-- VALUES
--     ('2024-05-20', '2024-05-25', '2024-05-30', 1500.0, 250.0, 200.0, 2, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 2) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-05-31', '2024-06-05', '2024-06-10', 1500.0, 250.0, 200.0, 2, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 2) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-11', '2024-06-16', '2024-06-21', 1500.0, 250.0, 200.0, 2, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 2) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-22', '2024-06-27', '2024-07-02', 1500.0, 250.0, 200.0, 2, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 2) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-07-03', '2024-07-08', '2024-07-13', 1500.0, 250.0, 200.0, 2, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 2) ORDER BY RANDOM() LIMIT 1), 1);
--
-- INSERT INTO booking (b_booking_date, b_checkin_date, b_checkout_date, b_nightly_price, b_service_fee, b_cleaning_fee, p_id, u_id, bs_id)
-- VALUES
--     ('2024-05-20', '2024-05-25', '2024-05-30', 2500.0, 300.0, 250.0, 3, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 3) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-05-31', '2024-06-05', '2024-06-10', 2500.0, 300.0, 250.0, 3, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 3) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-11', '2024-06-16', '2024-06-21', 2500.0, 300.0, 250.0, 3, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 3) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-22', '2024-06-27', '2024-07-02', 2500.0, 300.0, 250.0, 3, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 3) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-07-03', '2024-07-08', '2024-07-13', 2500.0, 300.0, 250.0, 3, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 3) ORDER BY RANDOM() LIMIT 1), 1);
--
--
-- INSERT INTO booking (b_booking_date, b_checkin_date, b_checkout_date, b_nightly_price, b_service_fee, b_cleaning_fee, p_id, u_id, bs_id)
-- VALUES
--     ('2024-05-20', '2024-05-25', '2024-05-30', 200.0, 50.0, 100.0, 4, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 4) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-05-31', '2024-06-05', '2024-06-10', 200.0, 50.0, 100.0, 4, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 4) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-11', '2024-06-16', '2024-06-21', 200.0, 50.0, 100.0, 4, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 4) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-22', '2024-06-27', '2024-07-02', 200.0, 50.0, 100.0, 4, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 4) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-07-03', '2024-07-08', '2024-07-13', 200.0, 50.0, 100.0, 4, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 4) ORDER BY RANDOM() LIMIT 1), 1);
--
--
-- INSERT INTO booking (b_booking_date, b_checkin_date, b_checkout_date, b_nightly_price, b_service_fee, b_cleaning_fee, p_id, u_id, bs_id)
-- VALUES
--     ('2024-05-20', '2024-05-25', '2024-05-30', 3000.0, 500.0, 100.0, 5, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 5) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-05-31', '2024-06-05', '2024-06-10', 3000.0, 500.0, 100.0, 5, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 5) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-11', '2024-06-16', '2024-06-21', 3000.0, 500.0, 100.0, 5, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 5) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-06-22', '2024-06-27', '2024-07-02', 3000.0, 500.0, 100.0, 5, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 5) ORDER BY RANDOM() LIMIT 1), 1),
--     ('2024-07-03', '2024-07-08', '2024-07-13', 3000.0, 500.0, 100.0, 5, (SELECT u_id FROM user_account WHERE u_id NOT IN (SELECT u_id FROM property WHERE p_id = 5) ORDER BY RANDOM() LIMIT 1), 1);


-- INSERT INTO booking_guests (bg_num_guests, b_id, gt_id)
-- VALUES
--     (1, 1, 1),
--     (1, 2, 1),
--     (1, 3, 1),
--     (1, 4, 1),
--     (1, 5, 1),
--     (1, 6, 1),
--     (1, 7, 1),
--     (1, 8, 1),
--     (1, 9, 1),
--     (1, 10, 1),
--     (1, 11, 1),
--     (1, 12, 1),
--     (1, 13, 1),
--     (1, 14, 1),
--     (1, 15, 1),
--     (1, 16, 1),
--     (1, 17, 1),
--     (1, 18, 1),
--     (1, 19, 1),
--     (1, 20, 1),
--     (1, 21, 1),
--     (1, 22, 1),
--     (1, 23, 1),
--     (1, 24, 1),
--     (1, 25, 1);


INSERT INTO property_availabilities (pav_start_date, pav_end_date, p_id)
VALUES
    ('2024-01-01', '2024-12-31', 1),
    ('2024-01-01', '2024-12-31', 2),
    ('2024-01-01', '2024-12-31', 3),
    ('2024-01-01', '2024-12-31', 4),
    ('2024-01-01', '2024-12-31', 5),
    ('2024-01-01', '2024-12-31', 6),
    ('2024-01-01', '2024-12-31', 7),
    ('2024-01-01', '2024-12-31', 8),
    ('2024-01-01', '2024-12-31', 9),
    ('2024-01-01', '2024-12-31', 10),
    ('2024-01-01', '2024-12-31', 11),
    ('2024-01-01', '2024-12-31', 12),
    ('2024-01-01', '2024-12-31', 13),
    ('2024-01-01', '2024-12-31', 14),
    ('2024-01-01', '2024-12-31', 15),
    ('2024-01-01', '2024-12-31', 16),
    ('2024-01-01', '2024-12-31', 17),
    ('2024-01-01', '2024-12-31', 18),
    ('2024-01-01', '2024-12-31', 19),
    ('2024-01-01', '2024-12-31', 20),
    ('2024-01-01', '2024-12-31', 21),
    ('2024-01-01', '2024-12-31', 22),
    ('2024-01-01', '2024-12-31', 23),
    ('2024-01-01', '2024-12-31', 24),
    ('2024-01-01', '2024-12-31', 25),
    ('2024-01-01', '2024-12-31', 26),
    ('2024-01-01', '2024-12-31', 27),
    ('2024-01-01', '2024-12-31', 28),
    ('2024-01-01', '2024-12-31', 29),
    ('2024-01-01', '2024-12-31', 30);