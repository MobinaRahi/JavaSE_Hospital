

create table medicals
(
    id          number primary key,
    title       nvarchar2(20) not null,
    description nvarchar2(200) not null,
    duration    number not null,
    payment_id  number,
    constraint fk_medical_payment FOREIGN KEY (payment_id) references payments (id)
);

create sequence medical_seq start with 1 increment by 1;


create table time_shifts
(
    id              number primary key,
    doctor_id       number,
    start_date_time date not null,
    end_date_time   date not null,

    constraint fk_time_shift_doctor FOREIGN KEY (doctor_id) references doctors (id)
);

create sequence time_shift_seq start with 1 increment by 1;


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

create table drugs (
                       id number primary key,
                       stock_id number,
                       name nvarchar2(100) not null,
                       price number (10,2),
                       quantity number not null,
                       constraint fk_drug_drug_stock foreign key (stock_id) references drugs_stock (id)
);
create sequence drug_seq start with 1 increment by 1;

create table drugs_stock (
                             id number primary key,
                             drug_count number default 0,
                             lastUpdate timestamp default systimestamp
);
create sequence drug_stock_seq start with 1 increment by 1;





