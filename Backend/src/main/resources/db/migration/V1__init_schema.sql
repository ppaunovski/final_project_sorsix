create table user_account (
    u_id bigserial primary key ,
    u_first_name text not null ,
    u_last_name text not null ,
    u_email text not null ,
    u_password text not null ,
    u_joined_date date not null,
    u_date_host_started date
);

create table city (
    c_id bigserial primary key ,
    c_name text not null
);

create table property_type (
    pt_id bigserial primary key ,
    pt_type_name text not null
);


create table attribute (
    a_id bigserial primary key ,
    a_name text not null ,
    a_description text not null
);


create table review_component (
    rc_id bigserial primary key ,
    rc_component_name text not null
);

create table property (
    p_id bigserial primary key ,
    p_nightly_price double precision not null ,
    p_property_name text not null ,
    p_num_guests int not null ,
    p_num_beds int not null ,
    p_num_bedrooms int not null ,
    p_num_bathrooms int not null ,
    p_is_guest_favorite boolean not null ,
    p_description text not null,
    p_address text not null ,
    p_longitude double precision not null ,
    p_latitude double precision not null ,
    u_id bigint references user_account(u_id),
    c_id bigint references city(c_id),
    pt_id bigint references property_type(pt_id)
);

create table property_attribute (
    pa_id bigserial primary key ,
    p_id bigint references property(p_id),
    a_id bigint references attribute(a_id)
);

create table property_images (
    pi_id bigserial primary key ,
    p_id bigint references property(p_id),
    pi_order int not null,
    pi_image bytea not null
);

create table user_review (
    ur_id bigserial primary key ,
    ur_comment text,
    ur_review_date date not null ,
    u_id bigint references user_account(u_id),
    p_id bigint references property(p_id)
);

create table component_rating (
    cr_id bigserial primary key ,
    cr_rating int not null ,
    ur_id bigint references user_review(ur_id),
    rc_id bigint references review_component(rc_id)
);

create table favorite (
    f_id bigserial primary key ,
    p_id bigint references property(p_id),
    u_id bigint references user_account(u_id)
);

create table booking_status
(
    bs_id          bigserial primary key,
    bs_status_name text not null
);

create table guest_type (
    gt_id bigserial primary key ,
    gt_type_name text not null,
    gt_description text not null
);

create table booking (
    b_id bigserial primary key ,
    b_checkin_date date not null ,
    b_checkout_date date not null ,
    b_nightly_price double precision not null ,
    b_service_fee double precision not null ,
    b_cleaning_fee double precision not null ,
    p_id bigint references property(p_id),
    u_id bigint references user_account(u_id),
    bs_id bigint references booking_status(bs_id)
);

create table booking_guests (
    bg_id bigserial primary key,
    bg_num_guests int not null,
    b_id bigint references booking(b_id),
    gt_id bigint references guest_type(gt_id)
);