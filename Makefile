.PHONY: all
.PHONY: clean
.PHONY: run

all : app.class grafo.class tela.class var.class
clean :
	rm -f *.class
run : all
	java app

%.class : %.java
	javac -source 1.6 $<