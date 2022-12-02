#!/bin/bash

curl "localhost:8080/publish?messagesPerThread=100000&threadCount=10&reportFrequency=50"
echo Stress phase done
