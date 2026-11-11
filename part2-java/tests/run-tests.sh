#!/bin/bash
# Compiles the program and runs every tests/*.txt file as input.
# The output of each run is saved in tests/output/<name>.out
# Run it from anywhere:  bash part2-java/tests/run-tests.sh

cd "$(dirname "$0")/.." || exit 1
mkdir -p out tests/output

javac -d out $(find src/main/java -name '*.java') || exit 1

for input in tests/*.txt; do
    name=$(basename "$input" .txt)
    java -cp out com.sait.enrollment.Main < "$input" > "tests/output/$name.out"
    echo "ran $name -> tests/output/$name.out"
done
