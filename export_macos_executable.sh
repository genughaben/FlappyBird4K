#!/bin/bash

sudo rm -rf out-mac
gradle lwjgl:dist
gradle lwjgl:jar
java -jar packr-all-4.0.0.jar export_mac.json