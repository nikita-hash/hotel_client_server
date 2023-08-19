create schema hotel;
create table hotel.account(
    `id` int primary key auto_increment,
    `login` varchar(90) not null ,
    `password` varchar(90) not null ,
    `status` varchar(90) not null ,
    `position` varchar(90) not null ,
    `date_registration` varchar(25) not null ,
    `avatar` text not null
);
create table hotel.network(
    `id` int primary key auto_increment,
    `inst` varchar(300) ,
    `VK` varchar(300) ,
    `tg` varchar(300),
    `id_account` int not null ,
    foreign key (id_account)  references account(id)  on delete cascade
);
create table hotel.men(
    `id` int primary key auto_increment,
    `name` varchar(90) not null ,
    `last_name` varchar(90) not null ,
    `patronymic` varchar(90) not null ,
    `phone` varchar(15) not null ,
    `id_account` int not null ,
    foreign key (id_account)  references account(id)  on delete cascade
);
create table hotel.pasport(
    `id` varchar(50) not null ,
    `seria_number` varchar(40) not null ,
    `id_account` int not null ,
    foreign key (id_account)  references account(id)  on delete cascade
);
create table hotel.black_list(
    `id` int auto_increment ,
    `reason` text ,
    `id_account` int not null ,
    `date_ban` varchar(25) not null ,
    foreign key (id_account)  references account(id)  on delete cascade
);
create table hotel.comment(
    `id` int auto_increment primary key ,
    `description` text not null ,
    `id_account` int ,
    `star` int not null ,
    foreign key (id_account)  references account(id)  ON DELETE SET NULL
);
create table hotel.room(
    `id` int auto_increment primary key ,
    `price` double not null ,
    `description` text not null ,
    `status` varchar(100),
    `type` varchar(100) not null
);
create table hotel.photo_room(
    `id` int auto_increment primary key ,
    `id_room` int not null ,
    `photo` longtext not null ,
    foreign key (id_room)  references room(id)  on delete cascade
);
create table hotel.employee(
    `id` int auto_increment primary key ,
    `name` varchar(90) not null ,
    `last_name` varchar(90) not null ,
    `patronymic` varchar(90) not null ,
    `phone` varchar(20) not null ,
    `id_passport` varchar(50) not null ,
    `seria_number` varchar(40) not null ,
    `position` varchar(400) not null ,
    `avatar` longtext not null ,
    `date_registration` varchar(35) not null
);