create table QuestionTypes(
	id int primary key auto_increment,
    type_name varchar(100) not null
);


-- @@@ default question !!! not used in final version
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
-- xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx


-- @@ question response
create table Questions_QuestionResponse(
	question_id int auto_increment primary key,
    quiz_id int not null,
    question_text varchar(500),
    question_data varchar(500),
--  question_type int not null,
--  question_description varchar(500) default null,
    question_time_limit int default -1,
    
    score int,
    
    foreign key (quiz_id) references Quizzes(quiz_id)
);

create table Answers_QuestionResponse(
	answer_id int auto_increment primary key,
	answer_text varchar(500),
--  answer_description varchar(500),
--  answer_correct bool not null,
-- 	answer_type varchar(100) not null,

	question_id int not null,
    
	foreign key (question_id) references Questions_QuestionResponse(question_id)
);


-- @@@ multiple choice
create table Questions_MultipleChoice(
	question_id int auto_increment primary key,
    quiz_id int not null,
    question_text varchar(500),
    question_data varchar(500),
--  question_type int not null,
--  question_description varchar(500) default null,
    question_time_limit int default -1,
    
    num_answers_display int default 4,
    num_answers_correct int default 1,
    
    score int,
    
    foreign key (quiz_id) references Quizzes(quiz_id)
);

create table Answers_MultipleChoice(
	answer_id int auto_increment primary key,
	answer_text varchar(500),
--  answer_description varchar(500),
	answer_correct bool not null,
-- 	answer_type varchar(100) not null,

	question_id int not null,
    
	foreign key (question_id) references Questions_MultipleChoice(question_id)
);


-- @@@ fill in the blank
create table Questions_FillInTheBlanks(
	question_id int auto_increment primary key,
    quiz_id int not null,
    question_text varchar(500),
    question_data varchar(500),
--  question_type int not null,
--  question_description varchar(500) default null,
    question_time_limit int default -1,
    
    
    score int,
    
    foreign key (quiz_id) references Quizzes(quiz_id)
);

create table Answers_FillInTheBlanks(
	answer_id int auto_increment primary key,
	answer_text varchar(500),
--  answer_description varchar(500),
-- 	answer_correct bool not null,
-- 	answer_type varchar(100) not null,

	blank_pos int not null,

	question_id int not null,
    
	foreign key (question_id) references Questions_FillInTheBlanks(question_id)
);

-- @@@ multiple answer
create table Questions_MultipleAnswers(
	question_id int auto_increment primary key,
    quiz_id int not null,
    question_text varchar(500),
    question_data varchar(500),
--  question_type int not null,
--  question_description varchar(500) default null,
    question_time_limit int default -1,
    
    answers_ordered tinyint default 0,
    
    num_answers int not null,
    
    score int,
    
    foreign key (quiz_id) references Quizzes(quiz_id)
);

create table Answers_MultipleAnswers(
	answer_id int auto_increment primary key,
	answer_text varchar(500),
--  answer_description varchar(500),
-- 	answer_correct bool not null,
-- 	answer_type varchar(100) not null,

	answer_num int not null,

	question_id int not null,
    
	foreign key (question_id) references Questions_MultipleAnswers(question_id)
);