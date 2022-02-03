#!/bin/bash -e

sbt -Dbrowser=remote-chrome -Denvironment=dev test
