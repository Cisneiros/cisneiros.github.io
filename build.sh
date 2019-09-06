#!/usr/bin/env bash

set -xe

IMAGE_NAME='cisneiros/cisne.dev'
M2_LOCATION='/root/.m2'
NODE_MODULES_LOCATION='/app/node_modules'
OUT_LOCATION='/app/target/production'

# Fetch dependencies and populate cache

docker build --target deps -t ${IMAGE_NAME}:deps .
docker run --volume=${PWD}/.cache/m2:${M2_LOCATION} --volume=${PWD}/.cache/node_modules:${NODE_MODULES_LOCATION} ${IMAGE_NAME}:deps

# Run tests

docker build --target test -t ${IMAGE_NAME}:test .
docker run --rm -it cisneiros/cisne.dev:test

# Generate production build

docker build --target build -t ${IMAGE_NAME}:build .
docker run --rm -it --volume=${PWD}/out:${OUT_LOCATION} cisneiros/cisne.dev:build
