/*
Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)

HH = hours, padded to 2 digits, range: 00 - 99
MM = minutes, padded to 2 digits, range: 00 - 59
SS = seconds, padded to 2 digits, range: 00 - 59
The maximum time never exceeds 359999 (99:59:59)
 */
function humanReadable(seconds) {
  if (seconds >= 359999) return "99:59:59";
  let hours = 0,
    minutes = 0;

  while (seconds >= 60) {
    minutes++;
    seconds -= 60;
  }

  while (minutes >= 60) {
    hours++;
    minutes -= 60;
  }

  if (hours < 10) hours = "0" + hours;
  if (minutes < 10) minutes = "0" + minutes;
  if (seconds < 10) seconds = "0" + seconds;

  return `${hours}:${minutes}:${seconds}`;
}
