var count = 1;
function addAnswer() {
	var fieldName = "answer" + count;
	var answerName = "answer" + count;
	$(
			'<textarea class="inputs" required name="' + answerName + '" id="' + fieldName
					+ '" rows="3" cols="30" placeholder="' + fieldName
					+ '" style="display:none;"></textarea><br />').appendTo(
			'#answers').slideDown('fast');
	$('#' + fieldName).focus();
	count += 1;
}

function addChoice() {
	var fieldName = "choice" + count;
	var answerName = "answer" + count;
	var tickName = "tick" + count;
	$(
			'<textarea class="inputs" required name="' + answerName + '" id="' + fieldName
					+ '" rows="3" cols="30" placeholder="' + fieldName
					+ '" style="display:none;"></textarea><br />').appendTo(
			'#answers').slideDown('fast');
	$(
			'<input type="checkbox" name="' + tickName
					+ '" style="display:none;">Correct<br />').appendTo(
			'#answers').slideDown('fast');
	$('#' + fieldName).focus();
	count += 1;
}

function clear() {
	$("#question").html("");
	count = 1;
}
function addBlank() {
	var fieldName = "blank" + count;
	var answerName = "answer" + count;
	$('#questionText').val($('#questionText').val() + ' ' + count + '. ____ ');
	$(
			'<input type="text" required class="inputs" name="' + answerName + '" id="' + fieldName
					+ '" placeholder="' + fieldName
					+ '" style="display:none;"><br />').appendTo('#answers')
			.slideDown('fast');
	$('#questionText').focus();
	count += 1;
}
function checkNum() {
	var a = parseInt($("#answersToShow").val());
	if (a >= count) {
		for (var i = a - count + 1; i > 0; i = i - 1) {
			addChoice();
		}
	}
}
function checkNumReq() {
	var a = parseInt($("#answersToShow").val());
	var b = parseInt($("#answersRequired").val());
	if (b >= count) {
		for (var i = b - count + 1; i > 0; i = i - 1) {
			addChoice();
		}
	}
	if(b > a){
		$('#answersToShow').val(b);
	}
}

function addQuestion() {
	clear();
	var choice = $("#type").val();
	switch (choice) {
	case 'Question-Response':
		$(
				'<br><textarea class="inputs" name="question" id="questionText" placeholder="Type your question here..." rows="3" cols="35" style="display:none;" autofocus required></textarea>'
						+ '<br><textarea class="inputs" name="description" style="display:none;" placeholder="Enter additional description" rows="3" cols="35"></textarea>'
						+ '<br><input type="number" min="0" max="100" style="width:40px; display:none;" maxlength=3 name="score" value=0>Score'
						+ '<div id="answers"></div>'
						+ '<button type="button" onclick="addAnswer()" style="display:none;">Create Answer</button><br> ')
				.appendTo('#question').slideDown('slow');
		break;
	case 'Fill in the Blank':
		$(
				'<br><textarea class="inputs" name="question" id="questionText" placeholder="Type your question here..." rows="3" cols="35" style="display:none;" autofocus required></textarea>'
						+ '<br><textarea class="inputs" name="description" style="display:none;" placeholder="Enter additional description" rows="3" cols="35"></textarea>'
						+ '<br><input type="number" min="0" max="100" style="width:40px; display:none;" maxlength=3 name="score" value=0>Score'
						+ '<div id="answers"></div>'
						+ '<button type="button" onclick="addBlank()" style="display:none;">Blank</button><br> ')
				.appendTo('#question').slideDown('slow');
		break;
	case 'Multiple Choice with Multiple Answers':
		$(
				'<br><textarea class="inputs" name="question" id="questionText" placeholder="Type your question here..." rows="3" cols="35" style="display:none;" autofocus required></textarea>'
						+ '<br><textarea class="inputs" name="description" style="display:none;" placeholder="Enter additional description" rows="3" cols="35"></textarea>'
						+ '<br><input type="number" min="0" max="100" style="width:40px; display:none;" maxlength=3 name="score" value=0>Score'
						+ '<br><input type="number" min="0" max="100" id="answersToShow" name="answersToShow" style="width:40px; display:none;" value=0 onchange="checkNum()"> Number of Choices'
						+ '<br><input type="number" min="0" max="100" id="answersRequired" name="correctNeeded" style="width:40px; display:none;" value=0 onchange="checkNumReq()"> Correct Answers Required'
						+ '<br><div id="answers"></div>'
						+ '<button type="button" onclick="addChoice()" style="display:none;">Add answer</button><br>')
				.appendTo('#question').slideDown('slow');
		break;
	case 'Question with Multiple Answers':
		$(
				'<br><textarea class="inputs" name="question" id="questionText" placeholder="Type your question here..." rows="3" cols="35" style="display:none;" autofocus required></textarea>'
						+ '<br><textarea class="inputs" name="description" style="display:none;" placeholder="Enter additional description" rows="3" cols="35"></textarea>'
						+ '<br><select name="order"> <option value="Ordered">Ordered</option><option value="Unordered">Unordered</option></select>'
						+ '<br><input type="number" id="answersToShow" value=0 min="0" max="15" required style="width:40px; display:none;" maxlength=3 name="numAnswers" onchange="checkNum()">Answers required'
						+ '<br><input type="number" min="0" max="100" style="width:40px; display:none;" maxlength=3 name="score" value=0>Score'
						+ '<div id="answers"></div>'
						+ '<button type="button" onclick="addAnswer()" style="display:none;">Create Answer</button><br> ')
				.appendTo('#question').slideDown('slow');
		break;
	}
	$('<input type="submit" value="Next Question" style="display:none;">')
			.appendTo('#question').slideDown('slow');
}
