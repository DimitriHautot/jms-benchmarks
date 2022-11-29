#!/bin/bash
# Basic while loop
counter=1
while [ $counter -le 10 ]
do
echo $counter
((counter++))
curl "localhost:8080/temp/jms?count=2000"
done
echo All done

