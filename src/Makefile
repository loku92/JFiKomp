
ANTLR=antlr-4.2.2-complete.jar

all: generate compile

generate:
	java -jar $(ANTLR) -o output Gramatyka.g4

compile:
	javac -cp $(ANTLR):output:. Main.java

test:
	java -cp $(ANTLR):output:. Main test.x > test.ll
	lli-3.4 test.ll

clean:
	rm *.class
	rm -rf output

