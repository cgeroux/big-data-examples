"""SimpleApp.py"""
from pyspark import SparkContext


def main():

  fileName = "/user/ubuntu/README.md"  #file in HDFS
  sc = SparkContext()
  fileData = sc.textFile(fileName).cache()
  fileData.saveAsTextFile("/user/ubuntu/READMERDD")
if __name__ == "__main__":
  main()