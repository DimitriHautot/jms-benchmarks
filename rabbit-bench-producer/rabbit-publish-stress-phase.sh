#!/bin/bash

curl "localhost:18080/publish?messagesPerThread=10000&threadCount=10&reportFrequency=50"
echo "Stress phase done"
