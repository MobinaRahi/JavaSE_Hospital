create table users
(
    id            number primary key,
    name          nvarchar2(20) not null,
    family        nvarchar2(20) not null,
    birth_date    date          not null,
    role          nvarchar2(20) default 'PATIENT',
    status        number(1)     default 1,
    username      nvarchar2(20) not null UNIQUE,
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
    user_id   number,
    specialty nvarchar2(20) not null,
    price     number,
    constraint fk_doctor_user FOREIGN KEY (user_id) references users (id)
);

create sequence doctor_seq start with 1 increment by 1;
--


create table employees
(
    id         number primary key,
    user_id    number,
    start_time date not null,
    end_time   date not null,
    constraint fk_employee_user FOREIGN KEY (user_id) references users (id)
);

create sequence employee_seq start with 1 increment by 1;
--


create table payments
(
    id            number primary key,
    pay_type      nvarchar2(20) default 'CASH',
    pay_date_time date   not null,
    price         number not null,
    payable       number not null

);
create sequence payment_seq start with 1 increment by 1;
--


create table patients
(
    id      number primary key,
    user_id number,
    constraint fk_patients_user FOREIGN KEY (user_id) references users (id)
);

create sequence patient_seq start with 1 increment by 1;
--


create table medicals
(
    id          number primary key,
    title       nvarchar2(20) not null,
    description nvarchar2(20) not null,
    duration    number         not null,
    doctor_id   number        not null,
    price       number        not null,
    constraint fk_medical_doctor FOREIGN KEY (doctor_id) references doctors (id)
);

create sequence medical_seq start with 1 increment by 1;
--


create table time_shifts
(
    id              number primary key,
    doctor_id       number,
    medical_id      number,
    start_date_time date not null,
    end_date_time   date not null,
    constraint fk_time_shift_doctor FOREIGN KEY (doctor_id) references doctors (id)
);

create sequence time_shift_seq start with 1 increment by 1;
--


create table visits
(
    id            number primary key,
    doctor_id     number,
    patient_id    number,
    time_shift_id number,
    price         number not null,
    constraint fk_visit_doctor FOREIGN KEY (doctor_id) references doctors (id),
    constraint fk_visit_patient FOREIGN KEY (patient_id) references patients (id),
    constraint fk_visit_time_shift FOREIGN KEY (time_shift_id) references time_shifts (id)
);

create sequence visit_seq start with 1 increment by 1;
--


create table prescriptions
(
    id         number primary key,
    visit_id   number,
    payment_id number,
    price      number,
    constraint fk_prescription_visit FOREIGN KEY (visit_id) references visits (id),
    constraint fk_prescription_payment FOREIGN KEY (payment_id) references payments (id)
);

create sequence prescription_seq start with 1 increment by 1;
--


create table drugs
(
    id       number primary key,
    name     nvarchar2(100) not null,
    price    number,
    quantity number         not null
);
create sequence drug_seq start with 1 increment by 1;
--


create table drugs_stock
(
    id          number primary key,
    drug_id     number,
    drug_count  number    default 0,
    last_update timestamp default systimestamp,
    constraint fk_drug_stock_drug foreign key (drug_id) references drugs (id)
);
create sequence drug_stock_seq start with 1 increment by 1;
--


CREATE TABLE cash_desks
(
    id           NUMBER PRIMARY KEY,
    bank_id      number ,
    payType      nvarchar2(20) default 'CASH',
    constraint fk_bank_cash_desk foreign key (bank_id) references banks (id)
);

CREATE SEQUENCE cash_desk_seq START WITH 1 INCREMENT BY 1;

--

CREATE TABLE banks
(
    id    NUMBER PRIMARY KEY,
    title NVARCHAR2(100) NOT NULL
);

CREATE SEQUENCE bank_seq START WITH 1 INCREMENT BY 1;

