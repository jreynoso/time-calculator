## Objecttive

Without using any built-in date or time functions, write a function or method that accepts two
mandatory arguments: the first argument is a 12-hour time string with the format "[H]H:MM
{AM|PM}", and the second argument is a (signed) integer. The second argument is the number of
minutes to add to the time of day represented by the first argument. The return value should be a
string of the same format as the first argument. For example, AddMinutes("9:13 AM", 200) would
return "12:33 PM". The exercise isn't meant to be too hard or take very long; we just want to see
how you code. Use any mainstream language you want, though Java and Scala are particularly
relevant to us. Please include any test cases that you write.

## Comments
Perhaps not the most *clever* implementation, but it was simple for me to reason about.
The basic idea is to convert the 12-hour format to 24-hour, do some simple math based on the minutes offset,
and then convert back to a 12-hour formatted string.