create table events
(
    id bigint auto_increment primary key unique ,
    event_name varchar(15) null,
    file_id bigint not null,
    user_id bigint not null,
    FOREIGN KEY (file_id) references files (id),
    FOREIGN KEY (user_id) references users (id)
);

create table files
(
    id bigint auto_increment primary key unique ,
    name varchar(15) not null ,
    user_id bigint not null ,
    FOREIGN KEY (user_id) references users (id)
);

create table users
(
    id bigint auto_increment primary key unique ,
    username varchar(50) not null,
    password varchar(25) not null
);

