#!/bin/bash

curl "localhost:8080/publish?messageCount=1000&threadCount=10&reportFrequency=50"
echo Load phase done
