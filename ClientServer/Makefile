JAVADIR=
JARNAME=client

all:
	(cd src; javac -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cf0 ../$(JARNAME).jar `find . -name \*.class -o -name \*.java`)

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class` `find . -name .DS_Store`
