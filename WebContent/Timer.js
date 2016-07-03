function startTimer(duration, display) {
	var timer = duration, minutes, seconds;
	setInterval(function() {
		minutes = parseInt(timer / 60, 10);
		seconds = parseInt(timer % 60, 10);

		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		display.textContent = "Time remaining: " + minutes + ":" + seconds;

		if (--timer < 0) {
			document.getElementById("quiz").submit();
		}
	}, 1000);
}
var timeLimit = 0;
function setTimeLimit(minutes) {
	timeLimit = parseInt(minutes) * 60;
}
window.onload = function() {
	display = document.getElementById('timer');
	if (timeLimit > 0) {
		startTimer(timeLimit, display);
	}
};