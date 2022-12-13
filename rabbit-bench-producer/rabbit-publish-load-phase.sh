#!/bin/bash

curl "localhost:18080/publish?messagesPerThread=1000&threadCount=10&reportFrequency=50"
echo "Load phase done"
