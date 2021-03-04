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
		 * 1.����������
		 * 2.�õ�document
		 * 3.��ȡ���ڵ�
		 * 4.�ڸ��ڵ���ֱ�����student��ǩ
		 * 5.��student��ǩ���������id��name��age
		 * 6.��id,name,age���������ֵ
		 * 7.��дxml
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
		 * 1.����������
		 * 2.�õ�document
		 * 3.��ø��ڵ�
		 * 4.ʹ��XPATH//id�õ�����id��list����
		 * 5.����
		 * 6.�ҵ�Ҫɾ����id
		 * 7.�ø��ڵ�ɾ��
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
	//����id��ѯѧ����Ϣ
	public static void search(String id) throws DocumentException {
		/*
		 * 1.����������
		 * 2.��ȡdoc
		 * 3.��ȡ���и��ڵ�
		 * 4.��ȡ��student��list
		 * 5.����list,��������id�����ݣ������ͬ����ӡ����student��������Ϣ
		 * 6.����Ҫ��д
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
