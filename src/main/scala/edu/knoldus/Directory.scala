package edu.knoldus

import java.io.{FileNotFoundException, File}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Directory {

  @throws(classOf[FileNotFoundException])
  def getPath(file: File): Future[List[File]] = Future {
    if (file.exists()) {
      def getFilesAndDirectories(file: File): List[File] = {
        if (file.isDirectory) {
          val listOfFiles = file.listFiles
          val result: List[File] = (listOfFiles.toList ++ listOfFiles.filter(_.isDirectory).flatMap(getFilesAndDirectories)).filter(!_.isDirectory)
          result
        }
        else {
          throw new FileNotFoundException()
        }
      }
      getFilesAndDirectories(file)
    }
    else {
      throw new FileNotFoundException()
    }
  }


}

