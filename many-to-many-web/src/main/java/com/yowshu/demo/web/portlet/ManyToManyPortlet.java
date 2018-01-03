package com.yowshu.demo.web.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.yowshu.demo.model.Course;
import com.yowshu.demo.model.Student;
import com.yowshu.demo.service.CourseLocalServiceUtil;
import com.yowshu.demo.service.StudentLocalServiceUtil;
import com.yowshu.demo.web.constants.ManyToManyPortletKeys;

/**
 * @author fanqi
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=many-to-many-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ManyToManyPortletKeys.ManyToMany,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ManyToManyPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		Student student1,student2;
		student1 = StudentLocalServiceUtil.createStudent(CounterLocalServiceUtil.increment());
		student2 = StudentLocalServiceUtil.createStudent(CounterLocalServiceUtil.increment());
		student1.setName("fanqi");
		student2.setName("huqiwen");
		StudentLocalServiceUtil.addStudent(student1);
		StudentLocalServiceUtil.addStudent(student2);
		Course course1,course2;
		course1 = CourseLocalServiceUtil.createCourse(CounterLocalServiceUtil.increment());
		course2 = CourseLocalServiceUtil.createCourse(CounterLocalServiceUtil.increment());
		course1.setName("Liferay Portal");
		course2.setName("Liferay Mobile SDK");
		CourseLocalServiceUtil.addCourse(course1);
		CourseLocalServiceUtil.addCourse(course2);
		CourseLocalServiceUtil.addStudentCourses(student1.getStudentId(), new long[]{course1.getCourseId(),course2.getCourseId()});
		CourseLocalServiceUtil.addStudentCourse(student2.getStudentId(), course1);
		super.doView(renderRequest, renderResponse);
	}
}