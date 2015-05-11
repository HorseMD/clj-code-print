# code-print

A simple Clojure script that recursively converts all files in a directory into
Markdown files with code blocks. To be used with something like pandoc, to
create pretty PDFs of your code.

I made this because of University assignments requiring code printouts.

## Usage

(-main) takes one argument: a string for the path to the root dir of your
project.

## TODO

Would be nice if we could parse .*ignore files to ignore files.
