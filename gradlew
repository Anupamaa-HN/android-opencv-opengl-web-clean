#!/usr/bin/env sh

DIR="$(cd "$(dirname "$0")" && pwd)"
APP_ARGS="$@"
exec "$DIR/gradle/wrapper/gradle-wrapper.jar" $APP_ARGS
