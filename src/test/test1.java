package test;

import java.io.IOException;

import org.dom4j.DocumentException;

import service.stuservice;
import vo.student;

public class test1 {

	public static void main(String[] args) throws DocumentException, IOException {
		Addtest();
		Searchtest();
	}
	public static void Addtest() throws DocumentException, IOException {
		student stu=new student();
		stu.setName("wangqianming");
		stu.setId("20195456");
		stu.setAge("19");
		stuservice.add(stu);
	}
	public static void Deletetest() throws DocumentException, IOException {
		String id=new String("20195456");
		stuservice.delete(id);
	}
	public static void Searchtest() throws DocumentException {
		stuservice.search("20195456");
	}
}
