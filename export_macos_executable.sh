#!/bin/bash

rm -rf out-mac
gradle lwjgl:jar
java -jar packr-all-4.0.0.jar export_mac.json