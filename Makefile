.PHONY: all
.PHONY: clean
.PHONY: run

all : Labirinto.class Tela.class
clean :
	rm -f *.class
run : all
	java Labirinto

%.class : %.java
	javac -source 1.6 $<