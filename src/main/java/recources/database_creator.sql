create table users
(
    id            number primary key,
    name          nvarchar2(20) not null,
    family        nvarchar2(20) not null,
    birth_date    date          not null,
    role          nvarchar2(20) default 'customer',
    status        number(1)     default 1,
    username      nvarchar2(20) not null unique ,
    password      nvarchar2(20) not null,
    nickname      nvarchar2(20) not null,
    locked        number(1)     default 1,
    register_date date          not null
);

create sequence user_seq start with 1 increment by 1;
--


create table doctors
(
    id        number primary key,
    specialty nvarchar2(20) not null,
    price     number
);

create sequence doctor_seq start with 1 increment by 1;
--


create table employees
(
    membername nvarchar2(20) not null,
    id         number primary key,
    user_id    number,
    start_date date          not null,
    end_date   date          not null,
    constraint fk_employees_user FOREIGN KEY (user_id) references users (id)
);

create sequence employee_seq start with 1 increment by 1;
--


create table payments
(
    id number primary key
);

create sequence payment_seq start with 1 increment by 1;
--


create table drugs_stock
(
    id          number primary key,
    drug_count  number    default 0,
    last_update timestamp default systimestamp
);
create sequence drug_stock_seq start with 1 increment by 1;
--


create table patients
(
    id              number primary key,
    user_id         number,
    constraint fk_patient_user FOREIGN KEY (user_id) references users (id)
);

create sequence patient_seq start with 1 increment by 1;
--


create table medicals
(
    id          number primary key,
    title       nvarchar2(20) not null,
    description nvarchar2(20) not null,
    duration    number        not null,
    payment_id     number        not null,
    constraint fk_medical_payment FOREIGN KEY (payment_id) references payments (id)

);

create sequence medical_seq start with 1 increment by 1;
--


create table time_shifts
(
    id              number primary key,
    doctor_id       number,
    start_date_time date not null,
    end_date_time   date not null,
    constraint fk_time_shift_doctor FOREIGN KEY (doctor_id) references doctors (id)
);

create sequence time_shift_seq start with 1 increment by 1;
--


create table visits
(
    id         number primary key,
    doctor_id  number,
    patient_id number,
    payment_id number,
    constraint fk_visit_doctor FOREIGN KEY (doctor_id) references doctors (id),
    constraint fk_visit_patient FOREIGN KEY (patient_id) references patients (id),
    constraint fk_visit_payment FOREIGN KEY (payment_id) references payments (id)
);

create sequence visit_seq start with 1 increment by 1;
--


create table prescriptions
(
    id          number primary key,
    visit_id    number,
    payment_id  number,
    constraint fk_prescription_visit FOREIGN KEY (visit_id) references visits (id),
    constraint fk_prescription_payment FOREIGN KEY (payment_id) references payments (id)

);

create sequence prescription_seq start with 1 increment by 1;
--


CREATE TABLE drugs
(
    id       number primary key,
    stock_id number,
    name     nvarchar2(100) not null,
    price    number,
    quantity number         not null,
    constraint fk_drug_drug_stock foreign key (stock_id) references drugs_stock (id)
);
create sequence drug_seq start with 1 increment by 1;
--



