package edu.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import java.io.{FileNotFoundException, File}
import scala.concurrent.duration._
import org.scalatest.FunSuite
import scala.concurrent.{Future, Await}

class DirectoryTest extends FunSuite {

  val dir = new Directory

  test("Should contain Files") {
    val path = Await.result(dir.getPath(new File("D:\\Scala Programs\\directory")), 10.second)
    val actual = path.size
    val expected = 4
    assert(actual == expected)

  }

  test("Should be empty") {

      val path = Await.result(dir.getPath(new File("D:\\Scala Programs\\directory\\directory2\\directory4")), 10.second)
      val actual = path.size
      val expected = 0
      assert(actual == expected)
  }

  test("Should give right Directory path") {
    intercept[FileNotFoundException] {
      Await.result(dir.getPath(new File("D:\\Scala Programs\\directory\\directory3")), 10.second)
    }
  }

  test("Should contain only files") {
    val path = Await.result(dir.getPath(new File("D:\\Scala Programs\\directory\\directory1")), 10.second)
    val actual = path.size
    val expected = 1
    assert(actual == expected)
  }

  test("Should contain files and empty folder") {
    val path = Await.result(dir.getPath(new File("D:\\Scala Programs\\directory\\directory2")), 10.second)
    val actual = path.size
    val expected = 1
    assert(actual == expected)
  }

  test("Should give path for Directory rather than File") {
    intercept[FileNotFoundException] {
       Await.result(dir.getPath(new File("D:\\Scala Programs\\directory\\charmy.txt")), 10.second)
    }

  }




}
