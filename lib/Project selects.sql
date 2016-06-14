create table Users ( 
	user_id int not null auto_increment primary key, 
    user_login varchar(50) not null unique,
	user_password varchar(100) not null,
    user_name varchar(50) not null,
    user_profile_image varchar(1000)
);

create table Quizzes (
	quiz_id int auto_increment primary key,
    quiz_name varchar(50) not null,
    category_id int not null,
    quiz_description varchar(1000),
    author_id int not null,
    quiz_likes int default 0,
    date_created timestamp not null,
    quiz_difficulty varchar(50),
    times_taken int default 0
);

create table QuestionTypes(
	type_id int not null auto_increment primary key,
    type_name varchar(50)
);

insert into QuestionTypes(type_name) values
	('Question-Response'),
    ('Fill in the Blank'),
    ('Multiple Choice'),
    ('Picture-Response Questions');
    

create table Categories(
	quiz_category int not null auto_increment primary key,
    category_name varchar(50) not null
);

insert into Categories(category_name) values 
	('History'),
    ('Biology'),
    ('Math');

create table Questions(
	question_id int auto_increment primary key,
    quiz_id long not null,
    question_text varchar(500),
    question_type varchar(50) not null,
    question_description varchar(500),
    question_time_limit long default -1,
	
 
	foreign key (quiz_id) references Quizzes(quiz_id)
);

create table Answers(
	answer_id long auto_increment primary key,
	quiz_id long not null,
	answer_text varchar(500),
    answer_description varchar(500),
    answer_correct bool not null,
	answer_type varchar(100) not null,
	question_id long not null
    /*foreign key (quiz_id) references Quizzes(quiz_id)*/
);

-- Friend system

create table Friends(
	`from` int not null,
	`to` int not null,
	
	foreign key (`from`) references Users(user_id),
	foreign key (`to`) references Users(user_id)
);

create table FriendshipRequests(
	friendhip_request_id int not null auto_increment primary key,
	`from` int not null,
	`to` int not null,
	
	foreign key (`from`) references Users(user_id),
	foreign key (`to`) references Users(user_id)
);
-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

-- Messaging system
create table Messages(
	message_id int primary key auto_increment,
	sender_id int not null,
	recipient_id int not null,
	message_text varchar(1000),
	message_subject varchar(500),
-- 	message_seen int(1) default 0, 
	
	foreign key (sender_id) references Users(user_id),
	foreign key (recipient_id) references Users(user_id)

)
-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

SET SQL_SAFE_UPDATES=0; 

insert into Users(user_login, user_password, user_name) values 
	('faskunji1', 'afasljflasjf', 'levan goderdzishvili');
    
select * from users