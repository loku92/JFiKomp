#!/bin/sh
java -cp antlr-4.2.2-complete.jar:output:. Main $1 > $1.ll
lli-3.4 $1.ll
