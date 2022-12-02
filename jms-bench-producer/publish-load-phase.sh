#!/bin/bash

curl "localhost:8080/publish?messagesPerThread=1000&threadCount=10&reportFrequency=50"
echo Load phase done
