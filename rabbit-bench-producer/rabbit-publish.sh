#!/bin/bash

echo
echo Starting load phase...
$(./rabbit-publish-load-phase.sh)
echo
echo "Load phase done"
echo
read -t 120 -p "Waiting for 2 minutes ..."

echo
echo Starting stress phase...
$(./rabbit-publish-stress-phase.sh)
echo
echo "Stress phase done"
echo
