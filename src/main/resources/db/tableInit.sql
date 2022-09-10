drop table if exists Book;
create table Book (
    id bigint auto_increment,
    author varchar(20) not null,
    title varchar(50) not null ,
    primary key (id)
)