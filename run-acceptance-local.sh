#!/bin/bash -e

sbt -Dbrowser=chrome -Denvironment=local test
