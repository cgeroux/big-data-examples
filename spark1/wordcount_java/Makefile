TARGET=WordCount

LOCALINPUTFILE=../../sampleArchive.txt
INPUTDIR=/user/ubuntu/examples/
INPUTFILE=sampleArchive.txt
OUTPUTDIR=${INPUTDIR}/wordcount/spark/java/output
MAINCLASS=${TARGET}


#Settings for how to run the job:
#see https://spark.apache.org/docs/1.2.1/submitting-applications.html 
#for more details
NUM_CORES=3

#run using yarn schedualar
MASTER=yarn

#run using yarn schedualar in cluster mode
#MASTER=yarn-cluster

#run using yarn schedualar in cluster mode
#MASTER=yarn-client

#run on local machine with given number of cores
#MASTER=local[${NUM_CORES]

#run on spark standalone cluster
#MASTER=spark://master:7077

#cluster deploy mode
#DEPLOY_MODE=--deploy-mode cluster

#client deploy mode
#DEPLOY_MODE=

${TARGET}.jar:${TARGET}.class
	jar cf ${TARGET}.jar ${TARGET}*.class

${TARGET}.class:${TARGET}.java
	javac -cp /usr/lib/spark/lib/spark-assembly-1.6.1-hadoop2.6.0.jar ${TARGET}.java

run: ${TARGET}.jar cleanoutput uploadinputtohdfs
	spark-submit --master ${MASTER} ${DEPLOY_MODE}--executor-cores ${NUM_CORES} --class ${MAINCLASS} ${TARGET}.jar ${INPUTDIR}${INPUTFILE} ${OUTPUTDIR}

runbg: ${TARGET}.jar cleanoutput
	hadoop jar ${TARGET}.jar ${MAINCLASS} ${SEARCHSTRING} ${INPUTDIR}${INPUTFILE} ${OUTPUTDIR} &>std_out.txt&

uploadinputtohdfs: ${LOCALINPUTFILE}
	hdfs dfs -mkdir -p ${INPUTDIR}
	-hdfs dfs -put ${LOCALINPUTFILE} ${INPUTDIR}${INPUTFILE}

showoutput:
	hdfs dfs -cat ${OUTPUTDIR}/part-00000

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
