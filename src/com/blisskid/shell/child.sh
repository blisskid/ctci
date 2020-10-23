#!/bin/sh
lockfile LOCK
echo "hello" > PIPE
rm -rf LOCK
