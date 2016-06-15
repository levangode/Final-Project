SET SQL_SAFE_UPDATES=0;

create table Users ( 
	user_id int not null auto_increment primary key, 
    user_login varchar(50) not null unique,
	user_password varchar(100) not null,
    user_name varchar(50) not null,
    user_profile_image varchar(1000)
);

create table Categories(
	category_id int auto_increment primary key,
    category_name varchar(50) not null
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
    times_taken int default 0,
    multiple_pages boolean default false,
    immediate_correction boolean default 0,
    random_questions boolean default 0,
    
    foreign key (author_id) references Users(user_id),
    foreign key (category_id) references Categories(category_id)
    
);



create table Quiz_edit(
	user_id int not null,
	quiz_id int not null,
	quiz_edited tinyint(1) default null,
	quiz_edit_date timestamp,
	
	foreign key (user_id) references Users(user_id),
	foreign key (quiz_id) references Quizzes(quiz_id)	
);

create table Quiz_taken(
	user_id int not null,
	quiz_id int not null,
	
	time_finished timestamp,
	time_taken timestamp,
	
	score int not null,
	
	foreign key (user_id) references Users(user_id),
	foreign key (quiz_id) references Quizzes(quiz_id)
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
    



insert into Categories(category_name) values 
	('History'),
    ('Biology'),
    ('Math');

create table Questions(
	question_id int auto_increment primary key,
    quiz_id int not null,
    question_text varchar(500),
    question_type int not null,
    question_description varchar(500),
    question_time_limit int default -1,
	
 
	foreign key (quiz_id) references Quizzes(quiz_id)
);

create table Answers(
	answer_id int auto_increment primary key,
	answer_text varchar(500),
    answer_description varchar(500),
    answer_correct bool not null,
-- 	answer_type varchar(100) not null,

	question_id int not null,
    
	foreign key (question_id) references Questions(question_id)
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
	date_sent timestamp,
-- 	message_seen int(1) default 0, 
	
	foreign key (sender_id) references Users(user_id),
	foreign key (recipient_id) references Users(user_id)

)
-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx



insert into Users(user_login, user_password, user_name) values 
	('faskunji1', 'afasljflasjf', 'levan goderdzishvili');
    
select * from users;

-- Caution remove scripts

drop table Answers;
drop table Friends;
drop table FriendshipRequests;
drop table Questions;
drop table QuestionTypes;
drop table Quizzes;
drop table Categories;
drop table Users;

-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxx