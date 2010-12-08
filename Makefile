.PHONY: all
.PHONY: clean
.PHONY: mrproper
.PHONY: run

all: prog doc

doc: relatorio.pdf

prog : app.class grafo.class tela.class var.class

clean :
	rm -f *.class *.log *.aux

mrproper: clean
	rm -f *.pdf

run : prog
	java app

lap : prog
	java app lap

mini : prog
	java app mini

%.class : %.java
	javac -source 1.6 $<

%.pdf: %.tex
	pdflatex $^