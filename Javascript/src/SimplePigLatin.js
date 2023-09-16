/*
Move the first letter of each word to the end of it, then add "ay" to the end of the word. Leave punctuation marks untouched.

Examples
pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
pigIt('Hello world !');     // elloHay orldway !
 */

function pigIt(s) {
  let result = s
    .split(" ")
    .map((e) => e.substring(1) + e[0] + "ay")
    .join(" ");
  if (
    result.charAt(result.length - 3) === "!" ||
    result.charAt(result.length - 3) === "?"
  ) {
    return result.slice(0, result.length - 2);
  }
  return result;
}
