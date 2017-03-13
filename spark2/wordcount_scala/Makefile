TARGET=wordCount

LOCALINPUTFILE=../../sampleArchive.txt
INPUTDIR=/user/ubuntu/examples/
INPUTFILE=sampleArchive.txt
OUTPUTDIR=${INPUTDIR}/wordcount/spark/java/output
MAINCLASS="SimpleApp"

#Settings for how to run the job:
#see https://spark.apache.org/docs/1.2.1/submitting-applications.html 
#for more details
NUM_CORES=3

#run using yarn schedualar
MASTER=yarn

#run on local machine with given number of cores
#MASTER=local[${NUM_CORES]

#run on spark standalone cluster
#MASTER=spark://master:7077

#cluster deploy mode
DEPLOY_MODE=cluster

#client deploy mode
#DEPLOY_MODE=client


build:./src/main/scala/${TARGET}.scala
	sbt package

run: build cleanoutput uploadinputtohdfs
	spark-submit --master ${MASTER} --deploy-mode ${DEPLOY_MODE} --executor-cores ${NUM_CORES} --class ${MAINCLASS} target/scala-2.10/simple-project_2.10-1.0.jar ${INPUTDIR}${INPUTFILE} ${OUTPUTDIR}

uploadinputtohdfs: ${LOCALINPUTFILE}
	hdfs dfs -mkdir -p ${INPUTDIR}
	-hdfs dfs -put ${LOCALINPUTFILE} ${INPUTDIR}${INPUTFILE}

showoutput:
	hdfs dfs -cat ${OUTPUTDIR}/part-00000

cleanoutput:
	-hdfs dfs -rm -R ${OUTPUTDIR}

clean:
	-rm -r project
	-rm -r target
help:
	@echo Targets:
	@echo   <no target>: builds the jar file
	@echo   run: runs the hadoop job in the forground
	@echo   clean: cleans up build files for a fresh rebuild
