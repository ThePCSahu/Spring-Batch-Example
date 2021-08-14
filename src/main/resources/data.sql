drop table if exists account_entity CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table account_entity (
       account_number bigint not null,
        account_holder varchar(255),
        balance double not null,
        primary key (account_number));
        
INSERT INTO account_entity (account_number,account_holder, balance) values (hibernate_sequence.nextval,'Poonam',120.5),(hibernate_sequence.nextval,'Raj',152.5),(hibernate_sequence.nextval,'Sumit',125), (hibernate_sequence.nextval,'James',1976),(hibernate_sequence.nextval,'Ronny',437),(hibernate_sequence.nextval,'Full Stack DEveloper',654),(hibernate_sequence.nextval,'Half Stack Developer',673.5);