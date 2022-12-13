#!/bin/bash

echo
echo Starting load phase...
$(.publish-load-phase.sh)
echo
echo "Load phase done"
echo
read -t 120 -p "Waiting for 2 minutes ..."

echo
echo Starting stress phase...
$(.publish-stress-phase.sh)
echo
echo "Stress phase done"
echo
