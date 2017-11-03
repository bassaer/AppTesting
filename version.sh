#!/bin/sh

VERSION=`cat version.properties | cut -d'=' -f2`

sed -i "" "/compile/s/[0-9]*\.[0-9]*\.[0-9]*/$VERSION/" README.md

