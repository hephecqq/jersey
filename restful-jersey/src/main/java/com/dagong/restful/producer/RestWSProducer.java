package com.dagong.restful.producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.dagong.restful.entity.Student;

@Path("/students")
public class RestWSProducer {

	private static Logger logger = Logger.getLogger(RestWSProducer.class);
	private static int index = 1;
	private static Map<Integer, Student> studentList = new HashMap<Integer, Student>();

	public RestWSProducer() {
		if (studentList.size() == 0) {
			//service->dao->db
			studentList.put(index, new Student(index++, "Frank", "CS"));
			studentList.put(index, new Student(index++, "Jersey", "Math"));
		}
	}

	/**
	 * 根据StudentId获取Student信息
	 * 
	 * @param studentid
	 * @return
	 */
	@GET
	@Path("{studentid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Student getMetadata(@PathParam("studentid") int studentid) {
		if (studentList.containsKey(studentid)) {
			return studentList.get(studentid);
		} else {
			return new Student(0, "Nil", "Nil1");
		}
	}

	/**
	 * 获取所有数据库中所有的Student数据
	 * 
	 * @return
	 */
	@GET
	@Path("list")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<Student>();
		students.addAll(studentList.values());
		return students;
	}

	/**
	 * 新增一个Student信息
	 * 
	 * @param name
	 *            Student的name
	 * @param dept
	 *            Student的dept
	 * @return
	 */
	@POST
	@Path("add")
	@Produces("text/plain")
	public String addStudent(@FormParam("name") String name, @FormParam("dept") String dept) {
		studentList.put(index, new Student(index++, name, dept));
		return String.valueOf(index - 1);
	}

	/**
	 * 根据Student的id删除一条记录
	 * @param studentid
	 * @return
	 */
	@DELETE
	@Path("delete/{studentid}")
	@Produces("text/plain")
	public String removeStudent(@PathParam("studentid") int studentid) {
		logger.info("Receieving quest for deleting student: " + studentid);

		Student removed = studentList.remove(studentid);
		if (removed == null)
			return "failed!";
		else
			return "true";
	}

	/**
	 * 根据Student的id修改一条记录
	 * @param studentid
	 * @param name
	 * @param dept
	 * @return
	 */
	@PUT
	@Path("put")
	@Produces("text/plain")
	public String putStudent(@QueryParam("studentid") int studentid, @QueryParam("name") String name,
			@QueryParam("dept") String dept) {
		logger.info("Receieving quest for putting student: " + studentid);
		if (!studentList.containsKey(studentid))
			return "failed!";
		else
			studentList.put(studentid, new Student(studentid, name, dept));

		return String.valueOf(studentid);
	}
}
