<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map"%>
<%@ page import="com.example.notasjakarta.mapping.dtos.StudentDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.notasjakarta.mapping.dtos.SubjectDto" %>
<%
    List<StudentDto> students = (List<StudentDto>)getServletContext().getAttribute("studentDtoList");
%>
<%
    List<SubjectDto> subjects = (List<SubjectDto>)getServletContext().getAttribute("subjectDtoList");
%>

<%
    Map<String,String> errorsmap =
            (Map<String,String>)request.getAttribute("errorsmap");
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Formulario Grades" %>
</h3>

<%
    if(errorsmap != null && errorsmap.size()>0){
%>
<ul class="alert alert-danger mx-5">
    <% for(String error: errorsmap.values()){%>
    <li><%=error%></li>
    <%}%>
</ul>
<%}%>

<form action="grade-form" method="post">
    <div class="row mb-3">
        <%
            if(students != null && !students.isEmpty()){}%>
            <div class="col-sm-4">
        <label for="student" class="col-form-label col-sm-2">Student</label>
        <select id="student" name="students" class="form-control">
            <option value="">-- Select --</option>
            <% for(StudentDto student: students){%>
            <option> <%=student.name()%></option>
            <%}%>
        </select>

        <%
            if(errorsmap != null && errorsmap.containsKey("student")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"+ errorsmap.get("student") + "</div>");
            }
        %>
            </div>
    </div>
    <div class="row mb-3">
        <%
            if(subjects != null && !subjects.isEmpty()) {
            }%>
            <div class="col-sm-4">
        <label for="subject" class="col-form-label col-sm-2">Subject</label>
        <select id="subject" name="subjects" class="form-control">
            <option value="">-- Select --</option>
            <% for(SubjectDto subject: subjects){%>
            <option> <%=subject.name()%></option>
            <%}%>
        </select>
        <%
            if(errorsmap != null && errorsmap.containsKey("subject")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"+ errorsmap.get("subject") + "</div>");
            }
        %>
            </div>
    </div>
    <div class="row mb-3">
        <label for="grade" class="col-form-label col-sm-2">Grade</label>
        <div class="col-sm-4"><input type="text" name="grade" id="grade" class="form-control"value="${param.grade}"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("grade")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' style='color: red;'>"+ errorsmap.get("grade") + "</div>");
            }
        %>
    </div>

    <div class="row mb-3">
        <div>
            <input type="submit" value="Enviar" class="btn btn-primary">
        </div>
    </div>
</form>
</body>
</html>
