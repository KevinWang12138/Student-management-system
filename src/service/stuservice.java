package service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import vo.student;

public class stuservice {

	public static void add(student stu) throws DocumentException, IOException {
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.获取根节点
		 * 4.在根节点上直接添加student标签
		 * 5.在student标签上依次添加id，name和age
		 * 6.在id,name,age上依次添加值
		 * 7.回写xml
		 */
		SAXReader saxreader=new SAXReader();
		Document doc=saxreader.read("src/Students.xml");
		Element root=doc.getRootElement();
		Element student=root.addElement("student");
		Element id=student.addElement("id");
		Element name=student.addElement("name");
		Element age=student.addElement("age");
		id.setText(stu.getId());
		name.setText(stu.getName());
		age.setText(stu.getAge());
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(new FileOutputStream("src/Students.xml"),format);
		writer.write(doc);
		writer.close();
	}
	public static void delete(String id) throws DocumentException, IOException {
		/*
		 * 1.创建解析器
		 * 2.得到document
		 * 3.获得根节点
		 * 4.使用XPATH//id得到所有id的list集合
		 * 5.遍历
		 * 6.找到要删除的id
		 * 7.用父节点删除
		 */
		SAXReader saxreader=new SAXReader();
		Document doc=saxreader.read("src/Students.xml");
		Element root=doc.getRootElement();
		List<Node> list=doc.selectNodes("//id");
		for(Node n:list) {
			String ids=n.getText();
			if(ids.equals(id)) {
				Element student=n.getParent();
				root.remove(student);
				break;
			}
		}
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(new FileOutputStream("src/Students.xml"),format);
		writer.write(doc);
		writer.close();
	}
	//根据id查询学生信息
	public static void search(String id) throws DocumentException {
		/*
		 * 1.创建解析器
		 * 2.获取doc
		 * 3.获取所有根节点
		 * 4.获取到student的list
		 * 5.遍历list,检查里面的id的内容，如果相同，打印出来student的所有信息
		 * 6.不需要回写
		 */
		SAXReader saxreader=new SAXReader();
		Document doc=saxreader.read("src/Students.xml");
		Element root=doc.getRootElement();
		List<Element> list=root.elements("student");
		boolean flag=false;
		for(Element e:list) {
			Element ID=e.element("id");
			String IDText=ID.getText();
			if(IDText.equals(id)) {
				flag=true;
				Element name=e.element("name");
				System.out.println("name:"+name.getText());
				System.out.println("id:"+IDText);
				Element age=e.element("age");
				System.out.println("age:"+age.getText());
			}
		}
		if(flag==false) {
			System.out.println("Not Found!");
		}
	}
}
