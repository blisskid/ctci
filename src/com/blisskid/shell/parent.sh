#!/bin/sh
mkfifo PIPE
for i in {1..3}; do
  sh child.sh &
done

# wait for n responses
for i in {1..3}; do
  read response < PIPE
  echo $response
done
