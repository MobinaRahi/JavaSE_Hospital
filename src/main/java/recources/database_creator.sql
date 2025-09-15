CREATE TABLE DRUGS
(
    ID    NUMBER PRIMARY KEY,
    NAME  NVARCHAR2(100) NOT NULL,
    PRICE NUMBER(10, 2) NOT NULL
);

CREATE SEQUENCE DRUG_SEQ START WITH 1 INCREMENT BY 1;
--

create table medicals
(
    id          number primary key,
    title       nvarchar2(20) not null,
    description nvarchar2(20) not null,
    duration    number not null,
    payment     number not null
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
    patient_id  number,
    visit_id    number,
    payment_id  number,
    employee_id number,

    constraint fk_prescription_patient FOREIGN KEY (patient_id) references patients (id),
    constraint fk_prescription_visit FOREIGN KEY (visit_id) references visits (id),
    constraint fk_prescription_payment FOREIGN KEY (payment_id) references payments (id),
    constraint fk_prescription_employee FOREIGN KEY (employee_id) references employees (id)

);

create sequence prescription_seq start with 1 increment by 1;
--

create table  users
(
    id              number primary key,
    name           nvarchar2(20) not null,
    family         nvarchar2(20) not null,
    birth_date     date          not null,
    role           nvarchar2(20) default 'customer',
    status         number(1)     default 1,
    username       nvarchar2(20) not null,
    password       nvarchar2(20) not null,
    nickname       nvarchar2(20) not null,
    locked         number(1)     default 1,
    register_date  date          not null
);

create sequence person_seq start with 1 increment by 1;
--

create table patients
(
    id              number primary key,
    user_id         number,
    visit_id        number,
    prescription_id number,
    constraint fk_patients_user FOREIGN KEY (user_id) references users (id),
    constraint fk_patients_visit FOREIGN KEY (visit_id) references visits (id),
    constraint fk_patients_prescription FOREIGN KEY (prescription_id) references prescriptions (id)
);

create sequence person_seq start with 1 increment by 1;
--

create table doctors
(
    id         number primary key,
    specialty  nvarchar2(20) not null
);

create sequence person_seq start with 1 increment by 1;
--

create table employees
(
    membername    nvarchar2(20) not null,
    id            number primary key,
    user_id       number,
    start_date      date          not null,
    end_date        date          not null,
    constraint fk_patients_user FOREIGN KEY (user_id) references users (id)
);

create sequence person_seq start with 1 increment by 1;