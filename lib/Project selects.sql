create table Users ( 
	user_id int not null auto_increment primary key, 
    user_login varchar(50) not null,
	user_password varchar(100) not null,
    user_name varchar(50) not null
);

create table Quizzes (
	quiz_id int not null auto_increment primary key,
    quiz_name varchar(50) not null,
    quiz_description varchar(1000),
    author_id int not null,
    quiz_likes int default 0,
    date_created timestamp not null,
    quiz_difficulty varchar(50),
    times_taken int default 0
);

create table Questions(
	question_id int not null auto_increment primary key,
    quiz_id int not null,
    question_text varchar(500),
    question_type varchar(50) not null,
    question_description varchar(500),
    question_time_limit int default -1,
  
	foreign key (quiz_id) references Quizzes(quiz_id)
);

create table Answers(
	answer_id int not null auto_increment primary key,
	quiz_id int not null,
	answer_text varchar(500),
    answer_description varchar(500),
    answer_correct bool not null,
<<<<<<< HEAD
    
    foreign key (quiz_id) references Quizzes(quiz_id)
=======
    answer_type varchar(100) not null,
    foreign key (quiz_id) references Quizes(quiz_id)
>>>>>>> origin/master
    
);



insert into Users(user_login, user_password, user_name) values 
	('faskunji1', 'afasljflasjf', 'levan goderdzishvili');
    
select * from users