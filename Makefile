##Property
SRC_FILES = $(shell find src/main/java src/test/java -name '*.java')
TESTS=$(subst /,.,$(shell find out -name '*Test.class'))
CLASSPATH= lib/junit4.10.jar:lib/org.hamcrest.core_1.1.0.v20090501071000.jar
CLASSPATH_ARG = -classpath ${CLASSPATH}

##ACTION
all: tests

detab: cleanbaks
	@find . -name '*.javax' -o -name '*.java' | while read f;\
		do echo "Coverting tabs in $$f"; \
		expand -t 2 $ff.bak > $$f; \
	done

cleanbaks:
	@find . -name '*.bak' | while read f; do rm $$f; done

tests: compile
	@for t in $(subst .class,,$(subst out.,,${TESTS})); do echo "Running $$t"; \
		java -ea ${CLASSPATH_ARG}:out org.junit.runner.JUnitCore $$t; \
		test $$? -eq 0 || exit 1; \
	done

clean: cleanbaks
	rm -rf out

compile: clean out
	javac ${CLASSPATH_ARG} -Xlint:unchecked -d out -sourcepath src -sourcepath test ${SRC_FILES}

out:
	mkdir -p out
