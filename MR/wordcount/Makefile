TARGET=WordCount

LOCALINPUTFILE=./sampleArchive.txt
INPUTDIR=/user/ubuntu/examples/
INPUTFILE=sampleArchive.txt
OUTPUTDIR=${INPUTDIR}/wordcount/MR/output
MAINCLASS=${TARGET}

${TARGET}.jar:${TARGET}.class
	jar cf ${TARGET}.jar ${TARGET}*.class

${TARGET}.class:${TARGET}.java
	hadoop com.sun.tools.javac.Main ${TARGET}.java

run: ${TARGET}.jar cleanoutput uploadinputtohdfs
	hadoop jar ${TARGET}.jar ${MAINCLASS} ${SEARCHSTRING} ${INPUTDIR}${INPUTFILE} ${OUTPUTDIR}

runbg: ${TARGET}.jar cleanoutput
	hadoop jar ${TARGET}.jar ${MAINCLASS} ${SEARCHSTRING} ${INPUTDIR}${INPUTFILE} ${OUTPUTDIR} &>std_out.txt&

uploadinputtohdfs: ${LOCALINPUTFILE}
	hdfs dfs -mkdir -p ${INPUTDIR}
	-hdfs dfs -put ${LOCALINPUTFILE} ${INPUTDIR}${INPUTFILE}

showoutput:
	hdfs dfs -cat ${OUTPUTDIR}/part-r-00000

cleanoutput:
	-hdfs dfs -rm -R ${OUTPUTDIR}

clean:
	@if [ -f ${TARGET}.class ];then rm ${TARGET}.class;echo rm ${TARGET}.class;fi;
	@if [ -f ${TARGET}.jar ];then rm ${TARGET}.jar;echo rm ${TARGET}.jar;fi;
	-rm *.class

help:
	@echo Targets:
	@echo   <no target>: builds the jar file
	@echo   run: runs the hadoop job in the forground
	@echo   runbg: runs the hadoop job in the background and redirects stdout and 
	@echo     std error to std_out.txt file
	@echo   clean: cleans up build files for a fresh rebuild
